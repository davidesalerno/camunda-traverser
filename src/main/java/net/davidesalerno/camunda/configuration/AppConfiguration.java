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

/**
* 
* A simple Java POJO containing the app configuration
* 
* @author Davide Salerno
* 
*/
public class AppConfiguration {

    private boolean verbose;
    private String startNode;
    private String endNode;

    public void setVerbose(final boolean verbose){
        this.verbose = verbose;
    }

    public boolean isVerbose(){
        return this.verbose;
    }

    public void setStartNode(final String startNode){
        this.startNode = startNode;
    }

    public void setEndNode(final String endNode){
        this.endNode = endNode;
    }

    public String getStartNode(){
        return this.startNode;
    }

    public String getEndNode(){
        return this.endNode;
    }


}
