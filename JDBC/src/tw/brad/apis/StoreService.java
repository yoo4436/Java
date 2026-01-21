package tw.brad.apis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class StoreService {
	private final String url, user, pwd;
	
	public StoreService(String url, String user, String pwd) {
		this.url = url;
		this.user = user;
		this.pwd = pwd;
	}
	
	public void restock(int productId, int qty) throws Exception {
		withRetry(() -> {
			try(Connection conn = DriverManager.getConnection(url, user, pwd)){
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				
				int stock = selectStockForUpdate(conn, productId);
				updateStock(conn, productId, stock + qty);
				insertLog(conn, "IN", productId, "IN:" + qty);
				
				conn.commit();
			}
			return null;
		});
	}
	
	public void purchase(int productId, int qty) throws Exception {
		withRetry(() -> {
			try(Connection conn = DriverManager.getConnection(url, user, pwd)){
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				
				int stock = selectStockForUpdate(conn, productId);
				System.out.println(stock + ":" + qty);
				if (stock < qty) {
					insertLog(conn, "ERR", productId, stock + ":" + qty);
					conn.commit();
					throw new NotEnoughException("不足");
				}else {
					updateStock(conn, productId, stock - qty);
					insertLog(conn, "OUT", productId, "OUT:" + qty);
					conn.commit();
				}
			}
			return null;
		});
	}
	
	private int selectStockForUpdate(Connection conn, int productId) throws Exception{
		String sql = """
				select stock
				from mytest
				where id = ?
				for update
				""";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, productId);
			try(ResultSet rs = pstmt.executeQuery()){
				if (!rs.next()) throw new SQLException();
				return rs.getInt(1);
			}
		}
		
	}
	
	private void updateStock(Connection conn, int productId, int newstock) throws Exception {
		String sql = """
				update mytest
				set stock = ?
				where id = ?
				""";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, newstock);
			pstmt.setInt(2, productId);
			pstmt.executeUpdate();
		}
	}
	
	private void insertLog(Connection conn, String type, int productId, String note) {
		String sql = """
				insert into log
					(type, pid, note)
				values 
					(?,?,?)
				""";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, type);
			pstmt.setInt(2, productId);
			pstmt.setString(3, note);
			pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//retry 框架
	private <T> T withRetry(Callable<T> action) throws Exception {
		int max = 3;
		int backoff = 10;
		
		for(int i = 0; i<=max; i++) {
			try {
				return action.call();
			}catch (Exception e) {
				//Deadlock, Lock
				if(i==max) throw e;
				Thread.sleep(backoff);
				backoff *=2;
			}
		}
		throw new IllegalStateException("ERROR(1)");
	}
}
