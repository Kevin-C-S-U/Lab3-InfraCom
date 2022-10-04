import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {
	public static final int PUERTO = 3400;
	
	public static final String SERVIDOR = "localhost";
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		Socket socket = null;
		PrintWriter escritor = null;
		BufferedReader lector = null;
		
		try {
			socket = new Socket(SERVIDOR,PUERTO);
			escritor = new PrintWriter(socket.getOutputStream(),true);
			lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromuser = "ack";
			escritor.println(fromuser);
			String fromServer = lector.readLine();
			System.out.println("numcliente");
			System.out.println(fromServer);
			DataInputStream is = new DataInputStream(socket.getInputStream());
			
			int lenData = is.readInt();
			if (lenData >0) {
				 byte[] b = new byte[lenData];
				 is.readFully(b, 0, b.length);
				 FileOutputStream fr = new FileOutputStream("Entrega1_ProyectoSistEmp.pptx");
				 fr.write(b,0,b.length);
				 System.out.println("Se recibio exitosamente");
				 File file = new File("Entrega1_ProyectoSistEmp.pptx");
				 String a = file.toString();
				 System.out.println(a.hashCode());
				 File filegen = new File("Cliente"+fromServer+".txt");
				 if (!filegen.exists()) {
		                filegen.createNewFile();
		         }
				 FileWriter fw = new FileWriter(filegen);
		         BufferedWriter bw = new BufferedWriter(fw);
		         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		         Date date = new Date();  
		         String contenido = " \n ";
		         contenido += ""+formatter.format(date)+"\n";
		         contenido += ""+socket.getPort()+" conectado\n";
		         contenido += "la entrega del archivo fué exitosa"+" conectado\n";
		         bw.write(contenido);
		         bw.close();
				 
			}
			
			
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
