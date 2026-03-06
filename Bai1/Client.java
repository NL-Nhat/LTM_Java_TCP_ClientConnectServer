package BTTH_TCP.Bai1;

import java.io.DataInputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            
            Socket socket = new Socket("localhost", 8085);

            DataInputStream input = new DataInputStream(socket.getInputStream());

            System.out.println(input.readUTF());

            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
