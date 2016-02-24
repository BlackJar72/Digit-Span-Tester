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

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;


class Sounds /*extends Thread*/ {
    private static final int NUM_SOUNDS = 14;

    private DataLine.Info lineInfo;
    private AudioFormat format;
    private AudioFileFormat fileFormat;
    private AudioFileFormat.Type type;
    private int nextByte;
    private AudioInputStream audioIn;
    private ByteArrayOutputStream arrayOut;
    private ByteArrayInputStream arrayIn;
    private AudioInputStream[] inStreams = new AudioInputStream[NUM_SOUNDS];
    private URL[] soundURL = new URL[NUM_SOUNDS];
    private byte[][] soundArrays = new byte[NUM_SOUNDS][];
    private ByteArrayInputStream[] arrayFiles = new ByteArrayInputStream[NUM_SOUNDS];
    private byte[] transfer;


    private void loadSound(int i)
            throws IOException, UnsupportedAudioFileException {
        audioIn = inStreams[i];
        arrayOut = new ByteArrayOutputStream(); // This will hold the audio data
        nextByte = 0;
        // This will read the audio data (only) into an array, to which
        // a ByteArrayInputStream can be attached.  File headers must be
        // obtained from other variables read from the file.
        do {
            nextByte = audioIn.read();
            if(nextByte != -1) arrayOut.write(nextByte);
        } while(nextByte != -1);
        transfer = arrayOut.toByteArray(); // This holds the audio data
        arrayIn = new ByteArrayInputStream(transfer);
        // Now to re-write the file as an array in memory;
        // this will add the file headers back on, so it will
        // look like the original file, but be read from memory.
        audioIn = new AudioInputStream(
            arrayIn, format, transfer.length);
        arrayOut = new ByteArrayOutputStream(); // This will now hold the full file
        AudioSystem.write(audioIn, type, arrayOut);
        soundArrays[i] = arrayOut.toByteArray(); // This holds a mirror of the file
        arrayFiles[i] = new ByteArrayInputStream(soundArrays[i]);
        inStreams[i] = AudioSystem.getAudioInputStream(arrayFiles[i]);
    }


    public Sounds() {
        soundURL[0]  = Sounds.class.getResource("sounds/0.wav");
        soundURL[1]  = Sounds.class.getResource("sounds/1.wav");
        soundURL[2]  = Sounds.class.getResource("sounds/2.wav");
        soundURL[3]  = Sounds.class.getResource("sounds/3.wav");
        soundURL[4]  = Sounds.class.getResource("sounds/4.wav");
        soundURL[5]  = Sounds.class.getResource("sounds/5.wav");
        soundURL[6]  = Sounds.class.getResource("sounds/6.wav");
        soundURL[7]  = Sounds.class.getResource("sounds/7.wav");
        soundURL[8]  = Sounds.class.getResource("sounds/8.wav");
        soundURL[9]  = Sounds.class.getResource("sounds/9.wav");
        soundURL[10] = Sounds.class.getResource("sounds/tone2.wav");
        soundURL[11] = Sounds.class.getResource("sounds/tone3.wav");
        soundURL[12] = Sounds.class.getResource("sounds/stop.wav");
        soundURL[13] = Sounds.class.getResource("sounds/go.wav");

        for(int i = 0; i < NUM_SOUNDS; i++) {
            try {
                format = AudioSystem.getAudioInputStream(soundURL[i]).getFormat();
                fileFormat = AudioSystem.getAudioFileFormat(soundURL[i]);
                type = fileFormat.getType();
                lineInfo = new DataLine.Info(Clip.class, format);
                inStreams[i] = AudioSystem.getAudioInputStream(soundURL[i]);
                loadSound(i);
            } catch (Exception ex) {
                Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

    void play(int i) {
        try {
            arrayFiles[i].reset();
            new PlayedSound(arrayFiles[i], lineInfo);
        } catch (Exception ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
