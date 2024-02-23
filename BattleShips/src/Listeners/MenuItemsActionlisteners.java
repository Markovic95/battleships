/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listeners;

import LoginFrame.StartUpMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/** Implements ActionListener for setting the IP address of the server 
 * 
 * @author Dalibor Markovic
 */
public class MenuItemsActionlisteners implements ActionListener{

    StartUpMenu start;
    public MenuItemsActionlisteners(StartUpMenu aThis) {
        this.start = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            setIP();
    }
    
    
    public void setIP(){
    
        String ip = JOptionPane.showInputDialog(start, "Δωσε Server IP για δυνατοτητα Multie Player");
        if(ip == null){
                
          
            return;
        }else if(ip.equals("")){
            return;
        }else{
        
        start.ServerIP = ip; 
        start.serverfunctionsinit();
        }
    
    }
    
}
