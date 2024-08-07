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

import net.davidesalerno.camunda.TraverserException;

/**
* 
* Diplayer interface to print the traverser output
* @author Davide Salerno
* 
*/
public interface Displayer {

    /**
     * <p>Method that will print the traverser output or it will throw a Traverser exception</p>
     *
     * @param String message the traverser output to print
     * @throws TraverserException the exception thrown if there will be a problem in the traverser
     */
    public void display(List<String> result) throws TraverserException;
} 