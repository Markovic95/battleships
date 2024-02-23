
package GameFrame;

import GameFrame.MakeConnection.MultiePlayerServerListener;
import GameFrame.RequestListener.ExitGameMultie;
import Grids.EnemyGrid;
import Grids.MyGrid;
import Grids.ShipOptions;
import GuiActionListeners.RotateListener;
import GuiMethods.ShipPlacement;
import LoginFrame.StartUpMenu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import javax.swing.JOptionPane;

/** The main Frame of the game that houses the Grids for each player to be able to play the game
 *
 * @author Dalibor Markovic
 */
public class MultiePlayerMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MultiePlayerMainFrame
     */
    StartUpMenu StartUpMenu;
    BufferedReader br;
    PrintWriter pw;
    int port;
    EnemyGrid EnemyGrid;
    MyGrid MyGrid;
    ShipOptions ShipOptions;
    
    RotateListener rotate;
    ShipPlacement placement;

    public MultiePlayerMainFrame(StartUpMenu startmenu, String GameSessionServerPort) throws MalformedURLException, IOException {
        StartUpMenu = startmenu;
        port = Integer.parseInt(GameSessionServerPort);
        rotate = new RotateListener();
        ShipOptions = new ShipOptions();
        EnemyGrid = new EnemyGrid(this,placement);
        MyGrid = new MyGrid(ShipOptions, this , rotate , placement);

        initComponents();
        mainenemypanel.setVisible(false);
        iniconnection();
        /*##################################################################
        at first when the game starts it connects to the game session server 
         */
       
        /*##################################################################*/
    }
    private void iniconnection(){
     try {
            Socket socket = new Socket(StartUpMenu.ServerIP, port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
            if (socket.isConnected()) {

                ExitGameMultie exit = new ExitGameMultie(this, StartUpMenu);
                MultiePlayerServerListener listentoserversessio = new MultiePlayerServerListener(exit, br, this , MyGrid,EnemyGrid);
                listentoserversessio.start();

            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex + "\n" + """
                                                                Ο Σερβερ Πιθανός να ειναι Down@@@@@
                                                                """);
            System.out.println(ex);
        }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainpanel = new javax.swing.JPanel();
        mainenemypanel = new javax.swing.JPanel();
        mymainpanel = new javax.swing.JPanel();
        mainleftpanel = new javax.swing.JPanel();
        leftsouthpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        myboardlabel = new javax.swing.JLabel();
        enemyboardlabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        startgamebutton = new javax.swing.JButton();
        rotatebutton = new javax.swing.JButton();
        Exitbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainenemypanel.add(EnemyGrid);

        mymainpanel.add(MyGrid);

        leftsouthpanel.add(ShipOptions);

        jLabel1.setText("               SHIPS");

        javax.swing.GroupLayout mainleftpanelLayout = new javax.swing.GroupLayout(mainleftpanel);
        mainleftpanel.setLayout(mainleftpanelLayout);
        mainleftpanelLayout.setHorizontalGroup(
            mainleftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainleftpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainleftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leftsouthpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainleftpanelLayout.setVerticalGroup(
            mainleftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainleftpanelLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftsouthpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("INFO");

        startgamebutton.setText("Start Game");
        startgamebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startgamebuttonActionPerformed(evt);
            }
        });

        rotatebutton.setText("Rotate");
        rotatebutton.addActionListener(rotate);

        Exitbutton.setText("Exit");
        Exitbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                .addComponent(mainleftpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(myboardlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(enemyboardlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232))
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addComponent(mymainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(mainenemypanel, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainpanelLayout.createSequentialGroup()
                                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(startgamebutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Exitbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(mainpanelLayout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(rotatebutton)))
                        .addContainerGap(21, Short.MAX_VALUE))))
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainpanelLayout.createSequentialGroup()
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myboardlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enemyboardlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mymainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mainenemypanel, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rotatebutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainpanelLayout.createSequentialGroup()
                                .addComponent(Exitbutton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(startgamebutton))))
                    .addComponent(mainleftpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startgamebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startgamebuttonActionPerformed
        // TODO add your handling code here:
        mainenemypanel.setVisible(true);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                MyGrid.button[i][j].removeMouseListener(MyGrid.bl);
            }

        }
        String temp = "0";
        String request = ":findenemy";
        String finals = (temp+request);
        sentdata(finals);

    }//GEN-LAST:event_startgamebuttonActionPerformed

    private void ExitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitbuttonActionPerformed
        // TODO add your handling code here:
      
            System.out.println("EXITED BY SENDING EXIT CODE AND REQUEST LISTENER MAKES THE EXIT ");
            String da = "0";
            String exit = ":SESSIONEXIT";
            String finalrequest = da + exit;
            System.out.println("senddata debug data" + da + " " + exit);
            sentdata(finalrequest);
            
        
        
        
    }//GEN-LAST:event_ExitbuttonActionPerformed
    public void sentdata(String datatosent) {
        pw.println(datatosent);
        pw.flush();

    }

    public void setINFO(String inf) {
        String temp = inf;
        jTextArea1.append(inf + "\n");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exitbutton;
    private javax.swing.JLabel enemyboardlabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel leftsouthpanel;
    private javax.swing.JPanel mainenemypanel;
    private javax.swing.JPanel mainleftpanel;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JLabel myboardlabel;
    private javax.swing.JPanel mymainpanel;
    private javax.swing.JButton rotatebutton;
    private javax.swing.JButton startgamebutton;
    // End of variables declaration//GEN-END:variables

}
