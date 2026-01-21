package tw.EEIIT.tutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerV4 {

	public static void main(String[] args) {
		List<Integer> poker = new ArrayList<>();
		for(int i=0;i<51;i++) poker.add(i);
		Collections.shuffle(poker);
		
		for(Integer card: poker) {
			System.out.println(card);
		}
	}

}
