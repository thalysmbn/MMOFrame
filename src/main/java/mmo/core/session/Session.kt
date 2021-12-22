package mmo.core.session

import mmo.core.netty.ChannelAdapter

interface Session {
    /**
     * Close the session
     */
    fun close()

    /**
     * Handle a received packet
     *
     * @param msg Packet to handle
     */
    fun receive(msg: Any?)

    /**
     * Call when connection is down
     */
    fun inactive()

    /**
     * Return IO channel
     */
    fun channel(): ChannelAdapter
}