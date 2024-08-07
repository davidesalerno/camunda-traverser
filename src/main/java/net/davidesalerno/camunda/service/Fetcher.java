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

import java.io.IOException;

import net.davidesalerno.camunda.dto.Workflow;

/**
* 
* Fetcher interface to define the methods needed to manage the workflow fetching
* @author Davide Salerno
* 
*/
public interface Fetcher {

    /** 
     * <p>Method that retrieves the JSON with the workflow definition inside using the default uri (see README.md).</p>
     * @return Workflow the workflow dto
     * @throws IOException
     * @throws InterruptedException
     */
    public Workflow retrieveWorkflow() throws IOException, InterruptedException;

    /** 
     * <p>Method that retrieves the JSON with the workflow definition inside.</p>
     * @param uri the string containing the uri where the workflow definition is available at
     * @return Workflow the workflow dto
     * @throws IOException
     * @throws InterruptedException
     */
    public Workflow retrieveWorkflow(final String uri) throws IOException, InterruptedException;
}
