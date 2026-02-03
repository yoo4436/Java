package tw.brad.h1.tutor;

import java.util.List;

import tw.brad.h1.entity.Order;
import tw.brad.h1.entity.OrderItem;
import tw.brad.h1.service.OrderService;
import tw.brad.h1.service.OrderServiceImp;

public class Brad11 {

	public static void main(String[] args) {
		OrderService service = new OrderServiceImp();
		
		List<OrderItem> items = List.of(new OrderItem("item1", 2, 100),
				new OrderItem("item2", 5, 450),
				new OrderItem("item4", 8, 5800));
		Long id = service.createOrderWithItems("Key", items);
		System.out.println(id);
		
	}

}
