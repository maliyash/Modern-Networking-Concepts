
public class Peer1 {

	
	public static void main(String[] args) {
				
		Thread Ser = new Thread(new ServerThread(9996));
		Thread cli = new Thread(new ClientThread(9997));
		Ser.start();
		cli.start();
		
		
	}
	
	
}
