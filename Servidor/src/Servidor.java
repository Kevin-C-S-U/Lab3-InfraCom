import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	
}
