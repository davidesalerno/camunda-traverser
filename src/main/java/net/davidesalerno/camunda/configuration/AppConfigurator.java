/*----------------------------------------------------------------------------------------
 *   This file is part of Camunda Traverser program.
 *
 *   Camunda Traverser program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Camunda Traverser program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Camunda Traverser program.  If not, see <http://www.gnu.org/licenses/>.
 *---------------------------------------------------------------------------------------*/

package net.davidesalerno.camunda.configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.List;

import org.apache.commons.cli.*;

/**
* 
* A class to manage the app configuration 
* 
* @author Davide Salerno
* 
*/
public class AppConfigurator {

    private static final Logger logger = LogManager.getLogger(AppConfigurator.class);
   
    public AppConfigurator(final String[] args, final AppConfiguration configuration){
        this.parseArgs(args, configuration);
        this.performBaseConfiguration(configuration);
    }

    private void parseArgs(String[] args,  final AppConfiguration configuration){

        Options options = new Options();
        options.addOption("v", "verbose", false, "Enable verbose mode");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            configuration.setVerbose(cmd.hasOption("verbose"));
            if(cmd.getArgList().size()>=2){
                List<String> argsList = cmd.getArgList();
                configuration.setStartNode(argsList.get(0));
                configuration.setEndNode(argsList.get(1));
            }else{
                logger.error("Error parsing arguments: you have to provide start and end node ID");
                System.exit(-1);
            }
        } catch (org.apache.commons.cli.ParseException e) {
            logger.error("Error parsing options and arguments: {}", e);
            System.exit(-1);
        }
    }

    private void performBaseConfiguration(final AppConfiguration configuration){
        if (configuration.isVerbose()) {
            Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.DEBUG);
        }
    }
}
