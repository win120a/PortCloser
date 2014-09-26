/*
   Copyright (C) 2011-2014 AC Inc. (Andy Cheung)

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

//The Java Package import.
import java.net.*;
import java.io.*;

//The public class, PortReserveThread.
public class PortReserveThread implements Runnable{
  int port; // This thread's port.
  
  // The TCP Settings Part.
  Socket s; //The Socket, not for use.
  ServerSocket ss; // The ServerSocket.
  
  //The UDP Settings Part.
  byte[] dpbyte = new byte[10];
  DatagramSocket ds; // The DatagramSocket
  DatagramPacket dp; // The DatagramPacket, not for use.
  
  public PortReserveThread(int which){
    port = which; // Set port.
    try {
      ss = new ServerSocket(port);  //Get New SS (ServerSocket[TCP]) instance.
      ds = new DatagramSocket(port);  // DS (DatagramSocket[UDP]).
    } catch (IOException e) {  //Catch the exception.
      System.out.println("Error,Will exit.");
      System.exit(1);
    }
  }
	  
  public void run(){ // Thread method body.
    while(true){ // Unlimited Accept.
      try{
      	dp = new DatagramPacket(dpbyte,9); //Create Packet Object.
        s = ss.accept(); // Reserve TCP.
        ds.receive(dp); // Reserve UDP.
        s.close();
      }
      catch(SocketTimeoutException ste){
        try {
          s.close();
	} catch (IOException e) {
          System.out.println("Error,Will exit.");
          System.exit(1);
	}
      }
      catch(IOException ioe){
	try {
	  s.close();
	} catch (IOException e) {
	  System.out.println("Error,Will exit.");
	  System.exit(1);
	}
      }
      finally{
	try {
	  s.close();
	} catch (IOException e) {
	  System.out.println("Error,Will exit.");
	  System.exit(1);
	}
        s = null;
      }
    }
  }
  
  public void stopSelf(){  //Stop Procress.
    Thread.currentThread().interrupt();
  }
  public int getPort(){
    return port;
  }
}
