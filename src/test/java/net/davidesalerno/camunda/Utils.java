package net.davidesalerno.camunda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.davidesalerno.camunda.dto.Workflow;
import net.davidesalerno.camunda.service.ParserImpl;

public class Utils {

    private Utils(){

    }

    public static String readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String currentLine = reader.readLine();
        reader.close();
        return currentLine;
    }

    public static void deleteFile(String filePath) {
        File toDelete = new File(filePath);
        if (toDelete.delete()) {
            System.out.println("Deleted the file: " + toDelete.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static Workflow getJSONWorkflow(String filePath){
        String json;
        Workflow workflow = null;
        try {
            json = new String(Utils.class.getClassLoader().getResourceAsStream(filePath).readAllBytes());
            ObjectMapper objectMapper = new ObjectMapper();
            workflow = objectMapper.readValue(json, Workflow.class);
        } catch (IOException e) {
            return workflow;
        }
        
        return workflow;
    }

    public static BpmnModelInstance getBpmnModelInstance(String filePath){
        Workflow workflowDto = getJSONWorkflow(filePath);
        ParserImpl parser = new ParserImpl();
        return parser.parseBpmn(workflowDto.getBpmn20Xml());
    }

}
