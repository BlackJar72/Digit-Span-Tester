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
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


class FileExporter extends Thread {

    private MainWindow mainWindow;
    private RandomSequencer generator;

    static final int SAVE_RESULTS = 0;
    static final int EXPORT_STIMULI = 1;
    static final int EXPORT_TESTS = 2;

    BufferedWriter exportWriter;
    ObjectOutputStream exportOutput;
    ResultRecord results;
    JFileChooser fileDialog;
    
    private int mode;
    private int numToDo;
    private int length;
    private int algorithm;
    private int minLength;
    private int maxLength;
    private int setSize;
    private int[] previous = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};



    public FileExporter(String recordID, String data, 
            MainWindow mainWindow, boolean digitsForward) {
        this.mainWindow = mainWindow;
        results = new ResultRecord(recordID, data, digitsForward);
        fileDialog = new JFileChooser();
        mode = SAVE_RESULTS;
    }


    public FileExporter(int mode, MainWindow mainWindow) throws IOException {
        if(mode == 0) throw new IOException("Wrong constructor: "
                + "Must supply record ID and data for "
                + "save results mode (mode 0).");
        if(mode < 1 || mode > 2 ) throw new IOException("Incorrect output mode!");
        this.mode = mode;
        this.mainWindow = mainWindow;
        numToDo = ControlVariables.numToDo;
        length = ControlVariables.length;
        algorithm = ControlVariables.algorithm;
        minLength = ControlVariables.minLength;
        maxLength = ControlVariables.maxLength;
        setSize = ControlVariables.setSize;
        fileDialog = new JFileChooser();
        generator = new RandomSequencer(); // Each thread needs its own!
    }


    @SuppressWarnings("finally")
	public boolean writeScore() {
        fileDialog.setDialogTitle("Name or select file to write to: ");
        fileDialog.showOpenDialog(mainWindow);
        File file = fileDialog.getSelectedFile();
        if(file == null) return false;
        try {
            exportWriter = new BufferedWriter(new FileWriter(file));
            exportWriter.write(results.toString());
            exportWriter.flush();
        } catch (IOException x) {
            if(exportWriter != null) try {
                exportWriter.close();
            } catch (IOException ex) {}
            return false;
        } finally {
            if(exportWriter != null) try {
                exportWriter.close();
            } catch (final IOException x) {}
            return true;
        }
    }


    private void writeSequences() {
        fileDialog.setDialogTitle("Name or select file to write to: ");
        fileDialog.showOpenDialog(mainWindow);
        File file = fileDialog.getSelectedFile();
        if(file == null) return;
        try {
            exportWriter = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < numToDo; i++) {
                exportWriter.write(generator.generate(length,
                        algorithm, previous) + "\r\n");
                previous = generator.toInts();
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE,
                    null, ex);
            System.err.println("Could not write to or create file \n"
                    + ex.toString()); 
        } finally {
            try {
                if(exportWriter != null) {
                    exportWriter.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE,
                        null, ex);
                System.err.println(ex.toString());
            }
        }
    }


    private void writeTests() {
        fileDialog.setDialogTitle("Name or select file to write to: ");
        fileDialog.showOpenDialog(mainWindow);
        GeneratedSequences test;
        File file = fileDialog.getSelectedFile();
        if(file == null) return;
        try {
            exportWriter = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < numToDo; i++) {
                test = new GeneratedSequences(minLength, maxLength, setSize,
                        algorithm, generator);
                exportWriter.write(test.toString() + "\r\n"
                        + "------------------------------------------------" +
                        "\r\n\r\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE,
                    null, ex);
            System.err.println("Could not write to or create file \n"
                    + ex.toString());
        } finally {
            try {
                if(exportWriter != null) {
                    exportWriter.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE,
                        null, ex);
                System.err.println(ex.toString());
            }
        }
    }


    @Override
    public void run() {
        switch(mode) {
            case SAVE_RESULTS:
                writeScore();
                return;
            case EXPORT_STIMULI:
                writeSequences();
                return;
            case EXPORT_TESTS:
                writeTests();
                return;
            default: return;
        }
    }
}
