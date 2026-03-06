package BTTH_TCP.Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8085);
            System.out.println("Doi Client ket noi");
            Socket socket = serverSocket.accept();
            System.out.println("Client da ket noi");

            String choice = "";

            DataInputStream intput = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while(!choice.equals("0")) {

                choice = intput.readUTF();
                System.out.println("Client: lua chon " + choice);

                switch (choice) {
                    case "1":
                        int so = Integer.valueOf(intput.readUTF());
                        System.out.println("So: " + so);
                        break;
                    
                    case "2":
                        int m = Integer.valueOf(intput.readUTF());
                        System.out.println("So: " + m);
                        break;
                    
                    case "3":
                        int a = Integer.valueOf(intput.readUTF());
                        int b = Integer.valueOf(intput.readUTF());
                        System.out.println("So a: " + a + ", So b: " + b);
                        break;

                    case "4":
                        String chuoi = intput.readUTF();
                        System.out.println("Chuoi: " + chuoi);
                        output.writeUTF(new StringBuilder(chuoi).reverse().toString());
                        output.flush();
                        break;

                    case "5":
                        String data = intput.readUTF();
                        System.out.println("Chuoi: " + data);
                        break;
                
                }
            }

            serverSocket.close();
            socket.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
