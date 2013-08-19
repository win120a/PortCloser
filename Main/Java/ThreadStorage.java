public class ThreadStorage{
  private static ThreadStorage cache;
  private ThreadStorage(){}

  private int nowAt = 0;
  private PortReserveThread[] storage;

  public int getPos(){
    return nowAt;
  }

  public PortReserveThread getThread(int index){
    return storage[index];
  }

  public PortReserveThread[] getStorage(){
    return storage;
  }

  public static ThreadStorage getInstance(){
     if(cache == null){
       cache = new ThreadStorage();
       return cache;
     }
     else{
       return cache;
     }
   }

   public synchronized void putThread(Thread what){
     storage[getPos() + 1] = what;
     nowAt++;
   }

   private Thread getLastThread(){
     return storage[getPos()]
   }

   public synchronized void putAndStart(Thread what){
     putThread(what);
     getLastThread().start();  //Thread Sync Ques. (Add Sync.)
   }
}
