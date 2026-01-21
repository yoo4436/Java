package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import tw.brad.apis.PurchaseTask;
import tw.brad.apis.RestockTask;
import tw.brad.apis.StoreService;
import tw.brad.apis.Task;

public class Brad26 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final BlockingQueue<Task> queue = new ArrayBlockingQueue<>(200);
	private static final AtomicBoolean running = new AtomicBoolean(true);
	
	public static void main(String[] args) throws Exception {
		try(Connection conn = DriverManager.getConnection(url, user, pw)){
			System.out.println("Connection 111");
		}
		
		/*
		 * running == true
		 * Producer 一直進貨/買貨 -> QUEUE
		 * logger 一直 -> QUEUE
		 * Main Thread -> 喊停 / 跑迴圈
		 */
	
		ExecutorService producer = Executors.newFixedThreadPool(2);
		ExecutorService writer = Executors.newFixedThreadPool(4);
	
		producer.submit(new Producer());
		producer.submit(new Producer());
		
		writer.submit(new Writer());
		writer.submit(new Writer());
		writer.submit(new Writer());
		writer.submit(new Writer());
		
		Thread.sleep(2*1000);
		running.set(false);
		producer.shutdown();
		System.out.println("FFFF");
	}
	
	static class Producer implements Runnable {
		private final Random r = new Random();
		@Override
		public void run() {
			try {
				while(running.get()) {
					Task t;
					if (r.nextInt(100) < 70) {
						t = new PurchaseTask(1 + r.nextInt(4));
					}else {
						t = new RestockTask(1 + r.nextInt(6));			
					}
					queue.put(t);
					Thread.sleep(30);
				}
			} catch (Exception e) {
				
			}
		}
	}
	
	static class Writer implements Runnable {
		@Override
		public void run() {
			StoreService service = new StoreService(url, user, pw);
			
			try {
				while(running.get() || queue.isEmpty()) {
					Task t = queue.poll(200, TimeUnit.MILLISECONDS);
					if ( t == null ) continue;
					
					t.execute(service);
					System.out.println(t.label());
				}
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}


