package network;

import mmo.core.netty.ChannelAdapter;
import mmo.core.session.Session;
import mmo.core.session.SessionFactory;

public class NetworkFactory implements SessionFactory {
    @Override
    public Session create(ChannelAdapter channel) {
        return new NetworkSession(channel);
    }
}
