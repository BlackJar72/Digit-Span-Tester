// Copyright (c) Jared Blackburn 2017
// It is licensed under the creative commons 4.0 attribution license:
// https://creativecommons.org/licenses/by/4.0/legalcode 
package jaredbgreat.arcade.entity;

/**
 *
 * @author jared
 */
public interface IInputController extends IController {
    /**
     * Receive a set of bit-flag encoded commands aggregated from all input 
     * devices which are supported.
     * 
     * @param commands Discrete commands from input devices
     * @param fvector An n-dimension vector for non-discrete input (e.g., mouse)
     */
    public void update(int commands);
}
