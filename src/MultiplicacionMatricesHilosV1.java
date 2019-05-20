
class Pares extends Thread {
	private int [][] M1,M2,M3;
	public Pares(int [][] M1,int [][] M2,int [][] M3){
		this.M1=M1;
		this.M2=M2;
		this.M3=M3;
	}
	public void run(){

		for(int i=0 ; i<M1.length ; i+=2){
			for(int j=0 ; j<M1[i].length ; j++){
				for(int k=0 ; k < M2[j].length ; k++){
					M3[i][j]+=M1[i][k]*M2[k][j];
				}
			}
		}
	}
}
class ImPares extends Thread {
	private int [][] M1,M2,M3;
	public ImPares(int [][] M1,int [][] M2,int [][] M3){
		this.M1=M1;
		this.M2=M2;
		this.M3=M3;
	}
	public void run(){
		for(int i=1 ; i<M1.length ; i+=2){
			for(int j=0 ; j<M1[i].length ; j++){
				for(int k=0 ; k < M2[j].length ; k++){
					M3[i][j]+=M1[i][k]*M2[k][j];
				}
			}
		}
	}
	
}


public class MultiplicacionMatricesHilosV1 {
	private static final int Renglones=10000;
	private static final int Columnas=Renglones;

	public static void main(String[] args) {
		int [][] M1,M2,M3;
		M1=new int[Renglones][Columnas];
		M2=new int[Renglones][Columnas];
		M3=new int[Renglones][Columnas];
		
		Genera(M1);
		Genera(M2);
		
		Pares HP=new Pares(M1,M2,M3);
		ImPares HI=new ImPares(M1,M2,M3);
		HP.start();
		HI.start();
//		Mostrar(M1);
//		Mostrar(M2);

		while(HP.isAlive() || HI.isAlive());
//		Mostrar(M3);

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
				M[i][j]=Rutinas.nextInt(1, 10);
	}
}
