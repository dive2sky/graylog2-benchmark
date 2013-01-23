/**
 * Copyright 2013 Lennart Koopmann <lennart@socketfeed.com>
 *
 * This file is part of Graylog2.
 *
 * Graylog2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog2.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.graylog2.graylog2benchmark;

import com.beust.jcommander.JCommander;
import org.graylog2.graylog2benchmark.senders.SenderFactory;

/**
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class Main 
{
    
    public static void main( String[] args) {
        // Read CLI parameters.
        final CommandLineArguments commandLineArguments = new CommandLineArguments();
        final JCommander jCommander = new JCommander(commandLineArguments, args);

        if (commandLineArguments.isShowHelp()) {
            jCommander.usage();
            System.exit(0);
        }

        /* 
         * "Who is the fastest cartoon character ever?"
         * http://answers.yahoo.com/question/index?qid=20101202152432AAP8Q92
         * 
         * Sonic the Hedgehog. At his fastest, he can travel faster than the
         * speed of sound. In Super Sonic mode, he can go faster than light.
         */
        Core sonic = new Core(
                commandLineArguments.getThreads(),
                commandLineArguments.getSleep(),
                commandLineArguments.getHost(),
                commandLineArguments.getPort(),
                SenderFactory.get(commandLineArguments.getFormat(), commandLineArguments)
        );
        
        sonic.initialize();
    }
    
}
