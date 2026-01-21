package tw.brad.apis;

public class NotEnoughException extends Exception{
	public NotEnoughException(String msg) {
		super(msg);
	}
}
