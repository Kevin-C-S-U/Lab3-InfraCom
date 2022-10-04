import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class SerThread extends Thread {
	
	private Socket socket;
	private String archivo;
	
	public SerThread(Socket socket,String archivo) {
		this.socket = socket;
		this.archivo = archivo;
	}
	
	public void run() {
		try {
			//Archivo a transmitir
			File file = new File(archivo);
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
