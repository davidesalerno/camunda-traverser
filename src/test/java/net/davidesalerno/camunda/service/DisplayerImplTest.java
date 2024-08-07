package net.davidesalerno.camunda.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.davidesalerno.camunda.TraverserException;
import net.davidesalerno.camunda.Utils;
import static net.davidesalerno.camunda.Constants.OUTPUT_FILE;

public class DisplayerImplTest {

    @Test
    void successfullyDisplayAMessage() throws IOException, TraverserException {
        PrintStream output = new PrintStream(new FileOutputStream(OUTPUT_FILE, true));
        DisplayerImpl cut = new DisplayerImpl(output);
        cut.display(new ArrayList<>(Arrays.asList("apple", "strawberry", "mellon", "orange")));
        String line = Utils.readFile(OUTPUT_FILE);
        Assertions.assertEquals(line, "[apple, strawberry, mellon, orange]");
        Utils.deleteFile(OUTPUT_FILE);
    }

    @Test
    void throwsATraverserExceptionWhenMessageIsNullOrEmpty() throws IOException{
        PrintStream output = new PrintStream(new FileOutputStream(OUTPUT_FILE, true));
        DisplayerImpl cut = new DisplayerImpl(output);
        Exception exceptionNull = Assertions.assertThrows(TraverserException.class, () -> {
            cut.display(null);
        });
        String expectedMessage = "Unable to traverse the flow from the given start to the end node";
        String nullMessage = exceptionNull.getMessage();

        Assertions.assertTrue(nullMessage.contains(expectedMessage));

        Exception exceptionEmpty = Assertions.assertThrows(TraverserException.class, () -> {
            cut.display(new ArrayList<String>());
        });

        String emptyMessage = exceptionEmpty.getMessage();

        Assertions.assertTrue(emptyMessage.contains(expectedMessage));
        Utils.deleteFile(OUTPUT_FILE);
    }

}
