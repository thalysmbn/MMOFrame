package mmo.core.netty.codec

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class MessageEncoder : MessageToByteEncoder<Any?>() {
    @Throws(Exception::class)
    override fun encode(channelHandlerContext: ChannelHandlerContext, o: Any?, byteBuf: ByteBuf) {
    }
}