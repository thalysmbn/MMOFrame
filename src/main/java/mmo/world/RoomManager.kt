package mmo.world

import org.apache.logging.log4j.LogManager
import java.util.concurrent.ConcurrentHashMap

class RoomManager {
    private var rooms: ConcurrentHashMap<String, Room>? = null

    private val logger = LogManager.getLogger(javaClass)

    fun addRoom(name: String): RoomCode {
        return if (rooms!!.containsKey(name)) RoomCode.Exists else try {
            val room = Room(name)
            rooms!![name] = room
            logger.info("new room: {}", name)
            RoomCode.Ok
        } catch (ex: Exception) {
            RoomCode.Fail
        }
    }

    fun removeRoom(room: Room): RoomCode {
        if (!rooms!!.containsKey(room.name)) return RoomCode.NotExists
        return if (rooms!!.remove(room.name, room)) RoomCode.Ok else RoomCode.Fail
    }

    companion object {
        var instance: RoomManager? = null
            get() {
                if (field == null) {
                    field = build()
                }
                return field
            }
            private set

        private fun build(): RoomManager {
            val roomManager = RoomManager()
            roomManager.rooms = ConcurrentHashMap()
            return roomManager
        }
    }
}
