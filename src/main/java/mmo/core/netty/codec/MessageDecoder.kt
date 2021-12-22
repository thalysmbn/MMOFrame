package mmo.core.netty.codec

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class MessageDecoder : ByteToMessageDecoder() {
    @Throws(Exception::class)
    override fun decode(ctx: ChannelHandlerContext, `in`: ByteBuf, out: List<Any>) {
        if (`in`.readableBytes() < 6) return
    }
}