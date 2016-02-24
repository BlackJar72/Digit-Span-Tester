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

import javax.swing.*;
import java.awt.*;


class SetupPanel extends JPanel {

    private ControlListener listener;
    DigitSpanTester mainClass;

    JTabbedPane contentPane;
    JPanel content1;
    JPanel content2;
    JPanel buttonPane;
    private BoxLayout buttons;

    JLabel basicLabel;
    JLabel modeLabel;
    JLabel specialLabel;
    
    JLabel timingLabel;
    JLabel limitLabel;
    JLabel keyNextLabel;

    JCheckBox autoShowB;
    JCheckBox exitSoundB;
    JCheckBox dontBlankB;
    JCheckBox blindB;
    JCheckBox soundB;
    JCheckBox logB;
    JCheckBox configB;
    JCheckBox storeFieldsB;

    ButtonGroup modeButtons;
    JRadioButton basiclMode;
    JRadioButton customMode;

    JButton saveConfigB;
    JButton restoreConfigB;
    JButton deleteConfigB;

    private GridBagLayout mainLayout;
    private Insets allIns;
    private GridBagConstraints autoShowC;
    private GridBagConstraints exitSoundC;
    private GridBagConstraints dontBlankC;
    private GridBagConstraints blindC;
    private GridBagConstraints soundC;
    private GridBagConstraints logC;
    private GridBagConstraints configC;
    private GridBagConstraints storeFieldsC;
    private GridBagConstraints clinicalC;
    private GridBagConstraints researchC;
    private GridBagConstraints basicC;
    private GridBagConstraints modeC;
    private GridBagConstraints specialC;
    private GridBagConstraints spacerH1C;
    private GridBagConstraints spacerV1C;
    
    JLabel stimTimeL;
    JLabel stimPauseL;
    JLabel errorLimitL;
    
    JTextField stimTimeF;
    JTextField stimPauseF;
    JTextField extraDelayF;
    JTextField errorLimitF;
    
    JCheckBox extraDelayLB;
    JCheckBox limitErrorsB;
    JCheckBox keyNextB;
    JCheckBox anyKeyB;
    
    private GridBagLayout advLayout;   
    private GridBagConstraints spacerH2C;
    private GridBagConstraints spacerV2C;
    private GridBagConstraints timingLC;    
    private GridBagConstraints stimTimeLC;
    private GridBagConstraints stimPauseLC;  
    private GridBagConstraints stimTimeFC;
    private GridBagConstraints stimPauseFC; 
    private GridBagConstraints extraDelayFC; 
    private GridBagConstraints extraDelayLBC; 
    private GridBagConstraints limitLabelC; 
    private GridBagConstraints errorLimitLC;  
    private GridBagConstraints errorLimitFC;
    private GridBagConstraints limitErrorsC; 
    private GridBagConstraints keyNextLabelC; 
    private GridBagConstraints keyNextC; 
    private GridBagConstraints anyKeyC; 




    public SetupPanel(ControlListener controls) {
        super(new BorderLayout());
        listener = controls;
        setName("setupPanel");
        setVisible(true);

        mainLayout = new GridBagLayout();
        advLayout = new GridBagLayout();
        content1 = new JPanel(mainLayout);
        content2 = new JPanel(advLayout);
        contentPane = new JTabbedPane(JTabbedPane.TOP, 
                JTabbedPane.WRAP_TAB_LAYOUT);
        
        
        contentPane.addTab("Basic Options", null, content1, "General options "
                + "for typical use");        
        contentPane.addTab("Advanced Options", null, content2, "Advanced "
                + "options for specialied uses");
        add(contentPane, BorderLayout.CENTER);

        allIns = new Insets(3, 3, 3, 3);
        basicC = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        autoShowC = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        exitSoundC = new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        dontBlankC = new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        blindC = new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        soundC = new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        specialC = new GridBagConstraints(2, 4, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        logC = new GridBagConstraints(2, 5, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        configC = new GridBagConstraints(2, 6, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        storeFieldsC = new GridBagConstraints(2, 7, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        modeC = new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        clinicalC = new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        researchC = new GridBagConstraints(2, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        spacerH1C = new GridBagConstraints(1, 0, 1, 1, 0.5, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        spacerV1C = new GridBagConstraints(2, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        
         
        timingLC = new GridBagConstraints(0, 0, 5, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);      
        spacerH2C = new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        spacerV2C = new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);  
        stimTimeLC = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);        
        stimTimeFC = new GridBagConstraints(1, 1, 4, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);        
        stimPauseLC = new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);        
        stimPauseFC = new GridBagConstraints(1, 2, 4, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);          
        extraDelayLBC = new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);        
        extraDelayFC = new GridBagConstraints(1, 3, 4, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);         
        limitLabelC = new GridBagConstraints(0, 4, 5, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);         
        limitErrorsC = new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);        
        errorLimitLC = new GridBagConstraints(3, 5, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);        
        errorLimitFC = new GridBagConstraints(4, 5, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);        
        keyNextLabelC = new GridBagConstraints(0, 6, 5, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);         
        keyNextC = new GridBagConstraints(0, 7, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);        
        anyKeyC = new GridBagConstraints(4, 7, 1, 1, 1.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);



        basicLabel = new JLabel("<HTML><BIG><B>Basic Setup</B></BIG></HTML>");
        modeLabel = new JLabel("<HTML><BIG><B>Usage Modes</B></BIG></HTML>");
        specialLabel = new JLabel("<HTML><BIG><B>Special Files</B></BIG></HTML>");
        content1.add(basicLabel, basicC);
        content1.add(modeLabel, modeC);
        content1.add(specialLabel, specialC);

        autoShowB = new JCheckBox("Show Results After Test");
        autoShowB.setName("autoShow");
        autoShowB.setActionCommand("autoShow");
        autoShowB.setSelected(ControlVariables.autoShowResults);
        autoShowB.addActionListener(listener);
        content1.add(autoShowB, autoShowC);

        exitSoundB = new JCheckBox("Play Sound when Test Ends");
        exitSoundB.setName("exitSound");
        exitSoundB.setActionCommand("exitSound");
        exitSoundB.setSelected(ControlVariables.endingSound);
        exitSoundB.addActionListener(listener);
        content1.add(exitSoundB, exitSoundC);

        dontBlankB = new JCheckBox("Don't Blank Info Fields");
        dontBlankB.setName("dontBlank");
        dontBlankB.setActionCommand("dontBlank");
        dontBlankB.setSelected(ControlVariables.dontBlank);
        dontBlankB.addActionListener(listener);
        content1.add(dontBlankB, dontBlankC);

        blindB = new JCheckBox("Use Verbal Cues (always)");
        blindB.setName("blindB");
        blindB.setActionCommand("voiceCues");
        blindB.setSelected(ControlVariables.blindAssist);
        blindB.setEnabled(ControlVariables.customMode);
        blindB.addActionListener(listener);
        content1.add(blindB, blindC);

        soundB = new JCheckBox("Verbal Cues with Audio");
        soundB.setName("soundB");
        soundB.setActionCommand("voiceAudio");
        soundB.setSelected(ControlVariables.soundWAudio);
        soundB.setEnabled(ControlVariables.customMode &&
                !ControlVariables.blindAssist);
        soundB.addActionListener(listener);
        content1.add(soundB, soundC);

        logB = new JCheckBox("Log Results");
        logB.setName("logButton");
        logB.setActionCommand("setLogging");
        logB.setSelected(ControlVariables.logResults);
        logB.addActionListener(listener);
        content1.add(logB, logC);

        configB = new JCheckBox("Use Config File");
        configB.setName("configButton");
        configB.setActionCommand("setConfig");
        configB.setSelected(ControlVariables.useConfig);
        configB.addActionListener(listener);
        content1.add(configB, configC);

        storeFieldsB = new JCheckBox("Store Fields in Config");
        storeFieldsB.setName("storeFields");
        storeFieldsB.setActionCommand("storeFields");
        storeFieldsB.setEnabled(ControlVariables.useConfig);
        storeFieldsB.setSelected(ControlVariables.saveResearch
                && ControlVariables.useConfig);
        storeFieldsB.addActionListener(listener);
        content1.add(storeFieldsB, storeFieldsC);

        modeButtons = new ButtonGroup();
        basiclMode = new JRadioButton("Default Tests");
        basiclMode.setSelected(!ControlVariables.customMode);
        basiclMode.setName("defaultMode");
        basiclMode.setActionCommand("defaultMode");
        basiclMode.addActionListener(listener);
        customMode = new JRadioButton("Custom Tests");
        customMode.setSelected(ControlVariables.customMode);
        customMode.setName("researchMode");
        customMode.setActionCommand("researchMode");
        customMode.addActionListener(listener);
        modeButtons.add(basiclMode);
        modeButtons.add(customMode);
        content1.add(basiclMode, clinicalC);
        content1.add(customMode, researchC);        
        
        timingLabel = new JLabel("<HTML><BIG><B>Timing Setup</B></BIG></HTML>");
        //content2.add(timingLabel, timingLC);
        
        content2.add(new JLabel(""), spacerV2C);
        
        stimTimeL = new JLabel("Simulus Time (ms): ");
        stimTimeL.setHorizontalAlignment(JLabel.RIGHT);
        stimTimeF = new JTextField("" + ControlVariables.stimulusTime);
        stimTimeF.setName("stimTime");
        stimTimeF.setActionCommand("stimTime");
        stimTimeF.setHorizontalAlignment(JTextField.TRAILING);
        content2.add(stimTimeL, stimTimeLC);
        content2.add(stimTimeF, stimTimeFC);
        stimTimeF.setEditable(ControlVariables.customMode);
        stimTimeF.addActionListener(listener);
        
        stimPauseL = new JLabel("Pause Time (ms): ");
        stimPauseL.setHorizontalAlignment(JLabel.RIGHT);
        stimPauseF = new JTextField("" + ControlVariables.pauseTime);
        stimPauseF.setName("pauseTime");
        stimPauseF.setActionCommand("pauseTime");
        stimPauseF.setHorizontalAlignment(JTextField.TRAILING);
        content2.add(stimPauseL, stimPauseLC);
        content2.add(stimPauseF, stimPauseFC);
        stimPauseF.setEditable(ControlVariables.customMode);
        stimPauseF.addActionListener(listener);
        
        //<editor-fold defaultstate="collapsed" desc="Not used but kpet just in case">
        // Reseting this can cause some problems, so it have been disabled
        /*
         * initPauseL = new JLabel("Initial Delay (ms): ");
         * initPauseL.setHorizontalAlignment(JLabel.RIGHT);
         * initPauseF = new JTextField("" + ControlVariables.initDelay);
         * initPauseF.setName("initPause");
         * initPauseF.setActionCommand("initPause");
         * initPauseF.setHorizontalAlignment(JTextField.TRAILING);
         * content2.add(initPauseL, initPauseLC);
         * content2.add(initPauseF, initPauseFC);
         * initPauseF.setEditable(ControlVariables.customMode);
         * initPauseF.setEnabled(ControlVariables.customMode);
         * initPauseF.addActionListener(listener);
         */
        //</editor-fold>    

        extraDelayLB = new JCheckBox("Extra Delay After Last (s): ");
        extraDelayLB.setName("extraDelay");
        extraDelayLB.setActionCommand("extraDelay");
        extraDelayLB.setHorizontalAlignment(JCheckBox.RIGHT);
        extraDelayLB.setEnabled(ControlVariables.customMode);
        extraDelayLB.setSelected(ControlVariables.extraDelay);
        extraDelayLB.addActionListener(listener);  
        extraDelayF = new JTextField("" + ControlVariables.extraDelayTime);
        extraDelayF.setName("extraDelayTime");
        extraDelayF.setActionCommand("extraDelayTime");
        extraDelayF.setHorizontalAlignment(JTextField.TRAILING);
        content2.add(extraDelayLB, extraDelayLBC); 
        content2.add(extraDelayF, extraDelayFC);
        extraDelayF.setEditable(ControlVariables.customMode 
                && ControlVariables.extraDelay);
        extraDelayF.setEnabled(ControlVariables.customMode
                && ControlVariables.extraDelay);
        extraDelayF.addActionListener(listener);
        
        limitLabel = new JLabel("<HTML><BIG><B>Error Limiting</B></BIG></HTML>");
        //content2.add(limitLabel, limitLabelC);

        limitErrorsB = new JCheckBox("End test after error limit");
        limitErrorsB.setName("limitErrors");
        limitErrorsB.setActionCommand("limitErrors");
        limitErrorsB.setEnabled(ControlVariables.customMode);
        limitErrorsB.setSelected(ControlVariables.limitErrors);
        limitErrorsB.addActionListener(listener);
        content2.add(limitErrorsB, limitErrorsC);
        
        errorLimitL = new JLabel("Error Limit: ");
        errorLimitL.setHorizontalAlignment(JLabel.RIGHT);
        errorLimitF = new JTextField("" + ControlVariables.errorLimit);
        errorLimitF.setName("errorLimit");
        errorLimitF.setActionCommand("errorLimit");
        errorLimitF.setHorizontalAlignment(JTextField.TRAILING);
        content2.add(errorLimitL, errorLimitLC);
        content2.add(errorLimitF, errorLimitFC);
        errorLimitF.setEditable(ControlVariables.customMode 
                && ControlVariables.limitErrors);
        errorLimitF.addActionListener(listener);     
        
        keyNextLabel = new JLabel("<HTML><BIG><B>Taker Control</B></BIG></HTML>");
        //content2.add(keyNextLabel , keyNextLabelC);

        keyNextB = new JCheckBox("Hit Key for Next");
        keyNextB.setName("keyNext");
        keyNextB.setActionCommand("keyNext");
        keyNextB.setEnabled(ControlVariables.customMode);
        keyNextB.setSelected(ControlVariables.keyForNext);
        keyNextB.addActionListener(listener);
        content2.add(keyNextB, keyNextC);

        anyKeyB = new JCheckBox("Any Key for Next");
        anyKeyB.setName("anyKey");
        anyKeyB.setActionCommand("anyKey");
        anyKeyB.setEnabled(ControlVariables.customMode 
                && ControlVariables.keyForNext);
        anyKeyB.setSelected(ControlVariables.anyKeyForNext);
        anyKeyB.addActionListener(listener);
        content2.add(anyKeyB, anyKeyC);

        buttonPane = new JPanel();
        buttons = new BoxLayout(buttonPane, BoxLayout.X_AXIS);
        add(buttonPane, BorderLayout.SOUTH);

        saveConfigB = new JButton("Save Config");
        saveConfigB.setName("saveConfig");
        saveConfigB.setActionCommand("saveConfig");
        saveConfigB.setEnabled(true);
        saveConfigB.addActionListener(listener);
        buttonPane.add(saveConfigB, 0);

        restoreConfigB = new JButton("Restore Defaults");
        restoreConfigB.setName("restoreConfig");
        restoreConfigB.setActionCommand("restoreConfig");
        restoreConfigB.setEnabled(true);
        restoreConfigB.addActionListener(listener);
        buttonPane.add(restoreConfigB, 1);
        
    }


    void restoreDefaults() {
        ControlVariables.restoreDefaults();
    }
}
