package edu.utk.web.enlil.digitspan;

/*
// <P><UL><LI>Digit Span Tester version 1.0</LI>
// <LI>Copyright (C) 2000, 2011 Jared Blackburn.</LI>
// <LI>(version 0.2.0, 22 March 2011)<LI></UL></P>
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
// <P>This is a simple ustility to act as a macro-prossesor for html
// documents.  Macros are stores as separate files, with the and
// are used by inserting <@filename> into the document.
// Primary source docuemnts are entered on the command line, and
// a macro-expanded version is sent to the standard output.</P>
//
// <P>This program was written by Jared Blackburn, on 11 June 2000,
// and is considerd property and Copyright (C) of the same.
// For questions of comments contact:</P>
//
// <P>Jared Blackburn<BR></BR>
// <A HREF ="http://www.facebook.com/Jared.Blackburn1">http://www.facebook.com/Jared.Blackburn1</A></P>
//
// For more information about the GNU General Public Liscnece see:
// http://www.gnu.org/copyleft/gpl.html
 *
 * // This will be some kind of HashTable or other Key-Pair generating system.
 * // It will allow names of listened components to be translated into numbers
 * // for efficient processing by ControlListener.  Codes will most likely be
 * // hard coded, but not sure yet.  This object should be immutable and unique
 * // within a given program, simply supplying fast, efficient conversion of
 * // names to numeric codes and doing nothing else.  the table used wiil be
 * // hard coded and constant.
 */

import java.util.HashMap;

/**
 *
 * @author jared
 */
final class ControlCodes extends HashMap<String,Integer>{

    public ControlCodes() {
        // Codes for the main menu
        put("quit",           new Integer(0));
        put("saveResults",    new Integer(1));
        put("printResults",   new Integer(2));
        put("saveSequences",  new Integer(3));
        put("printSequences", new Integer(4));
        put("saveStimuli",    new Integer(5));
        put("printStimuli",   new Integer(6));
        put("testingMenu",    new Integer(7));
        put("generationMenu", new Integer(8));
        put("algMenu",        new Integer(9));
        put("optionsMenu",    new Integer(10));
        put("helpHelp",       new Integer(11));
        put("helpTesting",    new Integer(12));
        put("generateHelp",   new Integer(13));
        put("setupHelp",      new Integer(14));
        put("algHelp",        new Integer(15));
        put("aboutHelp",      new Integer(16));
        put("showResults",    new Integer(25));
        put("gplHelp",        new Integer(54));
        //Codes from the sequence generation panel
        put("makeOne",        new Integer(17));
        put("optSequences",   new Integer(18));
        put("optTests",       new Integer(19));
        put("setLength",      new Integer(20));
        put("setNumber",      new Integer(21));
        put("setSize",        new Integer(22));
        put("setMin",         new Integer(23));
        put("setMax",         new Integer(24));
        //Codes from the testing pane
        put("startTest",      new Integer(26));
        put("optForward",     new Integer(27));
        put("optBackward",    new Integer(28));
        put("optAudio",       new Integer(37));
        put("optVisual",      new Integer(38));
        //Codes from the algorithm setup pane
        put("random",         new Integer(29));
        put("shuffle",        new Integer(30));
        put("alt1",           new Integer(31));
        put("alt2",           new Integer(32));
        put("alt3",           new Integer(33));
        put("alt4",           new Integer(34));
        put("alt5",           new Integer(35));
        put("alt6",           new Integer(36));
        put("alt7",           new Integer(55));
        put("alt8",           new Integer(56));
        //Codes from the basic options
        put("autoShow",       new Integer(39));
        put("fullScreen",     new Integer(40));
        put("exitSound",      new Integer(41));
        put("dontBlank",      new Integer(42));
        put("voiceCues",      new Integer(43));
        put("setLogging",     new Integer(44));
        put("setConfig",      new Integer(45));
        put("noSave",         new Integer(46));
        put("storeFields",    new Integer(47));
        put("defaultMode",    new Integer(48));
        put("researchMode",   new Integer(49));
        put("saveConfig",     new Integer(50));
        put("restoreConfig",  new Integer(51));
        put("deleteConfig",   new Integer(52));
        put("voiceAudio",     new Integer(55));
        // Codes from the advanced options
        put("stimTime",       new Integer(57));
        put("pauseTime",      new Integer(58));
        put("initPause",      new Integer(59)); // not current use (saved in case)
        put("limitErrors",    new Integer(60));
        put("errorLimit",     new Integer(61));
        put("keyNext",        new Integer(62));
        put("anyKey",         new Integer(63));
        put("extraDelay",     new Integer(64));
        put("extraDelayTime", new Integer(65));
    }


    public int getCode(String name) {
        return get(name).intValue();
    }
}
