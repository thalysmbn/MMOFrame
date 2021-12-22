package network;

import mmo.core.netty.ChannelAdapter;
import mmo.core.network.Channel;
import mmo.core.session.Session;

public class NetworkSession implements Session {
    private ChannelAdapter channel;

    public NetworkSession(ChannelAdapter channel) {
        this.channel = channel;
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
        return channel;
    }
}
