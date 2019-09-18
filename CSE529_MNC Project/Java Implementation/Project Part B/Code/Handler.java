import java.util.Scanner;

public class Handler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("Please Mention the Transmission Rate in Megabits per Second:");
		Scanner scan = new Scanner(System.in);
		Thread Ser = new Thread(new Server(9996,scan.nextInt()));
		Thread cli = new Thread(new Client(9997));
     	Ser.start();
		cli.start();
	}

}
