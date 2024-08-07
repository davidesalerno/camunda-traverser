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

import java.util.List;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;

/**
* 
* Traverser interface to define the methods needed to manage the workflow traversing
* @author Davide Salerno
* 
*/
public interface Traverser {

    /**
     * <p>Method that will traverse the workflow from the start node to the end one</p>
     * 
     * @param BpmnModelInstance the workflow to try to traverse
     * @param String startNode the name of the start node
     * @param String endNode the name of the end node
     */
    public List<String> traverse(BpmnModelInstance workflowInstance, String startNode, String endNode);

}
