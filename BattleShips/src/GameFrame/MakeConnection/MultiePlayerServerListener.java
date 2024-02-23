
package GameFrame.MakeConnection;

import GameFrame.MultiePlayerMainFrame;
import GameFrame.RequestListener.ExitGameMultie;
import Grids.EnemyGrid;
import Grids.MyGrid;
import GuiMethods.ShipPlacement;
import LoginFrame.StartUpMenu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;

/** MultiePlayerServerListener is responsible of receiving from and to the players the data that each player has send 
 * 
 *
 * @author Dalibor Markovic
 * @version 0.1
 * 
 */
public class MultiePlayerServerListener extends Thread{
    
    BufferedReader br;
    ExitGameMultie exit;
    boolean run = true;
    MultiePlayerMainFrame game;
    MyGrid Mygrid;
    EnemyGrid enemy;
    public MultiePlayerServerListener(ExitGameMultie exit, BufferedReader br, MultiePlayerMainFrame aThis, MyGrid MyGrid, EnemyGrid EnemyGrid) {
        this.br = br;
        this.exit = exit;
        this.game = aThis;
        this.Mygrid = MyGrid;
        this.enemy = EnemyGrid;
    
    
    }
    
    
    @Override
    public void run(){
        
        while(run){
            
            try {
                String read = br.readLine();
                
                String[] recieveddata = read.split(":");
                
                String data = recieveddata[0];
                String request = recieveddata[1];
                
                System.out.println(data + " " + request);
                switch (request) {
                    case "EXITSESSIONTHREAD":
                        System.out.println("TEST FOR EXIT");
                        ActionEvent e = null;
                        exit.actionPerformed(e);
                        br.close();
                        run = false;
                        break;
                    case "setbackenemypanel":
                        System.out.println("TEST MESG FROM ENEMY");
                        break;
                    case "shotat":
                        Mygrid.sethit(data);
                        break;
                    case "shiphitpriview":
                        String color = recieveddata[2];
                        System.out.println("Ship hit priview ok");
                        enemy.sethit(data,color);
                        break;
                    case "shipmisspriview":
                        
                        System.out.println("Ship miss priview ok");
                        enemy.setmiss(data);
                        
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
            }
        
        
        
        
        
        
        }
    
    
    
    }
    
    
    
    
    
    
    
    
    
    
}
