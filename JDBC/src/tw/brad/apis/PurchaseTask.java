package tw.brad.apis;

public class PurchaseTask implements Task{
	private static final int product_id = 1;
	final int qty;
	
	public PurchaseTask(int qty) {
		this.qty = qty;
	}
	
	@Override
	public void execute(StoreService service) throws Exception {
		
		try {
			service.purchase(product_id, qty);
		} catch (NotEnoughException e) {
			System.out.println("不夠：" + qty);
		}
	}

	@Override
	public String label() {
		return "OUT :" + qty;
	}

}
