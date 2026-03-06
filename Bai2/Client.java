package BTTH_TCP.Bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8085);
            Scanner sc = new Scanner(System.in);

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String stop = "";

            while (!stop.equals("stop")) {
                System.out.print("Client: ");
                stop = sc.nextLine();
                output.writeUTF(stop);
                output.flush();

                System.out.println("Server: " + input.readUTF());
            }

            sc.close();
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
