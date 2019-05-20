package AppContabilidad;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import utilidades.Rutinas;

public class Modelo {
	

	private RandomAccessFile archivo_cue,archivo_pol,index_cue;
	final String cuenta = "cuentas.dat",
			indCue = "indexCue.dat",
			pol = "polizas.dat";
	private int noCue;
	private ArrayList <String>listaCuentas = new ArrayList<String>();
	private ArrayList <Cuenta>arrayCuentas = new ArrayList<Cuenta>();
	private int posMod; 
	private int contAfect;
	
	public void agregarCuenta(Cuenta cue){
		
		try {
			File file = new File(cuenta);
			archivo_cue = new RandomAccessFile(file,"rw");
			File file2 = new File(indCue);
			index_cue = new RandomAccessFile(file2,"rw");
			
			noCue = (int) (archivo_cue.length()/Cuenta.TAM);
			
			archivo_cue.seek(archivo_cue.length());
			archivo_cue.writeUTF(cue.getNumCue());
			archivo_cue.writeUTF(cue.getNombre());
			archivo_cue.writeDouble(cue.getSaldo());
			archivo_cue.writeDouble(cue.getCargo());
			archivo_cue.writeDouble(cue.getAbono());
			archivo_cue.writeBoolean(cue.isActivo());
		
			System.out.println("\n# Cuentas >>> " + (noCue+1));
//			mostrarCuentas(archivo_cue);
			archivo_cue.close();
			String noC = cue.getNumCue();
			
			agregarIndexCue(noC);
			sortCue(0,noCue,index_cue);
			index_cue.seek(0);
			index_cue.close();

		}catch(IOException e) {e.printStackTrace();}
	}
	
	private void agregarIndexCue(String cue) throws IOException {
		index_cue.seek(index_cue.length());
		index_cue.writeUTF(cue);
		index_cue.writeInt(noCue);
		System.out.println("Index length  " + index_cue.length() );
	}

	
	public Cuenta getCuenta(int pos){
		Cuenta cue = null;
		posMod = pos;
		try {
			File file = new File(cuenta);
			RandomAccessFile  archivo_cue = new RandomAccessFile(file,"rw");
			File file2 = new File(indCue);
			RandomAccessFile indexC = new RandomAccessFile(file2,"r");
			
			String noCue = Rutinas.PonBlancos(listaCuentas.get(pos), 6);
			int posicion = busquedaBinaria(noCue,indexC);
			
			archivo_cue.seek(posicion*Cuenta.TAM);
			String nC = archivo_cue.readUTF();
			String nom = archivo_cue.readUTF();
			double sal = archivo_cue.readDouble();
			
			cue = new Cuenta(nC,nom,sal);
			
			archivo_cue.close();
			indexC.close();
			
		}catch(IOException e) {}
		return cue;
	}
	
	public Cuenta getCuenta(String cue) {
		Cuenta Cue = null;
		boolean b = false;
		cue = Rutinas.PonBlancos(cue, 9);
		try {
			File file = new File(cuenta);
			RandomAccessFile  archivo_cue = new RandomAccessFile(file,"rw");
			File file2 = new File(indCue);
			RandomAccessFile indexC = new RandomAccessFile(file2,"r");
			
			int pos = busquedaBinaria(cue, indexC);
			
			if(pos == -1)
				b = true;
			
			archivo_cue.seek(Cuenta.TAM * pos);
			String noC = archivo_cue.readUTF();
			String nombre = archivo_cue.readUTF();
			double saldo = archivo_cue.readDouble();
			double cargo = archivo_cue.readDouble();
			double abono = archivo_cue.readDouble();
			boolean activa = archivo_cue.readBoolean();
			Cue = new Cuenta(noC,nombre,saldo,cargo,abono,activa);
			
			if(!activa) 
				b = true;
			
			archivo_cue.close();
			indexC.close();
			
		}catch(IOException e) {}
		
		if(b)
			return null;
		
		return Cue;
	}
	
	public void modificaCuenta(String nuevo, int pos) {
		try {
			File file = new File(cuenta);
			RandomAccessFile archivoCuenta = new RandomAccessFile(file,"rw");
			
			archivoCuenta.seek(pos * Cuenta.TAM);
			archivoCuenta.readUTF();
			archivoCuenta.writeUTF(nuevo);
			
			archivoCuenta.close();
			
		}catch(IOException e) {System.out.println("HAY ERROR :(");}
	}
	
	public void bajaCuenta(String dato) {
		try {
			File file = new File(indCue);
			RandomAccessFile indexCuenta = new RandomAccessFile(file, "rw");
			File file2 = new File(cuenta);
			RandomAccessFile archivoCuenta = new RandomAccessFile(file2,"rw");
			
			int pos = busquedaBinaria(dato,indexCuenta);
			archivoCuenta.seek((pos * Cuenta.TAM) + (Cuenta.TAM-1));
			archivoCuenta.writeBoolean(false);		
			mostrarCuentas(archivoCuenta);
	
			indexCuenta.close();
			archivoCuenta.close();
		}catch(IOException e) {}
	}
	
	private int busquedaBinaria(String dato,RandomAccessFile indexCuenta) throws IOException {
		int noCue = (int) (indexCuenta.length()/Cuenta.TAM2);
		int inicio = 0;
		int fin = noCue-1,pos;
		String aux;
		dato = Rutinas.PonBlancos(dato, 9);
		
		while(inicio <= fin) {
			pos = (inicio+fin)/2;
			indexCuenta.seek(pos*Cuenta.TAM2);
			aux = indexCuenta.readUTF().trim();
			aux = Rutinas.PonBlancos(aux, 9);
			if(aux.compareTo(dato) == 0)
				return indexCuenta.readInt();
			if(aux.compareTo(dato)<0) {
				inicio = pos+1;
				continue;
			}
			fin = pos-1;
		}
		return -1;
	}
	
	public boolean Duplicada(String cue){ //Retorna true si ya existe una igual
		boolean b = false;
		try {
			File file = new File(indCue);
			RandomAccessFile indexCue = new RandomAccessFile(file,"rw");
			File file2 = new File(cuenta);
			RandomAccessFile archivoCuenta = new RandomAccessFile(file2, "rw");
			
			if(indexCue.length()==0) {
				archivoCuenta.close();
				indexCue.close();
				return b;
			}
			
			if(busquedaBinaria(cue,indexCue) != -1)
				b = true;
			
			indexCue.close();
			archivoCuenta.close();
		}catch(IOException e) {}
		
		return b;
	}
	
	public boolean validaMayor(String noCue) { 
		boolean b = false;
		
		noCue = noCue.trim();
		if(noCue.length() == 3) return true;
		
		try {
			File file = new File(indCue);
			RandomAccessFile indexCue = new RandomAccessFile(file,"rw");
			File file2 = new File(cuenta);
			RandomAccessFile archivoCuenta = new RandomAccessFile(file2, "rw");
			
			String AuxNoCue;
			if(noCue.length() == 6) {
				AuxNoCue = noCue.substring(0,3);
				if(busquedaBinaria(AuxNoCue,indexCue) != -1 )
					b = true;
			}
			
			if(noCue.length() == 9){
				AuxNoCue = noCue.substring(0,6);
				if(busquedaBinaria(AuxNoCue,indexCue) != -1 )
					b = true;
			}
			
			indexCue.close();
			archivoCuenta.close();
		} catch(IOException e) {}
		return b;
	}
	
	private int partitionCue(int low, int high, RandomAccessFile indexCuenta) throws IOException {
		
		String pivCue,cuenta;
		int pivPos, pos=0;
		indexCuenta.seek(high*Cuenta.TAM2);
		pivCue = indexCuenta.readUTF();
		pivPos = indexCuenta.readInt();
		
		int i = low-1;
		for(int j=low;j<high;j++) {
			indexCuenta.seek(j*Cuenta.TAM2);
			cuenta = indexCuenta.readUTF();
			cuenta = Rutinas.PonBlancos(cuenta.trim(), 9);
			pos = indexCuenta.readInt();
			
			if(cuenta.compareTo(pivCue) <=0) {
				i++;
				indexCuenta.seek(i*Cuenta.TAM2);
				String cuentaAux = indexCuenta.readUTF();
				int posAux = indexCuenta.readInt();
				indexCuenta.seek(i*Cuenta.TAM2);
				indexCuenta.writeUTF(cuenta);
				indexCuenta.writeInt(pos);
				indexCuenta.seek(j*Cuenta.TAM2);
				indexCuenta.writeUTF(cuentaAux);
				indexCuenta.writeInt(posAux);
			}
		}
		
		indexCuenta.seek((i*Cuenta.TAM2)+Cuenta.TAM2);
		String cuentaAux = indexCuenta.readUTF();
		int posAux = indexCuenta.readInt();
		indexCuenta.seek((i*Cuenta.TAM2)+Cuenta.TAM2);
		indexCuenta.writeUTF(pivCue);
		indexCuenta.writeInt(pivPos);
		indexCuenta.seek(high*Cuenta.TAM2);
		indexCuenta.writeUTF(cuentaAux);
		indexCuenta.writeInt(posAux);
		
		return i+1;
		
	}
	
	private void sortCue(int low, int high,RandomAccessFile indexCuenta) throws IOException {
		if(low < high) {
			int pi = partitionCue(low,high,indexCuenta);
			sortCue(low,pi-1,indexCuenta);
			sortCue(pi+1,high,indexCuenta);
		}
			
	}
	
	private int partitionPol(int low, int high, RandomAccessFile archPol) throws IOException{
		
		double pivImp,imp;
		int pivPol,pol;
		archPol.seek(high*Poliza.TAM);
		pivPol = archPol.readInt();
		pivImp = archPol.readDouble();
		
		int i = low -1;
		for(int j = low; j<high;j++) {
			archPol.seek(j*Poliza.TAM);
			pol = archPol.readInt();
			imp = archPol.readDouble();
				
			if(pol <= pivPol) {
				i++;
				archPol.seek(i*Poliza.TAM);
				int polAux = archPol.readInt();
				double impAux = archPol.readDouble();
				archPol.seek(i*Poliza.TAM);
				archPol.writeInt(pol);
				archPol.writeDouble(imp);
				archPol.seek(j*Poliza.TAM);
				archPol.writeInt(polAux);
				archPol.writeDouble(impAux);
			}
		}
		
		archPol.seek((i*Poliza.TAM)+Poliza.TAM);
		int polAux = archPol.readInt();
		double impAux = archPol.readDouble();
		archPol.seek((i*Poliza.TAM)+Poliza.TAM);
		archPol.writeInt(pivPol);
		archPol.writeDouble(pivImp);
		archPol.seek(high*Poliza.TAM);
		archPol.writeInt(polAux);
		archPol.writeDouble(impAux);
		
		return i+1;
	}
	
	private void sortPol(int low, int high, RandomAccessFile archPol) throws IOException{
		if(low < high) {
			int pi = partitionPol(low, high, archPol);
			sortPol(low,pi-1,archPol);
			sortPol(pi+1,high,archPol);
		}
	}
	
	private void mostrarCuentas(RandomAccessFile archivoCuenta) throws IOException {
		int pos;
		for(int i=0;i<noCue+1;i++) {
			pos = i * Cuenta.TAM;
			System.out.println("Tam" + archivoCuenta.length());
			archivoCuenta.seek(pos);		
			System.out.println("\nNoCuenta: "+archivoCuenta.readUTF());
			System.out.println("Nombre: "+archivoCuenta.readUTF());
			System.out.println("Saldo: "+archivoCuenta.readDouble());
			System.out.println("Cargo: " + archivoCuenta.readDouble());
			System.out.println("Abono: " + archivoCuenta.readDouble());
			System.out.println("Activo: "+archivoCuenta.readBoolean());
		}
	}

	public int getPosMod() {
		return posMod;
	}

	public void setPosMod(int posMod) {
		this.posMod = posMod;
	}
	
	public void agregarPoliza(Poliza poliza) {
		
		try {
			File file = new File(pol);
			archivo_pol = new RandomAccessFile(file,"rw");
			archivo_pol.seek(archivo_pol.length());
			archivo_pol.writeInt(poliza.getNoPol());
			archivo_pol.writeUTF(poliza.getNombrePol());
			archivo_pol.writeDouble(poliza.getSuma());
			
			int noPol = (int) (archivo_pol.length()/Poliza.TAM);
			
			sortPol(0,noPol,archivo_pol);
			
			archivo_pol.close();
		}catch(IOException e) {}
	}
	
	public boolean polizaRepetida(int noPol) {
		boolean b = false;
		try {
			File file = new File(pol);
			RandomAccessFile archPol = new RandomAccessFile(file, "rw");
			
			int nP = (int) (archPol.length()/Poliza.TAM);
			archPol.seek(0);
			
			for(int i=0; i<nP;i++) {
				int auxNoP = archPol.readInt();
				if(noPol == auxNoP) {
					b = true;
					break;
				}
			}
			
			archPol.close();
		}catch(IOException e) {}
		return b;
	}
	
	public boolean archEstado() {
		boolean b = true;
		try {
			File file = new File(indCue);
			RandomAccessFile raf = new RandomAccessFile(file,"rw");
			if(raf.length()==0)
				b = false;
			raf.close();
		}catch(IOException e) {}
		return b;
	}
	

	public void afectacion(Vector<Cuenta>ssCuentas) {
		try {
			File file = new File(indCue);
			RandomAccessFile index_cue = new RandomAccessFile(file,"rw");
			File file2 = new File(cuenta);
			RandomAccessFile archivo_cue = new RandomAccessFile(file2, "rw");

			String ssC;
			int pos;
			double cargo,abono;
			Cuenta Cue,sCue;
			noCue = (int) (archivo_cue.length()/Cuenta.TAM);
			setContAfect(ssCuentas.size() * 3);
			for(int i = 0; i<ssCuentas.size(); i++) {
				ssC = ssCuentas.get(i).getNumCue();
				cargo = ssCuentas.get(i).getCargo();
				abono = ssCuentas.get(i).getAbono();
				
				pos = busquedaBinaria(ssC,index_cue);
				
				if(abono == 0) {
					archivo_cue.seek((pos* Cuenta.TAM)+Cuenta.POSC);
					archivo_cue.writeDouble(cargo);
					//SCuenta
					sCue = getCuenta(ssC.substring(0, 6));
					pos = busquedaBinaria(sCue.getNumCue(),index_cue);
					archivo_cue.seek((pos* Cuenta.TAM)+Cuenta.POSC);
					archivo_cue.writeDouble(cargo + sCue.getCargo());
					//Cuenta
					Cue = getCuenta(ssC.substring(0, 3));
					pos = busquedaBinaria(Cue.getNumCue(),index_cue);
					archivo_cue.seek((pos* Cuenta.TAM)+Cuenta.POSC);
					archivo_cue.writeDouble(cargo + Cue.getCargo());
				}
				else {
					archivo_cue.seek((pos* Cuenta.TAM)+Cuenta.POSA);
					archivo_cue.writeDouble(abono);
					//SCuenta
					sCue = getCuenta(ssC.substring(0, 6));
					pos = busquedaBinaria(sCue.getNumCue(),index_cue);
					archivo_cue.seek((pos* Cuenta.TAM)+Cuenta.POSA);
					archivo_cue.writeDouble(abono + sCue.getAbono());
					//Cuenta
					Cue = getCuenta(ssC.substring(0, 3));
					pos = busquedaBinaria(Cue.getNumCue(),index_cue);
					archivo_cue.seek((pos* Cuenta.TAM)+Cuenta.POSA);
					archivo_cue.writeDouble(abono + Cue.getAbono());
				}
			}

			index_cue.close();
			archivo_cue.close();
		} catch(IOException e) {System.out.println("Hay error");}
	}
	

	public ArrayList<String> llenaArrayList(){
		try {
			File file2 = new File(indCue);	
			RandomAccessFile ind = new RandomAccessFile(file2,"rw");
			listaCuentas.clear();
			File file = new File(cuenta);
			RandomAccessFile raf = new RandomAccessFile(file,"rw");
	
			noCue = (int) (ind.length()/Cuenta.TAM2);
			
			int pos=0;
			String noC;
			for(int i=0; i<noCue; i++) {
				pos = i*Cuenta.TAM2;
				ind.seek(pos);
				noC = ind.readUTF();
				int posI = ind.readInt();
				raf.seek((posI * Cuenta.TAM)+(Cuenta.TAM-1));
				boolean b = raf.readBoolean();
				if(b) {
					listaCuentas.add(noC.trim());
				}
			}
			
			ind.close();
			raf.close();
		}catch(IOException e) {e.printStackTrace();}
		return listaCuentas;
		}
	
	public ArrayList <Cuenta> getArrayCuentas() {

		try {
			File file1 = new File(cuenta);
			RandomAccessFile archivoCuenta = new RandomAccessFile(file1,"rw");
			int noCue = (int) archivoCuenta.length()/Cuenta.TAM;
			arrayCuentas.clear();

			archivoCuenta.seek(0);
			for(int i=0; i<noCue; i++) {
				String cuenta = archivoCuenta.readUTF();
				String nombre = archivoCuenta.readUTF();
				double saldo = archivoCuenta.readDouble();
				double cargo = archivoCuenta.readDouble();
				double abono = archivoCuenta.readDouble();
				boolean activo = archivoCuenta.readBoolean();
				arrayCuentas.add(new Cuenta(cuenta,nombre,saldo,cargo,abono,activo));
			}
			archivoCuenta.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return arrayCuentas;
	}

	public int getContAfect() {
		return contAfect;
	}

	public void setContAfect(int contAfect) {
		this.contAfect = contAfect;
	}
}
