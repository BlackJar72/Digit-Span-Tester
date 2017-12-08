// Copyright (c) Jared Blackburn 2017
// It is licensed under the creative commons 4.0 attribution license:
// https://creativecommons.org/licenses/by/4.0/legalcode 
package jaredbgreat.arcade.ui.input;


import jaredbgreat.arcade.game.BaseGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Jared Blackburn
 */
public class KeyInput implements KeyListener { 
   private IKeyTranslator pressed, released;
   private int commands;
   
   
   public KeyInput(IKeyTranslator press, IKeyTranslator release) {       
        pressed = press;
        released = release;        
   }
   

   @Override
   public void keyPressed(KeyEvent e) {
       if(BaseGame.game.blockInput() || (pressed == null)) {
           return;
       }
       commands = commands | pressed.translate(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
       if(BaseGame.game.blockInput() || (released == null)) {
           return;
       }
       commands &= ~released.translate(e);
    }
    
    
    public void clear() {
        commands = 0;
    }
    
    
    public int getCommands() {
        return commands;
    }
    
    
    public void setTranslators(IKeyTranslator press, 
            IKeyTranslator release) {
        pressed = press;
        released = release;        
    }
    
    
    /*-************************************-*/
    /*          UNUSED METHODS              */
    /*-************************************-*/
    
    
    @Override
    public void keyTyped(KeyEvent e)  {/*Do nothing*/}
    
}
