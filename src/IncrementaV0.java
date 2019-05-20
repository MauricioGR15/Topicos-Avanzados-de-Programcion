
class IncrementaA extends Thread{
      private static long A=0;
      public void run(){
    	 System.out.println(this.getName());
         Sincronizado();
      }
      public String toString() {
      	return A+"";
      }
      private static synchronized void Sincronizado(){
    	  A++;
      }
}

public class IncrementaV0 {
   public static void main(String [] a){
	   IncrementaA [] VH=new IncrementaA[10000];
	   for(int i=0 ; i<VH.length ; i++)
		   VH[i]=new IncrementaA();
	   
	   
	   for(int i=0 ; i<VH.length ; i++)
		   VH[i].start();
	   
	   while(Vivos(VH));
	   
	   System.out.println("A=" +VH[0]);
   
/*	   
      IncrementaA H1=new IncrementaA();
      IncrementaA H2=new IncrementaA();
      IncrementaA H3=new IncrementaA();
      IncrementaA H4=new IncrementaA();
      IncrementaA H5=new IncrementaA();
      IncrementaA H6=new IncrementaA();
      H1.start();
      H2.start();
      H3.start();
      H4.start();
      H5.start();
      H6.start();

//      Thread t1 = new Thread(H1);
//      Thread t2 = new Thread(H1);
//      Thread t3 = new Thread(H1);
//      t1.start();
//      t2.start();
//      t3.start();
      while (H1.isAlive() || H2.isAlive() || H3.isAlive());
      while (H4.isAlive() || H5.isAlive() || H6.isAlive());
      System.out.println("A=" +H1);
 */
   }
   public static boolean Vivos(IncrementaA [] H){
	   for(int i=0 ; i<H.length ; i++){
		   if(H[i].isAlive())
			   return true;
	   }
	   return false;
   }
}
