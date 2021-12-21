package mmo.core.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import mmo.core.session.Session;
import mmo.core.session.SessionFactory;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ChannelHandler.Sharable
public final class SessionHandlerAdapter<S extends Session> extends ChannelInboundHandlerAdapter {
    private final AttributeKey<S> sessionAttribute = AttributeKey.valueOf("session");

    private final SessionFactory<S> factory;
    private final ConcurrentMap<ChannelId, S> sessions = new ConcurrentHashMap<>();

    public SessionHandlerAdapter(SessionFactory<S> factory) {
        this.factory = factory;
    }

    public Collection<S> sessions() {
        return sessions.values();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        final S session = factory.create(ctx.channel());
        ctx
                .channel()
                .attr(sessionAttribute)
                .set(session);
        sessions.put(ctx.channel().id(), session);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

    }
}
