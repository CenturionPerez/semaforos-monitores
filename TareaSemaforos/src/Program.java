
public class Program {

	public static void main(String[] args) {
		new Producer().start();
		new Consumer().start();
	}
}
