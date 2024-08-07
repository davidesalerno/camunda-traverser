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
package net.davidesalerno.camunda.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

/**
* 
* Parser interface default implementation to manage the workflow parsing
* @author Davide Salerno
* 
*/
public class ParserImpl implements Parser{
    private static final Logger logger = LogManager.getLogger(ParserImpl.class);
    
    public BpmnModelInstance parseBpmn(final String workflow){
        logger.debug("Starting to parse workflow {}", workflow);
        InputStream workflowInputStream = new ByteArrayInputStream(workflow.getBytes(StandardCharsets.UTF_8));
        return Bpmn.readModelFromStream(workflowInputStream);
    }
}
