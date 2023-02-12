

public class MonitorPuente {

	private boolean crossCars = true;

	public synchronized void comprobateCrossCars(String idName, String destino) {
		while (!crossCars) {
			//Puente ocupado
			System.out.println("Su coche " + idName + " tiene el puente ocupado para viajar al " + destino + "...");
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Su coche " + idName + " esta cruzando el puente para viajar al " + destino + "...");
		crossCars = false;
	}

	public synchronized void crossedCars(String idName, String destino) {
		//Notificamos que el puente esta disponible para cruzar con notify
		System.out.println("Su coche " + idName + " ha cruzado el puente para viajar al " + destino + " con exito...");
		crossCars = true;
		notify();
	}

	public boolean isCanCrossCars() {
		return crossCars;
	}

	public void setCanCrossCars(boolean canCrossCars) {
		this.crossCars = canCrossCars;
	}
	
	
}
