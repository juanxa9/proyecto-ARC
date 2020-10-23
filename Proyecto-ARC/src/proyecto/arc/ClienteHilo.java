/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.arc;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Semaphore;
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
    Semaphore semaphore;
    //private Random random;
    //Maricel creo no hacia falta coordenadas aun
    //private Coordenadas coordenadas;
    //private int x,y,z;
    private Socket sc;
    
    public ClienteHilo(int id,DatosPrograma datos){
        
        this.id = id;
        this.datos = datos;     
        
    }
    
    public void desconnectar() {
        try {
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        try {
            sc = new Socket("127.0.0.1",10578);
            dis = new DataInputStream(sc.getInputStream());    
            dos = new DataOutputStream(sc.getOutputStream());
            //Creo un semaforo`para escriba solo un cliente en writeInt
            semaphore = new Semaphore (1);
            
            try {
                semaphore.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Soy el cliente " + id);
            dos.writeInt(id);
            System.out.println("Soy el cliente " + id +"ENVIADO");
            semaphore.release();
           
            
            for(i=0; i<datos.s ; i++){
                System.out.println("Soy el cliente " + id +" me han despertado " + sc.getLocalPort());
                
                //Maricel
                //x = 10;
                //y = 5;
                //z = 7;
                
                //dos.writeUTF("coordena");
                /*dos.writeInt(x);
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
        desconnectar();
    }
    public int devolverLocalPort(){
        return sc.getLocalPort();
    }
}
