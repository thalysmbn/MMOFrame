package mmo;

import mmo.core.netty.NettyBootstrap;
import mmo.network.NetworkFactory;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class TestNetwork {


    @Test
    public void build() {
        NettyBootstrap bootstrap = new NettyBootstrap(new NetworkFactory(), 80, Duration.ofSeconds(10));
        bootstrap.build();
    }
}
