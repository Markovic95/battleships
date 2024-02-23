/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StartUp;

import LoginFrame.StartUpMenu;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/** Main class 
 * 
 * @author Dalibor Markovic
 */
public class main {
	
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override

            public void run() {
                /** the main method of the programm 
	 * @param url1 represents the icon of the programm
	 * @param url2 same as above but not in any use
	 * @param title The title of the programm
	 * @param 
	 * */
                String title = "BattleShips Game by Dalibor Markovic";
               
                ImageIcon url1 = null;
                ImageIcon url2 = null;
                
                url1 = new ImageIcon("assets/images/battleship.png") {};
                url2 = new ImageIcon("assets/images/background.png");
                BufferedImage iconimage = null;
                Image backImage = null;
                ImageIcon icon = null;
                try {
                    iconimage = ImageIO.read(new File("assets/images/battleship.png"));
                    backImage = ImageIO.read( new File("assets/images/background.png"));
                    icon = new ImageIcon(backImage);
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
          
               StartUpMenu start = new StartUpMenu(title, iconimage, icon);

                // start.setContentPane(new JLabel(new ImageIcon(backImage)));
               

                start.pack();
                start.setVisible(true);
                
            }
        });
    }
}
