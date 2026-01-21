package tw.brad.apis;

public class RestockTask implements Task{
	private static final int product_id = 1;
	final int qty;
	
	public RestockTask(int qty) {
		this.qty = qty;
	}
	
	@Override
	public void execute(StoreService service) throws Exception {
		service.restock(product_id, qty);
	}

	@Override
	public String label() {
		return "IN :" + qty;
	}

}
