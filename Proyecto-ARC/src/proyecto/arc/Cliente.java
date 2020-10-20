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
    int id;
    
    public Cliente(int id){
        this.id = id;
    }
    
    public static void main(String[] args) throws IOException
    {
        final String HOST = "127.0.0.1";
        final int PUERTO = 10578;
        ArrayList<Thread> clientes = new ArrayList<Thread>(); 
        DataInputStream dis;
        DataOutputStream dos;
        int n,v,s,p;
        int matriz[][] = null;
        
        try
        {
            Socket sc = new Socket(HOST, PUERTO);
            dis = new DataInputStream(sc.getInputStream());    
            dos = new DataOutputStream(sc.getOutputStream());
            
            n = dis.readInt();
            System.out.println("Me han llegado los clientes "+n);
            v = dis.readInt();
            System.out.println("Me han llegado el numero de vecinos por grupo "+ v);
            s = dis.readInt();
            System.out.println("Me han llegado que vamos a hacer "+ s +" iteraciones");
            
            for(int i=0; i<n ; i++)
            {
                for(int j = 0; j < v ; j++){
                    p = i+j;
                    matriz[i][j] = new Cliente(p);
                }
                    
            }
            
            
        }
        catch(IOException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Thread thread: clientes)
        {
            clientes.add(thread);
        }
    }
}
