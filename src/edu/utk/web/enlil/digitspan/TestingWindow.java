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

import jaredbgreat.arcade.ui.sound.Sound;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class TestingWindow extends JFrame implements KeyListener,
        WindowListener, ActionListener {
    

	private static final long serialVersionUID = 4037641553406420376L;

	private DigitSpanTester mainClass;

    private GeneratedSequences stimuli;
    private UserSequences responces;
    private int imput; // Sorry, I like the tradional, non-computer-geek, word
    private JPanel mainPane;
    private JLabel testArea;
    private JTextField responceArea;
    private JLabel responceLabel;
    private JPanel responcePane;
    private JPanel testPane;
    private Font stimulusFont;
    private Font responceFont;
    private Timer timer1, timer2, timer3;

    private int i, j, k, counter = 0;
    private boolean done = false;
    private boolean maxedOut = false;
    
    private boolean missedAll  = ControlVariables.limitErrors;
    private int timesMissedAll = -1; // Will be incremented once at beginning


    public TestingWindow(DigitSpanTester mainClass) {
        setVisible(false);
        this.mainClass = mainClass;
        addWindowListener(this);
        mainPane = new JPanel(new BorderLayout());
        setMinimumSize(new Dimension(512, 512));
        setLocationRelativeTo(null);
        setContentPane(mainPane);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Digit Span Tester");
        if(ControlVariables.keyForNext) {
            timer1 = new Timer(ControlVariables.initDelay, this);
            timer1.setInitialDelay(ControlVariables.initDelay);
            timer2 = new Timer(ControlVariables.pauseTime, this);
            timer2.setInitialDelay(ControlVariables.initDelay);
        } else {
            timer1 = new Timer(ControlVariables.stimulusTime + 
                    ControlVariables.pauseTime, this);
            timer1.setInitialDelay(ControlVariables.initDelay);
            timer2 = new Timer(ControlVariables.stimulusTime + 
                    ControlVariables.pauseTime, this);
            timer2.setInitialDelay(ControlVariables.initDelay + 
                    ControlVariables.stimulusTime);
        }
        timer3 = new Timer(1000 * ControlVariables.extraDelayTime, this);
        timer3.setInitialDelay(1000 * ControlVariables.extraDelayTime);
        testArea = new JLabel();
        testArea.setBorder(BorderFactory.createEmptyBorder());
        testArea.setBackground(Color.WHITE);
        testPane = new JPanel(new GridLayout(3,3));
        testPane.setBackground(Color.WHITE);
        responcePane = new JPanel(new GridLayout(1, 2));
        responceLabel = new JLabel("Enter Response: ");
        responceArea = new JTextField("");
        responceArea.setHorizontalAlignment(JTextField.CENTER);
        for(i = 0; i < 4; i++) testPane.add(new JLabel(""));
        testPane.add(testArea);
        for(i = 0; i < 4; i++) testPane.add(new JLabel(""));
        add(testPane, BorderLayout.CENTER);
        responcePane.add(responceLabel);
        responcePane.add(responceArea);
        responcePane.setBackground(Color.CYAN);
        add(responcePane, BorderLayout.SOUTH);
        responceArea.setEditable(false);
        responceArea.setVisible(true);
        responceLabel.setVisible(true);
        responcePane.setVisible(false);
        testPane.setVisible(true);
        testArea.setVisible(true);
        responceFont = new Font("Serif", Font.PLAIN, 28);
        stimulusFont = new Font("Serif", Font.BOLD, 128);
        responceArea.setFont(responceFont);
        responceLabel.setFont(responceFont);
        testArea.setFont(stimulusFont);
        testArea.setText("");
        //if(ControlVariables.keyForNext) addKeyListener(this);
    }


    public void beginAdministration(GeneratedSequences stimuli,
            UserSequences responces) {
        this.stimuli = stimuli;
        this.responces = responces;
        ControlVariables.testCompleted = done = false;
        i = j = -1;
        k = setCounter(0);
        setVisible(true);
        administerNextGroup();
    }


    private void administerNextGroup() {
    	if(done) return;
        if(missedAll && ControlVariables.limitErrors) {
            timesMissedAll++;
            if(timesMissedAll >= ControlVariables.errorLimit) {
                if((i+1) < stimuli.getNumSets()) maxedOut = true;
                endTesting();
            }
        } else {
            timesMissedAll = 0;
            missedAll = true;
        }
        j = -1;
        k = 0;
        if(++i >= stimuli.getNumSets()) endTesting();
        else administerNext();
    }


    private void administerNext() {
    	if(done) return;
        if(++j >= stimuli.getSetSize()) administerNextGroup();
        else {
            k = 0;
            if(!ControlVariables.keyForNext) timer1.start();
            timer2.start();
        }
    }


    public void actionPerformed(ActionEvent e) {
        if(k >= stimuli.getLength(i)) {
            if(!ControlVariables.keyForNext) timer1.stop();
            timer2.stop();
            if(ControlVariables.extraDelay) {
                if(k == stimuli.getLength(i)) {
                    k++;
                    timer3.start();
                    return;
                } else {
                    timer3.stop();
                }
            }
            k = 0;
            responceArea.setEditable(true);
            responcePane.setVisible(true);
            addKeyListener(this);
            if(ControlVariables.keyForNext) 
                timer2.setInitialDelay(ControlVariables.initDelay);
            if(ControlVariables.blindAssist || (ControlVariables.soundWAudio &&
                    ControlVariables.audioStimuli)) Sound.registry.getFromName("go").play();
        } else if(testArea.getText().equals("")) {
            if(ControlVariables.keyForNext) {
                timer2.stop();
                timer2.setInitialDelay(ControlVariables.pauseTime);
                addKeyListener(this);
            }
            if(ControlVariables.visualStimuli) {
                //System.out.print(stimuli.get(i, j, k) + " = ");
                testArea.setText(" " + stimuli.get(i, j, k) + " ");
            } else testArea.setText(" "); // This is needed to keep alternation.
            if(ControlVariables.audioStimuli) {
                //System.out.println(stimuli.get(i, j, k));
                Sound.registry.get(stimuli.get(i, j, k)).play();
            }
        } else {
            if(ControlVariables.keyForNext) {
                removeKeyListener(this);
                timer2.start();                
            }
            testArea.setText("");
            k++;
        }
    }


    private void endTesting() {
        ControlVariables.testCompleted = done = true;
        mainClass.testComplete(maxedOut, i + 1);
        setVisible(false);
    }


    public void keyTyped(KeyEvent e) {
        if(!responcePane.isVisible()) return;
        // This will record the takers responce and re-blank the text field
        try {
            imput = Integer.parseInt("" + e.getKeyChar());
        } catch (NumberFormatException ex) {
            // Don't accept non-numerals
            responceArea.setText("");
            return;
        }
        responces.imput(imput, i, j, k);
        // Blank for next imput
        responceArea.setText("" + imput);
        k++;
        // When enough digits have been entered begin testing again
        if(k >= responces.getLength(i)) {
            responceArea.setText("");
            responceArea.setEditable(false);
            responcePane.setVisible(false);
            removeKeyListener(this);
            if(((ControlVariables.blindAssist ||
                    (ControlVariables.soundWAudio &&
                    ControlVariables.audioStimuli))) &&
                    ((j + 1 < stimuli.getSetSize()) ||
                    (i + 1 < stimuli.getNumSets())))
                Sound.registry.getFromName("stop").play();
            if(responces.checkOne(stimuli, i, j) && ControlVariables.limitErrors) 
                missedAll = false;
            administerNext();
        }
    }


    public void keyPressed(KeyEvent e) {} // Does nothing in an of itself
    public void keyReleased(KeyEvent e) {
        // When set to allow the taker to get the next stimulus with a key 
        // this will take the press/release and translate it into an action 
        // event with the same effect as those usually produced by the timer1.
        if(!responcePane.isVisible() && ControlVariables.keyForNext) {
            if(e.getKeyChar() != ' ' && !ControlVariables.anyKeyForNext) return;
            removeKeyListener(this);  
            actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, 
                    "KeyReleased")); 
        }
    }



    public void killWindow() {
        responceArea.setEditable(false);
        responcePane.setVisible(false);
        ControlVariables.testCompleted = done;
        setVisible(false);
        timer1.stop();
        timer2.stop();
    }

    public void windowClosing(WindowEvent e) {
        // If the window is closed, minimized, or loses focus the test is
        // invalidated and must be aborted (and presumably restarted).
    	killWindow();
        mainClass.testAborted();
    }


    // These are treated as window closing events, since they have the same
    // practical effect of invalidating the test; windowClosed() is included
    // incase a different OS handles it differently.
    @Override
    public void windowClosed(WindowEvent e) {windowClosing(e);}
    @Override
    public void windowIconified(WindowEvent e) {windowClosing(e);}
    @Override
    public void windowDeactivated(WindowEvent e) {killWindow();}
    // These are empty as default behvior is acceptable (and these shouldn't be
    // possible anyway).
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowOpened(WindowEvent e) {}


	public int getCounter() {
		return counter;
	}


	public int setCounter(int counter) {
		this.counter = counter;
		return counter;
	}
}
