package mmo.core.session;

import mmo.core.netty.ChannelAdapter;

public interface SessionFactory<S extends Session> {

    /**
     * Create the session from the channel
     */
    S create(ChannelAdapter channel);
}
