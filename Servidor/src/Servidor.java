import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;


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
		int p = 0;
		if(a==1) {
			ar = ARC1;
			p=100;
		}else if(a==2) {
			ar = ARC2;
			p=250;
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
		long inicio = System.currentTimeMillis();
		for(SerThread i :thr) {
			i.start();
		}
		long fin = System.currentTimeMillis();
		double tiempo = (double) ((fin - inicio));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
		File filegen = new File("log"+".txt");
		 if (!filegen.exists()) {
               filegen.createNewFile();
        }
		FileWriter fw = new FileWriter(filegen);
        BufferedWriter bw = new BufferedWriter(fw);
        String contenido = " \n ";
        contenido += "arcvo"+ar+" tamaño"+p+"\n";
        String clientes ="";
        for(int i = 0;i<n;i++) {
        	clientes += "cliente "+i+"\n";
        }
        contenido += "se conectará con los clientes:"+clientes;
        contenido += "la entrega del archivo fué exitosa";
        contenido += "el tiempo de trnsferenciaes de "+tiempo+" milisegundos";
        bw.write(contenido);
        bw.close();
	}
	
}
