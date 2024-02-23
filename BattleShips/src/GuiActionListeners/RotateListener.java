/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GuiActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Implements ActionListener for the rotation of the ships on user input
 *
 * @author Dalibor Markovic
 */
public class RotateListener implements ActionListener {

    private boolean isVertical;
    private boolean isHorizontal;
    private boolean orientation;

    public RotateListener() {
        isVertical = true;
        isHorizontal = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        orientation = isVertical;
        setOrientation(orientation);

    }

    public void setOrientation(boolean temp) {

        this.orientation = temp;
        isVertical = isHorizontal;
        isHorizontal = orientation;
        
    }

    public boolean getOrientation() {

        return orientation;
    }

}
