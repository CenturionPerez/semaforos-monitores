

public class Main {

	public static void main(String[] args) {
		System.out.println("Inicio el Programa");
		MonitorPuente buffer = new MonitorPuente();
		new CarToNorth(buffer, "Seat Ibiza");
		new CarToSourth(buffer, "Porsche");
	}

}
