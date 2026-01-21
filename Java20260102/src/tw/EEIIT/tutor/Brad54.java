package tw.EEIIT.tutor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

public class Brad54 {

	public static void main(String[] args) {
		for (int i=0; i<65536 ; i++) {
			try (Socket socket = new Socket(InetAddress.getByName("10.0.101.187"), i);){
				System.out.println("OK:" + i);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		
	}

}
