

import java.util.Random;

public class CarToSourth extends Thread {
	
	private MonitorPuente buffer;
	private String nameCar;
	private String destino = "Norte";


	public CarToSourth(MonitorPuente buffer, String idCar) {
		this.buffer = buffer;
		this.nameCar = idCar;
		start();
	}
	
	
	@Override
	public void run() {
		while(true) {
			Random num = new Random();
			int sleeptime = num.nextInt(100 - 10 + 1) + 10;
			// Revisando si es posible cruzarlo
			System.out.println(nameCar + " esta comprobando si puede cruzar al " + destino + "...");
			this.buffer.comprobateCrossCars(nameCar,destino);

			try {
				sleep(sleeptime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.buffer.crossedCars(nameCar,destino);
		}
	}
}