package tw.EEIIT.tutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Iterator;


public class Brad46 {

	public static void main(String[] args) {
		File source = new File("dir1/file2.txt");
		try {
			FileReader reader = new FileReader(source);
			int c;
			while ((c = reader .read()) != -1) {
				System.out.print((char)c);
			}
				reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
