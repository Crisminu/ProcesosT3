package org.example;
// servidor
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Servidor {

    public static void main(String[] argv) {

        //Definimos los Sockets
        ServerSocket servidor; // Socket de escucha del servidor
        Socket cliente; // Socket para atender a un cliente
        int numCliente = 0; // Contador de clientes 
        int PUERTO = 50000; // Puerto para esuchar
        
        System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto: " + PUERTO);

        try {
            // Apertura de socket para escuhar a través de un puerto
            servidor = new ServerSocket(PUERTO);
            // Atendemos a los clientes
            do {
                numCliente++;
                // Aceptamos la conexión
                cliente = servidor.accept();
                System.out.println("\t Llega el cliente: " + numCliente);
                // Esribimos a través de la salida del cliente. Es out porque le enviamos al cliente cosas
                //PrintStream ps = new PrintStream(cliente.getOutputStream());
                //ps.println("Usted es mi cliente " + numCliente); // Le mandamos al cliente este texto
                //Si queremos escribir valores con formato en el socket, es aconsejable usar DataOutputStream.
                DataOutputStream ps = new DataOutputStream(cliente.getOutputStream());
                ps.writeUTF("Usted es mi cliente: "+numCliente);
                DataInputStream datos = new DataInputStream(cliente.getInputStream());
                String uno=datos.readUTF();
                File file = new File(uno);
                FileReader fr = new FileReader(file);
                Path ruta = Path.of(uno);
                String contenido = Files.readString(ruta);
                ps.writeUTF(contenido);
                // Y cerramos la conexión porque ya no vamos a operar más con él
                cliente.close();
                System.out.println("\t Se ha cerrado la conexión con el cliente: " +numCliente);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
