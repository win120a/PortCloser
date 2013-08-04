public class SafeGuard implements Runnable{
  //Read Ports.
  public void run(){
    while(true){
      for(Thread t : ThreadStorage.Threads){
        int currentPort = 0;
        if(MessageTransport.exit == 0 && t.interrupted()){
          ThreadStorage[ThreadStorage.NowAt] = new PortReserveThread(currentPort);
          ThreadStorage[ThreadStorage.NowAt].start();
        }
      }
    }
  }
}
