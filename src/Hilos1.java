class Tarea1 extends Thread{
   public void run(){
	   for(int i=0 ; i<20 ; i++)
      System.out.println(" *** PROCESO UNO TRABAJANDO ");
   }
}

class Tarea2 extends Thread{
   public void run(){
	   for(int i=0 ; i<20 ; i++)
      System.out.println(" *** PROCESO DOS TRABAJANDO *");
   }
}
class Tarea3 extends Thread{
   public void run(){
	   for(int i=0 ; i<20 ; i++)
      System.out.println(" *** PROCESO TRES TRABAJANDO ***");
   }
}

public class Hilos1 {
	public static void main(String [] a){
	      Tarea1 t1 = new Tarea1();
	      Tarea2 t2 = new Tarea2();
	      Tarea3 t3 = new Tarea3();
	      t1.start();
	      t2.start();
	      t3.start();
	      System.out.println("FINALIZÓ EL MAIN");
	   }

}
