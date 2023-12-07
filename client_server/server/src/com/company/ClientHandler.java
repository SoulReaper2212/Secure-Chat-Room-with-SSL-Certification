package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    public static ArrayList<ClientHandler> clients= new ArrayList<>();
    private BufferedReader bf;
    private InputStreamReader in;
    final Socket soc;
    final String userName;
    int con;


    public ClientHandler(Socket soc) throws IOException {

        this.in = new InputStreamReader(soc.getInputStream());
        this.bf = new BufferedReader(in);
        this.userName=bf.readLine();
        ECHO("\n** "+userName+ " Has join the chat room **\n");
        this.soc=soc;
        this.con=1;
        clients.add(this);
    }

    public void run() {
        String received = null;
        while(!soc.isClosed()) {


            try {
                received = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ECHO(userName + " : " + received);


            
        }



    }


    public void ECHO(String data) {
        for(ClientHandler c:clients)
        {
            if(!c.userName.equals(userName))
            {
                PrintWriter pr = null;
                try {
                    pr = new PrintWriter(c.soc.getOutputStream());
                    pr.println(data);
                    pr.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }




    }
}
