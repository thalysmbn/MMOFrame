package mmo.core.session;

import io.netty.channel.Channel;

public interface Session {

    /**
     * Check if the session is alive
     */
    boolean isAlive();

    /**
     * Close the session
     */
    void close();

    /**
     * Return IO channel
     */
    Channel channel();
}
