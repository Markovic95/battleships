/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listeners;

import LoginFrame.StartUpMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Implements ActionListener for the Main Menu Bar
 * 
 * @author Dalibor Markovic
 */
 public class SettingsListener implements MouseListener {

    StartUpMenu start;

    public SettingsListener(StartUpMenu aThis) {

        this.start = aThis;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(start.MainSettingsMenu)){
        start.MainSettingsMenu.doClick();
        start.MainSettingsMenu.setPopupMenuVisible(true);
        start.MainSettingsMenu.setSelected(true);
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       

        start.MainSettingsMenu.setFocusable(true);

    }

}
