package tw.EEIIT.tutor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Brad52 {

	public static void main(String[] args) {
		String msg = "?";
		//https://reurl.cc/VmoWO5
		byte[] data = msg.getBytes();
		
		try {
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("10.0.101.79"), 8888);
			
			socket.send(packet);
			socket.close();
			System.out.println("OK");
		
			
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
