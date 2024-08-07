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

package net.davidesalerno.camunda.dto;

/**
* The data transfer object (dto) for the JSON that wraps the workflow definition 
* 
* @author Davide Salerno
* 
*/
public class Workflow {
    private String id;
    private String bpmn20Xml;

    public String getId() {
        return id;
    }
    public String getBpmn20Xml() {
        return bpmn20Xml;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setBpmn20Xml(String bpmn20Xml) {
        this.bpmn20Xml = bpmn20Xml;
    }
    
}
