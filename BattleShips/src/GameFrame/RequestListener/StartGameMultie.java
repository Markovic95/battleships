/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameFrame.RequestListener;

import GameFrame.MultiePlayerMainFrame;
import LoginFrame.StartUpMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Implements ActionListener so the Game can be started
 * 
 * @author Dalibor Markovic
 */
public class StartGameMultie  implements ActionListener{
    StartUpMenu start;
    MultiePlayerMainFrame game;
    
    public StartGameMultie(StartUpMenu root, MultiePlayerMainFrame multiePlayerMainFrame) {
    this.start = root;
    this.game = multiePlayerMainFrame;
    
    }
    
    
    /**	Starts the Game Panel and closes the menu
     * 
     * 
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        start.setVisible(false);
        game.setVisible(true);
        
    }
    
}
