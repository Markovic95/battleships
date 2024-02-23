/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GuiActionListeners;

import GameFrame.MultiePlayerMainFrame;
import Grids.MyGrid;
import Grids.ShipOptions;
import GuiMethods.ShipPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Implements the MouseAdapter listener for displaying , moving and setting the ships on the board
 *
 * @author Dalibor Markovic
 */
public class GridButtonListener extends MouseAdapter {

    ShipOptions shipOptions;
    ShipPlacement shipPlacement;
    MultiePlayerMainFrame game;
    MyGrid mygrid;

    public GridButtonListener(MyGrid aThis) {

        mygrid = aThis;

    }
    //79.131.125.75

    @Override
    public void mouseClicked(MouseEvent e) {
        // System.out.println(shipOptions.getLengthofship());
        //mygrid.setInfo(String.valueOf("SHIP: "+shipOptions.getShipsname()+"LENGHT: "+shipOptions.getLengthofship()+" "));
        //mygrid.setShip();
        if(e.getButton()== MouseEvent.BUTTON1){
        mygrid.setShip(e);
        }else if(e.getButton() == MouseEvent.BUTTON3){
        mygrid.reposition(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (mygrid.getShiplenght() != 0) {

            mygrid.previewShip(e);
            

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (mygrid.getShiplenght() != 0) {
            mygrid.disposePreview(e);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
