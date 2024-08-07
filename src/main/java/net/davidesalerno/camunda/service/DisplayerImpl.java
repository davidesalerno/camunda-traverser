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

import java.io.PrintStream;
import java.util.List;

import net.davidesalerno.camunda.TraverserException;

/**
* 
* Displayer default implementation to print the traverser output to the std out
* @author Davide Salerno
* 
*/
public class DisplayerImpl implements Displayer{

    private PrintStream ps;

    public DisplayerImpl(){
        this.ps = System.out;
    }
    public DisplayerImpl(PrintStream ps ){
        this.ps = ps;
    }

    public void display(List<String> result) throws TraverserException {
        if(result!=null && !result.isEmpty()){
            ps.println(result);
            ps.flush();
            ps.close();
        }else{
            throw new TraverserException("Unable to traverse the flow from the given start to the end node");
        }
        
    }
}
