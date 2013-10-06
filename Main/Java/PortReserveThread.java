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
        s = ss.accept(); // Reserve TCP.
        ds.receive(dp);
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
