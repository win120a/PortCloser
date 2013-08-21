//The Java Package import.
import java.net.*;
import java.io.*;

//The public class, PortReserveThread.
public class PortReserveThread implements Runnable{
      Socket s;
	  int port; // This thread's port.
	  ServerSocket ss; // The ServerSocket.
	  
	  public PortReserveThread(int which){
	    port = which; // Set port.
	    try {
			ss = new ServerSocket(port);  //Get New SS (ServerSocket) instance.
		} catch (IOException e) {
			System.out.println("Error,Will exit.");
			System.exit(1);
		}
	  }
	  
	  public void run(){ // Thread method body.
	    while(true){ // Unlimited Accept.
	      try{
	        s = ss.accept(); // Core at here! Reserve the port.
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
