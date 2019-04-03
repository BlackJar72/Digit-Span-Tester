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
import jaredbgreat.arcade.loader.AudioLoader;
import jaredbgreat.arcade.ui.sound.Sound;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public final class DigitSpanTester implements Runnable {

    private MainWindow mainWindow;
    private TestingWindow theTest;
    private GeneratedSequences stimuli;
    private UserSequences responces;
    static final File dstHome = new File(System.getProperty("user.home")
            + System.getProperty("file.separator") + "DigitSpanTester");
    static final File logFile = new File(dstHome, "DigitSpanTester-log.log");
    static final File configFile = new File(dstHome, "DigitSpanTester-config.cfg");

    RandomSequencer generator;
    ConfigLoader configuration;
    private String testID; // Identifies taker / test for human use

    private int newValInt;
    private String newValS;


    public DigitSpanTester() {
        generator = new RandomSequencer();
        new JOptionPane();
        AudioLoader.initAudio();
        try {
            if(!dstHome.exists()) dstHome.mkdir();
            configuration = new ConfigLoader(this);
        } catch (Exception ex) {
            Logger.getLogger(DigitSpanTester.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        if(ControlVariables.logResults) ControlVariables.logResults =
                ResultLogger.openLog(logFile);
    }


    void startLog() {
        if(ResultLogger.isOff()) ControlVariables.logResults =
                ResultLogger.openLog(logFile);
        else ControlVariables.logResults = true;
        // Needs error dialog!
    }


    void stopLog() {
        // Once started the logger file is kept open, to keep its lock
        ControlVariables.logResults = false;
    }


    void setLogging(boolean turnOn) {
        if(turnOn) startLog();
        else stopLog();
    }


    public void shutdown() {
        if(ResultLogger.isConnected()) ResultLogger.disconnect();
        System.exit(0);
    }


    public void startTest() {
        stimuli = new GeneratedSequences(ControlVariables.minLength,
                ControlVariables.maxLength, ControlVariables.setSize,
                ControlVariables.algorithm, generator);
        responces = new UserSequences(stimuli);
        responces.digitsForward = ControlVariables.digitsForward;
        testID = mainWindow.mainPane.idField.getText();
        if(!ControlVariables.dontBlank) mainWindow.mainPane.idField.setText("");
        theTest = new TestingWindow(this);
        theTest.beginAdministration(stimuli, responces);
        mainWindow.setVisible(false);
    }


    void testComplete(boolean maxedOut, int longest) {        
        responces.score(stimuli, maxedOut, longest);
        mainWindow.showResults.setEnabled(ControlVariables.testCompleted);
        mainWindow.saveResults.setEnabled(ControlVariables.testCompleted);
        mainWindow.printResults.setEnabled(ControlVariables.testCompleted);
        mainWindow.resultPane.resultText.setText(responces.scoreToHTML());
        mainWindow.resultPane.resultText.setCaretPosition(0);
        if(ControlVariables.endingSound) 
            Sound.registry.getFromName("tone3").play();
        if(ControlVariables.autoShowResults) mainWindow.setPage(25);
        if(ControlVariables.logResults) {
            ResultLogger newLog = new ResultLogger(testID,
                    responces.scoreToText(), responces.digitsForward);
            newLog.start();
        }
        JOptionPane.showMessageDialog(mainWindow, "Done!");
        mainWindow.setVisible(true);
    }


    void testAborted() {
        mainWindow.showResults.setEnabled(ControlVariables.testCompleted);
        mainWindow.saveResults.setEnabled(ControlVariables.testCompleted);
        mainWindow.printResults.setEnabled(ControlVariables.testCompleted);
        if(ControlVariables.endingSound) 
            Sound.registry.getFromName("tone2").play();
        mainWindow.setVisible(true);
    }


    void saveResults() {
        FileExporter ouput = new FileExporter(testID,
                responces.scoreToText(),
                mainWindow, responces.digitsForward);
        ouput.start();
    }


    void saveSequences() {
        try {
            FileExporter ouput = new FileExporter(FileExporter.EXPORT_STIMULI,
                    mainWindow);
            ouput.start();
        } catch (IOException ex) {
            Logger.getLogger(DigitSpanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    void saveTests() {
        try {
            FileExporter ouput = new FileExporter(FileExporter.EXPORT_TESTS,
                    mainWindow);
            ouput.start();
        } catch (IOException ex) {
            Logger.getLogger(DigitSpanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    void printResults() {
        PrintExporter ouput = new PrintExporter(testID,
                responces.scoreToPrintable(), 
                mainWindow, responces.digitsForward);
        ouput.start();
    }


    void printSequences() {
        try {
            PrintExporter ouput = new PrintExporter(FileExporter.EXPORT_STIMULI,
                    mainWindow);
            ouput.start();
        } catch (IOException ex) {
            Logger.getLogger(DigitSpanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    void printTests() {
        try {
            PrintExporter ouput = new PrintExporter(FileExporter.EXPORT_TESTS,
                    mainWindow);
            ouput.start();
        } catch (IOException ex) {
            Logger.getLogger(DigitSpanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    int parseInt(JTextField source, int oldVal) {
        newValS = source.getText();
        newValS.replaceAll("\\D", "");
        try {
             newValInt= Integer.parseInt(newValS);
        } catch (NumberFormatException ex) {
            return oldVal;
        } 
        if(newValInt > 65535 || newValInt < 0) { 
            return oldVal; // Integer.MIN_VALUE is immune to abs, stays negative
        }
        return newValInt;
    }


    void updateTimeEstimates() {
        if(ControlVariables.keyForNext) {
            mainWindow.mainPane.stimulusTime.setText("");
            mainWindow.mainPane.estimateTime.setText("");
            return;
        }
        int num = (((ControlVariables.maxLength *
                (ControlVariables.maxLength + 1)) / 2) -
                (((ControlVariables.minLength *
                (ControlVariables.minLength - 1)) / 2)));
        int time = num;
        time *= ControlVariables.setSize;
        time *= (ControlVariables.stimulusTime + ControlVariables.pauseTime);
        time += (((ControlVariables.maxLength - ControlVariables.minLength + 1) * 
                ControlVariables.setSize) * ControlVariables.initDelay);
        time /= 1000; // Convert from miliseconds to seconds
        if(ControlVariables.extraDelay) 
            time += (num * ControlVariables.extraDelayTime);
        mainWindow.mainPane.stimulusTime.setText("   Maximum time for stimuli: " 
                + (time / 60) + " minutes, " + (time % 60) + " Seconds");
        time += (num * ControlVariables.setSize);
        mainWindow.mainPane.estimateTime.setText("    Estimated tesing time: " 
                + (time / 60) + " minutes, " + (time % 60) + " Seconds");
    }


    void updateLength(JTextField source) {
        ControlVariables.length = parseInt(source, ControlVariables.length );
        if(ControlVariables.length  <  1) ControlVariables.length  = 1;
        if(ControlVariables.length  > ControlVariables.ultimateMaxLen)
            ControlVariables.length  = ControlVariables.ultimateMaxLen;
        source.setText(Integer.toString(ControlVariables.length));
    }


    void updateNum(JTextField source) {
        ControlVariables.numToDo = parseInt(source, ControlVariables.numToDo);
        source.setText(Integer.toString(ControlVariables.numToDo));
    }


    void updateSetSize(JTextField source) {
        ControlVariables.setSize = parseInt(source,
                ControlVariables.setSize);
        source.setText(Integer.toString(ControlVariables.setSize));
        updateTimeEstimates();
    }


    void updateMax(JTextField source) {
        newValInt = parseInt(source,
                ControlVariables.maxLength);
        if(newValInt < ControlVariables.minLength)
            newValInt = ControlVariables.minLength;
        if(newValInt  > ControlVariables.ultimateMaxLen)
            newValInt  = ControlVariables.ultimateMaxLen;
        ControlVariables.maxLength = newValInt;
        mainWindow.generatePane.maxField.setText(Integer.toString(ControlVariables.maxLength));
        mainWindow.mainPane.maxField.setText(Integer.toString(ControlVariables.maxLength));
        updateTimeEstimates();
    }


    void updateMin(JTextField source) {
        ControlVariables.minLength = parseInt(source,
                ControlVariables.minLength);
        if(newValInt > ControlVariables.maxLength)
            newValInt = ControlVariables.maxLength;
        if(newValInt  > ControlVariables.ultimateMaxLen)  // This should never
            newValInt  = ControlVariables.ultimateMaxLen; // happen -- but just in case!
        ControlVariables.minLength = newValInt;
        mainWindow.mainPane.minField.setText(Integer.toString(ControlVariables.minLength));
        if(mainWindow.generatePane.testsMode) {
            mainWindow.generatePane.lengthField.setText(Integer.toString(ControlVariables.minLength));
        }
        updateTimeEstimates();
    }
    
    
    void updateStimTime(JTextField source) {
        int newST = parseInt(source, ControlVariables.stimulusTime);
        if (newST > 0) ControlVariables.stimulusTime = newST;
        mainWindow.setupPane.stimTimeF.setText(Integer.toString(ControlVariables.stimulusTime));
        updateTimeEstimates();
    }
    
    
    void updatePauseTime(JTextField source) {
        int newPT = parseInt(source, ControlVariables.pauseTime);
        if (newPT > 0) ControlVariables.pauseTime = newPT;
        mainWindow.setupPane.stimPauseF.setText(Integer.toString(ControlVariables.pauseTime));
        updateTimeEstimates();
    }


    void updateExtraDelay(JTextField source) {
        int newED = parseInt(source, ControlVariables.extraDelayTime);
        if(newED > 0) ControlVariables.extraDelayTime = newED;
        source.setText(Integer.toString(ControlVariables.extraDelayTime));
        updateTimeEstimates();
    }


    void updateErrorLimit(JTextField source) {
        int newEL = parseInt(source, ControlVariables.errorLimit);
        if(newEL > 0) ControlVariables.errorLimit = newEL;
        source.setText(Integer.toString(ControlVariables.errorLimit));
    }


    void setAduditory() {
        ControlVariables.audioStimuli
                = mainWindow.mainPane.audioBox.isSelected();
        if(!ControlVariables.audioStimuli && !ControlVariables.visualStimuli) {
            ControlVariables.audioStimuli = true;
            mainWindow.mainPane.audioBox.setSelected(true);
        }
    }


    void setVisual() {
        ControlVariables.visualStimuli
                = mainWindow.mainPane.visualBox.isSelected();
        if(!ControlVariables.audioStimuli && !ControlVariables.visualStimuli) {
            ControlVariables.visualStimuli = true;
            mainWindow.mainPane.visualBox.setSelected(true);
        }
    }


    void matchFieldsToVars() {
        //FIXME: Should probably be methods in relevant panes...
        mainWindow.mainPane.visualBox.setSelected(ControlVariables.visualStimuli);
        mainWindow.mainPane.audioBox.setSelected(ControlVariables.audioStimuli);
        mainWindow.mainPane.optForward.setSelected(ControlVariables.digitsForward);
        mainWindow.setupPane.autoShowB.setSelected(ControlVariables.autoShowResults);
        mainWindow.setupPane.exitSoundB.setSelected(ControlVariables.endingSound);
        mainWindow.setupPane.dontBlankB.setSelected(ControlVariables.dontBlank);
        mainWindow.setupPane.blindB.setSelected(ControlVariables.blindAssist);
        mainWindow.setupPane.blindB.setEnabled(ControlVariables.customMode);
        mainWindow.setupPane.logB.setSelected(ControlVariables.logResults);
        mainWindow.setupPane.configB.setSelected(ControlVariables.useConfig);
        mainWindow.setupPane.storeFieldsB.setSelected(ControlVariables.saveResearch);
        mainWindow.setupPane.customMode.setSelected(ControlVariables.customMode);
        mainWindow.setupPane.soundB.setSelected(ControlVariables.soundWAudio);
        mainWindow.setupPane.soundB.setEnabled(ControlVariables.customMode
                && !ControlVariables.blindAssist);
        mainWindow.setupPane.customMode.setSelected(ControlVariables.customMode);
        mainWindow.setupPane.basiclMode.setSelected(!ControlVariables.customMode);
        mainWindow.generatePane.lengthField.setText(Integer.toString(ControlVariables.length));
        mainWindow.mainPane.minField.setText(Integer.toString(ControlVariables.minLength));
        mainWindow.mainPane.maxField.setText(Integer.toString(ControlVariables.maxLength));
        mainWindow.mainPane.setField.setText(Integer.toString(ControlVariables.setSize));
        mainWindow.generatePane.maxField.setText(Integer.toString(ControlVariables.maxLength));
        mainWindow.generatePane.setField.setText(Integer.toString(ControlVariables.setSize));
        mainWindow.generatePane.numField.setText(Integer.toString(ControlVariables.numToDo));
        mainWindow.setupPane.stimTimeF.setText(Integer.toString(ControlVariables.stimulusTime));
        mainWindow.setupPane.stimPauseF.setText(Integer.toString(ControlVariables.pauseTime));
        mainWindow.setupPane.errorLimitF.setText(Integer.toString(ControlVariables.errorLimit));
        mainWindow.setupPane.limitErrorsB.setSelected(ControlVariables.limitErrors);
        mainWindow.setupPane.keyNextB.setSelected(ControlVariables.keyForNext);
        mainWindow.setupPane.anyKeyB.setSelected(ControlVariables.anyKeyForNext);
        mainWindow.setupPane.extraDelayLB.setSelected(ControlVariables.extraDelay);
        mainWindow.setupPane.extraDelayF.setText(Integer.toString(ControlVariables.extraDelayTime));
    }


    void restoreDefaults() {
        setBasic();
        ControlVariables.restoreDefaults();
        mainWindow.alt8Button.setSelected(true);
        matchFieldsToVars();
    }


    void restoreFields() {
        ControlVariables.restoreFields();
        matchFieldsToVars();
    }


    void restoreStandards() {
        ControlVariables.restoreStandards();
        //FIXME: Should probably be methods in relevant panes...
        mainWindow.mainPane.minField.setText(Integer.toString(ControlVariables.minLength));
        mainWindow.mainPane.maxField.setText(Integer.toString(ControlVariables.maxLength));
        mainWindow.mainPane.setField.setText(Integer.toString(ControlVariables.setSize));
        mainWindow.generatePane.lengthField.setText(Integer.toString(ControlVariables.minLength));
        mainWindow.generatePane.maxField.setText(Integer.toString(ControlVariables.maxLength));
        mainWindow.generatePane.setField.setText(Integer.toString(ControlVariables.setSize));
        mainWindow.setupPane.blindB.setSelected(ControlVariables.blindAssist);
        mainWindow.setupPane.soundB.setSelected(ControlVariables.soundWAudio);
        mainWindow.mainPane.audioBox.setSelected(ControlVariables.audioStimuli);
        mainWindow.mainPane.visualBox.setSelected(ControlVariables.visualStimuli);
        mainWindow.setupPane.stimPauseF.setText(Integer.toString(ControlVariables.pauseTime));
        mainWindow.setupPane.errorLimitF.setText(Integer.toString(ControlVariables.errorLimit));
        mainWindow.setupPane.limitErrorsB.setSelected(ControlVariables.limitErrors);
        mainWindow.setupPane.keyNextB.setSelected(ControlVariables.keyForNext);
        mainWindow.setupPane.anyKeyB.setSelected(ControlVariables.anyKeyForNext);
        mainWindow.setupPane.extraDelayLB.setSelected(ControlVariables.extraDelay);
        mainWindow.setupPane.extraDelayF.setText(Integer.toString(ControlVariables.extraDelayTime));
        mainWindow.alt8Button.setSelected(true);

    }


    void setBasic() {
        restoreStandards();
        //FIXME: Should probably be methods in relevant panes...
        mainWindow.mainPane.minField.setEditable(false);
        mainWindow.mainPane.minField.setEnabled(false);
        mainWindow.mainPane.maxField.setEditable(false);
        mainWindow.mainPane.maxField.setEnabled(false);
        mainWindow.mainPane.setField.setEditable(false);
        mainWindow.mainPane.setField.setEnabled(false);
        mainWindow.generatePane.maxField.setEditable(false);
        mainWindow.generatePane.maxField.setEnabled(false);
        mainWindow.generatePane.setField.setEditable(false);
        mainWindow.generatePane.setField.setEnabled(false);
        if(mainWindow.generatePane.optTests.isSelected()) {
            mainWindow.generatePane.lengthField.setEditable(false);
            mainWindow.generatePane.lengthField.setEnabled(false);
        }
        mainWindow.setupPane.blindB.setEnabled(false);
        mainWindow.setupPane.soundB.setEnabled(false);
        mainWindow.setupPane.limitErrorsB.setEnabled(false);
        mainWindow.setupPane.keyNextB.setEnabled(false);
        mainWindow.setupPane.anyKeyB.setEnabled(false);
        mainWindow.setupPane.stimTimeF.setEditable(false);
        mainWindow.setupPane.stimPauseF.setEditable(false);
        mainWindow.setupPane.errorLimitF.setEditable(false);
        mainWindow.setupPane.extraDelayLB.setEnabled(false);
        mainWindow.setupPane.extraDelayF.setEnabled(false);
        mainWindow.setupPane.extraDelayF.setEditable(false);
        mainWindow.mainPane.audioBox.setEnabled(false);
        mainWindow.mainPane.visualBox.setEnabled(false);
        mainWindow.mainPane.audioBox.setSelected(true);
        mainWindow.mainPane.visualBox.setSelected(true);
        mainWindow.algMenu.setVisible(false);
        ControlVariables.customMode = false;
        ControlVariables.basicMode = true;
        updateTimeEstimates();
    }


    void setCustom() {
        restoreStandards();
        //FIXME: Should probably be methods in relevant panes...
        mainWindow.mainPane.minField.setEditable(true);
        mainWindow.mainPane.minField.setEnabled(true);
        mainWindow.mainPane.maxField.setEditable(true);
        mainWindow.mainPane.maxField.setEnabled(true);
        mainWindow.mainPane.setField.setEditable(true);
        mainWindow.mainPane.setField.setEnabled(true);
        mainWindow.generatePane.maxField.setEditable(true);
        mainWindow.generatePane.maxField.setEnabled(true);
        mainWindow.generatePane.setField.setEditable(true);
        mainWindow.generatePane.setField.setEnabled(true);
        mainWindow.generatePane.lengthField.setEditable(true);
        mainWindow.generatePane.lengthField.setEnabled(true);
        mainWindow.setupPane.blindB.setEnabled(true);
        mainWindow.setupPane.soundB.setEnabled(true);
        mainWindow.setupPane.limitErrorsB.setEnabled(true);
        mainWindow.setupPane.keyNextB.setEnabled(true);
        mainWindow.setupPane.stimTimeF.setEditable(true);
        mainWindow.setupPane.stimPauseF.setEditable(true);
        mainWindow.setupPane.stimTimeF.setEnabled(true);
        mainWindow.setupPane.stimPauseF.setEnabled(true);
        mainWindow.setupPane.errorLimitF.setEnabled(true);
        mainWindow.setupPane.errorLimitF.setEditable(true);
        mainWindow.setupPane.extraDelayLB.setEnabled(true);
        mainWindow.mainPane.audioBox.setEnabled(true);
        mainWindow.mainPane.visualBox.setEnabled(true);
        mainWindow.algMenu.setVisible(true);
        ControlVariables.customMode = true;
        ControlVariables.basicMode = false;
    }


    public void run() {
        mainWindow = new MainWindow(this);
    }



    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new DigitSpanTester());
    }
}
