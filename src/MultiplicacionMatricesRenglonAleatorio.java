import utilidades.Semaforo;

class Elemento {
	public Semaforo S;
	public boolean  Band;
	public Elemento(){
		S=new Semaforo(1);
		Band=false;
	}
}
class HMultiplica extends Thread {
	Elemento [] VS;
	int [][] M1,M2,M3;
	public HMultiplica(Elemento [] VS,int [][] M1,int [][] M2,int [][] M3){
		this.VS=VS;
		this.M1=M1;
		this.M2=M2;
		this.M3=M3;
	}
	public void run(){
		while(HayaRenglones()){
			int Renglon=Rutinas.nextInt(0,M1.length-1);
			VS[Renglon].S.Espera();
			if(VS[Renglon].Band){
				VS[Renglon].S.Libera();
				continue;
			}
			VS[Renglon].Band=true;
			VS[Renglon].S.Libera();

			for(int j=0 ; j<M1[Renglon].length ; j++){
				for(int k=0 ; k < M2[j].length ; k++){
					M3[Renglon][j]+=M1[Renglon][k]*M2[k][j];
				}
			}

		}
	}
	public boolean HayaRenglones(){
		for(int i=0 ; i<VS.length ; i++)
			if(!VS[i].Band)
				return true;
		return false;
	}
}
public class MultiplicacionMatricesRenglonAleatorio {

	public static void main(String [] a) {
		final int Renglones=1000;
		final int Columnas=Renglones;
		Elemento [] VS=new Elemento[Renglones];
		for(int i=0 ; i<VS.length ; i++)
			VS[i]=new Elemento();
		
		int [][] M1,M2,M3;
		M1=new int[Renglones][Columnas];
		M2=new int[Renglones][Columnas];
		M3=new int[Renglones][Columnas];
		Genera(M1);
		Genera(M2);
		HMultiplica [] V=new HMultiplica[100];
		for(int i=0 ; i<V.length ; i++)
			V[i]=new HMultiplica(VS,M1,M2,M3);
		
		for(int i=0 ; i<V.length ; i++)
			V[i].start();
		Mostrar(M1);
		Mostrar(M2);
		while(HayaVivos(V));
		Mostrar(M3);
	}
	public static void Mostrar(int [] [] M){
		System.out.println("_____________________________________");
		for(int i=0 ; i<M.length ; i+=1){
			for(int j=0 ; j<M[i].length ; j++){
				System.out.printf("%3d ",M[i][j]);
			}
			System.out.println();
		}
	}
	public static void Genera(int [][] M){
		for(int i=0 ;  i<M.length ; i++)
			for(int j=0 ;j<M[i].length ; j++)
				M[i][j]=Rutinas.nextInt(1, 1);
	}
	public static boolean HayaVivos(HMultiplica [] V){
		for(int i=0 ;  i<V.length ; i++)
			if(V[i].isAlive())
				return true;
		return false;
			
		
	}
}
