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


class ResultsPanel extends JPanel {

    private DigitSpanTester mainClass;
    private ControlListener listener;

    private JScrollPane resultScroll;
    private BorderLayout mainLayout;
    private JPanel contentPane;
    private JPanel buttonPane;
    
    JButton saveButton;
    JButton printButton;

    //JTable table;  // Maybe in a later version...?
    JEditorPane resultText;



    public ResultsPanel(ControlListener controls) {
        listener = controls;
        setName("testPanel");
        setLayout(new BorderLayout());

        resultText = new JEditorPane();
        resultText.setContentType("text/html");
        resultText.setEditable(false);
        resultText.setText("");
        resultScroll = new JScrollPane(resultText);
        resultText.setCaretPosition(0);
        add(resultScroll, BorderLayout.CENTER);

        buttonPane = new JPanel();
        saveButton = new JButton("Save Results");
        saveButton.setName("saveButton");
        saveButton.setActionCommand("saveResults");
        saveButton.addActionListener(listener);
        printButton = new JButton("Print Results");
        printButton.setName("printButton");
        printButton.setActionCommand("printResults");
        printButton.addActionListener(listener);
        buttonPane.add(saveButton);
        buttonPane.add(printButton);
        add(buttonPane, BorderLayout.SOUTH);

        setVisible(true);
    }
}
