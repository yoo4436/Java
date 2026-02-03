package tw.brad.h1.tutor;

import tw.brad.h1.dao.MemberDao;
import tw.brad.h1.entity.Member;
import tw.brad.h1.entity.MemberInfo;
import tw.brad.h1.utils.BCrypt;

public class Brad07 {

	public static void main(String[] args) {
		MemberDao dao = new MemberDao();
		
		Member member = new Member();
		member.setEmail("rr@rr.tw");
		member.setName("BradRR");
		member.setPwd(BCrypt.hashpw("12345678", BCrypt.gensalt()));
		
		MemberInfo info = new MemberInfo();
		info.setBirth("1965-06-08");
		info.setMale(false);
		
		member.setMemberInfo(info);
		
		dao.addMember(member);
		
	}

}
