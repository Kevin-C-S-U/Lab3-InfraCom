import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SerThread extends Thread {
	
	private Socket socket;
	private String archivo;
	private int n;
	
	public SerThread(Socket socket,String archivo,int n) {
		this.socket = socket;
		this.archivo = archivo;
		this.n = n;
	}
	
	public void run() {
		try {
			
			PrintWriter escritor = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputline = lector.readLine();
			escritor.println(""+n);
			
			//Archivo a transmitir
			File file = new File(archivo);
			System.out.println(archivo.hashCode());
			//Creacion Input
			FileInputStream fr = new FileInputStream(archivo);
			//Creacion Output
			DataOutputStream data = new DataOutputStream(socket.getOutputStream());
			
			//Arreglo de bytes
			byte b[]=new byte[(int)file.length()];
			fr.read(b);
			
			data.writeInt(b.length);
			data.write(b);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
