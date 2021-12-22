package mmo.core.netty

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelId
import mmo.core.network.Channel
import java.net.InetSocketAddress

class ChannelAdapter(private val channel: ChannelHandlerContext) : Channel {
    override fun id(): ChannelId {
        return channel.channel().id()
    }

    override fun write(message: Any?) {
        channel.writeAndFlush(message)
    }

    override fun close() {
        channel.close()
    }

    override val isAlive: Boolean
        get() = channel.channel().isActive

    override fun address(): InetSocketAddress? {
        return channel.channel().remoteAddress() as InetSocketAddress
    }
}