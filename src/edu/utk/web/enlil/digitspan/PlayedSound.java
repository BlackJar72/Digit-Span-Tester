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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;


final class PlayedSound extends Thread {

    private ByteArrayInputStream inStream;
    private AudioInputStream inAudio;
    private Clip sound;


    private PlayedSound() {}


    public PlayedSound(ByteArrayInputStream in, DataLine.Info lineInfo)
            throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        inStream = in;
        inAudio = AudioSystem.getAudioInputStream(inStream);
        sound = (Clip) AudioSystem.getLine(lineInfo);
        start();
    }


    @Override
    public void run() {
        try {
            sound.open(inAudio);
            sound.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PlayedSound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayedSound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
