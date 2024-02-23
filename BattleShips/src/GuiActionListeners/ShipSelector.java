/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GuiActionListeners;

import Grids.ShipOptions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/** Implements ActionListener for selecting the ship that the user is going to place on the board
 *
 * @author Dalibor Markovic
 */
public class ShipSelector implements ActionListener {

    ShipOptions ShipotOptions;

    public ShipSelector(ShipOptions aThis) {
        this.ShipotOptions = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "carrier" -> {
                ShipotOptions.setLengthofship(Integer.parseInt(ShipotOptions.carrierbutton.getText()));
                ShipotOptions.setShipsname(command);
                ShipotOptions.setColor(ShipotOptions.carrierbutton.getBackground());
            }
            case "battleship" -> {
                ShipotOptions.setLengthofship(Integer.parseInt(ShipotOptions.battleshipbutton.getText()));
                ShipotOptions.setShipsname(command);
                ShipotOptions.setColor(ShipotOptions.battleshipbutton.getBackground());
            }
            case "cruiser" -> {
                ShipotOptions.setLengthofship(Integer.parseInt(ShipotOptions.cruiserbutton.getText()));
                ShipotOptions.setShipsname(command);
                ShipotOptions.setColor(ShipotOptions.cruiserbutton.getBackground());
            }
            case "submarine" -> {
                ShipotOptions.setLengthofship(Integer.parseInt(ShipotOptions.submarinebutton.getText()));
                ShipotOptions.setShipsname(command);
                ShipotOptions.setColor(ShipotOptions.submarinebutton.getBackground());
            }
            case "destroyer" -> {
                ShipotOptions.setLengthofship(Integer.parseInt(ShipotOptions.destroyerbutton.getText()));
                ShipotOptions.setShipsname(command);
                ShipotOptions.setColor(ShipotOptions.destroyerbutton.getBackground());
            }
            default -> {
                JOptionPane.showMessageDialog(ShipotOptions, "SELECT A SHIP");
            }
        }

    }

}
