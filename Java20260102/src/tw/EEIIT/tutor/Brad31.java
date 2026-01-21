package tw.EEIIT.tutor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import tw.brad.apis.Bike;

public class Brad31 {

	public static void main(String[] args) {
		Set set = new HashSet();
		set.add("Brad");
		set.add(new Bike());
		set.add("Brad");
		set.add(new int[3]);
		set.add(new Bike());
		set.add(123); // auto-boxing int => Integer
		System.out.println(set.size());
		System.out.println(set);
		System.out.println("----");
		Iterator it = set.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
		}
	}

}
