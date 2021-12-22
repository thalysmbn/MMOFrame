import mmo.core.netty.NettyBootstrap;
import network.NetworkFactory;

import java.time.Duration;

public class MMOServer {

    public static void main(String[] args) {
        NettyBootstrap bootstrap = new NettyBootstrap(new NetworkFactory(), 10000, Duration.ofSeconds(10));
        bootstrap.build();
    }
}
