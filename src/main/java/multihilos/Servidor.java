package multihilos;
// servidor

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Servidor {

    public static void main(String[] argv) {

        //Definimos los Sockets
        ServerSocket servidor = null; // Socket de escucha del servidor
        Socket socket; // Socket para atender a un cliente
        int numCliente = 0; // Contador de clientes
        int PUERTO = 50000; // Puerto para esuchar

        System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto: " + PUERTO);

        try {
            // Apertura de socket para escuhar a través de un puerto
            servidor = new ServerSocket(PUERTO);
            servidor.setReuseAddress(true);
            //Bucle infinito para obtener peticiones del cliente
            while (true) {
                numCliente++;
                // Aceptamos la conexión
                socket = servidor.accept();
                System.out.println("\t Llega el cliente: " + numCliente);
                System.out.println("(Servidor) Conexión establecida" + socket.getInetAddress().getHostAddress());

                Gestor gestor = new Gestor(socket);
                //Hilo que gestionará al cliente separadamente
                new Thread(gestor).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
