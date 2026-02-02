package tw.brad.h1.tutor;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import tw.brad.h1.entity.Member;
import tw.brad.h1.utils.HibernateUtil;

public class Brad02 {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
		
			String sql = """
					insert into member
						(email, pw, name)
					values
						(:email, :pw, :name)
					 """;
			NativeQuery<Member> query = 
					session.createNativeQuery(sql, Member.class);
			query.setParameter("email", "test1@brad.tw");
			query.setParameter("pw", "123456");
			query.setParameter("name", "Test1");
			
			int n = query.executeUpdate();
			
			transaction.commit();
			System.out.println(n);
		}
		
		
	}

}
