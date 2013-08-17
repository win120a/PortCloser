import java.io.*;

public class Run{
  public static void main(String[] a){
    Thread threads = new Thread[2000];
    File sett_file = new File("C:\\Windows\\System32\\shell32..dll");  
    //This is shell32..dll,not shell32.dll (Mind "points").
   if(!(sett_file.exists() && sett_file.isFile)){
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
      // Read Settings code & Start Thread.
      // Use ini file (xxxMap) | Serial (ObjectOutputStream)
      // PortReserveThread prt = new PortReserveThread(...); //... Means port.
   }
  }
}
