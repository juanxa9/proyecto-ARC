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
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException, InterruptedException
    {
        final String HOST = "127.0.0.1";
        final int PUERTO = 10578;
        ArrayList<Thread> clientes = new ArrayList<Thread>(); 
        //Maricel
        //Thread t = null;
        DataInputStream dis;
        DataOutputStream dos;
        int j,id,empInt,startSim;
        Cliente matriz[][];
        startSim = empInt = j = id = 0;
        double grupo_aux, decimal, grupo;
        Boolean empieza = false;
        Scanner entrada = new Scanner(System.in);
        DatosPrograma datos = new DatosPrograma();
        
        
        try
        {
            Socket sc = new Socket(HOST, PUERTO);
            dis = new DataInputStream(sc.getInputStream());    
            dos = new DataOutputStream(sc.getOutputStream());
            
            datos.n = dis.readInt();
            System.out.println("Me han llegado los clientes "+datos.n);
            datos.v = dis.readInt();
            System.out.println("Me han llegado el numero de vecinos por grupo "+ datos.v);
            datos.s = dis.readInt();
            System.out.println("Me han llegado que vamos a hacer "+ datos.s +" iteraciones");
            
            // Inicializamos la matriz aqui, por ej: n= 100, v = 10, una matriz 10x10
            matriz = new Cliente [datos.n/datos.v][datos.n/datos.v];
            
            for(int i=0; i<datos.n ; i++)
            {
                grupo_aux = i / datos.v;
                decimal = grupo_aux % 1;   //sacamos la parte decimal
                grupo = grupo_aux - decimal;
                //Pasamos de fila en la matriz
                if(j == datos.v){
                    j = 0;
                }
                matriz[(int)grupo][j] = new Cliente(id);
                clientes.add(new ClienteHilo(id, sc,datos));
                id++;
                j++;
            }
            
            System.out.println("Todos los clientes estan listos, presiona 1 +  enter "
                    + "para empezar la simulacion");
            
            startSim = entrada.nextInt();
            while(startSim != 1){
                System.out.println("No has introducido el numero correcto para"
                        + "iniciar la simulacion, presiona 1 + enter. ");
                startSim = entrada.nextInt();
            }
            dos.writeInt(startSim);
            empInt = dis.readInt();
            
            if(empInt == 1)
                empieza = true;
            
            for (Thread thread : clientes) {
                thread.start();
            }

            synchronized(clientes){
            if(!empieza)
                clientes.wait();
            
                
            }
            
            
        }
        catch(IOException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         
    }
}
