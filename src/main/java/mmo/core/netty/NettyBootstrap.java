package mmo.core.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import mmo.core.Server;
import mmo.core.session.Session;

import java.util.Collection;
import java.util.LinkedList;

public final class NettyBootstrap<S extends Session> implements Server<S> {

    private Channel channel;

    @Override
    public void build() {
        final ServerBootstrap bootstrap = new ServerBootstrap();

        channel = bootstrap.bind().channel();
    }

    @Override
    public void shutdown() {
        channel.closeFuture();
    }

    @Override
    public LinkedList<S> sessions() {
        return new LinkedList<>();
    }
}
