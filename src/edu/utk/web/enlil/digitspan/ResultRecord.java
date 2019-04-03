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

import java.io.Serializable;
import java.util.Date;



class ResultRecord implements Serializable {


	private static final long serialVersionUID = -30403692651927556L;
	String data;
    String recordID;
    Date when;
    boolean digitsForward;


    public ResultRecord(String recordID, String data, boolean digitsForward) {
        this.recordID = recordID;
        this.data = data;
        this.digitsForward = digitsForward;
        when = new Date(System.currentTimeMillis());
    }


    private String outputType() {
        if(digitsForward) {
            return "Digits Forward";
        } else {
            return "Digits Backward";
        }
    }


    @Override
    public String toString() {
        return ("\r\nTest ID: " + recordID + "\r\nTest Type: " + outputType() +
                "\r\nTime of Testing: " + when + " \r\n\r\nResults: \r\n" +
                data + "\r\n\r\n");
    }


    public String toHTML() {
        return ("<BR>\r\n<B>Test ID:</B> "
                    + recordID +  "<BR>\r\n<B>Test Type:</B> " + outputType()
                    + "<BR>\r\n<B>Time of Testing:</B> " + when
                    + data + "\r\n<BR>\r\n");
    }
}