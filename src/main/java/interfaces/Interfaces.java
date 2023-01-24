package interfaces;

import java.net.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class Interfaces {

    public static void muestraInfoInterfaz(NetworkInterface interfaz) throws SocketException{
        // Nombre
        System.out.printf("Nombre: %s (%s), loopback: %s\n", interfaz.getDisplayName(), interfaz.getName(), interfaz.isLoopback() ? "sí" : "no");
        System.out.printf("Soporta multicast: %s\n", interfaz.supportsMulticast() ? "sí" : "no");
        System.out.printf("MTU: %d\n", interfaz.getMTU());

        // Dir. MAC
        byte[] dirMac = interfaz.getHardwareAddress();
        System.out.printf("Dir. MAC: ", interfaz.getDisplayName());
        boolean primerByte = true;
        if (dirMac != null) {
            for (int i = 0; i < dirMac.length; i++) {
                if (!primerByte) {
                    System.out.print(":");
                }
                System.out.printf("%x", dirMac[i]);
                primerByte = false;
            }
        } else {
            System.out.println("(N/A)");
        }
        System.out.printf("\n");
        //Ampliación para obtener la dirección ip
        //Lista de direcciones
        List<InterfaceAddress> lista = interfaz.getInterfaceAddresses();
        for(InterfaceAddress ip: lista){
            //Obtener ip
            System.out.println("Ip: " + ip.getAddress());
            //Diferenciar si es IPV4 o IPV6
            System.out.printf("Dirección: %s\n", (ip.getAddress() instanceof Inet4Address) ? "IPV4" : "IPV6");
            //Máscara
            System.out.println("Máscara: " + ip.getNetworkPrefixLength());
            System.out.println("Dirección de Broadcast: " + ip.getBroadcast());
        }


    }

    public static void main(String[] args) {

        try {
            Enumeration<NetworkInterface> enumInterfaces;
            enumInterfaces = NetworkInterface.getNetworkInterfaces();
            Iterator<NetworkInterface> itInterfaces = enumInterfaces.asIterator();
            int i = 1;
            while (itInterfaces.hasNext()) {
                NetworkInterface interfaz = itInterfaces.next();
                System.out.printf("---- [%d] ----\n", i++);
                muestraInfoInterfaz(interfaz);
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }

    /*
    Stream streamInterfaces;
    try {
      streamInterfaces = NetworkInterface.networkInterfaces();
      Iterator itInterfaces = streamInterfaces.iterator();
      while (itInterfaces.hasNext()) {
        NetworkInterface interfaz = (NetworkInterface) itInterfaces.next();
        muestraInfoInterfaz(interfaz);
      }
    } catch (SocketException ex) {
      ex.printStackTrace();
    }
     */
    }

}
