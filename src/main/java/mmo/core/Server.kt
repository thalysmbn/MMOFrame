package mmo.core

import mmo.core.session.Session

interface Server<S : Session?> {
    /**
     * Build the server
     */
    fun build()

    /**
     * Shutdown the server
     */
    fun shutdown()

    /**
     * Get all connected sessions
     */
    fun sessions(): Collection<S>?
}