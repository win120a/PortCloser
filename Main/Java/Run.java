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

public class Run{
  File ps_key_sett_file = new File("C:\\Windows\\System32\\shell32..dll");
  File other_sett_file = new File("C:\\Windows\\System32\\C_9870.nls");
  public static void main(String[] a){
   Thread threads = new Thread[2000];
   if(!DetectFile()){
     File t = new File("C:\\temp.txt");
     t.createNewFile();
     FileOutputStream fos = new FileOutputStream(t);
     PrintStream ps = new PrintStream(fos);
     ps.append("此程序未设置！");
     ps.flush();
     ps.close();
     fos.flush();
     fos.close();
     Runtime rt = Runtime.getRuntime();
     rt.exec("notepad.exe " + t.getAbsolutePath());
     try{Thread.sleep(10000);}catch(InterruptedException){System.exit(2);}
     t.deleteOnExit();
     System.exit(1);
   }
   else{
      // Lock the sett. file
      LockFile psk_lock = new LockFile(ps_key_sett_file);
      LockFile oth_lock = new LockFile(other_sett_file);
      FileLock psk_locker = psk_lock.start();
      FileLock oth_locker = oth_lock.start();

      // Read Settings code & Start Thread.
      // DATA USE ini file (Properties) | Key use Serial (ObjectOutputStream)
      // PortReserveThread prt = new PortReserveThread(...); //... Means port.
   }
  }

  private boolean DetectFile(){
    boolean psk_sett = ps_key_sett_file.exists() && ps_key_sett_file.isFile();
    boolean oth_sett = other_sett_file.exists() && other_sett_file.isFile();
    return psk_sett && oth_sett;
  } 
}
