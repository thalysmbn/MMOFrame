package mmo.core.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import mmo.core.Server;
import mmo.core.netty.codec.MessageDecoder;
import mmo.core.network.SessionIdle;
import mmo.core.session.Session;
import mmo.core.session.SessionFactory;

import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public final class NettyBootstrap<S extends Session> implements Server<S> {

    private final SessionFactory<S> factory;
    private final int port;
    private final Duration inactivityDuration;

    private Channel channel;
    private EventLoopGroup eventLoopGroup;
    private SessionHandlerAdapter sessionHandlerAdapter;

    public NettyBootstrap(SessionFactory<S> factory, int port, Duration inactivityDuration) {
        this.port = port;
        this.inactivityDuration = inactivityDuration;
        this.factory = factory;
    }

    @Override
    public void build() {
        final ServerBootstrap bootstrap = new ServerBootstrap()
                .group(eventLoopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel channel) {
                        channel
                                .pipeline()
                                .addLast(new MessageDecoder())
                                .addLast(new IdleStateHandler(inactivityDuration.toMillis(), 0, 0, TimeUnit.MILLISECONDS) {
                                    @Override
                                    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
                                        ctx.fireChannelRead(new SessionIdle(inactivityDuration));
                                    }
                                })
                                .addLast(sessionHandlerAdapter = new SessionHandlerAdapter<>(factory));
                    }
                });

        this.channel = bootstrap.bind(port).channel();
    }

    @Override
    public void shutdown() {
        eventLoopGroup.shutdownGracefully();
        channel.closeFuture();
    }

    @Override
    public Collection<S> sessions() {
        return sessionHandlerAdapter.sessions();
    }
}
