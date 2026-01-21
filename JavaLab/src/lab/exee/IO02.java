package lab.exee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IO02 {
	private static final String inputFile = "dir1/tenData.txt";
	
	public static void main(String[] args) {
		try {
			long sum = Files.lines(Paths.get(inputFile)).mapToInt(Integer::parseInt).sum();
			System.out.printf("總和是: %d",sum);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
