import java.util.Arrays;

class DatoPersona implements Comparable {

	private String Nombre;
	private int Edad, Peso;

	public DatoPersona(String Nombre,int Edad){
		this.Nombre=Nombre;
		this.Edad=Edad;
		Peso=8;
	}
	public String getNombre(){
		return Nombre;
	}
	public int getEdad(){
		return Edad;
	}
	public int getPeso(){
		return Peso;
	}
	public String toString(){
		return Rutinas.PonCeros(Edad, 5)+Rutinas.PonBlancos(Nombre,50);
	}
	public int compareTo(Object Para) {
		if (this.toString().compareTo(Para.toString())>0) {
			return 1;
		} else if (this.toString().compareTo(Para.toString())<0) {
			return -1;
		}
		return 0;
	}
	

}
public class OrdenaPorAtributosComparable {
	public static void main(String[] args) {
		DatoPersona [] V=new DatoPersona[20];
		for(int i=0 ; i < V.length ; i++)
			V[i]=new DatoPersona(Rutinas.nextNombre(1),Rutinas.nextInt(1, 2));
			Arrays.sort(V);
			System.out.println("arreglo ordenado descendente  " + Arrays.toString(V));


		// También lo puedo usar en el método de ordenamiento intercambio
//		for(int i=0 ; i<V.length-1 ; i++)
//			for(int j=i+1 ; j<V.length ; j++)
//				if(V[i].compareTo(V[j])>0 ){
//					DatoPersona Aux=V[i];
//					V[i]=V[j];
//					V[j]=Aux;
//
//				}
		for(int i=0 ; i < V.length ; i++)
			System.out.println(V[i].getEdad()+"\t"+V[i].getNombre()+"\t"+V[i].getPeso());

	}
}
