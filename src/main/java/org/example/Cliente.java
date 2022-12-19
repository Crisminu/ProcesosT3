package org.example;
// cliente:

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente {

    public static void main(String argv[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        // Definimos los parámetros de conexión
        InetAddress direccion = null; // La IP o Dirección de conexión
        Socket servidor = null; // Socket para conectarnos a un servidor u otra máquina
        int numCliente = 0; // Mi número de cliente
        int PUERTO = 5000;  // Puerto de conexión
        String direccionUsuario;

        System.out.println("Soy el cliente e intento conectarme");
        boolean acceso = true;

        try {
            // Vamos a indicar la dirección de conexión
            direccion = InetAddress.getLocalHost(); // dirección local (localhost)
            // Nos conectamos al servidor: dirección y puerto
            servidor = new Socket(direccion, PUERTO);
            // Operamos con la conexión. En este caso recibimos los datos que nos mandan
            System.out.println("Conexión realizada con éxito");
            //Se le pide al Usuario el nombre de un fichero
            System.out.println("Introduce el nombre de un fichero y la ruta absoluta: ");
            direccionUsuario = sc.next();

            // Es inputStream porque los recibimos
            DataInputStream datos = new DataInputStream(servidor.getInputStream());
            InputStreamReader isr = new InputStreamReader(datos, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            //
            DataOutputStream ps = new DataOutputStream(servidor.getOutputStream());
            ps.writeUTF(direccionUsuario);
            // Si queremos leer normal
            //System.out.println(datos.readLine());
            // Si leemos con formato

            System.out.println(datos.readUTF());
            String contenido;
            while ((contenido=br.readLine())!=null){
                System.out.println(contenido + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
            servidor.close();
            System.out.println("Soy el cliente y cierro la conexión");
        }

    }
}
