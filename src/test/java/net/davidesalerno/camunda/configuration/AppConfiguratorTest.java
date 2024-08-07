package net.davidesalerno.camunda.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.stefanbirkner.systemlambda.SystemLambda;

public class AppConfiguratorTest {

    @Test
    void whenOnlyVerboseOptionAndNoArgsArePassedExitWithMinusOne() throws Exception {
        AppConfiguration configuration = new AppConfiguration();
        String[] args = new String[]{"-v"};
        int statusCode = SystemLambda.catchSystemExit(() -> {
            AppConfigurator cut = new AppConfigurator(args, configuration);
        });
        Assertions.assertEquals(-1, statusCode);
    }

    @Test
    void whenOnlyVerboseOptionAndOneArgIsPassedExitWithMinusOne() throws Exception {
        AppConfiguration configuration = new AppConfiguration();
        String[] args = new String[]{"-v", "startNode"};
        int statusCode = SystemLambda.catchSystemExit(() -> {
            AppConfigurator cut = new AppConfigurator(args, configuration);
        });
        Assertions.assertEquals(-1, statusCode);
    }

    @Test
    void whenTwoArgArePassedWithVerboseOptionEverythingIsFine(){
        AppConfiguration configuration = new AppConfiguration();
        String[] args = new String[]{"-v", "startNode","endNode"};
        AppConfigurator cut = new AppConfigurator(args, configuration);
        Assertions.assertTrue(configuration.isVerbose());
        Assertions.assertEquals(configuration.getStartNode(), "startNode");
        Assertions.assertEquals(configuration.getEndNode(), "endNode");
    }

    @Test
    void whenOnlyTwoArgAreWithoutOptionsEverythingIsFine(){
        AppConfiguration configuration = new AppConfiguration();
        String[] args = new String[]{"startNode","endNode"};
        AppConfigurator cut = new AppConfigurator(args, configuration);
        Assertions.assertFalse(configuration.isVerbose());
        Assertions.assertEquals(configuration.getStartNode(), "startNode");
        Assertions.assertEquals(configuration.getEndNode(), "endNode");
    }


}
