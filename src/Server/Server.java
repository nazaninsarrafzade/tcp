package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;

public class Server extends Task<List<File>>{
    String name;
    Server(String name1){name=name1;}
    protected List<File> call() throws Exception {
        String filename;List<File> copied = new ArrayList<File>();
        filename=name;
        while(true)
        {

        ServerSocket ss=new ServerSocket(5001);
        System.out.println ("Waiting for request");
        Socket s=ss.accept();
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
        int m=0;
        while((read = fin.read(b)) != -1){
        dout.write(b, 0, read);
        dout.flush();
        m=m+read;
        System.out.println(((float)m*100)/(float)sz);
        this.updateProgress(((float)m), (float)sz);
        }
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
        ss.close(); return copied;
        }

}
        }