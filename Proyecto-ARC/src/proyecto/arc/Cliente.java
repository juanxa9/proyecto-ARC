/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.arc;
import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author juan2
 */
public class Cliente {
    
    public static void main(String[] args) throws IOException
    {
        final String HOST = "127.0.0.1";
        final int PUERTO = 10578;
        DataInputStream dis;
        DataOutputStream dos;
        
        
        try
        {
            Socket sc = new Socket(HOST, PUERTO);
            dis = new DataInputStream(sc.getInputStream());    
            dos = new DataOutputStream(sc.getOutputStream());
            
            dos.writeUTF("Mensaje de prueba del cliente");
            String mensajeCliente = dis.readUTF();
            System.out.println(mensajeCliente);
            sc.close();
                    
        }
        catch(IOException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
