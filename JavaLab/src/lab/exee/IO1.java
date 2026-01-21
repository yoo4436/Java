package lab.exee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class IO1 {

	public static void main(String[] args) {
		Path path = Paths.get("dir1/tenData.txt");
		
		try {
			List<String> allLines = Files.readAllLines(path);
			
			int sum = 0;
			for (String lines : allLines) {
				sum += Integer.parseInt(lines);
			}
			System.out.printf("總和是: %d",sum);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}

}
