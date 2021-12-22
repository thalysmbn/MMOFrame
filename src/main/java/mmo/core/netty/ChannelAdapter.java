package mmo.core.netty;

import io.netty.channel.ChannelHandlerContext;
import mmo.core.network.Channel;

import java.net.InetSocketAddress;

public final class ChannelAdapter implements Channel {
    private final ChannelHandlerContext channel;

    public ChannelAdapter(ChannelHandlerContext channel) {
        this.channel = channel;
    }

    @Override
    public Object id() {
        return channel.channel().id();
    }

    @Override
    public void write(Object message) {
        channel.writeAndFlush(message);
    }

    @Override
    public void close() {
        channel.close();
    }

    @Override
    public boolean isAlive() {
        return channel.channel().isActive();
    }

    @Override
    public InetSocketAddress address() {
        return (InetSocketAddress) channel.channel().remoteAddress();
    }
}