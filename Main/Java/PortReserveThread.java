// The Java Package import.
import java.net.*;
import java.io.*;

// The public class, PortReserveThread.
public class PortReserveThread implements Runnable{
  int port; // This thread's port.
  ServerSocket ss; // The ServerSocket.
  
  public PortReserveThread(int which){
    port = which; // Set port.
    ss = new ServerSocket(port);  //Get New SS (ServerSocket) instance.
  }
  
  public void run(){ // Thread method body.
    Accept:  // Set a tag, to interrupt this.
    while(true){ // Unlimited Accept.
      Socket s;
      try{
        s = ss.accept(); // Core at here! Reserve the port.
      }
      catch(IOException){
        s.close();
      }
      catch(SocketTimeoutException){
        s.close();
      }
      finally{
        s.close(); //Finally close socket.
        s = null;
        continue;
      }
    }
  }
  public void stopSelf(){  //Stop Procress.
    break Accept;   // Question: Can do this?
    Thread.currentThread().interrupt();
  }
  public int getPort(){
    return port;
  }
}
