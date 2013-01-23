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

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import org.graylog2.graylog2benchmark.senders.Sender;

/**
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class Core {
    
    private final int STATISTIC_INTERVAL = 1;
    
    private final int threadCount;
    private final int sleepTime;
    private final String targetHost;
    private final int targetPort;
    private final Sender sender;
    
    private AtomicLong counter = new AtomicLong();
    
    private final Executor executor;
    
    public Core(int threadCount, int sleepTime, String targetHost, int targetPort, Sender sender) {
        this.threadCount = threadCount;
        this.sleepTime = sleepTime;
        this.targetHost = targetHost;
        this.targetPort = targetPort;
        this.sender = sender;
        
        executor = Executors.newFixedThreadPool(threadCount);
    }
    
    public void initialize() {
        // Print information.
        System.out.println("Starting " + sender.getClass().getCanonicalName() + ": "
                + threadCount + " threads, sleep " + sleepTime + "ms. "
                + "Target host: " + targetHost + ":" + targetPort);
        
        // Start threads.
        CountDownLatch signal = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i+=1) {
            System.out.println(" - Launching thread #" + i);
            executor.execute(new SenderThread(this, signal, sender, sleepTime));
        }
        
        try {
            signal.await();
            System.out.println("All threads ready. Starting benchmark now.");
        } catch (InterruptedException e) { /* */ }
        
        // Start statistics printing.
        while (true) {
            try { Thread.sleep(STATISTIC_INTERVAL*1000); } catch (InterruptedException e) { /* TROLOLOL */ }
            System.out.println(new Date() + ": " + counter);
            counter.set(0);
        }
        
        // Blocks forever.
    }
    
    public void incrementSentMessages() {
        counter.incrementAndGet();
    }
    
}
