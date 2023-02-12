import java.util.Random;

public class Consumer extends Thread{

	private void reserve() {
		Random generatorRandomNum = new Random();
		//Reservando cada existencia
		int numG = generatorRandomNum.nextInt(20 - 1 + 1) + 1;
		int aux = numG;
		int sizeStore = Buffer.getStore().size();
		//Siempre y cuando el consumidor desee reservar una cantidad posible
		if(Buffer.getStore().size() >= numG) {
			System.out.println("Consumidor: Reservando " + numG + " unidad/es...");
			String product;
			while (!Buffer.getStore().isEmpty() && numG != 0) {
				product = Buffer.getStore().poll();
				System.out.println("Consumidor: Reservando la existencia " + product);
				numG--;
			}
			System.out.println("Consumidor: Todas las unidades quedaron reservadas...");
			
			//Indicamos al buffer en proceso las unidades que tiene que generar el productor
			int bufferActual = Buffer.getBuffer_size_in_procces();
			System.out.println("Quedan un total de " + (sizeStore - aux) + " existencias");
			Buffer.setBuffer_size_in_procces(Buffer.buffer_size - (sizeStore - aux));
			
			int sleeptime = generatorRandomNum.nextInt(300 - 25 + 1) + 25;
			//Generamos retardo antes de comenzar a reservar
			try {
				sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Consumidor: El buffer no tiene existencias suficientes parar reservar " + numG +" unidad/es queda esperar...");
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				//Si hay espacio para preparar consumir el nuevo lote de existencias.
				//Si no hay se quedará esperando el productor
				Buffer.getsNoVacio().acquire();
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (Buffer.getStore().size() == 0) {
				System.out.println("Consumidor: El buffer esta vacio, esperando a que haya existencias...");
			}else {
				reserve();
				Buffer.getsNoLleno().release();
			}
		}
	}
}
