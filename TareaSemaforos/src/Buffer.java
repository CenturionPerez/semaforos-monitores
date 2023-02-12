import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Buffer {
	//Cola de enteros que imita el almacenen de existencias
	private static Queue<String> store = new LinkedList<String>();
	//Tamaño del buffer de unidades
	public static final int buffer_size = 20;
	//Semaforo que indica cuando el buffer esta vacio
	private static Semaphore sNoVacio = new Semaphore(0, true);
	//Semaforo que indica cuando el buffer esta lleno. Inicialmente comenzaria en 10.
	private static Semaphore sNoLleno = new Semaphore(buffer_size, true);
	
	private static int buffer_size_in_procces = 0;
	
	public static Queue<String> getStore() {
		return store;
	}
	public static void setStore(Queue<String> store) {
		Buffer.store = store;
	}
	public static Semaphore getsNoVacio() {
		return sNoVacio;
	}
	public static void setsNoVacio(Semaphore sNoVacio) {
		Buffer.sNoVacio = sNoVacio;
	}
	public static Semaphore getsNoLleno() {
		return sNoLleno;
	}
	public static void setsNoLleno(Semaphore sNoLleno) {
		Buffer.sNoLleno = sNoLleno;
	}
	public static int getBuffer_size_in_procces() {
		return buffer_size_in_procces;
	}
	public static void setBuffer_size_in_procces(int buffer_size_in_procces) {
		Buffer.buffer_size_in_procces = buffer_size_in_procces;
	}
	
	
}
