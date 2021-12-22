package mmo.core.netty

import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelId
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.AttributeKey
import mmo.core.session.Session
import mmo.core.session.SessionFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Sharable
class SessionHandlerAdapter<S : Session?>(private val factory: SessionFactory<S>) : ChannelInboundHandlerAdapter() {
    private val sessionAttribute = AttributeKey.valueOf<S>("session")
    private val sessions: ConcurrentMap<ChannelId, S> = ConcurrentHashMap()
    fun sessions(): Collection<S> {
        return sessions.values
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        val session = factory.create(ChannelAdapter(ctx))
        ctx
                .channel()
                .attr(sessionAttribute)
                .set(session)
        sessions[ctx.channel().id()] = session
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        ctx.channel().attr(sessionAttribute).get()!!.inactive()
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        ctx.channel().attr(sessionAttribute).get()!!.receive(msg)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {}
}