
class Ping extends Thread {
	public void run(){
		for(int i=0 ; i<20 ; i++)
			System.out.print("Ping ");
	}
}
class Pong extends Thread {
	public void run(){
		for(int i=0 ; i<20 ; i++)
			System.out.print("Pong ");
	}
}
public class HilosPingPong {

	public HilosPingPong() {
		Ping Pi=new Ping();
		Pong Po=new Pong();
		
		Pi.start();
		Po.start();
		
	}
	public static void main(String [] a){
		new HilosPingPong();
	}

}
