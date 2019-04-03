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

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


class HelpPanel extends JTabbedPane implements HyperlinkListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7689454844563641170L;
	private JPanel helpPane;
    private JPanel testingPane;
    private JPanel generatePane;
    private JPanel setupPane;
    private JPanel algPane;
    private JPanel aboutPane;
    private JPanel gplPane;

    private JScrollPane helpScroll;
    private JScrollPane testingScroll;
    private JScrollPane generateScroll;
    private JScrollPane setupScroll;
    private JScrollPane algScroll;
    private JScrollPane aboutScroll;
    private JScrollPane gplScroll;

    private JEditorPane helpText;
    private JEditorPane testingText;
    private JEditorPane generateText;
    private JEditorPane setupText;
    private JEditorPane algText;
    private JEditorPane aboutText;
    private JEditorPane gplText;

    private Desktop desktop;
    private URL currentURL;


// <editor-fold defaultstate="collapsed" desc="algContent">
    static final String algContent = "<HTML><HEAD><TITLE>Basic Help</TITLE><HEAD>"
            + "<BODY BGCOLOR=\"<ffffff\">"
            + "Below are descriptions of the methods of generation and "
            + "types of the sequence each will generate. <STRONG>Alternate "
            + "sequence generation algorithms can only be used with custom "
            + "tests</STRONG>; otherwise Advanced "
            + "Shuffle 8 is always used."
            + "<H2><A NAME=\"top\">Basic Algorithms<BR>(Not recommended)</A></H2>"
            + "These are very simple algorithms that do not try to remove "
            + "easily recognizable patterns.  They are not recommended for general use; "
            + "the more advance primary algorithms should typically be used instead. "
            + "<H3>Random Numbers</H3>"
            + "This will create a sequence based on random numbers, in which each "
            + "numeral from \"0\" to \"9\" has an equal chance of occurring in each "
            + "position.  Numbers may be repeated and there are no rules governing "
            + "what will appear where.  This is equivalent to rolling (ten "
            + "sided) dice for the numbers."
            + "This allows practically unlimited length (technically up to "
            + "4096 digits)."
            + "<H3>Simple Shuffle</H3>"
            + "This will shuffle the digit 0 through 9 and take the first N to create a "
            + "random digit sequence in which no number is repeated.  The shuffles "
            + "are fair (equal chance of any number at any location) and are made "
            + "with removal (no numeral can repeat in the sequence).  There are no "
            + "other rules governing the order beyond the lack of duplicate.  "
            + "Sequence length is limited to ten."
            + "<H2><A NAME=\"top\">Primary Algorithms</A></H2>"
            + "These are best suited for general use.  Besides the limitation "
            + "listed for each, these will "
            + "not put the same digit in the same position in any sequence as in the "
            + "sequence before it and will never begin a new sequence with the final "
            + "digit from the sequence immediately proceeding it.  Except for "
            + "Advance Shuffle 8, these all limit sequence length to ten."
            + "<H3>Advanced Shuffle 1</H3>"
            + "This will shuffle the sequence, producing results similar to "
            + "a simple shuffle, but without any digits in their natural order. "
            + "More specifically, no two numerals in a row may be exactly one more "
            + "or one less than each other.  Thus \"5 6\" or \"9 8\" will never "
            + "occur.  "
            + "Note that restriction is only applied if doing so will not reduce "
            + "the pool of potential next numerals to an empty set.  If no possible "
            + "numerals exist that would follow the limitation, then those "
            + "limitations will not apply, allowing a forbidden sub-sequence at the "
            + "end of a long sequence."
            + "<H3>Advanced Shuffle 2</H3>"
            + "This will shuffle the sequence, producing results similar to "
            + "a simple shuffle.  However, no skip distance may be repeated "
            + "back-to-back.  In other words, if the current numeral changed by "
            + "n from the last numeral, the next numeral differ by a value other "
            + "than n -- thus \"2 4 6\" (+2 twice), \"1 4 7\" (+3 twice), or \"5 3 1\" "
            + "(-2 twice) will not occur.  "
            + "Note that restriction is only applied if doing so will not reduce "
            + "the pool of potential next numerals to an empty set.  If no possible "
            + "numerals exist that would follow the limitation, then those "
            + "limitations will not apply, allowing a forbidden sub-sequence at the "
            + "end of a long sequence."
            + "<H3>Advanced Shuffle 3</H3>"
            + "This will shuffle the sequence, producing results similar to "
            + "a simple shuffle, but with two difference: "
            + "<UL><LI>No skip distance may be repeated "
            + "back-to-back.  In other words, if the current numeral changed by "
            + "n from the last numeral, the next numeral must differ by a value other "
            + "than n -- thus \"2 4 6\" (+2 twice), \"1 4 7\" (+3 twice), or \"5 3 1\" "
            + "(-2 twice) will not occur.</LI> "
            + "<LI>No two numerals in a row may be exactly one more or one less than "
            + "than each other.  Thus \"5 6\" or \"9 8\" will never occur.</LI></UL> "
            + "This is essentially a combination of Advance Shuffle 1 and "
            + "Advance Shuffle 2, "
            + "in terms of added, pattern-eliminating limitations.  This is the default "
            + "algorithm.  "
            + "Note that restrictions are only applied if doing so will not reduce "
            + "the pool of potential next numerals to an empty set.  If no possible "
            + "numerals exist that would follow the limitation, then those "
            + "limitations will not apply, allowing a forbidden sub-sequence at the "
            + "end of a long sequence."
            + "<H3>Advanced Shuffle 8</H3>"
            + "This is similar to Advance Shuffle 3, Advance Shuffle 6, and "
            + "Advance Shuffle 7 except "
            + "that the same numeral may appear an unlimited number of times in the "
            + "same sequence while not allowing it to reappear too soon.  This is to "
            + "create more usable long sequences.  "
            + "Technically, the sequence generated will be a series of random length "
            + "sub-sequences generated as in Advance Algorithm 3.  Each sub-sequence has a "
            + "length between three and seven (inclusive).  "
            + "<P>This allows practically unlimited length (technically up to 4096 digits).  This algorithm tends to produce "
            + "good sequences with low repetition, and is prefferable to the "
            + "supplemental algorithms below.</P>"
            + "<H2>Supplemental Algorithms<BR>(not recommended)</H2>"
            + "These all allow limit duplication, and might be useful with long "
            + "sequences.  However, they often produce unintended patterns.  "
            + "These allow up to 20 digits, except for advanced shuffle 7 (more)."
            + "<P>(Note: Adaptation to a language, such as Chinese, with very "
            + "short names for numbers might need this, but might also be too "
            + "long a test.)</P> "
            + "<H3>Advance Shuffle 4</H3>"
            + "This is the same as Advanced Shuffle 1 except that the same numeral may "
            + "appear up to twice in the same sequence (but never twice in a row)."
            + "<H3>Advanced Shuffle 5</H3>"
            + "This is the same as Advanced Shuffle 2 except that the same numeral may "
            + "appear up to twice in the same sequence (but never twice in a row)."
            + "<H3>Advanced Shuffle 6</H3>"
            + "This is the same as Advanced Shuffle 3 except that the same numeral may "
            + "appear up to twice in the same sequence (but never twice in a row)."
            + "<H3>Advanced Shuffle 7</H3>"
            + "This is the same as Advanced Shuffle 3 except that the same numeral may "
            + "appear an unlimited number of times in the same sequence (but "
            + "never twice in a row).  This allows practically unlimited length "
            + "(technically up to 4096 digits).  This algorithm tends to produce "
            + "highly repetetive sequences -- if you want good long sequences "
            + "it is prefferable to use Advance Algorithm 8."
            + "</BODY></HTML>";
    // </editor-fold>


// <editor-fold defaultstate="collapsed" desc="aboutContent">
    static final String aboutContent = "<HTML><HEAD><TITLE>Basic Help</TITLE><HEAD>"
            + "<BODY BGCOLOR=\"<ffffff\">"
            + "<H2>Digit Span Tester, version 2.1.3</H2>"
            + "<A HREF=\"https://sourceforge.net/projects/digitspantester\">https://sourceforge.net/projects/digitspantester</A>"
            + "<P>The purpose of this program is to perform automated digit span "
            + "tests as on human memory function.  It's intended to reduce practice "
            + "effects and eliminate the need use clinical testing material in research.  "
            + "It may have clinical "
            + "applications, but has not been evaluated for such uses.</P>"
            + "<BR><CENTER><B>Written by Jared Blackburn</B><BR>"
            + "<A HREF=\"http://www.facebook.com/jared.blackburn1\">http://www.facebook.com/jared.blackburn1</A>"
            + "<BR>Copyright &copy; Jared Blackburn, 2011</CENTER><BR>"
            + "</CENTER><HR>"
            + "<P><B>Legal:</B> <EM>No</EM> warranties or guarantees "
            + "of <EM>any kind</EM> are offered by the author, "
            + "neither for this program, nor for the results it produces, nor "
            + "anything else connected with it.  Nothing produced with it is "
            + "guaranteed by the author to be valid, useful, or marketable for any purpose. It is "
            + "free software released as open source under the terms of the GNU "
            + "General Public Lisence; see the Lisence tag for more information."
            + "<P>This program is free software; you can redistribute it and/or "
            + "modify it under the terms of the GNU General Public License "
            + "as published by the Free Software Foundation; either version 2 "
            + "of the License, or (at your option) any later version.</P> "
            + "<P>This program is distributed in the hope that it will be useful, "
            + "but <STRONG><EM>WITHOUT ANY WARRANTY</EM></STRONG>; "
            + "<EM>without even the implied warranty of "
            + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE</EM>.  See the "
            + "GNU General Public License for more details.  No liability is "
            + "accepted on the part of the author for any damages resulting "
            + "from any use, proper or improper, of this program or of "
            + "the results obtained from it.</P> "
            + "<P>The use, installation, or distribution of this software is implicit "
            + "acceptance of all lisencing agreements.  Failiure to read the "
            + "lisence does prevent it from applying.</P>"
            + "<P>You should have received a copy of the GNU General Public License "
            + "along with this program; if not, write to the Free Software "
            + "Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.</P> "
            + "<P>This program was written by Jared Blackburn "
            + "and is considerd property and Copyright &copy; of the same.</P> "
            + "</BODY></HTML>";
// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="helpContent">
    static final String helpContent = "<HTML><HEAD><TITLE>Help</TITLE>"
            + "<HEAD><BODY BGCOLOR=\"<ffffff\"> "
            + "<H2>Use of This Software</H2>"
            + "<P>This software was created to generate and "
            + "administer digit span test as an assessment of working "
            + "memory function.</P>"
            + "<H2>The Main Menu Bar</H2>"
            + "<UL> "
            + "<LI><B>File</B>:<UL>"
            + "<LI><B>Show Results</B>: Show the results for the last test; only "
            + "enabled if at least one test has been given and the last was "
            + "successfully completed.</LI>"
            + "<LI><B>Save Results</B>: Save the results for the last test to a "
            + "file; only enabled if at least one test has been given and the "
            + "last was successfully completed.</LI>"
            + "<LI><B>Print Results</B>: Print the results for the last test; "
            + "only enabled if at least one test has been given and the "
            + "last was successfully completed.</LI>"
            + "<LI><B>Sequences to File</B>: Generates random sequence and saves "
            + "them to a file.  See Help=>Saving/Printing Sequences for more "
            + "information.</LI>"
            + "<LI><B>Sequences to Print</B>: Generates random sequence and prints "
            + "them.  See Help=>Saving/Printing Sequences for more "
            + "information.</LI>"
            + "<LI><B>Tests to File</B>: Generates full testing stimuli and saves "
            + "them to a file.  See Help=>Saving/Printing Sequences for more "
            + "information.</LI>"
            + "<LI><B>Tests to Print</B>: Generates full testing stimuli and prints "
            + "them.  See Help=>Saving/Printing Sequences for more "
            + "information.</LI>"
            + "<LI><B>Exit</B>: Quit the program.</LI></LI></UL>"
            + "<LI><B>Functions</B>: The Primary functions of the program<UL>"
            + "<LI><B>Do Testing</B>: Go to the automatic / computerized test "
            + "administration screen.  (This is also the default screen, visible "
            + "when the program begins.)</LI>"
            + "<LI><B>Generate Random Sequences</B>: Go to the screen from which "
            + "random sequences or full tests (for human administration) can be"
            + "set up and generated.</LI>"
            + "<LI><B>Setup</B>: Setup of specialized options.  Research mode is "
            + "accessed through this aswell.</LI></LI></UL>"
            + "<UL><LI><B>Algorithms</B>: Select the method by which sequences are "
            + "(pseudo-)randomly generated.</LI></LI></UL>"
            + "<LI><B>Help</B>: Help and info; these texts.</LI></UL>"
            + "<H2>Intended Purposes</H2>"
            + "<P>It is hoped that this software can fulfill several "
            + "purposes: <OL> "
            + "<LI>By allowing for new, randomly generated stimuli, instrument "
            + "specific practice effects can be reduced during multiple "
            + "assessments or research trials.</LI>"
            + "<LI>Because of its visible presentation and non-verbal response "
            + "method, it can be readily used with hearing impaired or non-speaking "
            + "people.</LI>"
            + "<LI>In a research setting, it would reduce the use of published "
            + "clinical testing materials in non-clinical settings, with "
            + "non-clinical participants, and their administration by "
            + "non-clincal personel.  In addition, it would reduce repeat "
            + "exposure to such material durring research.</LI>"
            + "<LI>By allowing for responses with minimal head and (with "
            + "practice) eye movement, this could be used as a task during "
            + "a physiological measurement (e.g., QEEG) during relevant "
            + "research.</LI>"
            + "<LI>It allows for both computerized and human presentation of "
            + "continually new, fresh stimuli.</LI>"
            + "</BODY></HTML>";
    // </editor-fold>


// <editor-fold defaultstate="collapsed" desc="testingHelp">
    static final String testingContent = "<HTML><HEAD><TITLE>Testing Help</TITLE><HEAD>"
            + "<BODY BGCOLOR=\"<ffffff\"> "
            + "<H2>Test Setup</H2>"
            + "<P>Each of these options controls how the test will be administered "
            + "and scored.  It is important to hit <B>[enter]</B> or <B>[return]</B> after "
            + "updating numeric values here so that they will be entered;  "
            + "technically, it is not required, as they will be automatically "
            + "updated at when a new testing session begins.  However, the time "
            + "estimates will not be updated until the relevant values are "
            + "updated (either be hitting enter or beginning a test).</P> "
            + "<P>The effect of each of the fields and buttons are as follows:</P>"
            + "<UL> "
            + "<LI><B>Min Length</B>: The minimum sequence length to be used; "
            + "testing will begin at this length.  This must be a number "
            + "between 1 and 10 and less-than-or-equal to max length.  "
            + "This can only be changed in research mode.</LI> "
            + "<LI><B>Max Field</B>: The maximum sequence length to be used; "
            + "testing will end with this length.  This must be a number "
            + "between 1 and 10 and greater-than-or-equal to min length.  "
            + "This can only be changed in research mode.</LI> "
            + "<LI><B>Set Size</B>: The number of sequences of each length. "
            + "After this number of sequences, the length will increment and "
            + "a new set will begin (unless at max length, in which case it "
            + "the test will end.  This can only be changed in research mode.</LI> "
            + "<LI><B>Digits Forward</B>: The test will be scored as digits "
            + "forward, comparing each stimulus and response digits in the "
            + "same (forward) order.</LI> "
            + "<LI><B>Digits Backward</B>: The test will be scored as digits "
            + "backward, comparing each stimulus and response digits in the "
            + "opposite order.</LI> "
            + "<LI><B>Auditory Stimuli</B>: If this box is checked the test "
            + "will include auditory stimuli in the form of a verbal recording "
            + "of the spoken, English names of the numbers. This can only be "
            + "changed in research mode, and even then at either auditory or "
            + "visual stimuli (or both) must be selected; if only one is "
            + "currently selected then it cannot be deselected.</LI> "
            + "<LI><B>Visual Stimuli</B>: If this box is checked the test will "
            + "include visual stimuli in the form of printed Arabic numerals, "
            + "which are displayed for one second each, with one second pauses "
            + "between. This can only be changed in research mode, and even "
            + "then at either auditory or visual stimuli (or both) must be "
            + "selected; if only one is currently selected then it cannot be "
            + "deselected.</LI>"
            + "<LI><B>Test ID</B>: An arbitrary label you may add to indicate "
            + "the test taker and/or testing session.  This will appear in the "
            + "header of printed or saved results.  Things like a trial number "
            + "or patient ID might typically go here.</LI>"
            + "<LI><B>Total time for stimuli</B>: The exact time used by "
            + "stimuli, based on the fact that exactly one is present every "
            + "seconds.</LI> "
            + "<LI><B>Estimated testing time</B>: A rough estimate of testing "
            + "time, allowing for response time and a small amount of padding. "
            + "</LI> "
            + "<LI><B>Start Test</B>: Just what it says, it will start the "
            + "test.</LI> "
            + "</UL> "
            + "<H2>Administration</H2>"
            + "<P>Testing is administered in a separate window.  Numbers will "
            + "appear in the center of the testing window, each being displayed "
            + "for one second, followed by a one second delay before the next. "
            + "A light blue (\"cyan\") bar with the message \"Enter Response\" "
            + "and a text field will appear to cue the taker to begin entering "
            + "his or her response.  As long as the window is selected, the "
            + "responses will be registered (there is no need to click on or "
            + "have a cursor in the test area).  To let the taker know that "
            + "the response was received it will be echoed to (displayed in) "
            + "the test field; the last will not be seen as the bar will disappear "
            + "once the last number is taken (some takers may need to be informed of "
            + "this so they no it is being registered).  It is important that takers "
            + "be instructed to wait until the bar appears and to re-enter the "
            + "first number if they \"jump the gun\" and answer too soon. "
            + "The mouse should not be used during testing.  If the testing "
            + "window is closed, minimized, or loses focus(i.e., anything outside "
            + "it is selected, such as by mouse) the testing window will close and the test will "
            + "be aborted, as leaving this window potentially invalidates the administration. "
            + "Aborted tests will not be scored but must be restarted.</P>"
            + "<H2>Scoring</H2>"
            + "<P>The tests are scored for both completely correct sequences "
            + "and correct digits.  Each of these will be reported for each "
            + "sequence length in the proceeding test, both as a total number "
            + "correct, and as a percentage.</P>"
            + "<P>Scoring is straight forward, comparing number to their matches "
            + "in the original stimuli.  The one exception is the handling of "
            + "probable omissions errors.  When an incorrect digit is correct "
            + "for any of the next three digits, and would then begin either a "
            + "\"run\" of three correct in a row or a final correct two, it is treated as "
            + "a probable omission and comparison is moved ahead to compensate. "
            + "This is so that omitting a single number in an otherwise correct "
            + "sequences doesn't make all the others incorrect simply because "
            + "their position is off by one.</P>"
            + "<P>The final results consist of three values, the maximum "
            + "sequence length at which 100% accuracy for sequences was "
            + "obtained, the lowest point where accuracy for sequences crosses "
            + "below 50%, and the highest point where accuracy for sequences "
            + "crosses below 50%.  The crossing point is determined be "
            + "computing a linear function from the sequence length that drops "
            + "below 50% and the length proceeding it, and finding the actual "
            + "point where the line is at 50%. The highest (i.e., last) point "
            + "where accuracy for sequences drops below 50% is considered the "
            + "tests score.  It thus may produce fractional (rounded to one "
            + "decimal, such as 7.5). The highest / final 50% crossing is "
            + "considered the score. Note that these are all based on the "
            + "percentage of <STRONG>sequences</STRONG> (not digits) to be "
            + "accurately remembered.</P> "
            + "<P>To view the results, it is required that the File=>Show "
            + "Results menu be used, unless \"Show Results After Test\" has "
            + "been selected under the \"Setup / Options\" panel.</P>"
            + "</BODY></HTML>";
    // </editor-fold>


// <editor-fold defaultstate="collapsed" desc="generationHelp">
    static final String generateContent = "<HTML><HEAD><TITLE>Generation Help</TITLE>"
            + "<HEAD><BODY BGCOLOR=\"<ffffff\"> "
            + "<P>There are two ways to do this: from the menu bar or from the "
            + "various pages in the program."
            + "<H2>Generate Random Sequences</H2>"
            + "<P>Each of these options controls how number sequences will be "
            + "generated for output to file or printer.</P> "
            + "<P>It is good idea to hit <B>[enter]</B> or <B>[return]</B> after "
            + "updating numeric values here so that they will be entered, though "
            + "technically, it is not required, as they will be automatically "
            + "updated at when used.</P> "
            + "<P>There are two modes of operation.  In the first, random "
            + "Sequences, seqeunces of a length, are generated.  In the other "
            + "organized testing stimuli are created for human administration. "
            + "The effect of each of the fields and buttons are as follows:</P>"
            + "<H3>Mode Selection</H3>"
            + "<UL> "
            + "<LI><B>Stimuli</B>: Generate sequences of random numbers. "
            + "These will all be of the same length and will take the form "
            + "of a list with no other order imposed.</LI> "
            + "<LI><B>Tests</B>: Sequences of progressively longer length will "
            + "be generated, organized as testing stimuli.</LI> "
            + "</UL> "
            + "<H3>Stimuli / Sequences</H3>"
            + "<UL> "
            + "<LI><B>Length</B>: Number of digits per sequence.</LI> "
            + "<LI><B>Number</B>: How many sequences to create.</LI> "
            + "<LI><B>Show One</B>: Display a single, new sequence to the "
            + "screen, so that you can get a sense of what will be generated.</LI> "
            + "<LI><B>Save Batch</B>: Generate and save to a file.</LI> "
            + "<LI><B>Print Batch</B>: Generate and print.</LI> "
            + "</UL> "
            + "<H3>Full Tests</H3>"
            + "These controls are similar to those for test administration:"
            + "<UL> "
            + "<LI><B>Min Length</B>: The minimum sequence length to be used. "
            + "This must be a number between 1 and 10 and less-than-or-equal "
            + "to max length.</LI> "
            + "<LI><B>Max Field</B>: The maximum sequence length to be used. "
            + "This must be a number between 1 and 10 and greater-than-or-equal "
            + "to min length.</LI> "
            + "<LI><B>Number</B>: How many test to create.  This should usually "
            + "be one if they are going to be printed directly, as there is no "
            + "pagation between tests.</LI> "
            + "<LI><B>Set Size</B>: The number of sequences of each length.</LI> "
            + "<LI><B>Save Batch</B>: Generate and save to a file.</LI> "
            + "<LI><B>Print Batch</B>: Generate and print.</LI> "
            + "</UL> "
            + "<H2>Main Menu Bar</H2>"
            + "Sequences or tests can be generated from here.  However, this is "
            + "does not allow for any setup of types of out put.  Thus, this is "
            + "generally not the best way to do this unless you have already "
            + "used Generate Random Sequences to set things up."
            + "</BODY></HTML>";
    // </editor-fold>


// <editor-fold defaultstate="collapsed" desc="setupContent">
    static final String setupContent = "<HTML><HEAD><TITLE>Setup / Options Help"
            + "</TITLE><HEAD><BODY BGCOLOR=\"<ffffff\"> "
            + "<H2>Usage Mode</H2>"
            + "<UL>"
            + "<LI><B>Default Tests:</B> This forces a set, default "
            + "administration to be used.</LI>"
            + "<LI><B>Custom Tests:</B> This allows for a lot of customization "
            + "of the presentation that may be need for certain research "
            + "paradigms (e.g., a longer test, using only sequences near a "
            + "pre-measured limit of performance, etc.).</LI>"
            + "</UL>"
            + "<H2>Basic Setup</H2>"
            + "These options control basic feature of the programs presentation "
            + "and behavior.  Most of them are fairly self-explanatory."
            + "<UL>"
            + "<LI><B>Show Results After Testing:</B>  If this is set the main "
            + "window will automatically display test results immediately "
            + "after the completion of an administration.  Otherwise, this "
            + "must be accessed through the File=>Show Results menu item.</LI>"
            + "<LI><B>Play Sound when Test Ends:</B> If this is selected, a "
            + "tone will play at then end of testing; a high chord for a "
            + "successful completion or a lower sound for if the test has "
            + "aborted.  It is hoped this will alert an examiner whose back is "
            + "turned (such as to take notes or score a previous assessment.</LI>"
            + "<LI><B>Don't Blank Info Fields:</B> If selected Test ID will "
            + "not be blanked when testing starts; otherwise (by default) it "
            + "so as to prevent accidentally leaving the previous ID in for the "
            + "next administration.</LI>"
            + "<LI><B>Use Verbal Cues (always):</B> The word \"Go!\" will be "
            + "played to cue the test taker to begin typing a response.  "
            + "\"Stop\" will be played when all digits have been entered.</LI>"
            + "<LI><B>Verbal Cues with Audio:</B> The same as above, except "
            + "that the verbal cues will only be played if the test includes "
            + "and auditory component to the testing stimuli.</LI>"
            + "</UL>"
            + "<H2>Special Files</H2>"
            + "These options turn on and off the use of special files that keep "
            + "records between executions of the program.  Note that only one "
            + "instance of Digit Span Tester should be running or use of these "
            + "files could create an error, as instances try to access the same "
            + "file.  The files will be created in a directory (folder) named "
            + "\"DigitSpanTester\" under your home directory."
            + "<UL>"
            + "<LI><B>Log Results:</B> If this is selected a copy of all test "
            + "results will be saved in a text file named \"DigitSpanTest.log.\" "
            + "The purpose of this is as a back-up, and should not be relied on "
            + "for general record keeping (for which save and print should be "
            + "used).  The file will be created in a directory (folder) named "
            + "\"DigitSpanTester\" under your home directory.  Thus, the full "
            + "path name of the folder will be [$home]/DigitSpanTester/DigitSpanTester.log. "
            + "The use of logging also adds an extra reason not to use "
            + "personally identifiable information in Test ID's.</LI>"
            + "<LI><B>Use Config File:</B> If this is selected than a binary "
            + "file named \"DigitSpanTester.cfg\" will be used to initialize "
            + "other options, if one exists.  The <B>[Save Config]</B> buttton "
            + "at the bottom of the screen must be used to create or update "
            + "a configuration file.  "
            + "Whenever the button is pushed the current setting are saved "
            + "to the file, an will be used to initialize program next time it "
            + "is run if (and only if) the configuration includes this option turned "
            + "on.</LI>"
            + "<LI><B>Store Fields in Config:</B> This will cause the current "
            + "numeric values, such as sequence length and set size to be read "
            + "from the config file as well, thus allowing an alternate "
            + "protocol to be saved for a specific research or other purposes.</LI>"
            + "</UL>"
            + "<H2>Advanced Options</H2> "
            + "<P>Bellow are some advanced options for specialized purposes.  "
            + "advised that you consider carefully before changing these, and "
            + "only do so if you have a good (and definite) reason for it.  "
            + "These are found under the advanced options tab.  They will be "
            + "be saved just like the basic options of a config file is "
            + "used.</P> "
            + "<UL>"
            + "<LI><B>Stimulus Time (ms):</B> This is the amount of time (in "
            + "miliseconds) that each digit will be presented.  More "
            + "specifically, visual stimuli will be on the screen for this "
            + "amount of time.  The length of audio clips will not change, but "
            + "even in purely auditory mode the test will treat the stimulus as "
            + "filling this block.  By default this is 1000 (i.e., one "
            + "second).  This does not apply if \"Hit Key for Next\" is set.</LI>"
            + "<LI><B>Pause Time (ms):</B> This is the length (in "
            + "miliseconds) of the pause between each stimulus.  More "
            + "specifically, this is the time between digit -- in other words "
            + "amount of time between blocks of stimulus time.  Note that audio "
            + "clips may overlap this if stimulus time is very short. "
            + "Also, this pause will still occur even when \"Hit Key for Next\" "
            + "is set.  By default this is 1000 (i.e., one second).</LI>"
            + "<LI><B>Extra Delay After Last (s):</B> If the box is checked "
            + "there will be an extra delay after the last digit in each "
            + "sequence.  The delay will be the number of <EM><B>seconds</B></EM> "
            + "(<STRONG>NOT</STRONG> miliseconds) of the delay.  This may be "
            + "useful in looking at what the brain is doing while the material "
            + "is rehearsed without activily taking in new information or "
            + "producing an overt response, and maybe some other things.  This "
            + "is not generally recommended as it can make the task quite "
            + "long.</LI>"
            + "<LI><B>End test after error limit:</B> If this is set the test "
            + "will end early after the test taker has scored 0% correct on "
            + "the number of sentence lengths specified in error limit.  For "
            + "example, if error limit is \"2,\" the test will end if the taker "
            + "has missed all sequences at lengths four and five.  This set by "
            + "default, so that people will not be tormented with a long "
            + "continuing test after reaching the limit of their ability.  Note "
            + "that the number of individual digits correct is not considered, "
            + "and that all digit will be considered missed for sequence after "
            + "the end of the test.</LI>"
            + "<LI><B>Error Limit:</B> The number of sequence that must be "
            + "failed before the test automatically ends early.  This must be "
            + "greater than zero / at least one, and is two be default.</LI>"
            + "<LI><B>Hit Key for Next:</B> If this box is checked each digit "
            + "will remain until the test taker hits the space bar -- no mater "
            + "how long or short the time is until the space bar is hit, the "
            + "test will wait for input.  There will still be a pause betweem "
            + "digits; if no pause is desired pause time may be set to \"1\" "
            + "(one milisecond) to make it negligible and imperceptible to "
            + "humans.  If \"Any Key for Next\" is selected aswell then the "
            + "test taker can use any key to move on, not just the space "
            + "bar.</LI>"
            + "<LI><B>Any Key for Next:</B> This will allow the test taker to "
            + "get the next digit by pressing any key (rather than just the "
            + "space bar).  It will only work if \"Hit Key for Next\" is also "
            + "selected, and only them may it be checked or unchecked.</LI>"
            + "</UL>"
            + "</BODY></HTML>";
    // </editor-fold>
    

// <editor-fold defaultstate="collapsed" desc="GPL">
    static final String gpl = "<HTML><HEAD><TITLE>GNU General Public Lisence (GPL)"
            + "</TITLE><HEAD><BODY BGCOLOR=\"<ffffff\"> "
            + "The author of this software gives permission for free use on "
            + "the contingency that you accept the full conditions of the GNU "
            + "General Public Lisence (GPL).  Use of this software is therefore implied "
            + "acceptence of said lisence; use without acceptance of the below "
            + "lisence is without permission of the software author and a "
            + "violation of the auhtor's intellectual property rights."
            + " <HR> "
            + "<H2>GNU Public Lisence (GPL)<BR>"
            + "Version 2, June 1991</H2> "
            + "<P>Copyright (C) 1989, 1991 Free Software Foundation, Inc.  "
            + "<BR>59 Temple Place - Suite 330, Boston, MA  02111-1307, USA</P> "
            + "<P>Everyone is permitted to copy and distribute verbatim copies "
            + "of this license document, but changing it is not allowed.</P> "
            + "<H3>Preamble</H3> "
            + "<P>The licenses for most software are designed to take away your "
            + "freedom to share and change it. By contrast, the GNU General "
            + "Public License is intended to guarantee your freedom to share "
            + "and change free software--to make sure the software is free for "
            + "all its users. This General Public License applies to most of "
            + "the Free Software Foundation's software and to any other program "
            + "whose authors commit to using it. (Some other Free Software "
            + "Foundation software is covered by the GNU Library General Public "
            + "License instead.) You can apply it to your programs, too.</P> "
            + "<P>When we speak of free software, we are referring to freedom, "
            + "not price. Our General Public Licenses are designed to make sure "
            + "that you have the freedom to distribute copies of free software "
            + "(and charge for this service if you wish), that you receive "
            + "source code or can get it if you want it, that you can change "
            + "the software or use pieces of it in new free programs; and that "
            + "you know you can do these things.</P> "
            + "<P>To protect your rights, we need to make restrictions that "
            + "forbid anyone to deny you these rights or to ask you to "
            + "surrender the rights. These restrictions translate to certain "
            + "responsibilities for you if you distribute copies of the "
            + "software, or if you modify it.</P> "
            + "<P>For example, if you distribute copies of such a program, "
            + "whether gratis or for a fee, you must give the recipients all "
            + "the rights that you have. You must make sure that they, too, "
            + "receive or can get the source code. And you must show them these "
            + "terms so they know their rights.</P> "
            + "<P>We protect your rights with two steps: (1) copyright the "
            + "software, and (2) offer you this license which gives you legal "
            + "permission to copy, distribute and/or modify the software.</P> "
            + "<P>Also, for each author's protection and ours, we want to make "
            + "certain that everyone understands that there is no warranty for "
            + "this free software. If the software is modified by someone else "
            + "and passed on, we want its recipients to know that what they "
            + "have is not the original, so that any problems introduced by "
            + "others will not reflect on the original authors' reputations.</P> "
            + "<P>Finally, any free program is threatened constantly by "
            + "software patents. We wish to avoid the danger that "
            + "redistributors of a free program will individually obtain "
            + "patent licenses, in effect making the program proprietary. "
            + "To prevent this, we have made it clear that any patent must be "
            + "licensed for everyone's free use or not licensed at all.</P> "
            + "<P>The precise terms and conditions for copying, distribution "
            + "and modification follow.</P> "
            + "<H3>TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND "
            + "MODIFICATION</H3><UL> "
            + "<P>0. This License applies to any program or other work which "
            + "contains a notice placed by the copyright holder saying it may "
            + "be distributed under the terms of this General Public License. "
            + "The \"Program\", below, refers to any such program or work, and "
            + "a \"work based on the Program\" means either the Program or any "
            + "derivative work under copyright law: that is to say, a work "
            + "containing the Program or a portion of it, either verbatim or "
            + "with modifications and/or translated into another language. "
            + "(Hereinafter, translation is included without limitation in the "
            + "term \"modification\".) Each licensee is addressed as \"you\".</P> "
            + "<P>Activities other than copying, distribution and modification "
            + "are not covered by this License; they are outside its scope. "
            + "The act of running the Program is not restricted, and the output "
            + "from the Program is covered only if its contents constitute a "
            + "work based on the Program (independent of having been made by "
            + "running the Program). Whether that is true depends on what the "
            + "Program does.</P> "
            + "<P>1. You may copy and distribute verbatim copies of the "
            + "Program's source code as you receive it, in any medium, "
            + "provided that you conspicuously and appropriately publish on "
            + "each copy an appropriate copyright notice and disclaimer of "
            + "warranty; keep intact all the notices that refer to this "
            + "License and to the absence of any warranty; and give any other "
            + "recipients of the Program a copy of this License along with the "
            + "Program.</P> "
            + "<P>You may charge a fee for the physical act of transferring a "
            + "copy, and you may at your option offer warranty protection in "
            + "exchange for a fee.</P> "
            + "<P>2. You may modify your copy or copies of the Program or any "
            + "portion of it, thus forming a work based on the Program, and "
            + "copy and distribute such modifications or work under the terms "
            + "of Section 1 above, provided that you also meet all of these "
            + "conditions:</P><UL> "
            + "<P>a) You must cause the modified files to carry prominent "
            + "notices stating that you changed the files and the date of any "
            + "change.</P> "
            + "<P>b) You must cause any work that you distribute or publish, "
            + "that in whole or in part contains or is derived from the "
            + "Program or any part thereof, to be licensed as a whole at no "
            + "charge to all third parties under the terms of this License.</P> "
            + "<P>c) If the modified program normally reads commands "
            + "interactively when run, you must cause it, when started running "
            + "for such interactive use in the most ordinary way, to print or "
            + "display an announcement including an appropriate copyright "
            + "notice and a notice that there is no warranty (or else, saying "
            + "that you provide a warranty) and that users may redistribute "
            + "the program under these conditions, and telling the user how to "
            + "view a copy of this License. (Exception: if the Program itself "
            + "is interactive but does not normally print such an announcement, "
            + "your work based on the Program is not required to print an "
            + "announcement.)</P> "
            + "<P>These requirements apply to the modified work as a whole. If "
            + "identifiable sections of that work are not derived from the "
            + "Program, and can be reasonably considered independent and "
            + "separate works in themselves, then this License, and its terms, "
            + "do not apply to those sections when you distribute them as "
            + "separate works. But when you distribute the same sections as "
            + "part of a whole which is a work based on the Program, the "
            + "distribution of the whole must be on the terms of this License, "
            + "whose permissions for other licensees extend to the entire "
            + "whole, and thus to each and every part regardless of who wrote "
            + "it.</P> "
            + "<P>Thus, it is not the intent of this section to claim rights "
            + "or contest your rights to work written entirely by you; rather, "
            + "the intent is to exercise the right to control the distribution "
            + "of derivative or collective works based on the Program.</P> "
            + "<P>In addition, mere aggregation of another work not based on "
            + "the Program with the Program (or with a work based on the "
            + "Program) on a volume of a storage or distribution medium does "
            + "not bring the other work under the scope of this License.</P></UL> "
            + "<P>3. You may copy and distribute the Program (or a work based "
            + "on it, under Section 2) in object code or executable form under "
            + "the terms of Sections 1 and 2 above provided that you also do "
            + "one of the following:</P><UL> "
            + "<P>a) Accompany it with the complete corresponding "
            + "machine-readable source code, which must be distributed under "
            + "the terms of Sections 1 and 2 above on a medium customarily used "
            + "for software interchange; or,</P>  "
            + "<P>b) Accompany it with a written offer, valid for at least "
            + "three years, to give any third party, for a charge no more than "
            + "your cost of physically performing source distribution, a "
            + "complete machine-readable copy of the corresponding source "
            + "code, to be distributed under the terms of Sections 1 and 2 "
            + "above on a medium customarily used for software interchange; "
            + "or,</P> "
            + "<P>c) Accompany it with the information you received as to the "
            + "offer to distribute corresponding source code. (This alternative "
            + "is allowed only for noncommercial distribution and only if you "
            + "received the program in object code or executable form with "
            + "such an offer, in accord with Subsection b above.)</P> "
            + "<P>The source code for a work means the preferred form of the "
            + "work for making modifications to it. For an executable work, "
            + "complete source code means all the source code for all modules "
            + "it contains, plus any associated interface definition files, "
            + "plus the scripts used to control compilation and installation "
            + "of the executable. However, as a special exception, the source "
            + "code distributed need not include anything that is normally "
            + "distributed (in either source or binary form) with the major "
            + "components (compiler, kernel, and so on) of the operating "
            + "system on which the executable runs, unless that component "
            + "itself accompanies the executable.</P> "
            + "<P>If distribution of executable or object code is made by "
            + "offering access to copy from a designated place, then offering "
            + "equivalent access to copy the source code from the same place "
            + "counts as distribution of the source code, even though third "
            + "parties are not compelled to copy the source along with the "
            + "object code.</P></UL> "
            + "<P>4. You may not copy, modify, sublicense, or distribute the "
            + "Program except as expressly provided under this License. Any "
            + "attempt otherwise to copy, modify, sublicense or distribute the "
            + "Program is void, and will automatically terminate your rights "
            + "under this License. However, parties who have received copies, "
            + "or rights, from you under this License will not have their "
            + "licenses terminated so long as such parties remain in full "
            + "compliance.</P> "
            + "<P>5. You are not required to accept this License, since you "
            + "have not signed it. However, nothing else grants you permission "
            + "to modify or distribute the Program or its derivative works. "
            + "These actions are prohibited by law if you do not accept this "
            + "License. Therefore, by modifying or distributing the Program "
            + "(or any work based on the Program), you indicate your "
            + "acceptance of this License to do so, and all its terms and "
            + "conditions for copying, distributing or modifying the Program "
            + "or works based on it.</P> "
            + "<P>6. Each time you redistribute the Program (or any work based "
            + "on the Program), the recipient automatically receives a license "
            + "from the original licensor to copy, distribute or modify the "
            + "Program subject to these terms and conditions. You may not "
            + "impose any further restrictions on the recipients' exercise of "
            + "the rights granted herein. You are not responsible for "
            + "enforcing compliance by third parties to this License.</P> "
            + "<P>7. If, as a consequence of a court judgment or allegation of "
            + "patent infringement or for any other reason (not limited to "
            + "patent issues), conditions are imposed on you (whether by court "
            + "order, agreement or otherwise) that contradict the conditions "
            + "of this License, they do not excuse you from the conditions of "
            + "this License. If you cannot distribute so as to satisfy "
            + "simultaneously your obligations under this License and any "
            + "other pertinent obligations, then as a consequence you may not "
            + "distribute the Program at all. For example, if a patent license "
            + "would not permit royalty-free redistribution of the Program by "
            + "all those who receive copies directly or indirectly through "
            + "you, then the only way you could satisfy both it and this "
            + "License would be to refrain entirely from distribution of the "
            + "Program.</P> "
            + "<P>If any portion of this section is held invalid or "
            + "unenforceable under any particular circumstance, the balance of "
            + "the section is intended to apply and the section as a whole is "
            + "intended to apply in other circumstances.</P>"
            + "<P>It is not the purpose of this section to induce you to "
            + "infringe any patents or other property right claims or to "
            + "contest validity of any such claims; this section has the sole "
            + "purpose of protecting the integrity of the free software "
            + "distribution system, which is implemented by public license "
            + "practices. Many people have made generous contributions to the "
            + "wide range of software distributed through that system in "
            + "reliance on consistent application of that system; it is up to "
            + "the author/donor to decide if he or she is willing to "
            + "distribute software through any other system and a licensee "
            + "cannot impose that choice.</P> "
            + "</UL><H3>This section is intended to make thoroughly clear what is "
            + "believed to be a consequence of the rest of this License.</H3><UL> "
            + "<P>8. If the distribution and/or use of the Program is "
            + "restricted in certain countries either by patents or by "
            + "copyrighted interfaces, the original copyright holder who "
            + "places the Program under this License may add an explicit "
            + "geographical distribution limitation excluding those countries, "
            + "so that distribution is permitted only in or among countries "
            + "not thus excluded. In such case, this License incorporates the "
            + "limitation as if written in the body of this License.</P> "
            + "<P>9. The Free Software Foundation may publish revised and/or "
            + "new versions of the General Public License from time to time. "
            + "Such new versions will be similar in spirit to the present "
            + "version, but may differ in detail to address new problems or "
            + "concerns.</P> "
            + "<P>Each version is given a distinguishing version number. If "
            + "the Program specifies a version number of this License which "
            + "applies to it and \"any later version\", you have the option of "
            + "following the terms and conditions either of that version or of "
            + "any later version published by the Free Software Foundation. If "
            + "the Program does not specify a version number of this License,"
            + " you may choose any version ever published by the Free Software "
            + "Foundation.</P> "
            + "<P>10. If you wish to incorporate parts of the Program into "
            + "other free programs whose distribution conditions are "
            + "different, write to the author to ask for permission. For "
            + "software which is copyrighted by the Free Software Foundation, "
            + "write to the Free Software Foundation; we sometimes make "
            + "exceptions for this. Our decision will be guided by the two "
            + "goals of preserving the free status of all derivatives of our "
            + "free software and of promoting the sharing and reuse of "
            + "software generally.</P> "
            + "</UL><H3>NO WARRANTY</H3><UL> "
            + "<P>11. BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS "
            + "NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY "
            + "APPLICABLE LAW. EXCEPT WHEN OTHERWISE STATED IN WRITING THE "
            + "COPYRIGHT HOLDERS AND/OR OTHER PARTIES PROVIDE THE PROGRAM "
            + "\"AS IS\" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR "
            + "IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES "
            + "OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE "
            + "ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM "
            + "IS WITH YOU. SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME "
            + "THE COST OF ALL NECESSARY SERVICING, REPAIR OR CORRECTION.</P> "
            + "<P>12. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED "
            + "TO IN WRITING WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO "
            + "MAY MODIFY AND/OR REDISTRIBUTE THE PROGRAM AS PERMITTED ABOVE, "
            + "BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY GENERAL, SPECIAL, "
            + "INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR "
            + "INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO "
            + "LOSS OF DATA OR DATA BEING RENDERED INACCURATE OR LOSSES "
            + "SUSTAINED BY YOU OR THIRD PARTIES OR A FAILURE OF THE PROGRAM "
            + "TO OPERATE WITH ANY OTHER PROGRAMS), EVEN IF SUCH HOLDER OR "
            + "OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH "
            + "DAMAGES.</P></UL> "
            + "<H3>END OF TERMS AND CONDITIONS</H3> "
            + "<HR>"
            + "<P>For more information about the GNU General Public Liscnece see:<BR> "
            + "<A HREF=\"http://www.gnu.org/copyleft/gpl.html\">http://www.gnu.org/copyleft/gpl.html</P>"
            + "</BODY></HTML>";
    // </editor-fold>




    public HelpPanel(ControlListener controls) {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        setName("helpPanel");

        if(Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        } else desktop = null;
        currentURL = null;

        helpPane = new JPanel(new BorderLayout());
        testingPane = new JPanel(new BorderLayout());
        generatePane = new JPanel(new BorderLayout());
        setupPane = new JPanel(new BorderLayout());
        algPane = new JPanel(new BorderLayout());
        aboutPane = new JPanel(new BorderLayout());
        gplPane = new JPanel(new BorderLayout());

        helpText = new JEditorPane();
        helpText.setContentType("text/html");
        helpText.setEditable(false);
        helpText.setText(helpContent);
        helpScroll = new JScrollPane(helpText);
        helpText.setCaretPosition(0);
        helpPane.add(helpScroll);
        addTab("Help", null, helpPane, "General help, info, and guidance on "
                + "where else to look for more.");

        testingText = new JEditorPane();
        testingText.setContentType("text/html");
        testingText.setEditable(false);
        testingText.setText(testingContent);
        testingScroll = new JScrollPane(testingText);
        testingText.setCaretPosition(0);
        testingPane.add(testingScroll);
        addTab("Testing", null, testingPane, "Information and instruction for "
                + "test administration");

        generateText = new JEditorPane();
        generateText.setContentType("text/html");
        generateText.setEditable(false);
        generateText.setText(generateContent);
        generateScroll = new JScrollPane(generateText);
        generateText.setCaretPosition(0);
        generatePane.add(generateScroll);
        addTab("Generating Sequences", null, generatePane,
                "Information and instruction for generating random sequences "
                + "to be saved to a file or printed");

        setupText = new JEditorPane();
        setupText.setContentType("text/html");
        setupText.setEditable(false);
        setupText.setText(setupContent);
        setupScroll = new JScrollPane(setupText);
        setupText.setCaretPosition(0);
        setupPane.add(setupScroll);
        addTab("Setup / Options", null, setupPane, "Information on the various "
                + "setup and configuration options available");

        algText = new JEditorPane();
        algText.setContentType("text/html");
        algText.setEditable(false);
        algText.setText(algContent);
        algScroll = new JScrollPane(algText);
        algText.setCaretPosition(0);
        algPane.add(algScroll);
        addTab("Algorithms", null, algPane, "Information about the types of "
                + "random sequences that may be generated");

        aboutText = new JEditorPane();
        aboutText.setContentType("text/html");
        aboutText.setEditable(false);
        aboutText.setText(aboutContent);
        aboutScroll = new JScrollPane(aboutText);
        aboutText.setCaretPosition(0);
        aboutPane.add(aboutScroll);
        aboutText.addHyperlinkListener(this);
        addTab("About", null, aboutPane, "About this program (credits)");

        gplText = new JEditorPane();
        gplText.setContentType("text/html");
        gplText.setEditable(false);
        gplText.setText(gpl);
        gplScroll = new JScrollPane(gplText);
        gplText.setCaretPosition(0);
        gplPane.add(gplScroll);
        gplText.addHyperlinkListener(this);
        addTab("Lisence", null, gplPane, "GNU General Public Lisence (GPL)");

        this.setVisible(true);
    }


    void setPage(int id) {
        setSelectedIndex(id - 11);
    }
    

    public void hyperlinkUpdate(HyperlinkEvent e) {
        if(desktop == null) return;
        if(!e.getEventType().toString().matches("ACTIVATED")) {
            currentURL = null;
            return;
        }
        try {
            if(currentURL != null) return;
            currentURL = e.getURL();
            desktop.browse(currentURL.toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
