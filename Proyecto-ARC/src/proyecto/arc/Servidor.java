/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.arc;

;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author juan2
 */
public class Servidor {

    
    public static void main(String[] args) throws IOException {
       
        ServerSocket servidor;
        //Socket con el primer cliente para crearlos
        Socket sc;
        DataInputStream dis;
        DataOutputStream dos;
        final int PUERTO = 10578;
        int empieza = 1;
        int x = 0,
            y = 0,
            z = 0,
            grupo = 0,
            grupo_aux = 0,
            decimal = 0,
            j = 0;
        
        
        
        DatosPrograma datos = new DatosPrograma();
        
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el numero de clientes: ");
        datos.setN(entrada.nextInt());
        System.out.println("Introduzca el numero de vecinos por grupo: ");
        datos.setV(entrada.nextInt());
        if((datos.getN() % datos.getV()) != 0){
            System.out.println("El numero de vecinos por cliente es incorrecto, vuelva a introducirlo: ");
            datos.setV(entrada.nextInt());
        }
        Socket matriz[][] = new Socket [datos.getN()/datos.getV()][datos.getN()/datos.getV()];
        System.out.println("Introduzca el numero iteraciones: ");
        datos.setS(entrada.nextInt());
        
        try
        {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Se ha establecido la conexion con el servidor");
            
            /*
            while(true)
            {
                sc = servidor.accept();
                dis = new DataInputStream(sc.getInputStream());
                dos = new DataOutputStream(sc.getOutputStream());
                dos.writeInt(datos.getN());
                dos.writeInt(datos.getV());
                dos.writeInt(datos.getS());
                if(dis.readInt()==1)
                    dos.writeInt(empieza);
                if(dis.readUTF().equals("envioCoordenadas")){
                    x = dis.readInt();
                    y = dis.readInt();
                    z = dis.readInt();
                }
                System.out.println("Estas son las coordenadas del cliente "+ sc.getPort()+ x +","+y+","+z);
                    
            }
         */
            //Maricel el puto amo
            sc = servidor.accept();
            dis = new DataInputStream(sc.getInputStream());
            dos = new DataOutputStream(sc.getOutputStream());
            dos.writeInt(datos.getN());
            dos.writeInt(datos.getV());
            dos.writeInt(datos.getS());
            
            
            if(dis.readInt()==1)
                    dos.writeInt(empieza);
            while(true)
            {
                Socket sc2 = servidor.accept();
                for( int i = 0; i<datos.getN();i++){
                    grupo_aux = i / datos.getV();
                    decimal = grupo_aux % 1;   //sacamos la parte decimal
                    grupo = grupo_aux - decimal;
                    if(j == datos.getV()){
                        j = 0;
                }
                    
                    matriz[(int)grupo][j]= sc2;
                    j++;
                    System.out.println(matriz[0][0].getPort());
                    ((ServidorHilo) new ServidorHilo(sc2)).start();
                    System.out.println("Se ha hecho2");
                }
            }
        }
        catch(IOException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
}
