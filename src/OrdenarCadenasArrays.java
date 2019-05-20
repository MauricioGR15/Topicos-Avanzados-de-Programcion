import java.util.Arrays;
import java.util.Collections;



public class OrdenarCadenasArrays {

	public static void main(String[] args) {
		
		String[] nombres = new String[]{"Oscar", "Alex", "Maria", "Samuel", "Perla", "Fausto"};
		
		Arrays.sort(nombres, String::compareToIgnoreCase);
		System.out.println("arreglo ordenado ascendente  " + Arrays.toString(nombres));
		
		Arrays.sort(nombres, Collections.reverseOrder());	
		
		System.out.println("arreglo ordenado descendente  " + Arrays.toString(nombres));

	}



}
