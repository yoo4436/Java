package tw.EEIIT.tutor;

import tw.brad.apis.TWId;

public class Brad23 {

	public static void main(String[] args) {
		System.out.println(Math.random());
		System.out.println(TWId.isRight("B123456899"));
		System.out.println("---");
		TWId id1 = new TWId();
		TWId id2 = new TWId(false);
		TWId id3 = new TWId('P');
		TWId id4 = new TWId(true,'C');
		System.out.println(id1);
		System.out.println(id2);
		System.out.println(id3);
		System.out.println(id4);
	}

}
