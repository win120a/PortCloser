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

// May be it can use Collection instead this.
// Use Map<int,PortReserveThread>
public class ThreadObjectStorage{
  private static ThreadObjectStorage cache;
  private ThreadObjectStorage(){}

  private int nowAt = 0;
  private PortReserveThread[] storage;

  public int getPos(){
    //Collection: returns Map length
    return nowAt;
  }

  public PortReserveThread getThread(int index){
    //Collection: collectionobj.get(index);
    return storage[index];
  }

  public PortReserveThread[] getStorage(){
    //Return Map
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
