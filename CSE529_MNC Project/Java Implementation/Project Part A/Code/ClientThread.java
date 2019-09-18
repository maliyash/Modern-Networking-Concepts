import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable {

	int port;
	
	
	public ClientThread(int port)
	{
		this.port = port;
	}
	
	@Override
	public void run() {
		
	// receive a message from user 
	
		String msg;
		Scanner scan = new Scanner(System.in);
		
		
	// create a client socket 
		
		System.out.println("Start the conversation");
		
		
		
		while(true)
		{		
			
		    msg = scan.nextLine();
		  System.out.println("You: " + msg);
		 
		 try {
			Socket socket = new Socket("localhost",port);
			//System.out.println();
			 OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream();
             byte [] buffer = new byte[50];
             out.write(msg.getBytes());
             in.read(buffer);
             String  a = new String(buffer).trim();

             if(a.equals("ACK") ) {

                 out.flush();
                 out.close();
                 socket.close();

             }

	
		} catch (IOException e) {
			
			System.out.println("");
			e.printStackTrace();

		
		
		}
		 
		 
		}
		 
	
	}

}
