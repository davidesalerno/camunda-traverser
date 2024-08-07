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

package net.davidesalerno.camunda;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.davidesalerno.camunda.configuration.AppConfiguration;
import net.davidesalerno.camunda.dto.Workflow;
import net.davidesalerno.camunda.service.Parser;
import net.davidesalerno.camunda.service.ParserImpl;
import net.davidesalerno.camunda.service.TraverserImpl;
import net.davidesalerno.camunda.service.Displayer;
import net.davidesalerno.camunda.service.DisplayerImpl;
import net.davidesalerno.camunda.service.Fetcher;
import net.davidesalerno.camunda.service.FetcherImpl;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;


/**
* 
* The class that orchestrates all the operations needed for traversing a BPMN diagram with Camunda that are
* - fetching the workflow
* - parsing the flow using the camunda engine
* - traversing the flow from the given stand and end nodes
* - printing the node IDs as output
*
* @author Davide Salerno
* 
*/
public class Traverser {
    private static final Logger logger = LogManager.getLogger(Traverser.class);

    private AppConfiguration configuration;

    private Fetcher fetcher;

    private Parser parser;

    private net.davidesalerno.camunda.service.Traverser traverser;

    private Displayer displayer;

    public Traverser(AppConfiguration configuration) {
        this.configuration=configuration;
        this.fetcher = new FetcherImpl();
        this.parser = new ParserImpl();
        this.traverser = new TraverserImpl();
        this.displayer = new DisplayerImpl(System.out);
    }

    public Traverser(AppConfiguration configuration, Fetcher fetcher, Parser parser, net.davidesalerno.camunda.service.Traverser traverser, Displayer displayer) {
        this.configuration=configuration;
        this.fetcher = fetcher;
        this.parser = parser;
        this.traverser = traverser;
        this.displayer = displayer;
    }

    public AppConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setFetcher(Fetcher fetcher) {
        this.fetcher = fetcher;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setTraverser(net.davidesalerno.camunda.service.Traverser traverser) {
        this.traverser = traverser;
    }

    private Workflow retrieveJSONWorkflow(){
        Workflow workflow = null;
        logger.debug("Starting the BPMN diagram traversing");
        try {
            logger.debug("Trying to retrieve workflow definition");
            workflow =  fetcher.retrieveWorkflow();
            logger.debug("Workflow definition successfully retrieved");
        } catch (IOException | InterruptedException e) {
            logger.error("Error retrieving workflow definition: {}", e);
        }
        return workflow;
    }

    /**
     * <p>Method that orchestrates all the steps needed for the challenge</p>
     * @throws TraverserException
     */
    public void perform() throws TraverserException {
        logger.debug("Step 1: retrieving the workflow");
        Workflow workflowDto = retrieveJSONWorkflow();
        logger.debug("Step 2: parsing the workflow");
        BpmnModelInstance workflowInstance = parser.parseBpmn(workflowDto.getBpmn20Xml());
        logger.debug("Step 3: traversing the workflow");
        List<String> result = traverser.traverse(workflowInstance, configuration.getStartNode(),configuration.getEndNode());
        logger.debug("Step 4: printing the result");
        displayer.display(result);
    }

}
