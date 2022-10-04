import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static final int PUERTO = 3400;
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		boolean continuar = true;
		System.out.println("Main Server...");
		try {
			ss = new ServerSocket(PUERTO);
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		while(continuar) {
			Socket socket =ss.accept();
			try {
				FileInputStream fr = new FileInputStream("data/Entrega1_ProyectoSistEmp.pptx");
				byte b[]=new byte[2002];
				fr.read(b,0,b.length); 
				OutputStream os = socket.getOutputStream();
				os.write(b,0,b.length);
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
