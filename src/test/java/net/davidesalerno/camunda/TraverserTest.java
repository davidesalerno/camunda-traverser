package net.davidesalerno.camunda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import net.davidesalerno.camunda.configuration.AppConfiguration;
import net.davidesalerno.camunda.service.Displayer;
import net.davidesalerno.camunda.service.DisplayerImpl;
import net.davidesalerno.camunda.service.Fetcher;
import net.davidesalerno.camunda.service.Parser;
import net.davidesalerno.camunda.service.ParserImpl;
import net.davidesalerno.camunda.service.TraverserImpl;

import static net.davidesalerno.camunda.Constants.*;

public class TraverserTest {

    private Fetcher fetcher;

    private static Parser parser;

    private static net.davidesalerno.camunda.service.Traverser traverser;

    private Displayer displayer;

    @BeforeAll
    static void setup() {
        parser = new ParserImpl();
        traverser = new TraverserImpl();
    }

    @BeforeEach
    void init() {
        fetcher = mock(Fetcher.class);
    }

    @AfterAll
    static void tearDown() {
        Utils.deleteFile(OUTPUT_FILE);
    }

    @Test
    void successfullyTraverseIfEverythingIsFine() throws IOException, InterruptedException, TraverserException {
        AppConfiguration appConfiguration = new AppConfiguration();

        appConfiguration.setStartNode("approveInvoice");
        appConfiguration.setEndNode("invoiceProcessed");

        PrintStream ps = new PrintStream(new FileOutputStream(OUTPUT_FILE, true));
        displayer = new DisplayerImpl(ps);

        when(fetcher.retrieveWorkflow()).thenReturn(Utils.getJSONWorkflow(WORKFLOW_OK));

        Traverser cut = new Traverser(appConfiguration, fetcher, parser, traverser, displayer);

        cut.perform();
        String line = Utils.readFile(OUTPUT_FILE);
        Assertions.assertEquals(line,
                "[approveInvoice, invoice_approved, prepareBankTransfer, ServiceTask_1, invoiceProcessed]");

    }

    @Test
    void manageATraverseIfWorkflowIsMalformed()
            throws IOException, InterruptedException, TraverserException {
        AppConfiguration appConfiguration = new AppConfiguration();

        appConfiguration.setStartNode("approveInvoice");
        appConfiguration.setEndNode("invoiceProcessed");

        PrintStream ps = new PrintStream(new FileOutputStream(OUTPUT_FILE, true));
        displayer = new DisplayerImpl(ps);

        when(fetcher.retrieveWorkflow()).thenReturn(Utils.getJSONWorkflow(WORKFLOW_MALFORMED));

        Traverser cut = new Traverser(appConfiguration, fetcher, parser, traverser, displayer);

        Exception exception = assertThrows(org.camunda.bpm.model.xml.ModelParseException.class, () -> {
            cut.perform();
        });

        String expectedMessage = "SAXException while parsing input stream";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void manageATraverseIfWorkflowIsMissing()
            throws IOException, InterruptedException, TraverserException {
        AppConfiguration appConfiguration = new AppConfiguration();

        appConfiguration.setStartNode("approveInvoice");
        appConfiguration.setEndNode("invoiceProcessed");

        PrintStream ps = new PrintStream(new FileOutputStream(OUTPUT_FILE, true));
        displayer = new DisplayerImpl(ps);

        when(fetcher.retrieveWorkflow()).thenReturn(Utils.getJSONWorkflow(WORKFLOW_MISSING));

        Traverser cut = new Traverser(appConfiguration, fetcher, parser, traverser, displayer);
        
        Exception exception = assertThrows(java.lang.NullPointerException.class, () -> {
            cut.perform();
        });

        String expectedMessage = "Cannot invoke \"String.getBytes(java.nio.charset.Charset)\" because \"workflow\" is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        

    }
}
