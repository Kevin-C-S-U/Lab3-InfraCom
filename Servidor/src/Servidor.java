import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Servidor {
	
	public static final int PUERTO = 3400;
	
	public static final String ARC1 = "data/Entrega1_ProyectoSistEmp.pptx";
	
	public static final String ARC2 = "data/Entrega1_ProyectoSistEmp.pptx";
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(PUERTO);
		Scanner scan = new Scanner(System.in);
		System.out.println("ingrese el num de clientes");
		int num =scan.nextInt();
		System.out.println("escoja el archivo 1(100MB) o 2(250MB)");
		int a =scan.nextInt();
		String ar = null;
		if(a==1) {
			ar = ARC1;
		}else if(a==2) {
			ar = ARC2;
		}else {
			System.exit(-1);
		}
		int n = 0;
		SerThread[] thr = new SerThread[num];
		while(n<num) {
			Socket socket =ss.accept();
			SerThread thread = new SerThread(socket, ar,n);
			thr[n]=thread;
			n ++;
		}
		for(SerThread i :thr) {
			i.start();
		}
	}
	
}
