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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.Query;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.bpmn.instance.FlowNode;

/**
 * 
 * Traverser interface default implementation to define the methods needed to
 * manage the workflow traversing
 * This implementation is simple and won't compute the best path from start to
 * end node
 * 
 * @author Davide Salerno
 * 
 */
public class TraverserImpl implements Traverser {

    private static final Logger logger = LogManager.getLogger(TraverserImpl.class);

    public List<String> traverse(BpmnModelInstance workflowInstance, String startNode, String endNode) {
        List<String> result = traverse(workflowInstance, startNode, endNode, new HashSet<String>());
        if(result!=null){
            result.add(startNode);
            Collections.reverse(result);
        }
        return result;
    }

    private List<String> traverse(BpmnModelInstance workflowInstance, String startNode, String endNode, Set<String> alreadyVisited) {
        if(!alreadyVisited.contains(startNode)){
            logger.trace("Visiting Node: {}", startNode);
            alreadyVisited.add(startNode);
            FlowNode start = workflowInstance.getModelElementById(startNode);
            if (start != null) {
                if(start.getId().equals(endNode)){
                    return new ArrayList<>(Arrays.asList(endNode));
                }else{
                    Collection<EndEvent> endNodes = start.getChildElementsByType(EndEvent.class);
                    if (endNodes != null && endNodes.size() > 0) {
                        for (FlowNode node : endNodes) {
                            logger.trace("Visiting Node: {}", node.getId());
                            if (((FlowElement) node).getId().equals(endNode)) {
                                return new ArrayList<>(Arrays.asList(endNode));
                            }
                        }
                    } else {
                        Query<FlowNode> succedingNodes = start.getSucceedingNodes();
                        if (succedingNodes != null && succedingNodes.count() > 0) {
                            for (FlowNode node : succedingNodes.list()) {
                                logger.trace("Visiting Node: {}", node.getId());
                                if(node.getId().equals(endNode)){
                                    return new ArrayList<>(Arrays.asList(endNode));
                                }else{
                                    List<String> result = traverse(workflowInstance, node.getId(), endNode, alreadyVisited);
                                    if (result != null) {
                                        result.add(node.getId());                                    
                                        return result;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
