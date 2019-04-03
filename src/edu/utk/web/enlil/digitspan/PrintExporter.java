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
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;


// TODO
class PrintExporter extends Thread {

    private RandomSequencer generator;

    static final int PRINT_RESULTS = 0;
    static final int PRINT_STIMULI = 1;
    static final int PRINT_TESTS = 2;

    ResultRecord results;
    JEditorPane document;
    StringBuilder textBuilder;
    String text;

    private int mode;
    private int numToDo;
    private int length;
    private int algorithm;
    private int minLength;
    private int maxLength;
    private int setSize;
    private int[] previous = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};



    public PrintExporter(String recordID, String data, 
            MainWindow mainWindow, boolean digitsForward) {
        results = new ResultRecord(recordID, data, digitsForward);
        document = new JEditorPane();
        mode = PRINT_RESULTS;
    }


    public PrintExporter(int mode, MainWindow mainWindow) throws IOException {
        if(mode == 0) throw new IOException("Wrong constructor: "
                + "Must supply record ID and data for "
                + "save results mode (mode 0).");
        if(mode < 1 || mode > 2 ) throw new IOException("Incorrect output mode!");
        this.mode = mode;
        numToDo = ControlVariables.numToDo;
        length = ControlVariables.length;
        algorithm = ControlVariables.algorithm;
        minLength = ControlVariables.minLength;
        maxLength = ControlVariables.maxLength;
        setSize = ControlVariables.setSize;
        document = new JEditorPane();
        generator = new RandomSequencer(); // Each thread needs its own!
    }


    public boolean writeScore() {
        text = "<H2>Digit Span Test Results</H2>" + results.toHTML();
        document.setContentType("text/html");
        document.setText(text);
        try {
            document.print();
        } catch (PrinterException ex) {
            Logger.getLogger(PrintExporter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }


    private boolean writeSequences() {
        textBuilder = new StringBuilder("");
        document.setContentType("text/plain");
        for(int i = 0; i < numToDo; i++) {
            textBuilder.append(generator.generate(length,
                    algorithm, previous) + "\r\n");
            previous = generator.toInts();
            }
        document.setText(textBuilder.toString());
        try {
            document.print();
        } catch (PrinterException ex) {
            Logger.getLogger(PrintExporter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }


    private boolean writeTests() {
        GeneratedSequences test;
        textBuilder = new StringBuilder("");
        document.setContentType("text/plain");
        for(int i = 0; i < numToDo; i++) {
            test = new GeneratedSequences(minLength, maxLength, setSize,
                        algorithm, generator);
            textBuilder.append(test.toString());
            textBuilder.append("   \r\n");
            }
        document.setText(textBuilder.toString());
        try {
            document.print(null, new MessageFormat("Page {0}"));
        } catch (PrinterException ex) {
            Logger.getLogger(PrintExporter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }


    @Override
    public void run() {
        switch(mode) {
            case PRINT_RESULTS:
                writeScore();
                return;
            case PRINT_STIMULI:
                writeSequences();
                return;
            case PRINT_TESTS:
                writeTests();
                return;
            default: return;
        }
    }
}
