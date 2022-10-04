import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
	
	public static final int PUERTO = 3400;
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(PUERTO);
		System.out.println(ss);
		int num =2;
		int n = 0;
		SerThread[] thr = new SerThread[num];
		while(n<num) {
			Socket socket =ss.accept();
			SerThread thread = new SerThread(socket, 1);
			thr[n]=thread;
			n ++;
		}
		for(SerThread i :thr) {
			i.start();
		}
	}
	
}
