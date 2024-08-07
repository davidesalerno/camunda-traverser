package net.davidesalerno.camunda.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.davidesalerno.camunda.Utils;
import static net.davidesalerno.camunda.Constants.WORKFLOW_OK;

public class TraverserImplTest {
    
    @Test
    void successfullyTraverseAWellFormedWorkflow(){

        TraverserImpl cut = new TraverserImpl();
        BpmnModelInstance workflowInstance = Utils.getBpmnModelInstance(WORKFLOW_OK);
        List<String> result = cut.traverse(workflowInstance, "approveInvoice", "invoiceProcessed");
     
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result,  new ArrayList<>(Arrays.asList("approveInvoice", "invoice_approved", "prepareBankTransfer", "ServiceTask_1", "invoiceProcessed")));
    }
}
