/*
   Copyright (C) 2011-2014 AC Inc. (Andy Cheung)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

import java.io.*;
import java.nio.channels.*;

public class LockFile{
  private FileChannel ch;
  private FileLock lock;
  private FileOutputStream fos;

  public LockFile(File f){
    try {
      fos = new FileOutputStream(f);
    } catch (FileNotFoundException e) {
      System.out.println("Error,Will exit.");
      System.exit(1);
    }
    ch = fos.getChannel();
  }

  public FileLock start(){
    try {
      lock = ch.lock();
    } catch (IOException e) {
      System.out.println("Error,Will exit.");
      System.exit(1);
    }
    return lock;
  }

  public void end(){
    try {
      lock.release();
    } catch (IOException e) {
      System.out.println("Error,Will exit.");
      System.exit(1);
    }
  }

  public final class DataPackage{
    FileChannel channel;
    FileLock lock;
    protected DataPackage(FileChannel fc,FileLock lck){
      fc = channel;
      lock = lck;
    }
    public FileChannel getChannel(){return channel;}
    public FileLock getLock(){return lock;}
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
