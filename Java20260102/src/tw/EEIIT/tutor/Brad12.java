package tw.EEIIT.tutor;

public class Brad12 {

	public static void main(String[] args) {
		int b,c,d,e,f,g,h;
		b=c=d=e=f=g=h=0;
		
		for (int i=0;i<100;i++) {
			int a = (int)(Math.random() * 6) + 1;// 1~6
			switch(a) {
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
		if (h > 0) {
			System.out.println("Error: " + h);
		}else {
			System.out.printf("以下為各點總和:\n點數1有：%d次\n點數2有：%d次\n點數3有：%d次\n點數4有：%d次\n點數5有：%d次\n點數6有：%d次",b,c,d,e,f,g);
		}
	}

}
