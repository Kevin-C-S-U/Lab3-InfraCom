import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ProtocoloCliente {

	public static void procesar(BufferedReader stdIn, BufferedReader pIn, PrintWriter pOut)throws IOException {
		System.out.println("Escriba mensaje");
		String fromUser = stdIn.readLine();
		pOut.println(fromUser);
		String fromServer = "";
		if((fromServer = pIn.readLine())!=null) {
			System.out.println(fromServer);
		}
	}
	
}
