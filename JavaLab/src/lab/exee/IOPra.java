package lab.exee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOPra {
	private static String inputFile = "dir1/tenData.txt";
	private static String outputFile = "dir1/output.txt";
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))){
			String line;
			int sum = 0;
			
			while ((line = br.readLine()) != null) {
				try {
					sum += Integer.parseInt(line);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			
			bw.write(String.valueOf(sum));
			
			bw.newLine();
			
			System.out.printf("✅ 計算完成！總和 %d 已寫入 %s",sum,outputFile);
//			System.out.println(br.toString());
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}


