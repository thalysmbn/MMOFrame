package mmo.core.session;

import io.netty.channel.Channel;
import mmo.core.netty.ChannelAdapter;

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
     * Handle a received packet
     *
     * @param msg Packet to handle
     */
    void receive(Object msg);

    /**
     * Call when connection is down
     */
    void inactive();

    /**
     * Return IO channel
     */
    ChannelAdapter channel();
}
