import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolCreator implements Runnable {

	protected int serverPort = 6543;
	protected ServerSocket serverSocket = null;
	protected Thread runningThread = null;
	protected ExecutorService threadPool = Executors.newFixedThreadPool(10);

	public ThreadPoolCreator(int port) {
		this.serverPort = port;
	}

	public void run() {
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		try {
			serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port", e);
		}
		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Server Stopped.");
				break;
			}
			try {
				this.threadPool.execute(new HandleConnection(clientSocket));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// this.threadPool.shutdown();
		// System.out.println("Server Stopped.") ;
	}
}