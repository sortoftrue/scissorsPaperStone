package com.james;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Game implements Runnable{
    
    Socket socket;

    Game(Socket socket){
        this.socket = socket;
    }

    public void run(){

        try {
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            DataInputStream is = new DataInputStream(socket.getInputStream());

            //Integer compMove;

            //while(true){
                Random random = new Random();
                Integer compMove = random.nextInt(1, 4);
                
                boolean valid = false;
                Integer playMove = 0;

                while(!valid){
                    try{
                        playMove = Integer.parseInt(is.readUTF());

                        if(playMove<1 || playMove>3){
                            os.writeUTF("Enter number 1-3!");
                            os.flush();
                        } else {
                            valid = true;
                        }

                    } catch (NumberFormatException e){
                        os.writeUTF("Please enter a number!");
                        os.flush();
                    }
                }
                
                switch(compWin(compMove,playMove)){
                    case 0:{
                        os.writeUTF("Draw! Computer:%s, Player:%s".formatted(compMove,playMove));
                        os.flush();
                        break;
                    } 
                    case 1:{
                        os.writeUTF("Computer wins! Computer:%s, Player:%s".formatted(compMove,playMove));
                        os.flush();
                        break;
                    }
                    case 2:{
                        os.writeUTF("Player wins! Computer:%s, Player:%s".formatted(compMove,playMove));
                        os.flush();
                        break;
                    }
                    default:{
                        System.out.println("????");
                        break;
                    }
                }



            //}

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

    public int compWin(int comp, int player){

        if(comp == player) return 0;

        switch(comp){
            case 1:{
                switch(player){
                    case 2:{
                        return 1;
                    }
                    case 3:{
                        return 2;
                    }
                }
                break;
            }
            case 2:{
                switch(player){
                    case 1:{
                        return 2;
                    }
                    case 3:{
                        return 1;
                    }
                }
                break;
            }
            case 3:{
                switch(player){
                    case 1:{
                        return 1;
                    }
                    case 2:{
                        return 2;
                    }
                }
                break;
            }
        }

        return 0;

    }

}
