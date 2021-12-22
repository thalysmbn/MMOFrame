package mmo.core.network;

import java.net.InetSocketAddress;

public interface Channel {
    /**
     * Get the channel id
     */
    Object id();

    /**
     * Write message to the channel
     *
     * @param message Message to send
     */
    void write(Object message);

    /**
     * Close the channel
     */
    void close();

    /**
     * Check if the channel is alive
     */
    boolean isAlive();

    /**
     * Get the client address
     */
    InetSocketAddress address();
}