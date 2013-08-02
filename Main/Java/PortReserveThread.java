import java.net.*;
import java.io.*;

public class PortReserveThread implements Runnable{
  int port;
  ServerSocket ss;
  
  public PortReserveThread(int which){
    port = which;
    ss = new ServerSocket(port);
  }
  
  public void run(){
    Accept:
    while(true){
      Socket s;
      try{
        s = ss.accept();
      }
      catch(IOException){
        s.close();
      }
      catch(SocketTimeoutException){
        s.close();
      }
      finally{
        s = null;
        continue;
      }
    }
  }
  public void stopSelf(){
    break Accept;
    Thread.currentThread().interrupt();
  }
}
