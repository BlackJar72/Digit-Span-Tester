// Copyright (c) Jared Blackburn 2017
// It is licensed under the creative commons 4.0 attribution license:
// https://creativecommons.org/licenses/by/4.0/legalcode 
package jaredbgreat.arcade.game;

/**
 * Am interface for portions of the game loop (for example, entity update, 
 * collision, or rendering).
 * 
 * @author Jared Blackburn 
 */
public interface ILoopElement {
    /**
     * Update this portion of the loop.
     */
    public void update();
}
