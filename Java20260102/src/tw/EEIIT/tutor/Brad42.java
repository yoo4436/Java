package tw.EEIIT.tutor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Brad42 {

	public static void main(String[] args) {
		String msg = "Hello Brad\n";
		try {
			FileOutputStream fout = new FileOutputStream("dir1/file3.txt", true);
			fout.write(msg.getBytes());
			fout.flush();
			fout.close();
			System.out.println("OK");
		} catch (FileNotFoundException e) {
			System.out.println(e + "FNF");
		} catch (IOException e) {
			System.out.println(e + "IO");
		}
	}

}
