package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public void run(String a, String n, String m)throws Exception{
        String address = "";
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Server Address: ");
        address=a;
//create the socket on port 5000
        Socket s=new Socket(address,5000);
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        System.out.println("press Download to start...");
        String str="",filename="";
        try{
            while(!str.equals("Get"))
                str=n;

            dout.writeUTF(str);
            dout.flush();

            filename=din.readUTF();
            System.out.println("Receving file: "+filename);
            filename="new"+filename;
            System.out.println("Saving as file: "+filename);

            long sz=Long.parseLong(din.readUTF());
            System.out.println ("File Size: "+(sz/(1024))+" KB");

            byte b[]=new byte [1024];
            System.out.println("Receving file...");
            FileOutputStream fos=new FileOutputStream(new File(m+"\\"+filename));
            long bytesRead;
            do
            {
                bytesRead = din.read(b, 0, b.length);
                fos.write(b,0,b.length);
            }while(!(bytesRead<1024));
            System.out.println("Complete");
            fos.close();
            dout.close();
            s.close();
            sc.close();
        }
        catch(EOFException e)
        {

        }
    }
}

