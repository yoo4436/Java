package tw.EEIIT.tutor;

import java.io.Serializable;

public class Brad50 {
	public static void main(String[] args) {
		Brad503 obj = new Brad503();
	}
}

class Brad501 implements Serializable{
	Brad501(){
		System.out.println("Brad501()");
	}
}
class Brad502 extends Brad501 {
	Brad502(){
		System.out.println("Brad502()");
	}
}
class Brad503 extends Brad502{
	Brad503(){
		System.out.println("Brad503()");
	}
}


