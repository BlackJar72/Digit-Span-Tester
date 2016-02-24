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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;



final class ControlListener implements ActionListener {

    // The actual control codes will likely be hard coded in 
    // DigitSpanControlCodes and thus need no initialization here, and should
    // they be changable.
    private static final ControlCodes ID =
            new ControlCodes();
    private DigitSpanTester mainClass;
    private MainWindow mainWindow;

    private int oldNumToDo = 10;


    private ControlListener() {}; // Must not be instantiated alone!


    public ControlListener(MainWindow mainWindow,
            DigitSpanTester mainClass) {
        // Link to controled objects
        this.mainClass = mainClass;
        this.mainWindow = mainWindow;

        // Create look-up hashes for functions
    }
    

    public void actionPerformed(ActionEvent e) {
        int id = ID.getCode(e.getActionCommand());
        switch(id) {
            case 0: // Quit / Exit
                mainClass.shutdown();
                return;
            case 1: // Save results
                mainClass.saveResults();
                return;
            case 2: // Print results
                mainClass.printResults();
                return;
            case 3: // Save random sequnces
                mainClass.updateLength(mainWindow.generatePane.lengthField);
                mainClass.updateNum(mainWindow.generatePane.numField);
                mainClass.saveSequences();
                return;
            case 4: // Print random sequnces
                mainClass.updateLength(mainWindow.generatePane.lengthField);
                mainClass.updateNum(mainWindow.generatePane.numField);
                mainClass.printSequences();
                return;
            case 5: // Save random tests
                mainClass.updateMin(mainWindow.generatePane.lengthField);
                mainClass.updateMax(mainWindow.generatePane.maxField);
                mainClass.updateSetSize(mainWindow.generatePane.setField);
                mainClass.updateNum(mainWindow.generatePane.numField);
                mainClass.saveTests();
                return;
            case 6: // Print random tests
                mainClass.updateMin(mainWindow.generatePane.lengthField);
                mainClass.updateMax(mainWindow.generatePane.maxField);
                mainClass.updateSetSize(mainWindow.generatePane.setField);
                mainClass.updateNum(mainWindow.generatePane.numField);
                mainClass.printTests();
                return;
            // Numbers 7-16 all set the visible page
            case 7:  // Go to testing page
            case 8:  // Go to sequence generation page
            case 9:  // Go to algorithm secection page
            case 10: // Go to options page
            case 11: // Go to help page
            case 12: // Go to testing help
            case 13: // Go to generation help
            case 14: // Go to setup / options help
            case 15: // Go to algorithm help
            case 16: // Go to about page
                mainWindow.setPage(id);
                return;
            case 17: // Show one sample sequence in the generation window
                mainClass.updateLength(mainWindow.generatePane.lengthField);
                mainClass.updateNum(mainWindow.generatePane.numField);
                mainWindow.generatePane.generateOne();;
                return;
            case 18: // Choose to generate sequences
                ControlVariables.numToDo = 10;
                mainWindow.generatePane.numField.setText("10");
                if(ControlVariables.basicMode) {
                    mainWindow.generatePane.lengthField.setEditable(true);
                    mainWindow.generatePane.lengthField.setEnabled(true);
                }
                mainWindow.generatePane.setBasicMode();
                return;
            case 19: // Choose to generate complete tests
                ControlVariables.numToDo = 1;
                mainWindow.generatePane.numField.setText("1");
                if(ControlVariables.basicMode) {
                    mainWindow.generatePane.lengthField.setEditable(false);
                    mainWindow.generatePane.lengthField.setEnabled(false);
                }
                mainWindow.generatePane.setTestMode();
                return;
            case 20: // Set the length for sequences
                mainClass.updateLength((JTextField) e.getSource());
                return;
            case 21: // Set number of sequences / tests to generate
                mainClass.updateNum((JTextField) e.getSource());
                return;
            case 22: // Set sequences per length in a test
                mainClass.updateSetSize((JTextField) e.getSource());
                return;
            case 23: // Set minimum / starting sequence length for tests
                mainClass.updateMin((JTextField) e.getSource());
                return;
            case 24: // Set maximum / ending sequence length for tests
                mainClass.updateMax((JTextField) e.getSource());
                return;
            case 25: // Send user to the results pane
                mainWindow.setPage(id);
                return;
            case 26: // Launch TestingWindow and begin test
                mainClass.updateMin(mainWindow.mainPane.minField);
                mainClass.updateMax(mainWindow.mainPane.maxField);
                mainClass.updateSetSize(mainWindow.mainPane.setField);
                mainClass.updateStimTime(mainWindow.setupPane.stimTimeF);
                mainClass.updatePauseTime(mainWindow.setupPane.stimPauseF);
                mainClass.updateErrorLimit(mainWindow.setupPane.errorLimitF);
                if(ControlVariables.extraDelay)
                    mainClass.updateExtraDelay(mainWindow.setupPane.extraDelayF);
                mainClass.startTest();
                return;
            case 27: // Set digits forward
                ControlVariables.digitsForward = true;
                return;
            case 28: // Set digits backward
                ControlVariables.digitsForward = false;
                return;
            case 29: // Select random number generation
                ControlVariables.algorithm = RandomSequencer.RANDOM;
                ControlVariables.ultimateMaxLen = 4096;
                return;
            case 30: // Select simple shuffle
                ControlVariables.algorithm = RandomSequencer.SIMPLE;
                ControlVariables.ultimateMaxLen = 10;
                return;
            case 31: // Select advance shuffle 1
                ControlVariables.algorithm = RandomSequencer.ALT1;
                ControlVariables.ultimateMaxLen = 10;
                return;
            case 32: // Select advance shuffle 2
                ControlVariables.algorithm = RandomSequencer.ALT2;
                ControlVariables.ultimateMaxLen = 10;
                return;
            case 33: // Select advance shuffle 3
                ControlVariables.algorithm = RandomSequencer.ALT3;
                ControlVariables.ultimateMaxLen = 10;
                return;
            case 34: // Select advance shuffle 4
                ControlVariables.algorithm = RandomSequencer.ALT4;
                ControlVariables.ultimateMaxLen = 20;
                return;
            case 35: // Select advance shuffle 5
                ControlVariables.algorithm = RandomSequencer.ALT5;
                ControlVariables.ultimateMaxLen = 20;
                return;
            case 36: // Select advance shuffle 6
                ControlVariables.algorithm = RandomSequencer.ALT6;
                ControlVariables.ultimateMaxLen = 20;
                return;
            case 37: // Turn audio on / off
                mainClass.setAduditory();
                return;
            case 38: // Turn visual on / off
                mainClass.setVisual();
                return;
            case 39: // Set auto-show on test completion
                ControlVariables.autoShowResults =
                        mainWindow.setupPane.autoShowB.isSelected();
                return;
            case 40: // Set full screen mode
                return;
            case 41: // Set playing sounds when test ends
                ControlVariables.endingSound =
                        mainWindow.setupPane.exitSoundB.isSelected();
                return;
            case 42: // Set blank / don't blank ID and demographics
                ControlVariables.dontBlank =
                        mainWindow.setupPane.dontBlankB.isSelected();
                return;
            case 43: // Set use of voice cues (aka, blind assistive mode)
                ControlVariables.blindAssist =
                        mainWindow.setupPane.blindB.isSelected();
                mainWindow.setupPane.soundB.setEnabled(!ControlVariables
                                .blindAssist);
                return;
            case 44: // Turn result logging on / off
                mainClass.setLogging(mainWindow.setupPane.logB.isSelected());
                return;
            case 45: // Turn config file use on / off
                ControlVariables.useConfig =
                        mainWindow.setupPane.configB.isSelected();
                mainWindow.setupPane.storeFieldsB
                        .setEnabled(ControlVariables.useConfig);
                return;
            case 46: // Turn config file save on exit on / off
                return;
            case 47: // Turn storage of research field setting on / off
                ControlVariables.saveResearch =
                        mainWindow.setupPane.storeFieldsB.isSelected();
                return;
            case 48: // Set to clinical mode
                mainClass.setBasic();
                return;
            case 49: // Set to research mode
                mainClass.setCustom();
                return;
            case 50: // Save config file (now)                
                mainClass.updateStimTime(mainWindow.setupPane.stimTimeF);
                mainClass.updatePauseTime(mainWindow.setupPane.stimPauseF);
                mainClass.updateErrorLimit(mainWindow.setupPane.errorLimitF);
                mainClass.updateExtraDelay(mainWindow.setupPane.extraDelayF);
                try {
                    // Save config file (now)
                    mainClass.configuration.writeConfig();
                } catch (IOException ex) {
                    Logger.getLogger(ControlListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            case 51: // Restore default configs
                mainClass.restoreDefaults();
                return;
            case 52: // Delete config file(!!!)
                return;
            case 53: // Set use of voice cues (aka, blind assistive mode)
                ControlVariables.soundWAudio =
                        mainWindow.setupPane.soundB.isSelected();
                return;
            case 54: // Go to GNU General Public Lisence
                mainWindow.setPage(id);
                return;
            case 55: // Select advance shuffle 7
                ControlVariables.algorithm = RandomSequencer.ALT7;
                ControlVariables.ultimateMaxLen = 4096;
                return;
            case 56: // Select advance shuffle 7
                ControlVariables.algorithm = RandomSequencer.ALT8;
                ControlVariables.ultimateMaxLen = 4096;
                return;
            case 57: // Update stimulus time
                mainClass.updateStimTime((JTextField) e.getSource());
                return;
            case 58: // Update pause time
                mainClass.updatePauseTime((JTextField) e.getSource());
                return;
            case 59: return;
            case 60: // Turn error limiting mode on or off
                ControlVariables.limitErrors = 
                        mainWindow.setupPane.limitErrorsB.isSelected();
                mainWindow.setupPane.errorLimitF.
                        setEditable(ControlVariables.limitErrors);
                mainWindow.setupPane.errorLimitF.
                        setEnabled(ControlVariables.limitErrors);
                return;
            case 61: // Set error limit
                mainClass.updateErrorLimit((JTextField) e.getSource());
                return;
            case 62: // Turn key for next mode on or off
                ControlVariables.keyForNext = 
                        mainWindow.setupPane.keyNextB.isSelected();
                mainWindow.setupPane.anyKeyB.
                        setEnabled(ControlVariables.keyForNext);
                mainClass.updateTimeEstimates();
                return;
            case 63: // Allow or dislow the use of any key for next stimulus
                ControlVariables.anyKeyForNext = 
                        mainWindow.setupPane.anyKeyB.isSelected();
                return;
            case 64: // Turn extra delay time on/off
                ControlVariables.extraDelay = 
                        mainWindow.setupPane.extraDelayLB.isSelected();
                mainWindow.setupPane.extraDelayF
                        .setEditable(ControlVariables.extraDelay);
                mainWindow.setupPane.extraDelayF
                        .setEnabled(ControlVariables.extraDelay);
                mainClass.updateTimeEstimates();
                return;
            case 65: // Set extra delay time (in seconds)
                mainClass.updateExtraDelay((JTextField) e.getSource());
                return;
            //No valid code, do nothing
            default: return;
        }
    }
}
