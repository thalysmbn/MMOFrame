package mmo.core.network

import io.netty.channel.ChannelId
import java.net.InetSocketAddress

interface Channel {
    /**
     * Get the channel id
     */
    fun id(): ChannelId?

    /**
     * Write message to the channel
     *
     * @param message Message to send
     */
    fun write(message: Any?)

    /**
     * Close the channel
     */
    fun close()

    /**
     * Check if the channel is alive
     */
    val isAlive: Boolean

    /**
     * Get the client address
     */
    fun address(): InetSocketAddress
}