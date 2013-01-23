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
package org.graylog2.graylog2benchmark.senders;

import org.graylog2.graylog2benchmark.CommandLineArguments;

/**
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class SenderFactory {
    
    public static Sender get(String type, CommandLineArguments cli) {
        try {
            if (type.equals("syslogudp")) {
                return new SyslogUDPSender(cli.getHost(), cli.getPort());
            }
        } catch(Exception e) {
            throw new RuntimeException("Could not initialize sender. " + e);
        }
        
        throw new RuntimeException("No sender for format [" + type + "].");
    }
    
}
