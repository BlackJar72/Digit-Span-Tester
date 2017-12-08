// Copyright (c) Jared Blackburn 2017
// It is licensed under the creative commons 4.0 attribution license:
// https://creativecommons.org/licenses/by/4.0/legalcode 
package jaredbgreat.arcade.event;

/**
 * An interface for classes receiving synchronous messages of the given type.
 * 
 * 
 * @author Jared Blackburn
 * @param <T>
 */
public interface ISynchronousReceiver<T> {
    /**
     * The function called to receive / process the message.
     * 
     * @param type 
     */
    public void executeEvent(T type);     
}
