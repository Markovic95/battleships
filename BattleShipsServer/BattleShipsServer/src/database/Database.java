/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/** Database class stores clients connected to the server 
 * 
 * @author Dalibor Markovic
 */
public class Database {
    HashMap<String, Socket> user_socket = new HashMap<>();
    HashMap<String, Socket> updatemap = new HashMap<>();
    String username;
    Socket usersocket;
    int gamecounter = 0 ;
    boolean bool = true;
    
    /** Constructor of database
     * 
     * 
     */
    public Database() {

    }
    
    public void setHash(String usernamename, Socket s) {
        this.username = usernamename;
        this.usersocket = s;
       user_socket.put(username, s);

        System.out.println(user_socket);
       

    }

    public HashMap<String, Socket> getHash() {
        
        return user_socket;

    }
    
    public void setGameCounter(int ptr){
        
        
        
        this.gamecounter +=ptr;
        
    
    }
    
    public int getGameCounter(){
        
        System.out.println("GameCounter : " + gamecounter);
        return this.gamecounter;
    }
    
    
    public void update(Object key){
        if(key == null ){
        
            System.out.println("User doesnt exist");
        }else{
        user_socket.remove(key.toString());
        }
    
    
    
    }
    public void setisActive(boolean bool){
        this.bool = bool;
    
    
    }
    
    public boolean getisActive(){
    
        return bool;
    
    }

}

