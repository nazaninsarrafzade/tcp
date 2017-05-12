
        package Server;

        import javafx.scene.control.ProgressBar;

        import javax.swing.*;
        import java.awt.*;
        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.Scanner;

public class Server{
    public void run (String a)throws Exception{
        String filename;
        Scanner sc=new Scanner(System.in);
        filename=a;
        sc.close();
        while(true)
        {
            //create server socket on port 5000
            ServerSocket ss=new ServerSocket(5001);
            System.out.println ("Waiting for request");
            Socket s=ss.accept();
            final int MAX = 300;
            final JFrame frame = new JFrame("JProgress Demo");


            final JProgressBar pb = new JProgressBar();
            pb.setMinimum(0);
            pb.setMaximum(100);
            pb.setStringPainted(true);

            // add progress bar
            frame.setLayout(new FlowLayout());
            frame.getContentPane().add(pb);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setVisible(true);


            System.out.println ("Connected With "+s.getInetAddress().toString());
            DataInputStream din=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            try{
                String str="";

                str=din.readUTF();
                System.out.println("SendGet....Ok");

                if(!str.equals("stop")){

                    System.out.println("Sending File: "+filename);
                    dout.writeUTF(filename);
                    dout.flush();

                    File f=new File(filename);
                    FileInputStream fin=new FileInputStream(f);
                    long sz=(int) f.length();

                    byte b[]=new byte [1024];

                    int read;

                    dout.writeUTF(Long.toString(sz));
                    dout.flush();

                    System.out.println ("Size: "+sz);
                    System.out.println ("Buf size: "+ss.getReceiveBufferSize());
                    int m=0,i=0;
                    while(((read = fin.read(b)) != -1 )){
                        dout.write(b, 0, read);
                        dout.flush();
                        m=m+read;
                        pb.setValue((int) Math.round(m*100/sz));
                      //  int h= (int) (m*100/sz);
                      //  System.out.println(h);
                        float total=((float)m*100)/(float)sz;
                    //    pb.setMaximum((int) total);
//                        for (int i = 0; i <=(MAX); i++) {
//                            final int currentValue = i;
//                        i+=10;
//                            try {
//                                SwingUtilities.invokeLater(new Runnable() {
//                                    public void run() {
//                                        pb.setValue(currentValue);
//                                    }
//                                });
//                                java.lang.Thread.sleep(10);
//                            } catch (InterruptedException e) {
//                                JOptionPane.showMessageDialog(frame, e.getMessage());
//                            }
        //                }
                 //       System.out.println(total);
                    }
               //     pb.setValue(100);
                    fin.close();

                    System.out.println("..ok");
                    dout.flush();
                }
                dout.writeUTF("stop");
                System.out.println("Send Complete");
                dout.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("An error occured");
            }
            din.close();
            s.close();
            ss.close();
        }
    }
}