package tw.brad.tutor;

import java.util.Iterator;
import java.util.List;

import tw.brad.apis.Member;
import tw.brad.apis.MemberDAO;
import tw.brad.apis.MemberDAOImpl;

public class Brad18 {

	public static void main(String[] args) {
		MemberDAO  dao = new MemberDAOImpl();
		
//登入
//		Member member = new Member();
//		member.setEmail("eric@ee.tw");
//		member.setPasswd("123");
//		member.setName("Eric");
//		
//		try {
//			if (dao.addMember(member)) {
//				System.out.println("OK1");
//			}else {
//				System.out.println("XX1");
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	
//		try {
//			Member member = dao.findById(2);
//			System.out.println(member.getEmail() + ":" + member.getName());
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
//		try {
//			if(dao.delMember(4)) {
//				System.out.println("DEL Success");
//			}else {
//				System.out.println("DEL Fail");
//			}
//		}catch (Exception e) {
//			System.out.println(e);
//		}
		
//		try {
//			Member member = dao.findById(3);
//			System.out.println(member.getEmail() + ":" + member.getName());
//			member.setEmail("bb@brad.tw");
//			member.setPasswd("123456");
//			if (dao.updateMember(member)) {
//				System.out.println("OK3");
//			}else {
//				System.out.println("XX3");
//			}
//		}catch (Exception e) {
//			System.out.println(e);
//		}
		
//		try {
//			List<Member> members = dao.findAll();
//			for (Member member : members) {
//				System.out.printf("%d:%s:%s\n", member.getId(), member.getEmail(), member.getName());
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}

}
