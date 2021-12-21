package mmo.core;

import mmo.core.session.Session;

import java.util.LinkedList;

public interface Server<S extends Session> {

    /**
     * Build the server
     */
    void build();

    /**
     * Shutdown the server
     */
    void shutdown();

    /**
     * Get all connected sessions
     */
    LinkedList<S> sessions();
}
