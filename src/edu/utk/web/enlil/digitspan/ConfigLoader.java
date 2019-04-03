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

import java.util.HashMap;
import java.io.*;


final class ConfigLoader {

    DigitSpanTester mainClass;

    private static final File configFile = DigitSpanTester.configFile;
    private static ObjectOutputStream configOut;
    private static ObjectInputStream configIn;
    FileOutputStream test;
    private HashMap<String, String> setup;


    ConfigLoader(DigitSpanTester mainClass) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        mainClass = this.mainClass;
        load();
    }


    private void claimOutput() throws IOException {
        configOut = new ObjectOutputStream(new
                BufferedOutputStream(new FileOutputStream(configFile)));
    }


    void close() throws IOException {
        if(configOut == null) return;
        configOut.flush();
        configOut.close();
    }
    

    @SuppressWarnings("unchecked")
	void load() throws FileNotFoundException, IOException,
            ClassNotFoundException {
        // See if there is one, if not return.  Don't check for readability here
        // as an existant but unreadable config file shouldn't nornally occure
        // and should be handled with exception checking, as it is an error.
        if(!configFile.exists()) return;
        configIn = new ObjectInputStream(new
                BufferedInputStream(new FileInputStream(configFile)));
        setup = (HashMap<String, String>) configIn.readObject();
        configIn.close();
        if(Boolean.parseBoolean(setup.get("useConfig"))) setVariables();
        else return;
        if(ControlVariables.saveResearch) setFields();
    }


    private void setVariables() {
        ControlVariables.autoShowResults
                = Boolean.parseBoolean(setup.get("autoShowResults"));
        ControlVariables.endingSound
                = Boolean.parseBoolean(setup.get("endingSound"));
        ControlVariables.dontBlank
                = Boolean.parseBoolean(setup.get("dontBlank"));
        ControlVariables.blindAssist
                = Boolean.parseBoolean(setup.get("blindAssist"));
        ControlVariables.soundWAudio
                = Boolean.parseBoolean(setup.get("soundWAudio"));
        ControlVariables.fullScreen
                = Boolean.parseBoolean(setup.get("fullScreen"));
        ControlVariables.logResults
                = Boolean.parseBoolean(setup.get("logResults"));
        ControlVariables.useConfig
                = Boolean.parseBoolean(setup.get("useConfig"));
        ControlVariables.saveConfig
                = Boolean.parseBoolean(setup.get("saveConfig"));
        ControlVariables.saveResearch
                = Boolean.parseBoolean(setup.get("saveResearch"));
        ControlVariables.customMode
                = Boolean.parseBoolean(setup.get("researchMode"));
        if(!setup.containsKey("stimulusTime")) return; // Backward compatibility
        ControlVariables.stimulusTime
                = Integer.parseInt(setup.get("stimulusTime"));
        ControlVariables.pauseTime
                = Integer.parseInt(setup.get("pauseTime"));
        ControlVariables.errorLimit
                = Integer.parseInt(setup.get("errorLimit"));
        ControlVariables.limitErrors
                = Boolean.parseBoolean(setup.get("limitErrors"));
        ControlVariables.keyForNext
                = Boolean.parseBoolean(setup.get("keyForNext"));
        ControlVariables.anyKeyForNext
                = Boolean.parseBoolean(setup.get("anyKeyForNext"));
        if(!setup.containsKey("extraDelay")) return; // Backward compatibility
        ControlVariables.extraDelayTime
                = Integer.parseInt(setup.get("extraDelayTime"));
        ControlVariables.extraDelay
                = Boolean.parseBoolean(setup.get("extraDelay"));
    }


    private void setFields() {
        ControlVariables.visualStimuli
                = Boolean.parseBoolean(setup.get("visualStimuli"));
        ControlVariables.audioStimuli
                = Boolean.parseBoolean(setup.get("audioStimuli"));
        ControlVariables.digitsForward
                = Boolean.parseBoolean(setup.get("digitsForward"));
        ControlVariables.ultimateMaxLen
                = Integer.parseInt(setup.get("ultimateMaxLen"));
        ControlVariables.length
                = Integer.parseInt(setup.get("length"));
        ControlVariables.minLength
                = Integer.parseInt(setup.get("minLength"));
        ControlVariables.maxLength
                = Integer.parseInt(setup.get("maxLength"));
        ControlVariables.setSize
                = Integer.parseInt(setup.get("setSize"));
        ControlVariables.numToDo
                = Integer.parseInt(setup.get("numToDo"));
        ControlVariables.algorithm
                = Integer.parseInt(setup.get("algorithm"));
    }


    private void setMap() {
        setup.put("autoShowResults",
                Boolean.toString(ControlVariables.autoShowResults));
        setup.put("endingSound",
                Boolean.toString(ControlVariables.endingSound));
        setup.put("dontBlank",
                Boolean.toString(ControlVariables.dontBlank));
        setup.put("blindAssist",
                Boolean.toString(ControlVariables.visualStimuli));
        setup.put("blindAssist",
                Boolean.toString(ControlVariables.blindAssist));
        setup.put("soundWAudio",
                Boolean.toString(ControlVariables.soundWAudio));
        setup.put("fullScreen",
                Boolean.toString(ControlVariables.fullScreen));
        setup.put("logResults",
                Boolean.toString(ControlVariables.logResults));
        setup.put("useConfig",
                Boolean.toString(ControlVariables.useConfig));
        setup.put("saveConfig",
                Boolean.toString(ControlVariables.saveConfig));
        setup.put("saveResearch",
                Boolean.toString(ControlVariables.saveResearch));
        setup.put("researchMode",
                Boolean.toString(ControlVariables.customMode));
        setup.put("stimulusTime",
                Integer.toString(ControlVariables.stimulusTime));
        setup.put("pauseTime",
                Integer.toString(ControlVariables.pauseTime));
        setup.put("extraDelayTime",
                Integer.toString(ControlVariables.extraDelayTime));
        setup.put("extraDelay",
                Boolean.toString(ControlVariables.extraDelay));
        setup.put("errorLimit",
                Integer.toString(ControlVariables.errorLimit));
        setup.put("limitErrors",
                Boolean.toString(ControlVariables.limitErrors));
        setup.put("keyForNext",
                Boolean.toString(ControlVariables.keyForNext));
        setup.put("anyKeyForNext",
                Boolean.toString(ControlVariables.anyKeyForNext));
        setup.put("visualStimuli",
                Boolean.toString(ControlVariables.visualStimuli));
        setup.put("audioStimuli",
                Boolean.toString(ControlVariables.audioStimuli));
        setup.put("digitsForward",
                Boolean.toString(ControlVariables.digitsForward));
        setup.put("ultimateMaxLen",
                Integer.toString(ControlVariables.ultimateMaxLen));
        setup.put("length",
                Integer.toString(ControlVariables.length));
        setup.put("minLength",
                Integer.toString(ControlVariables.minLength));
        setup.put("maxLength",
                Integer.toString(ControlVariables.maxLength));
        setup.put("setSize",
                Integer.toString(ControlVariables.setSize));
        setup.put("numToDo",
                Integer.toString(ControlVariables.numToDo));
        setup.put("algorithm",
                Integer.toString(ControlVariables.algorithm));
    }


    void writeConfig() throws IOException {
        setup = new HashMap<String, String>();
        if(configOut == null) claimOutput();
        setMap();
        configOut.writeObject(setup);
        configOut.flush();
        close();
    }
}
