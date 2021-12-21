package mmo.core.session;

import io.netty.channel.Channel;

public interface SessionFactory<S extends Session> {

    /**
     * Create the session from the channel
     */
    S create(Channel channel);
}
