import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Server implements Runnable{

	 int port;
	 long transRate;
	
	public Server(int port, long transRate)
	{
		this.port = port;
		this.transRate = transRate;
		
	}
	
	
	

	@Override
	public void run() {
		ServerSocket serSoc = null;
		FileDir fd = new FileDir();
		Scanner scan = new Scanner(System.in);
        File f [] = fd.finder("C:\\Users\\maliy\\Desktop\\");
        File fp [] = fd.finderpdf("C:\\Users\\maliy\\Desktop\\");
        long startTime;
        long finishTime;
        long initialStartTime;
      
       
        
		try {
		
			serSoc = new ServerSocket(port);
		    byte [] buffer = new byte[1024];
		     String a = "";
		     int c;
		     long size=0;
		     long midTime = 0;
		     long temp=0;
			 while(true) {

				 	 
		             Socket soc = serSoc.accept();
		             System.out.println("Connected to other Peer");
		             OutputStream os = soc.getOutputStream();
		             InputStream in = soc.getInputStream();   
		             os.write("Other Peer : I have the following files:\n".getBytes());
		             for(int i=0;i<f.length;i++)
		             {
		            	 os.write((f[i].getName()+"\n").getBytes());

		             }
		             for(int i=0;i<fp.length;i++)
		             {
		            	 os.write((fp[i].getName()+"\n").getBytes());

		             }
		             os.write("Other Peer: Which file do you want?".getBytes());
		             os.write("done".getBytes());
		             in.read(buffer);
		             a = new String(buffer).trim();
		             FileInputStream fin = new FileInputStream("C:\\Users\\maliy\\Desktop\\"+ a);
		             transRate = transRate/8;
		             transRate = transRate*1024*1024;
		             byte k [] = new byte[1024];
		             System.out.println("Sending file " + a +" to other Peer");
		             startTime = System.currentTimeMillis(); 
		             initialStartTime = System.currentTimeMillis();
		             
		     		while((c = fin.read(k))!= -1)
		     		{
		     		 	os.write(k);
		     		 	Arrays.fill(k, (byte)0);
		     		 	size = size + 1024;
		     		    temp = temp +1024;
		     		  
		     		    if(temp>transRate)
		     		    {  
		     		    	 midTime = System.currentTimeMillis() - startTime;
		     		    	 if(1000-midTime>0)
		     		    	 {	 
		     		    	 Thread.sleep(1000 - midTime);
		     		    	 }
		     		    	temp = 0;
		     		    	startTime = System.currentTimeMillis();
     
		     		   }
		     		   
		     		}
		     		
		     		
		     		
		     		finishTime = System.currentTimeMillis();
		     		System.out.println("File Transfer Completed");
		     		System.out.println("Time required to Transfer File: " + (double)(finishTime - initialStartTime)*0.001 + " Seconds");
		     		double timeReq = (finishTime - initialStartTime)*0.001;
		     		System.out.println("Size of File in MegaByte: " + (size/(1024*1024)));
		     		long sizeInMB = (size/(1024*1024));
		     		System.out.println("Transmission Rate in Mega bits per Second: " + (double)(sizeInMB/timeReq)*8);
		     	      
		     		Arrays.fill(buffer, (byte)0); 
		            os.flush();
		            soc.close();
		            System.out.println("If you need a file from other Peer, Press 1");	
		            size=0;
				 	midTime = 0;
				 	temp=0;

		         }

		
		
		
		} catch (IOException | InterruptedException e) {
			
			System.out.println("Something really went wrong.Please Consider Reconnecting");
		}
		
		finally {
			
	   
			
		}
	}

}
