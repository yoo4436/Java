package tw.brad.h2;

public class Brad08 {
	private static final String hql = """
			select 
				o.customer.cname,
				o.employee.lastName,
				p.productName,
				d.unitPrice,
				d.quantity
			from order o
			join o.orderDetails d
			join d.product p
			where o.orderId = :orderId
			""";
	
	public static void main(String[] args) {
		
	}

}
