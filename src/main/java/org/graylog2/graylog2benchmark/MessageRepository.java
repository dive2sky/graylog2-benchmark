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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class MessageRepository {
    
    private MessageRepository () { /* Pure static utility class */ };
    
    public static final List<String> SYSLOG_MESSAGES = new ArrayList<String>() {{
        // Structured syslog.
        add("<165>1 2012-12-25T22:14:15.003Z mymachine.example.com evntslog - ID47 [exampleSDID@32473 iut=\\\"3\\\" eventSource=\\\"Application\\\" eventID=\\\"1011\\\"] BOMAn application event log entry\"");
        
        // BSD syslog.
        add("<86>Dec 24 17:05:01 foo-bar CRON[10049]: pam_unix(cron:session): session closed for user root");
        
        // BSD syslog with k=v pairs. (tokenizer)
        add("<133>NOMA101FW01A: NetScreen device_id=NOMA101FW01A [Root]system-notification-00257(traffic): start_time=\"2011-12-23 17:33:43\" duration=0 reason=Creation");
    }};
    
    public static String udpSyslog() {
        return SYSLOG_MESSAGES.get(randomInRange(SYSLOG_MESSAGES.size()));
    }
    
    private static int randomInRange(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }
    
}
