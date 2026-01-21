package tw.EEIIT.tutor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Brad33 {

	public static void main(String[] args) {
		Set<Integer> nums = new TreeSet<>();
		while (nums.size() < 6) {
			nums.add((int)(Math.random()*49+1));
		}
		for (Integer i :nums) {
			System.out.println(i);
		}
		
//		for(int i=1;i<50;i++) {
//			nums.add(i);
//		}
//		System.out.println(nums);
	}

}
