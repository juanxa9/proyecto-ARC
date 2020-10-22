/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.arc;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author juan2
 */
public class ClienteHilo extends Thread{
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int id;
    private int i;
    private DatosPrograma datos;
    
    public ClienteHilo(int id, Socket socket,DatosPrograma datos){
        this.socket = socket;
        this.id = id;
        this.datos = datos;
        
        
        
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        try {
            //Aqui poner las instrucciones que hara el hilo del cliente - jx
            
            for(i=0; i<datos.s ; i++){
                System.out.println("Soy el cliente " + id +" me han despertado");
                
            }
                
             
           
            
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
