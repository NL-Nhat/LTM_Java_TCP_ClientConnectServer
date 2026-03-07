package BTTH_TCP.Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8085);
            Scanner sc = new Scanner(System.in);

            DataInputStream intput = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            int choice;

            do {
                System.out.println("=========================");
                System.out.println("1. Kiem tra so nguyen to, chinh huong, hoan hao, amstrong");
                System.out.println("2. Tong, tich cac chu so");
                System.out.println("3. UCLN va BCNN");
                System.out.println("4. Chuoi dao");
                System.out.println("5. Dem so tu");
                System.out.println("0. Dung chuong trinh");
                System.out.println("=========================");
                System.out.print("Nhap lua chon: ");
                choice = sc.nextInt();
                sc.nextLine();
                
                switch (choice) {
                    case 1, 2:
                        output.writeUTF(String.valueOf(choice));
                        System.out.print("Nhap so: ");
                        int so = sc.nextInt();
                        output.writeUTF(String.valueOf(so));

                        output.flush();
                        break;
                
                    case 3:
                        output.writeUTF(String.valueOf(choice));
                        System.out.print("Nhap so thu 1: ");
                        int a = sc.nextInt();
                        output.writeUTF(String.valueOf(a));
                        System.out.print("Nhap so thu 2: ");
                        int b = sc.nextInt();
                        output.writeUTF(String.valueOf(b));

                        output.flush();
                        break;

                    case 4, 5:
                        output.writeUTF(String.valueOf(choice));
                        System.out.print("Nhap chuoi: ");
                        String chuoi = sc.nextLine();
                        output.writeUTF(chuoi);
                        output.flush();
                        break;

                    case 0:
                        System.out.println("Da dung chuong trinh");
                        sc.close();
                        socket.close();
                        return;

                    default:
                        System.out.println("Lua chon khong hop le vui long nhap lai");
                        break;
                }

                if(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
                    System.out.println("Ket qua tu Server: " + intput.readUTF());
                }
                
            } while (true);
                
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
