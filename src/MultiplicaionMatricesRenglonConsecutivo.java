import utilidades.Semaforo;

class HRenglon extends Thread {
	private int [][] M1,M2,M3;
	private static int Renglon;
	private static Semaforo S;
	
	public HRenglon(int [][] M1,int [][] M2,int [][] M3){
		this.M1=M1;
		this.M2=M2;
		this.M3=M3;
		Renglon=-1;
		S=new Semaforo(1);
	}
	public static synchronized int getRenglon(){
		Renglon++;
		return Renglon;
	}
	public void run(){
		int MiRenglon;
		while(true){
//			int Renglon=getRenglon();
//			if(Renglon>=M1.length)
//				return;
			S.Espera();
			   if(Renglon>=M1.length-1){
				   S.Libera();
				   break;
			   }
			   Renglon++;
			   MiRenglon=Renglon;
			S.Libera();
			for(int j=0 ; j<M1[MiRenglon].length ; j++){
				for(int k=0 ; k < M2[j].length ; k++){
					M3[MiRenglon][j]+=M1[MiRenglon][k]*M2[k][j];
				}
			}
		}	

	}
}
public class MultiplicaionMatricesRenglonConsecutivo {
	private static  final int Renglones=10;

	private static final int Columnas=Renglones;

	public static void main(String [] a) {
		int [][] M1,M2,M3;
		M1=new int[Renglones][Columnas];
		M2=new int[Renglones][Columnas];
		M3=new int[Renglones][Columnas];
		
		Genera(M1);
		Genera(M2);
		
		
		HRenglon [] VH=new HRenglon[50];
		for(int i=0 ; i<VH.length ; i++)
			VH[i]=new HRenglon(M1,M2,M3);
		
		for(int i=0 ; i<VH.length ; i++)
			VH[i].start();
		
		while(Vivos(VH));
		
		Mostrar(M1);
		Mostrar(M2);
		Mostrar(M3);
		
	}
	public static void Genera(int [][] M){
		for(int i=0 ;  i<M.length ; i++)
			for(int j=0 ;j<M[i].length ; j++)
				M[i][j]=Rutinas.nextInt(1, 1);
	}
	  public static boolean Vivos(Thread [] H){
		   for(int i=0 ; i<H.length ; i++){
			   if(H[i].isAlive())
				   return true;
		   }
		   return false;
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
}
