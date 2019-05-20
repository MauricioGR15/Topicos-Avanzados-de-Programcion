import utilidades.Semaforo;

class OPing extends Thread {
	Semaforo SPing,SPong;
	public OPing(Semaforo SPing,Semaforo SPong){
		this.SPing=SPing;
		this.SPong=SPong;
	}
	public void run(){
		for(int i=0 ; i<50;i++){
			SPing.Espera();
			System.out.println("Ping ");
			SPong.Libera();

		}
	}
	
}
class OPong extends Thread{
	Semaforo SPing,SPong;
	public OPong(Semaforo SPing,Semaforo SPong){
		this.SPing=SPing;
		this.SPong=SPong;
	}

	public void run(){
		
		for(int i=0 ; i<50;i++){
			SPong.Espera();
			System.out.println("Pong ");
			SPing.Libera();
		}
	}
	
}
public class AplPingPong {

	public static void main(String [] a){
		
		Semaforo SPing=new Semaforo(0);
		Semaforo SPong=new Semaforo(1);
		
		OPing ObjPing=new OPing(SPing,SPong);
		OPong ObjPong=new OPong(SPing,SPong);
		
		ObjPing.start();
		ObjPong.start();
		
		
		
	}
}
