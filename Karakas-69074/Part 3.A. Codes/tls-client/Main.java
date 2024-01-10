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
 
        String genStr ="";
        String in_char;

        for (int i = 0; i < 16; i++) {
            sslConnectToServer.Connect();
            String str = i+"";
            in_char = sslConnectToServer.SendForAnswer(str);
            sslConnectToServer.Disconnect();

            if (in_char == null) {
                break;
            }
            
            genStr.concat(in_char);
            System.out.println("Character at index" + i + " : " + in_char);
        }

        System.out.println("Final merged string : " + genStr);
        
    }
}
