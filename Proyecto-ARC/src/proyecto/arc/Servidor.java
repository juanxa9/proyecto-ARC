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
        /*Aqui creo dos tuberias la primera para un cliente y server, 
          para pasarle las varibles; y la segunda donde se conectan los clientes y donde
          se les pasa la id al server
        */
        DataInputStream dis,in;
        DataOutputStream dos,out;
        //Variable donde se guarda la id
        int id_cliente;
        final int PUERTO = 10578;
        int empieza = 1;
        int grupo = 0,
            grupo_aux = 0,
            decimal = 0,
            columna_aux = 0,
            contador = 0;

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
        System.out.println("Introduzca el numero iteraciones: ");
        datos.setS(entrada.nextInt());
        //Siempre es matriz cuadrada
        Socket matriz[][] = new Socket [datos.getN()/datos.getV()][datos.getN()/datos.getV()];
        
        try
        {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Se ha establecido la conexion con el servidor");
            
            //Maricel 
            sc = servidor.accept();
            dis = new DataInputStream(sc.getInputStream());
            dos = new DataOutputStream(sc.getOutputStream());
            dos.writeInt(datos.getN());
            dos.writeInt(datos.getV());
            dos.writeInt(datos.getS());
            
            if(dis.readInt()==1)
                    dos.writeInt(empieza);
            while(contador < datos.getN())
            {
                //Acepto los clientes
                Socket sc2 = servidor.accept();
                System.out.println("Se ha guardado el puerto:"+sc2.getPort());
                //Aqui estoy esparando su id
                in = new DataInputStream(sc2.getInputStream());
                //out = new DataOutputStream(sc2.getOutputStream());
                //Asigno el id del cliente
                id_cliente = in.readInt();
                System.out.println("Id_cliente"+id_cliente);
                //Calculamos el grupo
                grupo_aux = id_cliente / datos.getV();
                decimal = grupo_aux % 1;   //sacamos la parte decimal
                grupo = grupo_aux - decimal;
                //Calculamos la columna para guardar el socket en la matriz en funcion del id
                columna_aux = id_cliente % datos.getV();
                matriz[grupo][columna_aux]= sc2;
                contador++;
            }
            contador = 0;
            
            //
            while(contador < datos.getS())
            {
                for(int i= 0; i < datos.getN()/datos.getV(); i++)
                {
                    for(int j= 0; j <datos.getN()/datos.getV(); j++)
                    {     
                        in = new DataInputStream(matriz[i][j].getInputStream());
                        int tupapi = in.readInt();
                        if(tupapi == 2)
                        {
                            for(int k=0; k < datos.getN()/datos.getV(); k++)
                            {
                                if((matriz[i][j].getPort() != matriz[i][k].getPort()))
                                {
                                    out = new DataOutputStream(matriz[i][k].getOutputStream());
                                    out.writeUTF("Soy el cliente "+matriz[i][j].getPort()+" --> Coordenadas enviadadas a: ");  
                                }                            
                            }
                        }    
                    }
                }
                contador++;
            } 
        }    
        catch(IOException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
}
