package com.company;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.net.ssl.*;

public class Main {

    public void RecieveData(Socket soc, BufferedReader BF)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true)
                {
                    String rec = null;
                    try {
                        rec = BF.readLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(rec);
                }
            }
        }).start();

    }

    public void SendData(Socket soc)  {
        Scanner sc = new Scanner(System.in);
        while(true)
        {



            String msg = "";
             msg = sc.nextLine();
            PrintWriter pr = null;
            try {
                pr = new PrintWriter(soc.getOutputStream());
                pr.println(msg);
                pr.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }


    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER THE USER NAME : ");
        String name = sc.nextLine();
       // System.out.print("\n\n");
        System.setProperty("javax.net.ssl.trustStore","C:\\Users\\SOULREAPER\\Desktop\\myTrustStore.jts");
        System.setProperty("javax.net.ssl.trustStorePassword","123456");
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket soc= sf.createSocket("localhost",4999);
        InputStreamReader IN = new InputStreamReader(soc.getInputStream());
        BufferedReader BF = new BufferedReader(IN);

        PrintWriter pr = new PrintWriter(soc.getOutputStream());
        pr.println(name);
        pr.flush();

        Main m=new Main();
        m.RecieveData(soc,BF);
        m.SendData(soc);














    }
}
