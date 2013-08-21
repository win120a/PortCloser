public class ThreadObjectStorage{
  private static ThreadObjectStorage cache;
  private ThreadObjectStorage(){}

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

  public static ThreadObjectStorage getInstance(){
     if(cache == null){
       cache = new ThreadObjectStorage();
       return cache;
     }
     else{
       return cache;
     }
   }

   public synchronized void putThread(PortReserveThread what){
     storage[getPos() + 1] = what;
     nowAt++;
   }

   private synchronized PortReserveThread getLastThread(){
     return storage[getPos()];
   }

   public synchronized void putAndStart(PortReserveThread what){
     putThread(what);
     getLastThread().start();  //Thread Sync Ques. (Add Sync.)
   }
}
