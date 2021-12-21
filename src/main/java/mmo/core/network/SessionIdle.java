package mmo.core.network;

import java.time.Duration;

public final class SessionIdle {

    private final Duration duration;

    public SessionIdle(Duration duration) {
        this.duration = duration;
    }

    public Duration duration() {
        return duration;
    }
}
