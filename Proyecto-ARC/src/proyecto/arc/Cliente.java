package proyecto.arc;
import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

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
        Thread t = null;
        DataInputStream dis;
        DataOutputStream dos;
        int n,v,s,j,id;
        Cliente matriz[][];
        j = id = 0;
        double grupo_aux, decimal, grupo;
        
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
            
            // Inicializamos la matriz aqui, por ej: n= 100, v = 10, una matriz 10x10
            matriz = new Cliente [n/v][n/v];
            for(int i=0; i<n ; i++)
            {
                grupo_aux = i / v;
                decimal = grupo_aux % 1;   //sacamos la parte decimal
                grupo = grupo_aux - decimal;
                if(j == v){
                    j = 0;
                }
                matriz[(int)grupo][j] = new Cliente(id);
                clientes.add(new ClienteHilo(id, sc));
                id++;
                j++;
            }
            for (Thread thread : clientes) {
                thread.start();
            }
            
        }
        catch(IOException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         
    }
}
