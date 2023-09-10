package mmo.world

import io.netty.channel.ChannelId
import mmo.core.session.Session
import java.util.concurrent.ConcurrentHashMap

class Room(@JvmField var name: String) {
    var max: Int = -1

    var users: ConcurrentHashMap<ChannelId, Session> = ConcurrentHashMap()
}
