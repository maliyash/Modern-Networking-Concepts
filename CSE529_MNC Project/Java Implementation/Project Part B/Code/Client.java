import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class Client implements Runnable{

	int port;
	
	public Client(int port)
	{
		this.port = port;
	}
	
	
	


	@Override
	public void run() {
		
		Scanner scan = new Scanner(System.in);
		
	
		try {

			
			
			while(true)
			{
				System.out.println("If you need a file from other Peer, Press 1");
				if(scan.nextInt() == 1)
				{
			        		
				
				
			 Socket socket = new Socket("localhost",port);
			 System.out.println("Connected to other Peer");
			 OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream();
             byte [] buffer = new byte[100];
             String fName ="";
            while(in.read(buffer)!=-1)
            {
            	fName = new String(buffer).trim();
            	
            	if(fName.equals("done"))
            	{
            		break;
            	}
            	System.out.println(fName);
            	Arrays.fill(buffer, (byte)0);
		
		    } 
            
              
             String fileName = scan.next();
             out.write(fileName.getBytes());
             File file = new File("C:\\Users\\maliy\\Desktop\\" + "client"+fileName);
             FileOutputStream fout = new FileOutputStream(file,true);
             while(in.read(buffer)!=-1)
             {
            	 
            	 fout.write(buffer);
            	 Arrays.fill(buffer, (byte)0);
            	 
             }
		     System.out.println("File Transfer Completed");
             fout.close();
             out.close();
             in.close();
             socket.close();
			}
		
			}
		} 
		
		
		
		catch (UnknownHostException e) {
			
			e.printStackTrace();
		
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
