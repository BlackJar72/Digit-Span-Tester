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


final class GeneratedSequences extends AbstractSequences {

    public GeneratedSequences(int minLength, int maxLength, int setSize,
                int algorithm, RandomSequencer generator) {
        int numSets = maxLength - minLength + 1;
        data = new int[numSets][][];
        for(i = 0, k = minLength; i < numSets; i++, k++) {
            data[i] = new int[setSize][];
            for(j = 0; j < setSize; j++) {
                data[i][j] = generator.generateInts(k, algorithm, 
                        ControlVariables.previous);
                ControlVariables.previous = data[i][j];
            }
        }
    }
}
