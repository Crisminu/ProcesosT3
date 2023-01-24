package multihilos;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
public class Gestor implements Runnable{

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

    private final Socket socket;

    public Gestor(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try{
            //Coger el outputstream del cliente
            dos = new DataOutputStream(socket.getOutputStream());
            //Coger el inputstream del cliente
            dis = new DataInputStream(socket.getInputStream());
            InputStreamReader isr = new InputStreamReader(dis);

            String linea;
            String ruta = dis.readUTF();
            File file = new File(ruta);
            FileReader fr = new FileReader(file);
            Path path = Path.of(ruta);
            linea = Files.readString(path);
            dos.writeUTF(linea);

        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(dos != null){
                    dos.close();
                }
                if(dis != null){
                    dis.close();
                    socket.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
