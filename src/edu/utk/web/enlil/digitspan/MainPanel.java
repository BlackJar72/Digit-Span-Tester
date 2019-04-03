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


class MainPanel extends JPanel {


	private static final long serialVersionUID = 2837315943139524154L;
	DigitSpanTester mainClass;
     ControlListener listener;

     BorderLayout mainLayout;
     GridBagLayout fields;
     BoxLayout buttons;
     JPanel buttonBox;
     JPanel fieldBox;
     JPanel outputBox;

     JButton goButton;

     JLabel minLabel;
     JTextField minField;
     JLabel maxLabel;
     JTextField maxField;
     JLabel setLabel;
     JTextField setField;
     JLabel idLabel;
     JTextField idField;

     ButtonGroup optSelect;
     JRadioButton optForward;
     JRadioButton optBackward;

     JCheckBox audioBox;
     JCheckBox visualBox;

     GridBagConstraints minLCon;
     GridBagConstraints minFCon;
     GridBagConstraints maxLCon;
     GridBagConstraints maxFCon;
     GridBagConstraints setLCon;
     GridBagConstraints setFCon;
     GridBagConstraints optFCon;
     GridBagConstraints optBCon;
     GridBagConstraints idLCon;
     GridBagConstraints idFCon;
     GridBagConstraints optACon;
     GridBagConstraints optVCon;
     GridBagConstraints spacerC;

     JLabel stimulusTime;
     JLabel estimateTime;



    public MainPanel(ControlListener controls) {
        listener = controls;
        setName("testPanel");
        fields = new GridBagLayout();
        fieldBox = new JPanel(fields);
        buttonBox = new JPanel();
        outputBox = new JPanel(new GridLayout(2,1));
        buttons = new BoxLayout(buttonBox, BoxLayout.X_AXIS);
        mainLayout = new BorderLayout();
        setLayout(mainLayout);
        add(fieldBox, BorderLayout.NORTH);
        add(buttonBox, BorderLayout.SOUTH);
        add(outputBox, BorderLayout.CENTER);

        Insets allIns = new Insets(3, 3, 3, 3);
        minLCon = new GridBagConstraints(0, 0, 1, 1, 0.5, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        minFCon = new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        maxLCon = new GridBagConstraints(0, 1, 1, 1, 0.5, 1.0,
                GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        maxFCon = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        setLCon = new GridBagConstraints(0, 2, 1, 1, 0.5, 1.0,
                GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        setFCon = new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        optFCon = new GridBagConstraints(4, 0, 2, 1, 2.0, 1.0,
                GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        optBCon = new GridBagConstraints(4, 1, 2, 1, 2.0, 1.0,
                GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        idLCon = new GridBagConstraints(3, 3, 1, 1, 0.5, 1.0,
                GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        idFCon = new GridBagConstraints(4, 3, 2, 1, 2.0, 1.0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        optACon = new GridBagConstraints(4, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        optVCon = new GridBagConstraints(5, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        spacerC = new GridBagConstraints(3, 0, 1, 1, 0.5, 1.0,
                GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL,
                allIns, 3, 3);
        fieldBox.add(new JLabel("  "), spacerC);

        optSelect = new ButtonGroup();
        optForward = new JRadioButton("Digits Forward");
        optForward.setSelected(true);
        optForward.setName("optForward");
        optForward.setActionCommand("optForward");
        optForward.addActionListener(listener);
        optBackward = new JRadioButton("Digits Backward");
        optBackward.setName("optBackward");
        optBackward.setActionCommand("optBackward");
        optBackward.addActionListener(listener);
        optSelect.add(optForward);
        optSelect.add(optBackward);
        fieldBox.add(optForward, optFCon);
        fieldBox.add(optBackward, optBCon);

        audioBox = new JCheckBox("Auditory");
        audioBox.setSelected(true);
        audioBox.setName("audioBox");
        audioBox.setActionCommand("optAudio");
        audioBox.setEnabled(ControlVariables.customMode);
        audioBox.addActionListener(listener);
        visualBox = new JCheckBox("Visual");
        visualBox.setSelected(true);
        visualBox.setName("visualoBox");
        visualBox.setActionCommand("optVisual");
        visualBox.setEnabled(ControlVariables.customMode);
        visualBox.addActionListener(listener);
        fieldBox.add(audioBox, optACon);
        fieldBox.add(visualBox, optVCon);

        goButton = new JButton("Start Test");
        goButton.setName("goButton");
        goButton.setActionCommand("startTest");
        buttonBox.add(goButton, 0);
        goButton.addActionListener(listener);

        minLabel = new JLabel(" Min Length: ");
        minField = new JTextField("" + ControlVariables.minLength);
        minField.setName("setMin");
        minField.setActionCommand("setMin");
        minField.setHorizontalAlignment(JTextField.TRAILING);
        fieldBox.add(minLabel, minLCon);
        fieldBox.add(minField, minFCon);
        minLabel.setLabelFor(minField);
        minField.setEditable(ControlVariables.customMode);
        minField.setEnabled(ControlVariables.customMode);
        minField.addActionListener(listener);

        maxLabel = new JLabel(" Max Length: ");
        maxField = new JTextField("" + ControlVariables.maxLength);
        maxField.setName("setMax");
        maxField.setActionCommand("setMax");
        maxField.setHorizontalAlignment(JTextField.TRAILING);
        fieldBox.add(new JLabel(""));
        fieldBox.add(maxLabel, maxLCon);
        fieldBox.add(maxField, maxFCon);
        maxLabel.setLabelFor(maxField);
        maxField.setEditable(ControlVariables.customMode);
        maxField.setEnabled(ControlVariables.customMode);
        maxField.addActionListener(listener);

        setLabel = new JLabel(" Set Size: ");
        setField = new JTextField("" + ControlVariables.setSize);
        setField.setName("setSize");
        setField.setActionCommand("setSize");
        setField.setHorizontalAlignment(JTextField.TRAILING);
        fieldBox.add(setLabel, setLCon);
        fieldBox.add(setField, setFCon);
        setLabel.setLabelFor(setField);
        setField.setEditable(ControlVariables.customMode);
        setField.setEnabled(ControlVariables.customMode);
        setField.addActionListener(listener);

        idLabel = new JLabel("Test ID: ");
        idField = new JTextField("");
        idField.setName("idField");
        idField.setHorizontalAlignment(JTextField.LEADING);
        fieldBox.add(idLabel, idLCon);
        fieldBox.add(idField, idFCon);
        idLabel.setLabelFor(idField);

        // Initialize time estimates
        int num = (((ControlVariables.maxLength *
                (ControlVariables.maxLength + 1)) / 2) -
                (((ControlVariables.minLength *
                (ControlVariables.minLength - 1)) / 2)));
        int time = num;
        time *= ControlVariables.setSize;
        time *= (ControlVariables.stimulusTime + ControlVariables.pauseTime);
        time += (((ControlVariables.maxLength - ControlVariables.minLength + 1)  
                * ControlVariables.setSize) * ControlVariables.initDelay);
        time /= 1000; // Convert from miliseconds to seconds
        stimulusTime = new JLabel("   Maximum time for stimuli: " + (time / 60)
                +" minutes, " + (time % 60) + " Seconds");
        time += (num * ControlVariables.setSize);
        estimateTime = new JLabel("    Estimated tesing time: " + (time / 60)
                +" minutes, " + (time % 60) + " Seconds");        
        if(ControlVariables.keyForNext) {
            stimulusTime.setText("");
            estimateTime.setText("");
        }
        outputBox.add(stimulusTime);
        outputBox.add(estimateTime);

        setVisible(true);
    }    
}
