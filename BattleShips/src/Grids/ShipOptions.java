/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grids;

import GuiActionListeners.ShipSelector;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** The class for the main side panel that houses the ships that the 
 * player can use
 *
 * @author Dalibor Markovic
 */
public class ShipOptions extends JPanel {
    
    public int length;
    public JLabel destroyer, battleship, submarine, carrier, cruiser;
    public JButton destroyerbutton, battleshipbutton, submarinebutton, carrierbutton, cruiserbutton;
    public ImageIcon ship_image;
    Image url;
    public String shipsname;
    public Color col;
    Image temp;
    ShipSelector shipselector;
   
    
    public ShipOptions() throws MalformedURLException, IOException {
    /** constructor of this class 
     * @param shipselector represents the ShipSelector class 
     * 
     */
        url = ImageIO.read(new File("assets/images/ships.png"));
       // ship_image = new ImageIcon(url);
        setLayout(new GridLayout(5, 2, 5, 10));
        shipselector = new ShipSelector(this);
        BufferedImage image = (BufferedImage) (url);
        temp = image.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        ship_image = new ImageIcon(temp);
        initComponents();
        setVisible(true);

    }

    private void initComponents() {
        /*LABELS CREATION*/
        carrier = new JLabel();
        carrier.setIcon(ship_image);
        
        
        battleship = new JLabel();
        battleship.setIcon(ship_image);
        
        
        cruiser = new JLabel();
        cruiser.setIcon(ship_image);
        
       
        submarine = new JLabel();
        submarine.setIcon(ship_image);
        
        
        destroyer = new JLabel();
        destroyer.setIcon(ship_image);
        
        
        /**BUTTONS CREATION
         * @Button carrierbutton The button representing the carrier
         * @Button battleshipbutton The button representing the battleshipbutton
         * @Button cruiserbutton The button representing the cruiserbutton
         * @Button submarinebutton The button representing the submarinebutton
         * @Button destroyerbutton The button representing the destroyerbutton
         * 
         * 
         * */
        carrierbutton = new JButton("5");
        carrierbutton.setActionCommand("carrier");
        carrierbutton.addActionListener(shipselector);
        carrierbutton.setBackground(Color.MAGENTA);
        
        battleshipbutton = new JButton("4");
        battleshipbutton.setActionCommand("battleship");
        battleshipbutton.addActionListener(shipselector);
        battleshipbutton.setBackground(Color.red);
        
        cruiserbutton = new JButton("3");
        cruiserbutton.setActionCommand("cruiser");
        cruiserbutton.addActionListener(shipselector);
        cruiserbutton.setBackground(Color.ORANGE);
        
        submarinebutton = new JButton("3");
        submarinebutton.setActionCommand("submarine");
        submarinebutton.addActionListener(shipselector);
        submarinebutton.setBackground(Color.BLUE);
        
        destroyerbutton = new JButton("2");
        destroyerbutton.setActionCommand("destroyer");
        destroyerbutton.addActionListener(shipselector);
        destroyerbutton.setBackground(Color.green);
        
        
        /*ADDING LABELS AND BUTTONS TO THE PANEL*/
        add(carrier);
        add(carrierbutton);
        add(battleship);
        add(battleshipbutton);
        add(cruiser);
        add(cruiserbutton);
        add(submarine);
        add(submarinebutton);
        add(destroyer);
        add(destroyerbutton);
    }
    
    /**GETTERs AND SETTERs FOR THE SHIPS LENGHT
     * @return length The length of the ship
     * @return shipsname The ships name (not used)
     * @return col The color that represents the type of the ship
     * */
    
    
    /**sets the ships length*/
    public void setLengthofship(int len){
        this.length = len;
        System.out.println("LENGHT OF SHIP :"+length);
    
    }
    /** gets the ships length
     * @return  length
     */
    public int getLengthofship(){
    
        return length;
    }
    
    /** set the ships name
     */
    public void setShipsname(String name){
        this.shipsname = name;
        
    
    }
    /** gets the ships name
     * @return shipsname
     */
    public String getShipsname(){
    
    
        return shipsname;
    }
    /** sets the color of the ship
     * 
     */
    public void setColor(Color c){
        
        this.col = c;
    
    
    }
    /** get the ships color
     * @return col
     */
    public Color getColor(){
    
    
        return col;
    }
    
    

}
