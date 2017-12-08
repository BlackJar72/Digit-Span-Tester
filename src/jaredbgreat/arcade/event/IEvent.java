// Copyright (c) Jared Blackburn 2017
// It is licensed under the creative commons 4.0 attribution license:
// https://creativecommons.org/licenses/by/4.0/legalcode 
package jaredbgreat.arcade.event;

/**
 *
 * @author Jared Blackburn
 * @param <T>
 */
public interface IEvent<T> {
    void send();
    public T getType();
    public Object getSender();
    public IEventReceiver<T>[] getRecipients();
    
}
