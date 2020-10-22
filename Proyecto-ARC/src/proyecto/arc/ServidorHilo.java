/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.arc;

import java.io.*;
import java.net.*;
import java.util.logging.*;

/**
 *
 * @author aurel
 */
public class ServidorHilo extends Thread {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int id;
    public ServidorHilo(Socket socket) {
        this.socket = socket;
        //this.id = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    //Gestionamos qué hacer cuando recibamos un mensaje del cliente
    @Override
    public void run() {
        String accion = "";
        try {
            //Esta instruccion se queda a la espera de que le llegue algo del cliente
            accion = dis.readUTF();
            if(accion.equals("coordenada")){
                //Aqui el servidor se tiene que poner en contacto con los vecinos pero nose
                System.out.println( "coordenada"+accion);
                dos.writeUTF("recibido");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconnectar();
    }
}
