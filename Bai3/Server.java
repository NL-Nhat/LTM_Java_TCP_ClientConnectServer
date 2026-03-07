package BTTH_TCP.Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public String soNguyenTo(int so) {
        if(so < 2)
            return so + " ko phai so nto";
        
        for(int i = 2; i <= Math.sqrt(so); i++) {
            if(so % i == 0)
                return so + " ko phai so nto";
        }
        

        return so + " la so nto";
    }
    
    public String soChinhPhuong(int so) {
        int can = (int)Math.sqrt(so);
        if(can * can == so)
            return so + " la so chinh phuong";

        return so + " ko phai so chinh phuong";
    }

    public String soHoanHao(int so) {
        int tong = 0;
        for(int i = 1; i <= so/2; i++) {
            if(so % i == 0) {
                tong += i;
            }
        }

        if(tong == so)
            return so + " la so hoan hao";

        return so + " ko phai so hoan hao";
    }

    public String soAmstrong(int so) {
        int tong = 0;
        int a = so;
        int k = String.valueOf(so).length();

        while (a != 0) {
            int b = a % 10;
            tong += Math.pow(b, k);
            a /= 10;
        }

        if(tong == so)
            return so + " la so amstrong";

        return so + " ko phai so amstrong";
    }

    public String tongTich(int so) {
        int tong = 0, tich = 1;

        if(so == 0)
            tich = 0;

        while (so != 0) {
            int b = so % 10;
            tong += b;
            tich *= b;
            so /= 10;
        }

        return "Tong = " + tong + ", Tich = " + tich;
    }

    public int UCLN(int a, int b) {
        while (b != 0) {
            int tg = a % b;
            a = b;
            b = tg;
        }
        return a;
    }

    public int BCNN(int a, int b) {
        int bcnn = (a * b) / UCLN(a, b);
        return bcnn;
    }

    public String dem(String chuoi) {

        if (chuoi == null || chuoi.trim().isEmpty()) {
            return "Chuoi rong";
        }

        String kq = "";
        int[] dem = new int[256];

        for (int i = 0; i < chuoi.length(); i++) {
            dem[chuoi.charAt(i)]++;
        }

        for (int i = 0; i < 256; i++) {
            if (dem[i] > 0) {
                kq += (char)i + " : " + dem[i] + "\n";
            }
        }

        return kq;
    }

    public String tachTu(String chuoi) {
        if (chuoi == null || chuoi.trim().isEmpty()) {
            return "Ko co tu nao";
        }
        // trim() để bỏ khoảng trắng thừa ở 2 đầu
        // split("\\s+") để tách chuỗi bởi bất kỳ cụm khoảng trắng nào
        String[] tu = chuoi.trim().split("\\s+");
        String kq = "";

        for(int i = 0; i < tu.length; i++) {
            kq += tu[i] + "\n";
        }
        return kq;
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();

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
                        String kq = "";
                        kq += server.soNguyenTo(so);
                        kq += "\n" + server.soChinhPhuong(so);
                        kq += "\n" + server.soHoanHao(so);
                        kq += "\n" + server.soAmstrong(so);

                        output.writeUTF(kq);
                        output.flush();
                        break;
                    
                    case "2":
                        int m = Integer.valueOf(intput.readUTF());
                        System.out.println("So: " + m);
                        output.writeUTF(server.tongTich(m));
                        output.flush();
                        break;
                    
                    case "3":
                        int a = Integer.valueOf(intput.readUTF());
                        int b = Integer.valueOf(intput.readUTF());
                        System.out.println("So a: " + a + ", So b: " + b);
                        String text = "";
                        text += "UCLN la: " + server.UCLN(a, b);
                        text += "\nBCNN la: " + server.BCNN(a, b);
                        output.writeUTF(text);
                        output.flush();
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
                        String txt = "\n" + server.tachTu(data) + server.dem(data);
                        output.writeUTF(txt);
                        output.flush();
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
