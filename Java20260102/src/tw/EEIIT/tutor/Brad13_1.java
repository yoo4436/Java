package tw.EEIIT.tutor;

public class Brad13_1 {

	public static void main(String[] args) {
		int[] p = new int[7];
		
		for (int i=0;i<1000000;i++) {
			int point = (int)(Math.random() * 9) + 1;// 1~6
			if (point >= 1 && point <= 9) {
				p[point>=7?point-3:point]++;
			}else {
				p[0]++;
			}
			
		}
		if (p[0] > 0) {
			System.out.println("Error: " + p[0]);
		}else {
			for(int i=1; i<p.length;i++) {
				System.out.printf("點數%d有：%d次\n",i,p[i]);
			}
		}
	}

}
