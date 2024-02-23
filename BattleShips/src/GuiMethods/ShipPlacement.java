/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GuiMethods;

import GameFrame.MultiePlayerMainFrame;
import Grids.MyGrid;
import Grids.ShipOptions;
import GuiActionListeners.RotateListener;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/** ShipPlacement class is responsible for calculating the borders of placing the ships, checking the shot value on logic grid
 *
 * @author Dalibor Markovic
 *
 */
public class ShipPlacement {

    MultiePlayerMainFrame MF;
    boolean canplace = false;
    MyGrid MyGrid;
    int positionstart = 0, positionend = 0, column = 0, row = 0;

//stores values as 0 and 1 , 1 if the ship is on that place and 0 if not
    int[][] logicgrid = new int[10][10];

    public ShipPlacement(MyGrid mygrid, MultiePlayerMainFrame Game) {
        MF = Game;
        this.MyGrid = mygrid;

        //79.130.49.38
    }

    public void setShip(MouseEvent e) {
        if (MyGrid.getShiplenght() == 0) {

            JOptionPane.showMessageDialog(MyGrid.ShipOptions, "YOU HAVE TO SELECT A SHIP BEFORE PLACING IT TO THE BOARD");

        } else {
            int len = MyGrid.getShiplenght();

            placeship(e, len);
        }

    }

    public void previewShip(MouseEvent e) {
        if (MyGrid.getShiplenght() == 0) {

            JOptionPane.showMessageDialog(MyGrid, "YOU HAVE TO SELECT A SHIP TO SEE THE PREVIEW");

        } else {
            int len = MyGrid.getShiplenght();
            Color c = MyGrid.getColor();
            makevisible(len, e, c);

        }

    }

    public void stopPreview(MouseEvent e) {
        int len = MyGrid.getShiplenght();
        makeinvisible(len, e);

    }

    public void repositionShip(MouseEvent e) {
        int len = MyGrid.getShiplenght();
        repoShip(e, len);

    }

    /*FROM HERE AND FORWARD ARE ALL THE METHODS TO PREVIEW THE SHIPS ON THE BOARD FROM DESTROYER with LEN 2 ALL UP TO CARRIER with LEN 5*/
    private void makevisible(int len, MouseEvent e, Color c) {
        /*VERTICAL*/
        if (MyGrid.getOrientation() == true) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {

                    if (e.getSource().equals(MyGrid.button[i][j])) {

                        for (int k = 0; k < len; k++) {

                            if ((i > -1 && j > -1 && j < 10 && i + k < 10 && logicgrid[i + k][j] != 1 && logicgrid[i][j] != 1)) {

                                MyGrid.button[i + k][j].setIcon(MyGrid.ShipOptions.ship_image);
                                MyGrid.button[i + k][j].setBackground(c);
                                positionstart = i;
                                positionend = i + k;
                                column = j;
                                canplace = true;

                            } else if ((i > -1 && j > -1 && j < 10 && i + k < 10 && (logicgrid[i + k][j] == 1 | logicgrid[i][j] == 1))) {

                                canplace = false;
                                makeinvisible(len, e);

                            } else if ((i + k > 9 && j >= 0 && j < 10)) {
                                canplace = false;
                                makeinvisible(len, e);

                            } else if ((i + k > 9 && j >= 0 && j < 10)) {
                                canplace = false;
                                System.out.println("OUT OF BOUNDS");
                            }

                        }

                    }

                }
            }
            System.out.println("ROW START " + positionstart + "\n" + "ROW END" + positionend);
            /*HORIZONTAL*/
        } else if (MyGrid.getOrientation() == false) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (e.getSource().equals(MyGrid.button[i][j])) {

                        for (int k = 0; k < len; k++) {

                            if ((i > -1 && j > -1 && i < 10 && j + k < 10 && logicgrid[i][j + k] != 1 && logicgrid[i][j] != 1)) {

                                MyGrid.button[i][j + k].setIcon(MyGrid.ShipOptions.ship_image);
                                MyGrid.button[i][j + k].setBackground(c);
                                positionstart = j;
                                positionend = j + k;
                                row = i;
                                canplace = true;

                            } else if ((i > -1 && j > -1 && i < 10 && j + k < 10) && (logicgrid[i][j + k] == 1 | logicgrid[i][j] == 1)) {

                                canplace = false;
                                makeinvisible(len, e);

                            } else if (j + k > 9 && j >= 0 && j < 10) {
                                canplace = false;
                                makeinvisible(len, e);

                            } else {

                                canplace = false;
                                System.out.println("OUT OF BOUNDS");
                            }

                        }
                    }

                }

            }
            System.out.println("COL START " + positionstart + "\n" + "COL END" + positionend);

        }
    }

    private void makeinvisible(int len, MouseEvent e) {
        if (MyGrid.getOrientation() == true) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {

                    if (e.getSource().equals(MyGrid.button[i][j])) {
                        // if ((i - 1 >= 0 && j >= 0 && i + 1 < 10 && j < 10)) {
                        for (int k = 0; k < len; k++) {
                            if ((i > -1 && j > -1 && j < 10 && i + k < 10 && logicgrid[i + k][j] != 1)) {

                                MyGrid.button[i + k][j].setIcon(MyGrid.finalbackground);
                                MyGrid.button[i + k][j].setBackground(Color.blue);
                                canplace = false;
                            } else if ((i + k < 10 && j >= 0 && j < 10 && logicgrid[i + k][j] != 1)) {

                                MyGrid.button[i + k][j].setIcon(MyGrid.finalbackground);
                                MyGrid.button[i + k][j].setBackground(Color.blue);
                                canplace = false;
                            } else {
                                System.out.println("OUTOFBOUNDS");
                                canplace = false;
                            }

                        }

                    }
                }

            }

        } else if (MyGrid.getOrientation() == false) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {

                    if (e.getSource().equals(MyGrid.button[i][j])) {
                        // if ((i - 1 >= 0 && j >= 0 && i + 1 < 10 && j < 10)) {
                        for (int k = 0; k < len; k++) {
                            if ((i > -1 && j >= 0 && j + k < 10 && i < 10 && logicgrid[i][j + k] != 1)) {
                                MyGrid.button[i][j + k].setIcon(MyGrid.finalbackground);
                                MyGrid.button[i][j + k].setBackground(Color.blue);
                                canplace = false;
                            } else if ((i < 10 && j >= 0 && j + k < 10 && i >= 0 && logicgrid[i][j + k] != 1)) {

                                MyGrid.button[i][j + k].setIcon(MyGrid.finalbackground);
                                MyGrid.button[i][j + k].setBackground(Color.blue);
                                canplace = false;
                            } else {
                                System.out.println("OUTOFBOUNDS");
                                canplace = false;
                            }

                        }

                    }

                }

            }

        }
    }

    private void placeship(MouseEvent e, int len) {

        if (canplace != true) {

            JOptionPane.showMessageDialog(MyGrid, "YOU CANT PLACE THE SHIP AT THAT POSITION", "WARNING", 3);
        } else if (canplace == true) {
            System.out.println("ORIENTATION : " + MyGrid.getOrientation());

            if (MyGrid.getOrientation() == true) {
                System.out.println("ROW START " + positionstart + "\n" + "ROW END" + positionend);

                for (int i = positionstart; i < positionend; i++) {
                    for (int j = column; j < column; j++) {
                        if (e.getSource().equals(MyGrid.button[i][j])) {

                            System.out.println("TEST FOR LEN:" + len);
                            MyGrid.button[i][j].setIcon(MyGrid.ShipOptions.ship_image);

                        }
                    }

                }

                for (int i = positionstart; i <= positionend; i++) {

                    logicgrid[i][column] = 1;

                }

            } else if (MyGrid.getOrientation() == false) {
                boolean orie2 = MyGrid.getOrientation();
                System.out.println("COL START " + positionstart + "\n" + "COL END" + positionend);

                for (int i = positionstart; i < positionend; i++) {
                    for (int j = row; j < row; j++) {
                        if (e.getSource().equals(MyGrid.button[i][j])) {

                            MyGrid.button[j][i].setIcon(MyGrid.ShipOptions.ship_image);

                        }
                    }

                }
                for (int i = positionstart; i <= positionend; i++) {

                    logicgrid[row][i] = 1;

                }

            }

        }
        printgrid();
        positionend = 0;
        positionstart = 0;
        row = 0;
        MyGrid.setShiplenght();
    }

    private void repoShip(MouseEvent e, int len) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (e.getSource().equals(MyGrid.button[i][j])) {

                }
            }

        }

    }

    public void printgrid() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                System.out.print(logicgrid[i][j]);

            }
            System.out.print("\n");

        }

    }

    public int[][] getGrid() {

        return logicgrid;
    }

    public void checkhit() {
        String data = MyGrid.gethit();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (data.equals(MyGrid.button[i][j].getActionCommand())) {
                    System.out.println("Action Command ok " + data);
                    if (logicgrid[i][j] == 1) {

                        System.err.println("SHIP HIT");
                        MyGrid.button[i][j].setIcon(MyGrid.finalexplo);

                        String request = ":shiphit";
                        Color color = MyGrid.button[i][j].getBackground();
                        System.out.println(color.toString());
                        String finals = data + request + ":" + color;
                        MF.sentdata(finals);

                    }
                    if (logicgrid[i][j] == 0) {

                        System.err.println("SHIP MISS");
                        MyGrid.button[i][j].setIcon(MyGrid.miss);

                        String request = ":shipmiss";
                        String finals = data + request;
                        MF.sentdata(finals);
                    }

                }

            }
        }

    }

}
