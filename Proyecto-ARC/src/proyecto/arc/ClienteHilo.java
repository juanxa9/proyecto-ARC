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
    private int grupo;
    private DatosPrograma datos;
    Semaphore semaphore;
    //private Random random;
    //Maricel creo no hacia falta coordenadas aun
    //private Coordenadas coordenadas;
    //private int x,y,z;
    private Socket sc;
    private String uwu;
    private int p;
    
    public ClienteHilo(int id,DatosPrograma datos, int grupo){
        this.grupo = grupo;
        this.id = id;
        this.datos = datos;  
        p = 0;
        
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
                
                dos.writeInt(id);
                dos.flush();
                
                for(int i = 0; i<datos.s;i++){
                    dos.writeInt(2);
                    dos.flush();
                    
                }
                
                uwu = dis.readUTF();
                System.out.println(uwu+ sc.getLocalPort());
                   
                

                //dis.close();
                //dos.close();
                //sc.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        //desconnectar();
    }
    public int devolverLocalPort(){
        return sc.getLocalPort();
    }
}
