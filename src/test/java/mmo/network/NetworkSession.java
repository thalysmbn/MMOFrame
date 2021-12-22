package mmo.network;

import mmo.core.netty.ChannelAdapter;
import mmo.core.session.Session;

public class NetworkSession implements Session {

    public NetworkSession(ChannelAdapter channel) {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public void receive(Object msg) {

    }

    @Override
    public void inactive() {

    }

    @Override
    public ChannelAdapter channel() {
        return null;
    }
}
