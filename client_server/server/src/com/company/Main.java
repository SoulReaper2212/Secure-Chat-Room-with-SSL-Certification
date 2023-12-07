package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.*;
import java.security.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        int port = 4999;
        System.setProperty("javax.net.ssl.keyStore","C:\\Users\\SOULREAPER\\Desktop\\myKeyStore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        System.out.println("STARTING THE SERVER.........");
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket serverSoc = ssf.createServerSocket(port);

        while(!serverSoc.isClosed()) {
            Socket soc = null;
            soc = serverSoc.accept();
            System.out.println("NEW CLIENT IS CONNECTED");

            ClientHandler client=new ClientHandler(soc);
            Thread t = new Thread(client);
            // Invoking the start() method
            t.start();

        }
    }
}
