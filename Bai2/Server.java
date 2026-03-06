package BTTH_TCP.Bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public String chuoidao(String mgs) {
         String dao = new StringBuilder(mgs).reverse().toString();
         return dao;
    }

    public String hoathuong(String mgs) {
        //Viết hoa chữ cái đầu
        String result = mgs.substring(0,1).toUpperCase() + mgs.substring(1).toLowerCase();

        return result;
    }

    public int dem(String mgs) {
        if (mgs == null || mgs.trim().isEmpty()) {
            return 0;
        }
        // trim() để bỏ khoảng trắng thừa ở 2 đầu
        // split("\\s+") để tách chuỗi bởi bất kỳ cụm khoảng trắng nào
        String[] words = mgs.trim().split("\\s+");
        return words.length;
    }

    public static void main(String[] args) {
        try {

            Server server = new Server();
            
            ServerSocket serverSocket = new ServerSocket(8085);
            System.out.println("Doi Client ket noi");
            Socket socket = serverSocket.accept();
            System.out.println("Client da ket noi");

            String stop = "";
            int chose;

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            
            while (true) {
                stop = input.readUTF();
                System.out.println("Client: " + stop);
                
                if(!stop.equals("stop")) {
                    do {
                        System.out.println("=========================");
                        System.out.println("1. Chuoi dao");
                        System.out.println("2. Chuoi in hoa");
                        System.out.println("3. Chuoi thuong");
                        System.out.println("4. Chuoi vua hoa vua thuong");
                        System.out.println("5. Dem so tu");
                        System.out.println("=========================");
                        System.out.print("Nhap lua chon: ");
                        chose = sc.nextInt();

                        switch (chose) {
                            case 1:
                                output.writeUTF(server.chuoidao(stop));
                                output.flush();
                                break;
                            case 2:
                                output.writeUTF(stop.toUpperCase());
                                output.flush();
                                break;
                            
                            case 3:
                                output.writeUTF(stop.toLowerCase());
                                output.flush();
                                break;

                            case 4:
                                output.writeUTF(server.hoathuong(stop));
                                output.flush();
                                break;
                            
                            case 5:
                                output.writeUTF(String.valueOf(server.dem(stop)));
                                output.flush();
                                break;
                            default:
                                System.out.println("Lua chon khong hop le. Vui long chon lai");
                                break;
                        }
                    } while (chose != 1 && chose != 2 && chose != 3 && chose != 4 && chose != 5);
                }
                else {
                    output.writeUTF("Da dung chuong trinh");
                    output.flush();
                    sc.close();
                    socket.close();
                    serverSocket.close();
                    return;
                } 
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
