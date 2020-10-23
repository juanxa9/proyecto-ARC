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
        DataOutputStream dos;
        //Variable donde se guarda la id
        int id_cliente;
        final int PUERTO = 10578;
        int empieza = 1;
        int x = 0,
            y = 0,
            z = 0,
            grupo = 0,
            grupo_aux = 0,
            decimal = 0,
            columna_aux = 0,
            columna = 0,
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
            //Maricel 
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
                //Acepto los clientes
                Socket sc2 = servidor.accept();
                //Aqui estoy esparando su id
                in = new DataInputStream(sc2.getInputStream());
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
                System.out.println("Se ha guardado el puerto:"+sc2.getPort());
                //Este contador me sirve para saber cuando llega el ultimo cliente
                contador++;
                //Cuando llegamos al ultimo cliente mostramos matriz
                if( contador == (datos.getN()-1))
                {
                    for (int i = 0;i < datos.getV(); i++){
                        for (int j = 0;j < datos.getN(); j++){
                            System.out.println(matriz[i][j].getPort() + "");
                        }
                    }
                }
                    
                //System.out.println("Se ha hecho2");
                /*
                    //Esto esta mal, porque asigna en funcion del orden de llegada
                    //no en funcion del id del cliente - JX
 
               
                    ((ServidorHilo) new ServidorHilo(sc2)).start();
                    
                    
                    /*
                    Lo que tenia pensado hacer es una primera conexion con los clientes
                    donde sacamos el socket, y donde el cliente le pasa la coordenada
                    y su propio id. Estos 3 datos los guardamos en 3 matrices distintas
                    Y luego se crearia un for, donde le pasamos las 3 matrices al 
                    ServidorHilo y ahi el servidor ya le manda a los vecinos las coordenadas.
                    
                    Porque a partir del id, del socket de cada cliente y de las coordenadas
                    se puede hacer todo, porque teniendo el id del cliente, ya sabes el socket
                    que le corresponde. Porque al servidor se le conecta un socket con un id.
                    Y aunque lleguen en un orden distinto, se guarda el socket en la matriz
                    en funcion del id y no del orden de llegada.
  */
            }
        }
        catch(IOException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
}
