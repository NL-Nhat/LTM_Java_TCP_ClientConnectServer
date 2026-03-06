package BTTH_TCP.Bai1;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8085);
            System.out.println("Doi Client ket noi");
            Socket socket = serverSocket.accept();
            System.out.println("Client da ket noi");

            DataOutputStream outPut = new DataOutputStream(socket.getOutputStream());

            LocalDateTime day = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            // Sử dụng phương thức .format() để chuyển sang String
            String txt = day.format(formatter);
            
            outPut.writeUTF(txt);
            outPut.flush();

            serverSocket.close();
            socket.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
