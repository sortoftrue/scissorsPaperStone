package com.james;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    

    public static void main(String[] args) {
        
        try (Socket socket = new Socket("localhost",12345)) {
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            DataInputStream is = new DataInputStream(socket.getInputStream());

            boolean isEnd=false;

            //System.out.println(is.readUTF());

            while(!isEnd){

                System.out.println("Enter choice:\n1.Scissors\n2.Paper\n3.Stone");
                String input = new Scanner(System.in).next();
                os.writeUTF(input);
                os.flush();

                String received = is.readUTF();

                if(received.contains("Draw") || received.contains("wins")){
                    System.out.println(received);
                    isEnd=true;
                    break;
                }
                System.out.println(received);
                


            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
