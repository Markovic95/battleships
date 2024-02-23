/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grids;

import GameFrame.MultiePlayerMainFrame;
import GuiActionListeners.GridButtonListener;
import GuiActionListeners.RotateListener;
import GuiMethods.ShipPlacement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @param button Represents a 2d button array
 * @param bl The MouseListener for each button of the 2d button array
 * @param ships The ShipPlacement Class that is passed to the constructor of
 * this class
 * @param MF The MultiePlayerMainFrame class that is passed to the constructor
 * of this class
 */
/**
 * The class for the enemy grid on each players Frame
 *
 * @author Dalibor Markovic
 *
 */
public class EnemyGrid extends JPanel {

    public JButton[][] button;
    public MouseListener bl;
    Image back, back1, back2;
    public ImageIcon finalbackground;
    public ImageIcon finalexplo;
    public ImageIcon miss;
    public Image sea;
    public Image explosion;
    public Image missed;
    ShipPlacement ships;

    MultiePlayerMainFrame MF;

    public EnemyGrid(MultiePlayerMainFrame aThis, ShipPlacement placement) throws IOException {
        MF = aThis;
        ships = placement;
        setLayout(new GridLayout(10, 10));
        setBackground(Color.GRAY);

        BufferedImage sea = null;
        BufferedImage explosion = null;
        BufferedImage missed = null;

        try {
            sea = ImageIO.read(new File("assets/images/water01.png"));
            explosion = ImageIO.read(new File("assets/images/explosion.png"));
            missed = ImageIO.read(new File("assets/images/miss.png"));

        } catch (IOException e) {

            e.getMessage();
        }

        initInstances();
        initComponents(sea, explosion, missed);
        setVisible(true);

    }

    private void initInstances() {
        setLayout(new GridLayout(10, 10));
        button = new JButton[10][10];
        setBackground(Color.GREEN);
        /*new GridButtonListener class instance*/
        bl = new enemyGridButtonListener(this);

    }

    /**
     * Method for the initialization of the games grid
     *
     * @param image Represents the background of the sea
     * @param image1 Represents the explosion of a ship
     * @param image2 Represents the missed shot
     *
     *
     *
     */
    private void initComponents(BufferedImage sea1, BufferedImage explosion1, BufferedImage missed1) throws IOException {

        BufferedImage image = (sea1);
        BufferedImage image1 = (explosion1);
        BufferedImage image2 = (missed1);
        back = image.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        back1 = image1.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        back2 = image2.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        finalbackground = new ImageIcon(back);
        finalexplo = new ImageIcon(back1);
        miss = new ImageIcon(back2);
        int i = 0, j = 0;
        int cnt = 0;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                button[i][j] = new JButton();
                button[i][j].setBackground(Color.BLUE);
                button[i][j].setPreferredSize(new Dimension(35, 35));
                button[i][j].setActionCommand(String.valueOf(cnt));
                button[i][j].addMouseListener(bl);
                // button[i][j].setTransferHandler(new TransferHandler("icon"));
                cnt++;
                button[i][j].setIcon((Icon) finalbackground);
                add(button[i][j]);

            }
        }

    }

    /**
     * Gets the button clicked on the enemy grid of the current players round
     * and sends the data to the server
     *
     *
     *
     *
     *
     */
    public void shot(MouseEvent e) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                /**
                 * @param command The ActionCommand of the button pressed
                 * @param request The request to be send to the server
                 * @param finals The final concatenated String that is send to
                 * the server
                 *
                 *
                 */
                if (e.getSource().equals(button[i][j])) {
                    String command = button[i][j].getActionCommand();
                    String temp = command;
                    String request = ":shot";
                    String finals = temp + request;
                    MF.sentdata(finals);

                }
            }
        }

    }

    /**
     * sethit method is called from the MultiePlayerServerListener class and
     * gets as inputs the data that represents the button that is hit and the
     * color that represents the type of shit
     *
     *
     *
     */
    public void sethit(String data, String color) {
        System.err.println("ship sethit ok");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (data.equals(button[i][j].getActionCommand())) {

                    button[i][j].setIcon(finalexplo);
                    MF.setINFO("SHIP HIT AT " + data + " SHIP COLOR " + color);
                }

            }

        }
    }

    /**
     * setmiss method is called from the MultiePlayerServerListener class and
     * gets as inputs the data that represents the button that is shot but no
     * ship is hit
     *
     *
     *
     */
    public void setmiss(String data) {
        System.err.println("ship setmiss ok");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (data.equals(button[i][j].getActionCommand())) {

                    button[i][j].setIcon(miss);
                    MF.setINFO("SHIP MISS AT" + data);
                }

            }

        }
    }

}

class enemyGridButtonListener extends MouseAdapter {

    /**
     * @param enemy represents the EnemyGrid class passed to the constructor of
     * this class
     *
     *
     */
    EnemyGrid enemy;

    public enemyGridButtonListener(EnemyGrid aThis) {
        enemy = aThis;
    }

    /**
     * MouseAdapter evt action that calls the shot method in the EnemyGrid class
     * & passed the originator of the call (e)
     *
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            enemy.shot(e);

        }

    }
}
