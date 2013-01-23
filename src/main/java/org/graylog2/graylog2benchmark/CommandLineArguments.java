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

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
@Parameters(commandDescription = "Sonic: Graylog2 benchmark")
public class CommandLineArguments {

    private static final int DEFAULT_SLEEP = 5;
    
    @Parameter(names = {"-t", "--threads"}, description = "Number of parallel threads to use")
    private int threads = 1;
    
    @Parameter(names = {"-d", "--sleep"}, description = "Delay between each message that is sent in milliseconds (per thread). Default: " + DEFAULT_SLEEP)
    private int sleep = DEFAULT_SLEEP;

    @Parameter(names = {"-x", "--host"}, description = "Target host", required = true)
    private String host;
    
    @Parameter(names = {"-p", "--port"}, description = "Target port", required = true)
    private int port;
    
    @Parameter(names = {"-f", "--format"}, description = "Message format (e.g. tcpgelf)", required = true)
    private String format;
 
    @Parameter(names = {"-h", "--help"}, description = "Show usage information and exit")
    private boolean showHelp = false;
    
    public boolean isShowHelp() {
        return showHelp;
    }
    
    public int getThreads() {
        return threads;
    }
    
    public int getSleep() {
        return sleep;
    }
    
    public String getHost() {
        return host;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getFormat() {
        return format;
    }
    
}
