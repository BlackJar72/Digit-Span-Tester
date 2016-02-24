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
import javax.swing.*;
import java.awt.*;


class GenerationPanel extends JPanel {

     DigitSpanTester mainClass;
     ControlListener listener;
     RandomSequencer generator;

     BorderLayout mainLayout;
     GridLayout rows;
     GridLayout fields;
     BoxLayout buttons;
     Font outFont;
     Font bigFont;
     JPanel buttonBox;
     JPanel fieldBox;
     JPanel outputBox;

     JButton makeOne;
     JButton makePrint;
     JButton makeExport;

     JLabel lenLabel;
     JTextField lengthField;
     JLabel numLabel;
     JTextField numField;
     JLabel maxLabel;
     JTextField maxField;
     JLabel setLabel;
     JTextField setField;

     ButtonGroup optSelect;
     JRadioButton optSequences;
     JRadioButton optTests;

     JLabel output;
    
     JFileChooser fileDialog;

     boolean testsMode = false;



    public GenerationPanel(ControlListener listener,
            RandomSequencer generator) {
        this.listener = listener;
        this.generator = generator;
        setName("generatePanel");

        mainLayout = new BorderLayout();
        rows = new GridLayout(3, 1);
        fields = new GridLayout(0, 5);
        outFont = new Font("Times New Roman", 1, 14);
        bigFont = new Font("Times New Roman", 1, 30);
        fieldBox = new JPanel(fields);
        outputBox = new JPanel(rows);
        buttonBox = new JPanel();
        buttons = new BoxLayout(buttonBox, BoxLayout.X_AXIS);
        setLayout(mainLayout);
        add(fieldBox, BorderLayout.NORTH);
        add(outputBox, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.SOUTH);

        makeOne = new JButton("Show One");
        makeOne.setName("makeOne");
        makeOne.setActionCommand("makeOne");
        buttonBox.add(makeOne, 0);
        makeOne.addActionListener(listener);

        makeExport = new JButton("Batch to File");
        makeExport.setName("makeExport");
        makeExport.setActionCommand("saveSequences");
        buttonBox.add(makeExport, 1);
        makeExport.addActionListener(listener);

        makePrint = new JButton("Print Batch");
        makePrint.setName("makePrint");
        makePrint.setActionCommand("printSequences");
        buttonBox.add(makePrint, 2);
        makePrint.addActionListener(listener);

        output = new JLabel("");
        output.setFont(bigFont);
        outputBox.add(new JLabel(""), 0);
        outputBox.add(output, 1);
        outputBox.add(new JLabel(""), 2);

        optSelect = new ButtonGroup();
        optSequences = new JRadioButton("Stimuli");
        optSequences.setSelected(true);
        optSequences.setName("optSequences");
        optSequences.setActionCommand("optSequences");
        optSequences.addActionListener(listener);
        optTests = new JRadioButton("Tests");
        optTests.setName("optTests");
        optTests.setActionCommand("optTests");
        optTests.addActionListener(listener);
        optSelect.add(optSequences);
        optSelect.add(optTests);
        fieldBox.add(new JLabel(""));
        fieldBox.add(new JLabel(""));
        fieldBox.add(new JLabel(""));
        fieldBox.add(optSequences);
        fieldBox.add(optTests);

        lenLabel = new JLabel(" Length: ");
        lengthField = new JTextField("" + ControlVariables.length);
        lengthField.setEditable(ControlVariables.customMode);
        lengthField.setEnabled(ControlVariables.customMode);
        lengthField.setName("setLength");
        lengthField.setActionCommand("setLength"); // Changes to setMin
        lengthField.setHorizontalAlignment(JTextField.TRAILING);
        fieldBox.add(lenLabel);
        fieldBox.add(lengthField);
        lenLabel.setLabelFor(lengthField);
        lengthField.addActionListener(listener);

        numLabel = new JLabel(" Number: ");
        numField = new JTextField("" + ControlVariables.numToDo);
        numField.setName("setNumber");
        numField.setName("setNumber");
        numField.setHorizontalAlignment(JTextField.TRAILING);
        fieldBox.add(new JLabel(""));
        fieldBox.add(numLabel);
        fieldBox.add(numField);
        numLabel.setLabelFor(numField);
        numField.addActionListener(listener);

        maxLabel = new JLabel("Max Length: ");
        maxLabel.setVisible(false); // Must be set to visible
        maxField = new JTextField("" + ControlVariables.maxLength);
        maxField.setVisible(false); // Must be set to visible
        maxField.setEditable(ControlVariables.customMode);
        maxField.setEnabled(ControlVariables.customMode);
        maxField.setName("setMax");
        maxField.setActionCommand("setMax");
        maxField.setHorizontalAlignment(JTextField.TRAILING);
        fieldBox.add(maxLabel);
        fieldBox.add(maxField);
        maxLabel.setLabelFor(maxField);
        maxField.addActionListener(listener);

        setLabel = new JLabel(" Set Size: ");
        setLabel.setVisible(false); // Must be set to visible
        setField = new JTextField("" + ControlVariables.setSize);
        setField.setVisible(false); // Must be set to visible
        setField.setEditable(ControlVariables.customMode);
        setField.setEnabled(ControlVariables.customMode);
        setField.setName("setSize");
        setField.setName("setSize");
        setField.setHorizontalAlignment(JTextField.TRAILING);
        fieldBox.add(new JLabel(""));
        fieldBox.add(setLabel);
        fieldBox.add(setField);
        setLabel.setLabelFor(setField);
        setField.addActionListener(listener);
        
        setVisible(true);
    }


    void setBasicMode() {
        testsMode = false;
        lenLabel.setText(" Length: ");
        lengthField.setName("setLength");
        lengthField.setActionCommand("setLength");
        makeExport.setActionCommand("saveSequences");
        makePrint.setActionCommand("printSequences");
        lengthField.setText(Integer.toString(ControlVariables.length));
        lengthField.setEditable(true);
        lengthField.setEnabled(true);
        maxLabel.setVisible(false);
        maxField.setVisible(false);
        setLabel.setVisible(false);
        setField.setVisible(false);
        makeOne.setEnabled(true);
    }


    void setTestMode() {
        testsMode = true;
        lenLabel.setText("Min Length: ");
        lengthField.setName("setMin");
        lengthField.setActionCommand("setMin");
        makeExport.setActionCommand("saveStimuli");
        makePrint.setActionCommand("printStimuli");
        lengthField.setText(Integer.toString(ControlVariables.minLength));
        lengthField.setEditable(ControlVariables.customMode);
        lengthField.setEnabled(ControlVariables.customMode);
        maxLabel.setVisible(true);
        maxField.setVisible(true);
        setLabel.setVisible(true);
        setField.setVisible(true);
        makeOne.setEnabled(false);
    }


    void generateOne() {
        output.setText(generator.generate(ControlVariables.length,
                ControlVariables.algorithm, ControlVariables.previous));
        ControlVariables.previous = generator.toInts();
    }
}
