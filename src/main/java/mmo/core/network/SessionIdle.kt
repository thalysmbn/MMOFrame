package mmo.core.network

import java.time.Duration

class SessionIdle(private val duration: Duration) {
    fun duration(): Duration {
        return duration
    }
}