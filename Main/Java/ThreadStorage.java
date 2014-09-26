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

public class ThreadStorage{
  private static ThreadStorage cache;
  private ThreadStorage(){}

  private int nowAt = 0;
  private Thread[] storage;

  public int getPos(){
    return nowAt;
  }

  public Thread getThread(int index){
    return storage[index];
  }

  public Thread[] getStorage(){
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

   private synchronized Thread getLastThread(){
     return storage[getPos()];
   }

   public synchronized void putAndStart(Thread what){
     putThread(what);
     getLastThread().start();  //Thread Sync Ques. (Add Sync.)
   }
}
