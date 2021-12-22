package mmo.core.netty

import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelId
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.util.AttributeKey
import mmo.core.session.Session
import mmo.core.session.SessionFactory
import org.apache.logging.log4j.LogManager
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Sharable
class SessionHandlerAdapter<S : Session?>(private val factory: SessionFactory<S>) :  SimpleChannelInboundHandler<Any?>() {
    private val sessionAttribute = AttributeKey.valueOf<S>("session")
    private val sessions: ConcurrentMap<ChannelId, S> = ConcurrentHashMap()
    private val logger = LogManager.getLogger(javaClass)
    fun sessions(): Collection<S> {
        return sessions.values
    }

    @Throws(Exception::class)
    override fun channelRegistered(ctx: ChannelHandlerContext) {
        val session = factory.create(ChannelAdapter(ctx))
        logger.info("new connection from address: {}", session!!.channel().address())
        ctx
                .channel()
                .attr(sessionAttribute)
                .set(session)
        sessions[ctx.channel().id()] = session
    }

    @Throws(Exception::class)
    override fun channelUnregistered(ctx: ChannelHandlerContext) {
        ctx.channel().attr(sessionAttribute).get()!!.inactive()
    }

    @Throws(Exception::class)
    public override fun channelRead0(ctx: ChannelHandlerContext?, message: Any?) {
    }

    @Throws(Exception::class)
    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
    }
}