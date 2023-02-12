import java.util.Random;

public class Producer extends Thread{
	
	private void liberar() {
		Random generatorRandomNum = new Random();
		//Siempre y cuando la store no este llena y haya sitio en el buffer en proceso
		if (Buffer.getStore().size() < Buffer.buffer_size && Buffer.getBuffer_size_in_procces() != 20) {
			if(Buffer.getStore().size() == 0 && Buffer.getBuffer_size_in_procces() != 20) {
				generatorProduct(true);
			}else {
				generatorProduct(false);
			}
			//Generamos retardo tras producir los 10 tipos unidades
			int sleeptime = generatorRandomNum.nextInt(259 - 25 + 1) + 25;

			try {
				sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				//Si hay espacio para preparar lote de existencias a liberar.
				//Si no hay se quedará esperando el productor
				Buffer.getsNoLleno().acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Buffer.getStore().size() == Buffer.buffer_size) {
				System.out.println("Productor: El buffer esta lleno, esperando a que las existencias se reduzcan...");
			}else {
				//Se realiza la liberacion en caso de no existir existencias
				liberar();
				//Una vez liberada las nuevas existencias se ejecuta release para que el consumidor pueda reservar
				Buffer.getsNoVacio().release();
			}

		}
	}
	private void generatorProduct(boolean typeSituation) {
		int countGenProducts = 0;
		//Si no hay existencias
		if(typeSituation) {
			System.out.println("Productor: Generando 20 unidades...");
			for (int i = 0; i < 20; i++) {
				int numG = (int)(Math.random()*(122 - 97 + 1) + 97);
				String typeProduct = String.valueOf((char)numG).toUpperCase();
				System.out.println("Productor: Generado el producto "+ typeProduct);
				Buffer.getStore().add(typeProduct);
				countGenProducts++;
			}			
		}else {
			System.out.println("Productor: Generando " + Buffer.getBuffer_size_in_procces() + " unidades...");
			for (int i = 0; i < Buffer.getBuffer_size_in_procces(); i++) {
				int numG = (int)(Math.random()*(122 - 97 + 1) + 97);
				String typeProduct = String.valueOf((char)numG).toUpperCase();
				System.out.println("Productor: Generado el producto "+ typeProduct);
				Buffer.getStore().add(typeProduct);
				countGenProducts++;
			}
		}
		//Store llena
		Buffer.setBuffer_size_in_procces(20);
		System.out.println("Productor: Generadas las "+ countGenProducts + " unidades para llenar la store, liberamos existencias...");
	}
}
