package mmo.core.session

import mmo.core.netty.ChannelAdapter

interface SessionFactory<S : Session?> {
    /**
     * Create the session from the channel
     */
    fun create(channel: ChannelAdapter?): S
}