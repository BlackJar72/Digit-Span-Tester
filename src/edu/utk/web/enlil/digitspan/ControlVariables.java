package edu.utk.web.enlil.digitspan;

/*
// <P><UL><LI>Digit Span Tester version 1.0</LI>
// <LI>Copyright (C) 2011 Jared Blackburn.</LI>
//
// <P>This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.</P>
//
// <P>This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.</P>
//
// <P>You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.</P>
//
// <P>Jared Blackburn<BR></BR>
// <A HREF ="http://www.facebook.com/Jared.Blackburn1">http://www.facebook.com/Jared.Blackburn1</A></P>
//
// For more information about the GNU General Public Liscnece see:
// http://www.gnu.org/copyleft/gpl.html
*/

import edu.utk.web.enlil.RandomSequencer;


final class ControlVariables {
    
    // These are the default values -- changes in default behavior
    // should be made to these variables.
    private static final boolean DEFAULT_visualStimuli   = true;
    private static final boolean DEFAULT_audioStimuli    = true;
    private static final boolean DEFAULT_digitsForward   = true;
    private static final boolean DEFAULT_autoShowResults = false;
    private static final boolean DEFAULT_endingSound     = true;
    private static final boolean DEFAULT_dontBlank       = false;
    private static final boolean DEFAULT_blindAssist     = false;
    private static final boolean DEFAULT_soundWAudio     = true;
    private static final boolean DEFAULT_fullScreen      = false;
    private static final boolean DEFAULT_logResults      = false;
    private static final boolean DEFAULT_useConfig       = false;
    private static final boolean DEFAULT_saveConfig      = true;
    private static final boolean DEFAULT_saveResearch    = false;
    private static final boolean DEFAULT_customMode      = false;

    private static final int DEFAULT_ultimateMaxLen = 10;
    
    private static final int DEFAULT_initDelay    = 2000;    
    private static final int DEFAULT_stimulusTime = 1000;    
    private static final int DEFAULT_pauseTime    = 1000;
    
    private static final boolean DEFAULT_limitErrors = true;  
    private static final int     DEFAULT_errorLimit  = 2;     
    
    private static final boolean DEFAULT_keyForNext     = false; 
    private static final boolean DEFAULT_anyKeyForNext  = false; 

    private static final boolean DEFAULT_extraDelay = false;
    private static final int     DEFAULT_extraDelayTime = 30;
    
    private static final int DEFAULT_length    = 7;
    private static final int DEFAULT_minLength = 2;
    private static final int DEFAULT_maxLength = 9;
    private static final int DEFAULT_setSize   = 3; // Not sure: 2?  4?
    private static final int DEFAULT_numToDo   = 10;
    private static final int DEFAULT_algorithm = RandomSequencer.ALT8;

    
    // These are the variables that dirrectly represent the state of
    // the program and control its behavior.  These are the variables
    // that are changed at run time to control testing mode and stimulus
    // presentation.
    static boolean visualStimuli   = DEFAULT_visualStimuli;
    static boolean audioStimuli    = DEFAULT_audioStimuli;
    static boolean digitsForward   = DEFAULT_digitsForward;
    static boolean autoShowResults = DEFAULT_autoShowResults;
    static boolean endingSound     = DEFAULT_endingSound;
    static boolean dontBlank       = DEFAULT_dontBlank;
    static boolean blindAssist     = DEFAULT_blindAssist;
    static boolean soundWAudio     = DEFAULT_soundWAudio;
    static boolean fullScreen      = DEFAULT_fullScreen;
    static boolean logResults      = DEFAULT_logResults;
    static boolean useConfig       = DEFAULT_useConfig;
    static boolean saveConfig      = DEFAULT_saveConfig;
    static boolean saveResearch    = DEFAULT_saveResearch;
    static boolean customMode      = DEFAULT_customMode;

    static int ultimateMaxLen = DEFAULT_ultimateMaxLen;
    
    static int initDelay    = DEFAULT_initDelay;    
    static int stimulusTime = DEFAULT_stimulusTime;    
    static int pauseTime    = DEFAULT_pauseTime;
    
    static boolean limitErrors = DEFAULT_limitErrors; 
    static int     errorLimit  = DEFAULT_errorLimit;  
    
    static boolean keyForNext     = DEFAULT_keyForNext;     
    static boolean anyKeyForNext  = DEFAULT_anyKeyForNext; 

    static boolean extraDelay = DEFAULT_extraDelay;
    static int     extraDelayTime = DEFAULT_extraDelayTime;

    static int length    = DEFAULT_length;
    static int minLength = DEFAULT_minLength;
    static int maxLength = DEFAULT_maxLength;
    static int setSize   = DEFAULT_setSize;
    static int numToDo   = DEFAULT_numToDo;
    static int algorithm = DEFAULT_algorithm;

    static boolean testCompleted = false;
    static boolean configExits = false;
    static boolean basicMode = !customMode;
    static int[]   previous = { };


    static void restoreDefaults() {
        visualStimuli = DEFAULT_visualStimuli;
        audioStimuli = DEFAULT_audioStimuli;
        digitsForward = DEFAULT_digitsForward;
        autoShowResults = DEFAULT_autoShowResults;
        endingSound = DEFAULT_endingSound;
        dontBlank = DEFAULT_dontBlank;
        blindAssist = DEFAULT_blindAssist;
        soundWAudio = DEFAULT_soundWAudio;
        fullScreen = DEFAULT_fullScreen;
        logResults = DEFAULT_logResults;
        useConfig = DEFAULT_useConfig;
        saveConfig = DEFAULT_saveConfig;
        saveResearch = DEFAULT_saveResearch;
        customMode = DEFAULT_customMode;
        initDelay = DEFAULT_initDelay;
        stimulusTime = DEFAULT_stimulusTime;
        pauseTime = DEFAULT_pauseTime;
        limitErrors = DEFAULT_limitErrors;
        errorLimit = DEFAULT_errorLimit;
        keyForNext = DEFAULT_keyForNext;
        anyKeyForNext = DEFAULT_anyKeyForNext;
        algorithm = DEFAULT_algorithm; 
        extraDelay = DEFAULT_extraDelay;
        extraDelayTime = DEFAULT_extraDelayTime;
    }


    static void restoreFields() {
        ultimateMaxLen = DEFAULT_ultimateMaxLen;
        length = DEFAULT_length;
        minLength = DEFAULT_minLength;
        maxLength = DEFAULT_maxLength;
        setSize = DEFAULT_setSize;
        numToDo = DEFAULT_numToDo;
        algorithm = DEFAULT_algorithm;
    }


    static void restoreStandards() {
        visualStimuli = DEFAULT_visualStimuli;
        audioStimuli = DEFAULT_audioStimuli;
        minLength = DEFAULT_minLength;
        maxLength = DEFAULT_maxLength;
        setSize = DEFAULT_setSize;
        algorithm = DEFAULT_algorithm;
        ultimateMaxLen = DEFAULT_ultimateMaxLen;
        blindAssist = DEFAULT_blindAssist;
        soundWAudio = DEFAULT_soundWAudio;
        initDelay = DEFAULT_initDelay;
        stimulusTime = DEFAULT_stimulusTime;
        pauseTime = DEFAULT_pauseTime;
        limitErrors = DEFAULT_limitErrors;
        errorLimit = DEFAULT_errorLimit;
        keyForNext = DEFAULT_keyForNext;
        anyKeyForNext = DEFAULT_anyKeyForNext;
        extraDelay = DEFAULT_extraDelay;
        extraDelayTime = DEFAULT_extraDelayTime;
    }
}
