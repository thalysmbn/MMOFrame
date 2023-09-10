package mmo.core.netty

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.timeout.IdleStateEvent
import io.netty.handler.timeout.IdleStateHandler
import mmo.core.Server
import mmo.core.netty.codec.MessageDecoder
import mmo.core.netty.codec.MessageEncoder
import mmo.core.network.SessionIdle
import mmo.core.session.Session
import mmo.core.session.SessionFactory
import org.apache.logging.log4j.LogManager
import java.time.Duration
import java.util.concurrent.TimeUnit

class NettyBootstrap<S : Session?>(private val factory: SessionFactory<S>, private val port: Int, private val inactivityDuration: Duration) : Server<S> {
    private var channel: Channel? = null
    private var eventLoopGroup: EventLoopGroup? = null
    private var sessionHandlerAdapter: SessionHandlerAdapter<S>? = null
    private val logger = LogManager.getLogger(javaClass)
    override fun build() {
        logger.info("building netty on network port: {}", port)
        val bootstrap = ServerBootstrap()
                .group(NioEventLoopGroup(Runtime.getRuntime().availableProcessors()).also { eventLoopGroup = it })
                .channel(NioServerSocketChannel::class.java)
                .childHandler(object : ChannelInitializer<Channel>() {
                    override fun initChannel(channel: Channel) {
                        channel
                                .pipeline()
                                //.addLast(DelimiterBasedFrameDecoder(4096, Unpooled.wrappedBuffer(ByteArray(10))))
                                .addLast(MessageEncoder())
                                .addLast(MessageDecoder())
                                .addLast(object : IdleStateHandler(inactivityDuration.toMillis(), 0, 0, TimeUnit.MILLISECONDS) {
                                    @Throws(Exception::class)
                                    override fun channelIdle(ctx: ChannelHandlerContext, evt: IdleStateEvent) {
                                        ctx.fireChannelRead(SessionIdle(inactivityDuration))
                                    }
                                })
                                .addLast(SessionHandlerAdapter(factory).also { sessionHandlerAdapter = it })
                    }
                })
        bootstrap.bind(port).also { channel = it.channel() }
    }

    override fun shutdown() {
        eventLoopGroup!!.shutdownGracefully()
        channel!!.closeFuture()
    }

    override fun sessions(): Collection<S> {
        return sessionHandlerAdapter!!.sessions()
    }
}