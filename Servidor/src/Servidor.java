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
	
	public static final String ARC2 = "data/cap1.pptx";
	
	public static double tiempo = 0;
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(PUERTO);
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese el num de clientes: ");
		int num =scan.nextInt();
		System.out.println("Escoja el archivo 1 (100MB o pequeño) o 2 (250MB o grande): " );
		int a =scan.nextInt();
		String ar = null;
		long p = 0;
		if(a==1) {
			ar = ARC1;
			p= (new File(ar)).length();
		}else if(a==2) {
			ar = ARC2;
			p=(new File(ar)).length();;
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
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
		File filegen = new File("log"+".txt");
		 if (!filegen.exists()) {
               filegen.createNewFile();
        }
		FileWriter fw = new FileWriter(filegen);
        BufferedWriter bw = new BufferedWriter(fw);
        String contenido = " \n ";
        contenido += "Archivo "+ar+" de tamaño "+p+"B\n";
        String clientes ="\n";
        for(int i = 0;i<n;i++) {
        	clientes += "cliente "+i+"\n";
        }
        contenido += "Se conectará con los clientes:"+clientes;
        contenido += "La entrega de los archivos fue exitosa\n";
        contenido += "El tiempo total de transferencias fue de "+tiempo+" milisegundos";
        bw.write(contenido);
        bw.close();
	}
	
	public static void addTiempo(double tiemp) {
		Servidor.tiempo = Servidor.tiempo+tiemp;
	}
	
}
