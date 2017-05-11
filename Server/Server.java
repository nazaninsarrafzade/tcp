
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
class Server {
    public static double Percent(long wholeSize,double count){
        return ((count/(double) wholeSize)*100);
    }
    public static void main(String args[]) throws Exception {
        String filename;
        System.out.println("Enter File Name: ");
        Scanner scanner = new Scanner(System.in);
        filename = scanner.nextLine();
        scanner.close();
        while (true) {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Waiting for request");
            Socket s = ss.accept();
            System.out.println("Connected With " + s.getInetAddress().toString());
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            try {
                String str = "";

                str = din.readUTF();
                System.out.println("SendGet....Ok");

                if (!str.equals("stop")) {

                    System.out.println("Sending File: " + filename);
                    dout.writeUTF(filename);
                    dout.flush();

                    File f = new File(filename);
                    FileInputStream fin = new FileInputStream(f);
                    long sz = (int) f.length();

                    byte b[] = new byte[1024];

                    int read;
                    int percentage=0;

                    dout.writeUTF(Long.toString(sz));
                    dout.flush();




                    while ((read = fin.read(b)) != -1) {


                        dout.write(b, 0, read);
                        dout.flush();
                        percentage+=read;

                  //      System.out.println(read);
                 //       System.out.println(Percent(sz,count));
                        System.out.println(((float) (percentage*100))/(float)sz);
                    }
                    fin.close();

                    System.out.println("..ok");
                    dout.flush();
                }
           //     dout.writeUTF("stop");
                System.out.println("Send Complete");
                dout.flush();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occured");
            }
            din.close();
            s.close();
            ss.close();
        }
    }
}
