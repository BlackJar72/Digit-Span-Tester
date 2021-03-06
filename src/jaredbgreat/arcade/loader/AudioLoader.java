// Copyright (c) Jared Blackburn 2017
// It is licensed under the creative commons 4.0 attribution license:
// https://creativecommons.org/licenses/by/4.0/legalcode 
package jaredbgreat.arcade.loader;

import jaredbgreat.arcade.ui.sound.Sound;

/**
 *
 * @author Jared Blackburn
 */
public class AudioLoader extends AbstractLoader {
    private static final AudioLoader READER = new AudioLoader();
    private static final String LOC     = "/assets/audio/";
    private static final String INFO_LOC = LOC + "AudioData.txt"; 
    
    
    /**
     * One private instance exists to conveniently hold temporary 
     * information.  This should never be instantiated elsewhere nor
     * shared with other classes, but only used internally.
     */
    private AudioLoader(){
        super();
        loc = LOC;
        infoLoc = INFO_LOC;   
    }
    
    
    /**
     * The static entryway to this sound loading system.  It should be 
     * called only once during initialization.  This then calls the private 
     * methods that have access to internal data storage.
     */
    public static void initAudio() {
        READER.openInfo();
    }

    @Override
    protected void makeResource() {
        Sound.addSound(name, list);
    }
    
}
