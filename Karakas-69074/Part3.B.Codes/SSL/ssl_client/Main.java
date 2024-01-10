import java.io.InputStreamReader;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

/**
 * Copyright [Yahya Hassanzadeh-Nazarabadi]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
public class Main
{
    public final static String TLS_SERVER_ADDRESS = "localhost";
    public final static String MESSAGE_TO_TLS_SERVER = "hello from client";
    public final static int TLS_SERVER_PORT = 4444;

    public static void main(String[] args)
    {
        /*
        Creates an SSLConnectToServer object on the specified server address and port
         */
        SSLConnectToServer sslConnectToServer = new SSLConnectToServer(TLS_SERVER_ADDRESS, TLS_SERVER_PORT);
        
        Scanner stdIn = new Scanner (System.in );
        
        System.out.println("Type 1 for persistent connection, type 2 for non-persistent connection: ");
        String userInput ="";
        String genStr ="";
        String in_char;
        
        userInput = stdIn.nextLine();
        
        if(userInput.equals("1")) {
        	
        	Instant start = Instant.now();
            sslConnectToServer.Connect();
               
            genStr = sslConnectToServer.SendForAnswer("1");
   
            sslConnectToServer.Disconnect();
            Instant end = Instant.now();
            
            System.out.println("Final string from persistent connection: " + genStr);
            Duration elapsedTime = Duration.between(start, end);
            System.out.println("Time delay between request and response: "+ elapsedTime.toMillis() +" milliseconds");
           
        }else if(userInput.equals("2")) {
        	
        	Instant start = Instant.now();
            for (int i = 0; i < 16; i++) {
                sslConnectToServer.Connect();
                in_char = sslConnectToServer.SendForAnswer("2");
                sslConnectToServer.Disconnect();

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
