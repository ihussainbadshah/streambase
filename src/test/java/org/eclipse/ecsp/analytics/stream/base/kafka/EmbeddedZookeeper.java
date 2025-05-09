/*
 *
 *
 *   ******************************************************************************
 *
 *    Copyright (c) 2023-24 Harman International
 *
 *
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *
 *    you may not use this file except in compliance with the License.
 *
 *    You may obtain a copy of the License at
 *
 *
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 *    Unless required by applicable law or agreed to in writing, software
 *
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *    See the License for the specific language governing permissions and
 *
 *    limitations under the License.
 *
 *
 *
 *    SPDX-License-Identifier: Apache-2.0
 *
 *    *******************************************************************************
 *
 *
 */

package org.eclipse.ecsp.analytics.stream.base.kafka;

import org.apache.curator.test.TestingServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;



/**
 * Runs an in-memory, "embedded" instance of a ZooKeeper server.
 * The ZooKeeper server instance is automatically started when you create a new instance of this class.
 */
public class EmbeddedZookeeper {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedZookeeper.class);

    /** The server. */
    private final TestingServer server;

    /**
     * Creates and starts a ZooKeeper instance.
     *
     * @throws Exception Exception
     */
    public EmbeddedZookeeper() throws Exception {
        LOG.debug("Starting embedded ZooKeeper server...");
        this.server = new TestingServer();
        LOG.debug("Embedded ZooKeeper server at {} uses the temp directory at {}",
                server.getConnectString(), server.getTempDirectory());
    }

    /**
     * setUp().
     *
     * @throws IOException IOException
     */
    public void stop() throws IOException {
        LOG.debug("Shutting down embedded ZooKeeper server at {} ...", server.getConnectString());
        server.close();
        LOG.debug("Shutdown of embedded ZooKeeper server at {} completed", server.getConnectString());
    }

    /**
     * The ZooKeeper connection string aka `zookeeper.connect` in `hostnameOrIp:port` format. Example: `127.0.0.1:2181`.
     * You can use this to e.g. tell Kafka brokers how to connect to this instance.
     *
     * @return the string
     */
    public String connectString() {
        return server.getConnectString();
    }

    /**
     * The hostname of the ZooKeeper instance.  Example: `127.0.0.1`
     *
     * @return the string
     */
    public String hostname() {
        // "server:1:2:3" -> "server:1:2"
        return connectString().substring(0, connectString().lastIndexOf(':'));
    }

}