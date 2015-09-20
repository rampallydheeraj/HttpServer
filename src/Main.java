import java.io.IOException;


public class Main {

	//ServerSocket serverSocket;
	//entry point for our program
	public static void main(String[] args) throws IOException {
		new Main().runServer(); // to avoid any problem with static fields
	}

	public void runServer() throws IOException{
		
		//serverSocket = new ServerSocket(6543);
		System.out.println("Server is started");
		// for accepting requests
		acceptRequests();
	}
	
	private void acceptRequests() throws IOException{
		while(true){
			/*Socket s = serverSocket.accept();
			HandleConnection ch = new HandleConnection(s);
			ch.start();*/
			ThreadPoolCreator server = new ThreadPoolCreator(6543);
			new Thread(server).start();

			try {
			    Thread.sleep(20 * 1000);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			
		}
	}
}
