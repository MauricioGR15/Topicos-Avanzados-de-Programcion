import utilidades.Semaforo;

class Liebre extends Thread{
	private int Km;
	Elemento S;
	boolean LoTiene;
	Semaforo SP;
	public Liebre(Elemento S,Semaforo SP){
		Km=0;
		this.S=S;
		this.SP=SP;
		LoTiene=false;
	}
	public void run(){
		while(Km<1000){
			Km+=Rutinas.nextInt(1,1);
			if(Km>=100 && Km<=200 && !LoTiene){
				SP.Espera();
				LoTiene=true;
			}
			if(Km>200 && LoTiene){
				SP.Libera();
				LoTiene=false;
			}
			System.out.println("Liebre Km "+Km);
//			if(Rutinas.nextInt(1,50)==5){
//				try{
//					sleep(5);
//				} catch (Exception e){}
//			}
			
		}
		S.S.Espera();
		   if(!S.Band){
		     System.out.println("Ganó liebre");
		     S.Band=true;
		   }
		S.S.Libera();   
	}
}
class Tortuga extends Thread{
	private int Km;
	Elemento S;
	Semaforo SP;
	boolean LoTiene;
	public Tortuga(Elemento S,Semaforo SP){
		this.S=S;
		Km=0;
		this.SP=SP;
		LoTiene=false;
	}
	public void run(){
		while(Km<1000){
			Km++;
			if(Km>=100 && Km<=200 && !LoTiene){
				SP.Espera();
				LoTiene=true;
			}
			if(Km>200 && LoTiene){
				SP.Libera();
				LoTiene=false;
			}			
			System.out.println("Tortuga Km "+Km);
		}
		S.S.Espera();
		   if(!S.Band){
		     System.out.println("Ganó TORTUGA");
		     S.Band=true;
		   }
		S.S.Libera(); 
	}
	
}
public class AplLiebreTortyuga {

	public static void main(String [] a){
		Elemento S=new Elemento();
		Semaforo SP=new Semaforo(1);
		Liebre Lie=new Liebre(S,SP);
		Tortuga Tor=new Tortuga(S,SP);
		
		
		Lie.start();
		Tor.start();
		while (Lie.isAlive() || Tor.isAlive()){
			if(!Tor.isAlive()){
				System.out.println("Ganó tortuga");
				return;
			}
			if(!Lie.isAlive()){
				System.out.println("Ganó Liebre");
				return;
			}
			
		}
		System.out.println("Empate ");
	}
}
