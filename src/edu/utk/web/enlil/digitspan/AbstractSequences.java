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

abstract class AbstractSequences {

    int i, j, k;
    int[][][] data;


    int get(int vi, int vj, int vk) {
        return data[vi][vj][vk];
    }


    int getNumSets() {
        return data.length;
    }


    int getSetSize() {
        return data[0].length;
    }


    int getLength(int index) {
        return data[index][0].length;
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("");
        for(i = 0; i < data.length; i++) {
            for(j = 0; j < data[i].length; j++) {
                for(k = 0; k < data[i][j].length; k++) {
                    output.append(" ").append(Integer.toString(data[i][j][k])).append(" ");
                }
                output.append("\r\n");
            }
            output.append("\r\n");
        }
        output.append("\r\n");
        return output.toString();
    }
}
