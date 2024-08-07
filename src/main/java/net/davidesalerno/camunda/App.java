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

import net.davidesalerno.camunda.configuration.AppConfiguration;
import net.davidesalerno.camunda.configuration.AppConfigurator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
    private static AppConfiguration configuration = new AppConfiguration();

    public static void main(String[] args){
        
        try{
            AppConfigurator configurator = new AppConfigurator(args, configuration);
            Traverser challenge = new Traverser(configuration);
            challenge.perform();
        }catch(TraverserException ce){
            logger.error("Traverser exception:",  ce);
            System.exit(-1);
        }catch(Throwable t){
            logger.error("Unexpected exception:",  t);
            System.exit(-1);
        }
        
    }
}
