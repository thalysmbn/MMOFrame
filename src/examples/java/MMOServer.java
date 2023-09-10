import mmo.core.netty.NettyBootstrap;
import mmo.world.RoomManager;
import network.NetworkFactory;

import java.time.Duration;
import java.util.Objects;

public class MMOServer {

    public static void main(String[] args) {
        Objects.requireNonNull(RoomManager.Companion.getInstance()).addRoom("test");

        NettyBootstrap bootstrap = new NettyBootstrap(new NetworkFactory(), 10000, Duration.ofSeconds(10));
        bootstrap.build();
    }
}
