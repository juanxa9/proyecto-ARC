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
    private Random random;
    //Maricel creo no hacia falta coordenadas aun
    //private Coordenadas coordenadas;
    //private int x,y,z;
    
    public ClienteHilo(int id,DatosPrograma datos){

        this.id = id;
        this.datos = datos;
        //x = 0;
        //y = 0;
        //z = 0;
        
        /*try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            
        
     
    }
    
    @Override
    public void run(){
        final String HOST = "127.0.0.1";
        final int PUERTO = 10578;
        
        try {
            Socket sc = new Socket(HOST, PUERTO);
            dis = new DataInputStream(sc.getInputStream());    
            dos = new DataOutputStream(sc.getOutputStream());
            //Aqui poner las instrucciones que hara el hilo del cliente - jx
            
            for(i=0; i<datos.s ; i++){
                System.out.println("Soy el cliente " + id +" me han despertado " + socket.getPort());
                
                //Maricel
                //x = 10;
                //y = 5;
                //z = 7;
                
                /*dos.writeUTF("envioCoordenadas");
                dos.writeInt(x);
                dos.writeInt(y);
                dos.writeInt(z);*/
                // ahora veras, me dice que esta cerrado el socket
            }
                
            dis.close();
            dos.close();

        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //JX - he puesto el close fuera
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
