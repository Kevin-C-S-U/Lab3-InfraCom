import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static final int PUERTO = 3400;
	
	public static final String SERVIDOR = "localhost";
	
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		PrintWriter escritor = null;
		BufferedReader lector = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("ingrese su numero de cliente");
		int num =scan.nextInt();
		
		try {
			socket = new Socket(SERVIDOR,PUERTO);
			DataInputStream is = new DataInputStream(socket.getInputStream());
			
			int lenData = is.readInt();
			if (lenData >0) {
				 byte[] b = new byte[lenData];
				 is.readFully(b, 0, b.length);
				 FileOutputStream fr = new FileOutputStream("a.pptx");
				 fr.write(b,0,b.length);
				 System.out.println("Se recibio exitosamente");
				 
			}
			
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
