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

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.graylog2.graylog2benchmark.MessageRepository;

/**
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class SyslogUDPSender implements Sender {

    private final InetAddress target;
    private final DatagramSocket clientSocket;
    private final int port;
    
    public SyslogUDPSender(String host, int port) throws Exception {
        this.port = port;
        
        target = InetAddress.getByName(host);
        clientSocket = new DatagramSocket();
    }

    @Override
    public void trigger() {
        try {
            byte[] m = MessageRepository.udpSyslog().getBytes();
            DatagramPacket p = new DatagramPacket(m, m.length, target, port);

            clientSocket.send(p);
        } catch (Exception e) {
            System.err.println("Error while trying to send message: " + e);
        }
    }
    
}
