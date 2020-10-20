/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.arc;

import com.sun.istack.internal.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 * @author juan2
 */
public class Servidor {

    
    public static void main(String[] args) throws IOException {
       
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream dis;
        DataOutputStream dos;
        final int PUERTO = 10578;
        
        try
        {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Se ha establecido la conexion con el servidor");
            
            while(true)
            {
                sc = servidor.accept();
                dis = new DataInputStream(sc.getInputStream());
                dos = new DataOutputStream(sc.getOutputStream());
                String mensajePrueba = dis.readUTF();
                System.out.println(mensajePrueba);
                dos.writeUTF("La conexion con el servidor ha sido correcta");
                sc.close();
                System.out.println("El cliente se ha desconectado del servidor");
            }
        }
        catch(IOException ex)
        {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
}
