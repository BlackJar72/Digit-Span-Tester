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

final class UserSequences extends AbstractSequences {

    private int l, n;
    private int[] correct, digitsRight;
    private double[] percentCorrect, percentDigitsRight;
    private int max100 = 0;
    private double[] literalSpan = { 0.0, 0.0 };
    boolean digitsForward = true; // Default
    private boolean maxedOut = false;
    private int longest;

    private UserSequences() {}


    public UserSequences(GeneratedSequences match) {
        int numSets = match.getNumSets();
        int setSize = match.getSetSize();
        data = new int[numSets][][];
        for(i = 0; i < numSets; i++) {
            data[i] = new int[setSize][];
            for(k = 0; k < setSize; k++) {
                data[i][k] = new int[match.getLength(i)];
                for(j = 0; j < data[i][k].length; j++) data[i][k][j] = -1;
            }
        }
    }


    void imput(int datum, int vi, int vj, int vk) {
        data[vi][vj][vk] = datum;
    }


    boolean searchAhead(GeneratedSequences match) {
        for(n = 1; k + n + 1 < match.data[i][j].length; n++) {
            if(match.data[i][j][k + n + 1] > match.data[i][j].length)
                return false;
            if(data[i][j][l] == match.data[i][j][k + n] &&
                    data[i][j][l + 1] == match.data[i][j][k + n + 1]) {
                if(k + n + 2 >= match.data.length) return true;
                if(data[i][j][l + 2] == match.data[i][j][k + n + 2]) return true;
            }
        }
        return false;
    }


    boolean searchBehind(GeneratedSequences match) {
        for(n = 1; k - n - 1 > 0; n++) {
            if(match.data[i][j][k - n - 1] < 0) return false;
            if(data[i][j][l] == match.data[i][j][k - n] &&
                    data[i][j][l + 1] == match.data[i][j][k - n - 1]) {
                if(k - n - 2 <= 0) return true;
                if(data[i][j][l + 2] == match.data[i][j][k - n - 2]) return true;
            }
        }
        return false;
    }


    void scoreForward(GeneratedSequences match) {
        boolean sequenceRight = true;
        correct = new int[data.length];
        digitsRight = new int[data.length];
        percentCorrect = new double[data.length];
        percentDigitsRight = new double[data.length];
        for(i = 0; i < data.length; i++) {
            correct[i] = digitsRight[i] = 0;
            for(j = 0; j < match.data[i].length; j++) {
                sequenceRight = true;
                for(k = 0, l = 0; k < match.data[i][j].length; k++, l++) {
                    if(data[i][j][l] == match.data[i][j][k]) {
                        digitsRight[i]++;
                    } else {
                        sequenceRight = false;
                        if(searchAhead(match)) {
                            digitsRight[i]++;
                            k += n;
                        }
                    }                    
                }
                if(sequenceRight) {
                    correct[i]++;
                }
            }
            percentCorrect[i] = ((double) correct[i]) / ((double) data[i].length);
            percentDigitsRight[i] = ((double) digitsRight[i]) /
                    ((double) (data[i].length * data[i][0].length));
            if(correct[i] == data[i].length) max100 = i + ControlVariables.minLength;
        }
        if(max100 == (data.length + ControlVariables.minLength - 1))
            max100 = 100; // Represents ceiling
    }


    void scoreBackward(GeneratedSequences match) {
        boolean sequenceRight = true;
        correct = new int[data.length];
        digitsRight = new int[data.length];
        percentCorrect = new double[data.length];
        percentDigitsRight = new double[data.length];
        for(i = 0; i < data.length; i++) {
            correct[i] = digitsRight[i] = 0;
            for(j = 0; j < match.data[i].length; j++) {
                sequenceRight = true;
                for(k = match.data[i][j].length - 1, l = 0;
                k >= 0  && l >= 0 ; k--, l++) {
                    if(data[i][j][l] == match.data[i][j][k]) {
                        digitsRight[i]++;
                    } else {
                        sequenceRight = false;
                        if(searchBehind(match)) {
                            digitsRight[i]++;
                            k -= n;
                        }
                    }
                }
                if(sequenceRight) {
                    correct[i]++;
                }
            }
            percentCorrect[i] = ((double) correct[i]) / ((double) data[i].length);
            percentDigitsRight[i] = ((double) digitsRight[i]) /
                    ((double) (data[i].length * data[i][0].length));
            if(correct[i] == data[i].length) max100 = i + ControlVariables.minLength;
        }
        if(max100 == (data.length + ControlVariables.minLength - 1))
            max100 = 100; // Represents ceiling
    }


    void score(GeneratedSequences match, boolean maxedOut, int longest) {
        this.maxedOut = maxedOut;
        if(longest > data.length) longest = data.length; // Shouldn't actually happen!
        this.longest = longest;
        max100 = 0; // Represents floor
        literalSpan[1] = literalSpan[0] = 0.0; // Represents floor
        if(ControlVariables.digitsForward) scoreForward(match);
        else scoreBackward(match);
        calculateSpanUp();
        calculateSpanDown();
    }


    void calculateSpanDown() {
        for(i = data.length - 1; i >= 0; i--) {
            if(percentCorrect[i] == 0.5) {
                literalSpan[0] = (double) (i + ControlVariables.minLength);
                break;
            } else if(percentCorrect[i] > 0.5) {
                if(i >= data.length - 1) {
                    literalSpan[0] = 100.0; // Represents ceiling
                    break;
                } else {
                    literalSpan[0] = ((0.5 - percentCorrect[i]) /
                            (percentCorrect[i+1] - percentCorrect[i]))
                            + ((double) (i + ControlVariables.minLength));
                    break;
                }
            } else if(i == 0) literalSpan[0] = 0.0; // Represents floor
        }
    }


    void calculateSpanUp() {
        for(i = 0; i < data.length; i++) {
            if(percentCorrect[i] == 0.5) {
                literalSpan[1] = (double) (i + ControlVariables.minLength);
                break;
            } else if(percentCorrect[i] < 0.5) {
                if(i == 0) {
                    literalSpan[1] = 0.0; // Floor
                    break;
                } else {
                    literalSpan[1] = ((0.5 - percentCorrect[i-1]) /
                            (percentCorrect[i] - percentCorrect[i-1]))
                            + ((double) (i + ControlVariables.minLength - 1));
                    break;
                }
            } else if(i >= data.length - 1) literalSpan[1] = 100.0; // Ceiling
        }
    }


    String scoreToText() {
        StringBuilder output = new StringBuilder("");
        for(i = 0; i < longest; i++) {
            output.append("For length ");
            output.append(Integer.toString(data[i][0].length));
            output.append(": ");
            output.append(Integer.toString(correct[i]));
            output.append(" sequences correct (");
            output.append(String.format("%1$.0f", (percentCorrect[i] * 100)));
            output.append("%); ");
            output.append(Integer.toString(digitsRight[i]));
            output.append(" digits correct (");
            output.append(String.format("%1$.0f", (percentDigitsRight[i] * 100)));
            output.append("%)\r\n");
        }
        if(maxedOut) {
            output.append("Task ended at ");
            output.append(Integer.toString(longest + 1));
            output.append(" due to reaching apparent limit of ability.\r\n");
        } else {
            output.append("Planned task fully completed.\r\n");
        }
        output.append("\r\n");
        output.append("Longest with 100% accuracy: ");
        if(max100 == 0) {
            output.append("Never achieved (less than floor of ");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append(")\r\n");
        } else if(max100 == 100) {
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append(" (ceiling; could be higher)\r\n");
        } else {
            output.append(Integer.toString(max100));
            output.append("\r\n");
        }
        output.append("Lowest 50% Crossing: ");
        if(literalSpan[1] == 0.0) {
            output.append("Less than ");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append("(floor, true score undetermined)\r\n");
        } else if(literalSpan[1] == 100.0) {
            output.append("At least ");
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append(" (ceiling, true score undetermined)\r\n");
        } else {
            output.append(String.format("%1$.1f", literalSpan[1]));
            output.append("\r\n");
        }
        output.append("Socre (Highest 50% Crossing): ");
        if(literalSpan[0] == 0.0) {
            output.append("Less than ");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append("(floor, true score undetermined)\r\n");
        } else if(literalSpan[0] == 100.0) {
            output.append("At least ");
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append(" (ceiling, true score undetermined)\r\n");
        } else {
            output.append(String.format("%1$.1f", literalSpan[0]));
            output.append("\r\n");
        }
        output.append("\r\n");
        return output.toString();
    }


    String scoreToHTML() {
        StringBuilder output = new StringBuilder("<HR>\r\n");
        for(i = 0; i < longest; i++) {
            output.append("<B>For length ");
            output.append(Integer.toString(data[i][0].length));
            output.append(":</B> <UL><LI>");
            output.append(Integer.toString(correct[i]));
            output.append(" sequences correct (<B>");
            output.append(String.format("%1$.0f", (percentCorrect[i] * 100)));
            output.append("%</B>);</LI> <LI>");
            output.append(Integer.toString(digitsRight[i]));
            output.append(" digits correct (<B>");
            output.append(String.format("%1$.0f", (percentDigitsRight[i] * 100)));
            output.append("%</B>)</LI></UL>\r\n");
        }
        if(maxedOut) {
            output.append("Task ended at ");
            output.append(Integer.toString(longest + 1));
            output.append(" due to reaching apparent limit of ability.<BR>\r\n");
        } else {
            output.append("Planned task fully completed.<BR>\r\n");
        }
        output.append("<HR><BR>\r\n");
        output.append("Longest with 100% accuracy: ");
        if(max100 == 0) {
            output.append("<B>Never achieved</B> (less than <B>floor</B> of <B>");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append("</B>)<BR>\r\n");
        } else if(max100 == 100) {
            output.append("<B>");
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append("</B> (<B>ceiling</B>)<BR>\r\n");
        } else {
            output.append("<B>");
            output.append(Integer.toString(max100));
            output.append("</B><BR>\r\n");
        }
        output.append("Lowest 50% Crossing: ");
        if(literalSpan[1] == 0.0) {
            output.append("Less than <B>");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append("</B> (<B>floor</B>)<BR>\r\n");
        } else if(literalSpan[1] == 100.0) {
            output.append("At least <B>");
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append("</B> (<B>ceiling</B>)<BR>\r\n");
        } else {
            output.append("<B>");
            output.append(String.format("%1$.1f", literalSpan[1]));
            output.append("</B><BR>\r\n");
        }
        output.append("<B>Score</B> (Highest 50% Crossing): ");
        if(literalSpan[0] == 0.0) {
            output.append("Less than <B>");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append("</B> (<B>floor</B>)<BR>\r\n");
        } else if(literalSpan[0] == 100.0) {
            output.append("At least <B>");
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append("</B> (<B>ceiling</B>)<BR>\r\n");
        } else {
            output.append("<B>");
            output.append(String.format("%1$.1f", literalSpan[0]));
            output.append("</B><BR>\r\n");
        }
        output.append("\r\n");
        return output.toString();
    }


    String scoreToPrintable() {
        StringBuilder output = new StringBuilder("<P>\r\n<HR>\r\n");
        for(i = 0; i < longest; i++) {
            output.append("<B>For length ");
            output.append(Integer.toString(data[i][0].length));
            output.append(":</B> ");
            output.append(Integer.toString(correct[i]));
            output.append(" sequences correct (<B>");
            output.append(String.format("%1$.0f", (percentCorrect[i] * 100)));
            output.append("%</B>); ");
            output.append(Integer.toString(digitsRight[i]));
            output.append(" digits correct (<B>");
            output.append(String.format("%1$.0f", (percentDigitsRight[i] * 100)));
            output.append("%</B>)<BR>\r\n");
        }
        if(maxedOut) {
            output.append("Task ended at ");
            output.append(Integer.toString(longest + 1));
            output.append(" due to reaching apparent limit of ability.<BR>\r\n");
        } else {
            output.append("Planned task fully completed.<BR>\r\n");
        }
        output.append("<HR><BR>\r\n");
        output.append("Longest with 100% accuracy: ");
        if(max100 == 0) {
            output.append("<B>Never achieved</B> (less than <B>floor</B> of <B>");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append("</B>)<BR>\r\n");
        } else if(max100 == 100) {
            output.append("<B>");
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append("</B> (<B>ceiling</B>)<BR>\r\n");
        } else {
            output.append("<B>");
            output.append(Integer.toString(max100));
            output.append("</B><BR>\r\n");
        }
        output.append("Lowest 50% Crossing: ");
        if(literalSpan[1] == 0.0) {
            output.append("Less than <B>");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append("</B> (<B>floor</B>)<BR>\r\n");
        } else if(literalSpan[1] == 100.0) {
            output.append("At least <B>");
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append("</B> (<B>ceiling</B>)<BR>\r\n");
        } else {
            output.append("<B>");
            output.append(String.format("%1$.1f", literalSpan[1]));
            output.append("</B><BR>\r\n");
        }
        output.append("<B>Score</B> (Highest 50% Crossing): ");
        if(literalSpan[0] == 0.0) {
            output.append("Less than <B>");
            output.append(Integer.toString(ControlVariables.minLength));
            output.append("</B> (<B>floor</B>)<BR>\r\n");
        } else if(literalSpan[0] == 100.0) {
            output.append("At least <B>");
            output.append(Integer.toString(ControlVariables.maxLength));
            output.append("</B> (<B>ceiling</B>)<BR>\r\n");
        } else {
            output.append("<B>");
            output.append(String.format("%1$.1f", literalSpan[0]));
            output.append("</B><BR>\r\n");
        }
        output.append("\r\n</P>\r\n");
        return output.toString();
    }

    
    /**
     * This checks to see if a the sequence compared matches (is correct),
     * and is intended to allow ongoing checking of current accuracy of the
     * response.  The primary purpose is to allow test to be ended early if 
     * the taker has reached an apparent ability limit (which requires an 
     * ongoing count of sequences missed in a row).
     * 
     * @param match the stimulus variable to compare to
     * @param set the sequence length to compare to
     * @param row the sequence in set to compare to
     * @return 
     */
    boolean checkOne(GeneratedSequences match, int set, int row) {
        boolean seqCorrect = true;
        if(ControlVariables.digitsForward) {
            for(k = 0; k < data[set][row].length 
                    && k < match.data[set][row].length; k++) {
                if(match.data[set][row][k] != data[set][row][k]) {
                    seqCorrect = false;
                    break;
                }
            }
        } else {
            for(k = data[set][row].length - 1, l = 0;
                   k >= 0 && l < data[set][row].length; k--, l++) {
                if(match.data[set][row][k] != data[set][row][l]) {
                    seqCorrect = false;
                    break;
                }
            }
        }
        return seqCorrect; 
    }
    
    
    
}
