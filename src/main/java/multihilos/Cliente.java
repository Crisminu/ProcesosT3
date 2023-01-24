package multihilos;
// cliente:

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        // Definimos los parámetros de conexión
        InetAddress direccion = null; // La IP o Dirección de conexió
        Socket socket = null; // Socket para conectarnos a un servidor u otra máquina
        OutputStream os = null;
        InputStream is = null;
        int numCliente = 0; // Mi número de cliente
        int PUERTO = 50000;  // Puerto de conexión
        String direccionUsuario;

        System.out.println("Soy el cliente e intento conectarme");
        boolean acceso = true;

        try {
            // Vamos a indicar la dirección de conexión
            direccion = InetAddress.getLocalHost(); // dirección local (localhost)
            // Nos conectamos al servidor: dirección y puerto
            socket = new Socket(direccion, PUERTO);
            // Operamos con la conexión. En este caso recibimos los datos que nos mandan
            System.out.println("Conexión realizada con éxito");

            //Se le pide al Usuario el nombre de un fichero
            System.out.println("Introduce el nombre de un fichero y la ruta absoluta: ");
            direccionUsuario = sc.next();

            DataInputStream datos = new DataInputStream(socket.getInputStream());
            InputStreamReader isr = new InputStreamReader(datos);
            BufferedReader br = new BufferedReader(isr);

            DataOutputStream ps = new DataOutputStream(socket.getOutputStream());
            ps.writeUTF(direccionUsuario);
            System.out.println(datos.readUTF());
            // Escuchamos desde el servidor

            String contenido;
            while ((contenido=br.readLine())!=null){
                System.out.println(contenido);
            }
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Soy el cliente y cierro la conexión");
        }
    }
}
