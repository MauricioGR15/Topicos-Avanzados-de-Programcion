
public class MultiplicacionMatrices {

	private static final int Renglones=10000;
	private static final int Columnas=Renglones;
	public static void main(String[] args) {
		int [][] M1,M2,M3;
		M1=new int[Renglones][Columnas];
		M2=new int[Renglones][Columnas];
		M3=new int[Renglones][Columnas];
		
		Genera(M1);
		Genera(M2);
		
		for(int i=0 ; i<M1.length ; i+=1){
			for(int j=0 ; j<M1[i].length ; j++){
				for(int k=0 ; k < M2[j].length ; k++){
					M3[i][j]+=M1[i][k]*M2[k][j];
				}
			}
		}
//		Mostrar(M1);
//		Mostrar(M2);
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
