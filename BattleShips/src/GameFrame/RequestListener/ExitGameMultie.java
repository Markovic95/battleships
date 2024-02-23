/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameFrame.RequestListener;

import GameFrame.MultiePlayerMainFrame;
import LoginFrame.StartUpMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Implements ActionListener so that the game can be closed on users request
 *
 * @author Dalibor Markovic
 * 
 * 
 * 
 */
public class ExitGameMultie implements ActionListener{
    MultiePlayerMainFrame game;
    StartUpMenu startmenu;
    public ExitGameMultie(MultiePlayerMainFrame aThis, StartUpMenu startUpMenu) {
    this.startmenu= startUpMenu;
    this.game = aThis;
    
    }
    
    
    /** Closes the game and the menu is set back to front
     * 
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        startmenu.setVisible(true);
        game.setVisible(false);
        game.dispose();
    }
    
}
