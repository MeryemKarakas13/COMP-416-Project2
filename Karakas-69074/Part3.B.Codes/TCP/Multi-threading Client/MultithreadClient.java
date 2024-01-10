/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 *
 * @author AbdulrazakZakieh
 */
public class MultithreadClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConnectionToServer connectionToServer = new ConnectionToServer(ConnectionToServer.DEFAULT_SERVER_ADDRESS, ConnectionToServer.DEFAULT_SERVER_PORT);
Scanner stdIn = new Scanner (System.in );
        
        System.out.println("Type 1 for persistent connection, type 2 for non-persistent connection: ");
        String userInput ="";
        String genStr ="";
        String in_char;
        
        userInput = stdIn.nextLine();
        
        if(userInput.equals("1")) {
        	
        	Instant start = Instant.now();
        	connectionToServer.Connect();
               
            genStr = connectionToServer.SendForAnswer("1");
   
            connectionToServer.Disconnect();
            Instant end = Instant.now();
            
            System.out.println("Final string from persistent connection: " + genStr);
            Duration elapsedTime = Duration.between(start, end);
            System.out.println("Time delay between request and response: "+ elapsedTime.toMillis() +" milliseconds");
           
        }else if(userInput.equals("2")) {
        	
        	Instant start = Instant.now();
            for (int i = 0; i < 16; i++) {
            	connectionToServer.Connect();
                in_char = connectionToServer.SendForAnswer("2");
                connectionToServer.Disconnect();

                if (in_char == null) {
                    break;
                }
                
                genStr += in_char;
                System.out.println("Character at index " + i + " : " + in_char);
            }
            Instant end = Instant.now();
            System.out.println("Final string from non-persistent connection : " + genStr);
            Duration elapsedTime = Duration.between(start, end);
            System.out.println("Time delay between request and response: "+ elapsedTime.toMillis() +" milliseconds");
            
            
        }else {
        	System.out.println("Wrong connection type!");
        }
    }
    
}
