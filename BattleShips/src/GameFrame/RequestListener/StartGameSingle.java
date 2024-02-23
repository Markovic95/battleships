/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameFrame.RequestListener;

import GameFrame.SinglePlayerMainFrame;
import LoginFrame.StartUpMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Implements ActionListener for single player vs computer
 * 
 * @author Dalibor Markovic
 */
public class StartGameSingle  implements ActionListener{
    StartUpMenu start;
    SinglePlayerMainFrame game;
    public StartGameSingle(StartUpMenu root, SinglePlayerMainFrame siglePlayer) {
    this.start = root;
    this.game = siglePlayer;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        start.setVisible(false);
        game.setVisible(true);
    }
    
}
