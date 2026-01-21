package tw.brad.tutor;

import tw.brad.apis.BCrypt;

public class Brad09 {

	public static void main(String[] args) {
		String pw = "12345678";
		String hashPW = BCrypt.hashpw(pw, BCrypt.gensalt());
		System.out.println(hashPW);
//		System.out.println(hashPW.length());
		String input = new String("12345678");
		if (BCrypt.checkpw(input, hashPW)) {
			System.out.println("OK");
		}else {
			System.out.println("XX");
		}
	}

}
