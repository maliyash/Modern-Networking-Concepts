import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServerThread implements Runnable{

	int port;
	
	public ServerThread(int port)
	{
		this.port = port;
		
	}
	
	
	@Override
	public void run() {

		ServerSocket serSoc = null;
		
		try {
			serSoc = new ServerSocket(port);
		} 
		catch (IOException e1) {
			
			e1.printStackTrace();
		} 
		
		int b = 0;
        char c;
        String a = "";
        byte [] buffer = new byte[100];
        
        try {
		
		 while(true) {

         
             Socket soc = serSoc.accept(); 
             b = 0;
             InputStream in = soc.getInputStream();
            
             in.read(buffer);

             a = new String(buffer).trim();
        
             System.out.println("She: " + a);
           
             OutputStream os = soc.getOutputStream();
             os.write("ACK".getBytes());

             Arrays.fill(buffer, (byte)0);
             os.flush();
             soc.close();


         }
		
        }
        
        catch(Exception e)
        {
        	
        	System.out.println(e);
        	
        }

		
	}
 
}
