package tw.EEIIT.tutor;

public class Brad13 {

	public static void main(String[] args) {
		int b,c,d,e,f,g,h;
		b=c=d=e=f=g=h=0;
		int[] point = new int[100];
		
		for (int i=0;i<100;i++) {
			point[i] = (int)(Math.random() * 6) + 1;
			switch(point[i]) {
				case 1: 
					b++;
					break;
				case 2: 
					c++;
					break;
				case 3: 
					d++;
					break;
				case 4: 
					e++;
					break;
				case 5: 
					f++;
					break;
				case 6: 
					g++;
					break;
				default:h++;
			}
		}
		int total = b+c+d+e+f+g;
		if (h > 0) {
			System.out.println("Error: " + h);
		}else {
			System.out.printf("以下為各點總和:\n點數1有：%d次\n點數2有：%d次\n點數3有：%d次\n點數4有：%d次\n點數5有：%d次\n點數6有：%d次\n",b,c,d,e,f,g);
			System.out.printf("一共骰了：%d次",total);
		}
	}
}
