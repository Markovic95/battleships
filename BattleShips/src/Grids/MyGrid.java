/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grids;

import GameFrame.MultiePlayerMainFrame;
import GuiActionListeners.GridButtonListener;
import GuiActionListeners.RotateListener;
import GuiMethods.ShipPlacement;
import LoginFrame.StartUpMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The class for the initialization and view of the players grid
 *
 * @author Dalibor Markovic
 */
public class MyGrid extends JPanel {

    public JButton[][] button;
    Image back, back1, back2;
    public ImageIcon finalbackground;
    public ImageIcon finalexplo;
    public ImageIcon miss;
    public Image sea;
    public Image explosion;
    public Image missed;
    String hitposition;
    public MouseListener bl;
    public ShipOptions ShipOptions;
    ShipPlacement Shipplacement;
    MultiePlayerMainFrame Game;
    RotateListener Rotate;

    public MyGrid(ShipOptions shipoptions, MultiePlayerMainFrame Game, RotateListener rotate, ShipPlacement placement) throws MalformedURLException, IOException {

        /*Start referencing*/
        this.Rotate = rotate;
        this.Shipplacement = placement;
        this.Game = Game;
        this.ShipOptions = shipoptions;
        Shipplacement = new ShipPlacement(this, Game);
        sea = null;
        explosion = null;
        missed = null;

        try {
            sea = ImageIO.read(new File("assets/images/water01.png"));
            explosion = ImageIO.read(new File("assets/images/explosion.png"));
            missed = ImageIO.read(new File("assets/images/miss.png"));
        } catch (IOException e) {

            e.getMessage();
        }
        /*end referencing*/
        initInstances();
        initComponents();
        setVisible(true);

    }

    private void initInstances() {
        setLayout(new GridLayout(10, 10));
        button = new JButton[10][10];
        setBackground(Color.GREEN);

        /**
         * new GridButtonListener class instance
         *
         */
        bl = new GridButtonListener(this);

    }

    private void initComponents() throws IOException {
        BufferedImage image = (BufferedImage) (sea);
        BufferedImage image1 = (BufferedImage) (explosion);
        BufferedImage image2 = (BufferedImage) (missed);
        back = image.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        back1 = image1.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        back2 = image2.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        finalbackground = new ImageIcon(back);
        finalexplo = new ImageIcon(back1);
        miss = new ImageIcon(back2);
        int cnt = 0;
        int i = 0, j = 0;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                button[i][j] = new JButton();
                button[i][j].setBackground(Color.blue);
                button[i][j].setPreferredSize(new Dimension(35, 35));
                button[i][j].setActionCommand(String.valueOf(cnt));
                button[i][j].addMouseListener(bl);
                // button[i][j].setTransferHandler(new TransferHandler("icon"));
                button[i][j].setIcon((Icon) finalbackground);
                cnt++;
                add(button[i][j]);

            }
        }

    }

    public boolean getOrientation() {
        boolean bool = Rotate.getOrientation();

        return bool;
    }

    public void setInfo(String inf) {
        String ve = "Vertical", ho = "Horizontal";
        if (getOrientation() == true) {
            Game.setINFO(inf + ve);

        } else if (getOrientation() == false) {

            Game.setINFO(inf + ho);
        }

    }

    //called by ShipPlacement
    public int getShiplenght() {

        int len = ShipOptions.getLengthofship();

        return len;

    }

    public Color getColor() {

        Color c = ShipOptions.getColor();

        return c;
    }

    public void setShiplenght() {

        ShipOptions.setLengthofship(0);

    }

    // called by GridButtonListener
    public void setShip(MouseEvent e) {

        Shipplacement.setShip(e);

    }

    // called by GridButtonListener
    public void previewShip(MouseEvent e) {

        Shipplacement.previewShip(e);
    }

    public void disposePreview(MouseEvent e) {

        Shipplacement.stopPreview(e);

    }

    public void reposition(MouseEvent e) {
        Shipplacement.repositionShip(e);

    }

    public void sethit(String data) {
        hitposition = data;
        Shipplacement.checkhit();
    }

    public String gethit() {

        return hitposition;
    }

}
