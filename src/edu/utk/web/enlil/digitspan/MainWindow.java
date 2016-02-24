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

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;



final class MainWindow extends JFrame {

    private DigitSpanTester mainClass;
    private ControlListener listener;

    private JPanel contentPane;
    private CardLayout mainLayout;
    private JMenuBar mainMenu;
    private JDialog errorFrame;

    MainPanel mainPane;
    GenerationPanel generatePane;
    SetupPanel setupPane;
    HelpPanel helpPane;
    ResultsPanel resultPane;

    JMenu fileMenu;
    JMenu workMenu;
    JMenu setupMenu1;
    JMenu helpMenu;

    JMenuItem showResults;
    JMenuItem saveResults;
    JMenuItem printResults;
    JMenuItem saveSequences;
    JMenuItem printSequences;
    JMenuItem saveStimuli;
    JMenuItem printStimuli;
    JMenuItem quitButton;
    JMenuItem testingMenu;
    JMenuItem generationMenu;
    JMenuItem optionsMenu;
    JMenuItem mainHelpMenu;
    JMenuItem testingHelp;
    JMenuItem generateHelp;
    JMenuItem setupHelp;
    JMenuItem algHelp;
    JMenuItem gplHelp;
    JMenuItem aboutHelp;


    JMenu algMenu;
    ButtonGroup algGroup;
    JRadioButtonMenuItem randButton;
    JRadioButtonMenuItem shuffleButton;
    JRadioButtonMenuItem alt1Button;
    JRadioButtonMenuItem alt2Button;
    JRadioButtonMenuItem alt3Button;
    JRadioButtonMenuItem alt4Button;
    JRadioButtonMenuItem alt5Button;
    JRadioButtonMenuItem alt6Button;
    JRadioButtonMenuItem alt7Button;
    JRadioButtonMenuItem alt8Button;


    public MainWindow(DigitSpanTester mainClass) {
        setVisible(false);
        this.mainClass = mainClass;
        listener = new ControlListener(this, mainClass);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainLayout = new CardLayout();
        mainMenu = new JMenuBar();
        setJMenuBar(mainMenu);
        contentPane = new JPanel(mainLayout);
        setContentPane(contentPane);
        setSize(new Dimension(400, 320));
        setMinimumSize(new Dimension(400, 256));
        setTitle("Digit Span Tester");

        fileMenu = new JMenu("File");
        showResults = new JMenuItem("Show Results");
        showResults.setName("showResults");
        showResults.setActionCommand("showResults");
        showResults.setEnabled(ControlVariables.testCompleted);
        showResults.addActionListener(listener);
        fileMenu.add(showResults);
        saveResults = new JMenuItem("Save Results");
        saveResults.setName("saveResults");
        saveResults.setActionCommand("saveResults");
        saveResults.setEnabled(ControlVariables.testCompleted);
        saveResults.addActionListener(listener);
        fileMenu.add(saveResults);
        printResults = new JMenuItem("Print Results");
        printResults.setName("printResults");
        printResults.setActionCommand("printResults");
        printResults.setEnabled(ControlVariables.testCompleted);
        printResults.addActionListener(listener);
        fileMenu.add(printResults);
        fileMenu.add(new JSeparator());
        saveStimuli = new JMenuItem("Sequences to File");
        saveStimuli.setName("saveSequences");
        saveStimuli.setActionCommand("saveSequences");
        saveStimuli.addActionListener(listener);
        fileMenu.add(saveStimuli);
        printStimuli = new JMenuItem("Sequences to Print");
        printStimuli.setName("printSequences");
        printStimuli.setActionCommand("printSequences");
        printStimuli.addActionListener(listener);
        fileMenu.add(printStimuli);
        saveStimuli = new JMenuItem("Tests to File");
        saveStimuli.setName("saveStimuli");
        saveStimuli.setActionCommand("saveStimuli");
        saveStimuli.addActionListener(listener);
        fileMenu.add(saveStimuli);
        printStimuli = new JMenuItem("Tests to Print");
        printStimuli.setName("printStimuli");
        printStimuli.setActionCommand("printStimuli");
        printStimuli.addActionListener(listener);
        fileMenu.add(printStimuli);
        fileMenu.add(new JSeparator());
        quitButton = new JMenuItem("Exit");
        quitButton.setName("quit");
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(listener);
        fileMenu.add(quitButton);
        mainMenu.add(fileMenu);

        workMenu = new JMenu("Functions");
        testingMenu = new JMenuItem("Do Testing");
        testingMenu.setName("testingMenu");
        testingMenu.setActionCommand("testingMenu");
        testingMenu.addActionListener(listener);
        workMenu.add(testingMenu);
        generationMenu = new JMenuItem("Generate Random Sequences");
        generationMenu.setName("generationMenu");
        generationMenu.setActionCommand("generationMenu");
        generationMenu.addActionListener(listener);
        workMenu.add(generationMenu);
        workMenu.add(new JSeparator());
        optionsMenu = new JMenuItem("Setup Options");
        optionsMenu.setName("optionsMenu");
        optionsMenu.setActionCommand("optionsMenu");
        optionsMenu.addActionListener(listener);
        workMenu.add(optionsMenu);
        mainMenu.add(workMenu);

        algMenu = new JMenu("Algorithm");
        algMenu.setName("algMenu");
        mainMenu.add(algMenu);
        algMenu.setVisible(ControlVariables.customMode);
        algGroup = new ButtonGroup();
        randButton = new JRadioButtonMenuItem("Random Numbers");
        algGroup.add(randButton);
        algMenu.add(randButton);
        randButton.setActionCommand("random");
        randButton.addActionListener(listener);
        shuffleButton = new JRadioButtonMenuItem("Simple Shuffle");
        algGroup.add(shuffleButton);
        algMenu.add(shuffleButton);
        shuffleButton.setActionCommand("shuffle");
        shuffleButton.addActionListener(listener);
        alt1Button = new JRadioButtonMenuItem("Advance Shuffle 1");
        algGroup.add(alt1Button);
        algMenu.add(alt1Button);
        alt1Button.setActionCommand("alt1");
        alt1Button.addActionListener(listener);
        alt2Button = new JRadioButtonMenuItem("Advance Shuffle 2");
        algGroup.add(alt2Button);
        algMenu.add(alt2Button);
        alt2Button.setActionCommand("alt2");
        alt2Button.addActionListener(listener);
        alt3Button = new JRadioButtonMenuItem("Advance Shuffle 3");
        //alt3Button.setSelected(true);
        algGroup.add(alt3Button);
        algMenu.add(alt3Button);
        alt3Button.setActionCommand("alt3");
        alt3Button.addActionListener(listener);
        alt4Button = new JRadioButtonMenuItem("Advance Shuffle 4");
        algGroup.add(alt4Button);
        algMenu.add(alt4Button);
        alt4Button.setActionCommand("alt4");
        alt4Button.addActionListener(listener);
        alt5Button = new JRadioButtonMenuItem("Advance Shuffle 5");
        algGroup.add(alt5Button);
        algMenu.add(alt5Button);
        alt5Button.setActionCommand("alt5");
        alt5Button.addActionListener(listener);
        alt6Button = new JRadioButtonMenuItem("Advance Shuffle 6");
        algGroup.add(alt6Button);
        algMenu.add(alt6Button);
        alt6Button.setActionCommand("alt6");
        alt6Button.addActionListener(listener);
        alt7Button = new JRadioButtonMenuItem("Advance Shuffle 7");
        algGroup.add(alt7Button);
        algMenu.add(alt7Button);
        alt7Button.setActionCommand("alt7");
        alt7Button.addActionListener(listener);
        alt8Button = new JRadioButtonMenuItem("Advance Shuffle 8");
        algGroup.add(alt8Button);
        algMenu.add(alt8Button);
        alt8Button.setSelected(true);
        alt8Button.setActionCommand("alt8");
        alt8Button.addActionListener(listener);

        helpMenu = new JMenu("Help");
        mainHelpMenu = new JMenuItem("Help");
        mainHelpMenu.setName("helpHelp");
        mainHelpMenu.setActionCommand("helpHelp");
        mainHelpMenu.addActionListener(listener);
        helpMenu.add(mainHelpMenu);
        helpMenu.add(new JSeparator());
        testingHelp = new JMenuItem("Testing");
        testingHelp.setName("helpTesting");
        testingHelp.setActionCommand("helpTesting");
        testingHelp.addActionListener(listener);
        helpMenu.add(testingHelp);
        generateHelp = new JMenuItem("Generating Sequences");
        generateHelp.setName("generateHelp");
        generateHelp.setActionCommand("generateHelp");
        generateHelp.addActionListener(listener);
        helpMenu.add(generateHelp);
        setupHelp = new JMenuItem("Setup / Options");
        setupHelp.setName("setupHelp");
        setupHelp.setActionCommand("setupHelp");
        setupHelp.addActionListener(listener);
        helpMenu.add(setupHelp);
        algHelp = new JMenuItem("Algorithms");
        algHelp.setName("algHelp");
        algHelp.setActionCommand("algHelp");
        algHelp.addActionListener(listener);
        helpMenu.add(algHelp);
        helpMenu.add(new JSeparator());
        gplHelp = new JMenuItem("Lisence");
        gplHelp.setName("gplHelp");
        gplHelp.setActionCommand("gplHelp");
        gplHelp.addActionListener(listener);
        helpMenu.add(gplHelp);
        aboutHelp = new JMenuItem("About");
        aboutHelp.setName("aboutHelp");
        aboutHelp.setActionCommand("aboutHelp");
        aboutHelp.addActionListener(listener);
        helpMenu.add(aboutHelp);
        mainMenu.add(helpMenu);

        mainPane = new MainPanel(listener);
        generatePane = new GenerationPanel(listener, mainClass.generator);
        setupPane = new SetupPanel(listener);
        helpPane = new HelpPanel(listener);
        resultPane = new ResultsPanel(listener);

        contentPane.add(mainPane, "main");
        contentPane.add(generatePane, "generation");
        contentPane.add(setupPane, "setup");
        contentPane.add(helpPane, "help");
        contentPane.add(resultPane, "results");
        mainLayout.show(contentPane, "main");

        setVisible(true);
    }


    void setPage(int id) {
        switch(id) {
            case 7:
                mainLayout.show(contentPane, "main");
                setTitle("Digit Span Tester");
                return;
            case 8:
                mainLayout.show(contentPane, "generation");
                setTitle("Digit Span Tester");
                return;
            case 9:
            case 10:
                mainLayout.show(contentPane, "setup");
                setTitle("Digit Span Tester (Setup)");
                return;
            case 11:                
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                helpPane.setPage(id);
                mainLayout.show(contentPane, "help");
                setTitle("Digit Span Tester (Help)");
                return;
            case 25:
                mainLayout.show(contentPane, "results");
                setTitle("Digit Span Tester (Test Results)");
                return;
            case 54:
                helpPane.setPage(17);
                mainLayout.show(contentPane, "help");
                setTitle("Digit Span Tester (Help)");
            default: return;
        }        
    }


    @Override
    protected void finalize() throws Throwable {
        mainClass.configuration.writeConfig();
        mainClass.shutdown();
        super.finalize();
    }
}
