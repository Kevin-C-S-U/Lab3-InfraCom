import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	public static final int PUERTO = 3400;
	
	public static final String SERVIDOR = "localhost";
	
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		PrintWriter escritor = null;
		BufferedReader lector = null;
		
		try {
			byte []b = new byte[20000002];
			socket = new Socket(SERVIDOR,PUERTO);
			InputStream is = socket.getInputStream();
			FileOutputStream fr = new FileOutputStream("a.txt");
			is.read(b,0,b.length);
			fr.write(b,0,b.length);
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
