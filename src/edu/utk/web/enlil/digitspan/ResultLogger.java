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
 *
 * 
 * This class will maintain a running log of all test results.  Its use be on
 * by default but it will be possible to turn it off.  These logs will most 
 * likely by human readable and will serve to keep a record in case human error
 * prevents another record of the result from being made, or in case they are
 * later desired as an afterthought.
 * 
 * Each record will contain a unique ID of type long along with a user specified
 * ID (optional), a date of testing, and the score results. Actual stimuli and 
 * responses will (probably) not be recorded.  (This is a work in progress and
 * this description may change or be invalidated.)
 *
 *
 * @author Jared Blackburn
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


class ResultLogger extends Thread {

    private static int logID = 0;
    private static BufferedWriter logWriter;
    
    private ResultRecord results;


    public ResultLogger(String recordID, String data, boolean digitsForward) {
        results = new ResultRecord(recordID, data, digitsForward);
    }


    static void setLogID(int id) {
        logID = id;
    }


    static boolean openLog(File logFile) {
        int newID = 0;
        String read;
        if(logFile.exists() &&
                (!logFile.isFile() || !logFile.canWrite() || !logFile.canRead()))
            return false;
        if(logFile.exists()) try {
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            do {
                read = reader.readLine();
                if(read == null) break;
                if(read.length() >= 8) {
                    if(read.substring(0, 8).contains("Log ID:"))
                        newID = Integer.parseInt(read.substring(7).trim());
                    if(newID >= logID) logID = (newID + 1);
                }
            } while (read != null);
            reader.close();
        } catch (Exception ex) {
            Logger.getLogger(ResultLogger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        setStreamOut(logFile);
        return true;
    }


    static void setStreamOut(File logFile) {
        try {
            logWriter = new BufferedWriter(new FileWriter(logFile, true));
        } catch (IOException ex) {
            Logger.getLogger(ResultLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    static boolean isConnected() {
        return (logWriter != null);
    }


    static boolean isOff() {
        return (logWriter == null);
    }


    static void disconnect() {
        if(logWriter != null) {
            try {
                logWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(ResultLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public boolean writeText() {
        try {
            logWriter.write(" \r\nLog ID: " + logID);
            logWriter.write(results.toString());
            logWriter.write("----------------------------------------------"
                    + "--------------------------------------------------\r\n ");
            logWriter.flush();
        } catch (IOException x) {}
        return true;
    }
    
    

    @Override
    public void run() {
        writeText();
        logID++;
        return;
    }
}
