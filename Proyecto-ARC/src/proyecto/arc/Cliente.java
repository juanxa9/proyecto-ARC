package proyecto.arc;
import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan2
 */
public class Cliente {
    
    public static void main(String[] args) throws IOException
    {
        final String HOST = "127.0.0.1";
        final int PUERTO = 10578;
        Thread t;
        ArrayList<Thread> clientes = new ArrayList<Thread>(); 
        DataInputStream dis;
        DataOutputStream dos;
        DatosPrograma datos;
        int clientesTotales;
        
        
        t = new Thread();
        datos = new DatosPrograma();
        clientesTotales = datos.getN();
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
        
        for(int i=0; i<clientesTotales;i++)
        {
            clientes.add(t);
        }
        
        
    }
}
