import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class LockFile{
  private FileChannel ch;
  private FileLock lock;

  public LockFile(File f){
    FileOutputStream fos = new FileOutputStream(f);
    ch = fos.getChannel();
  }

  public FileLock start(){
    lock = ch.lock();
    return lock;
  }

  public void end(){
    lock.release();
  }

  public class DataPackage{
    FileChannel channel;
    FileLock lock;
    protected DataPackage(FileChannel fc,FileLock lck){
      fc = channel;
      lock = lck;
    }
    protected FileChannel getChannel(){return channel;}
    protected FileLock getLock(){return lock;}
  }

  public LockFile.DataPackage getLockAndChannel(){
    return new DataPackage(ch,lock);
  }

  public FileLock getLockOnly(){
    return lock;
  }

  public FileChannel getChannelOnly(){
    return ch;
  }
}
