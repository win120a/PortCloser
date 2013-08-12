public class ThreadStorage{
  private ThreadStorage cache;
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

  public ThreadStorage getInstance(){
     r
