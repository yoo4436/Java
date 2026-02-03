package tw.brad.h1.tutor;

import tw.brad.h1.service.OrderService;
import tw.brad.h1.service.OrderServiceImp;

public class Brad12 {

	public static void main(String[] args) {
		OrderService service = new OrderServiceImp();
		
		Long id = service.createOrder("Eric");
		System.out.println(id);
		service.addItem(id, "item3", 8, 700);
		service.addItem(id, "item6", 45, 70896046);
	}

}
