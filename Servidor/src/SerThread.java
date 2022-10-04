import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class SerThread extends Thread {
	
	private Socket socket;
	
	public SerThread(Socket socket,int num) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			//Archivo a transmitir
			File file = new File("data/Entrega1_ProyectoSistEmp.pptx");
			//Creacion Input
			FileInputStream fr = new FileInputStream("data/Entrega1_ProyectoSistEmp.pptx");
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
