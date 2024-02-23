/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerFunctions;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/** SentToServer class that only sends data to server for processing 
 * 
 * @author Dalibor Markovic
 */
public class SentToServer {
	/** 
	 * @BufferedReader BR Its the buffered reader that is pasted to the constructor during init phase
	 * @PrintWriter PW Its the PrintWriter that is pasted to the constructor during init phase
	 * @Socket soc Its the Socket that is pasted to the constructor during init phase
	 * 
	 * */
    BufferedReader BR;
    PrintWriter PW;
    Socket soc;

    public SentToServer(PrintWriter pw1, BufferedReader br1) {
        this.BR =br1;
        this.PW = pw1;
       
        

    }
    /** Broadcast method used to send data to server
     * 
     * */
    public void broadcast(String data) {
        

        PW.println(data);
        PW.flush();

    }
    

}
