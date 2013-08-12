public class ThreadStorage{

  private ThreadStorage(){}

  private int nowAt = 0;
  private PortReserveThread[] storage;

  public int getPos(){
    return nowAt;
  }

  public PortReserveThread getThread(int index){
    return storage[index];
  }

  
