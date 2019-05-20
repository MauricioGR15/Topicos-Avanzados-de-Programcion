package utilidades;

public class ListaDL<T> {
	private NodoDL<T> Frente,
					   Fin;
	public T 		   Dr;
	
	public ListaDL(){
		Frente=Fin=null;
		Dr=null;
	}
	
	public boolean InsertaFrente(T dato){
		if(dato==null)
			return false;
		NodoDL<T> nuevo;
		try{
			nuevo = new NodoDL<T>(dato);
		}catch(Exception e){
			return false;
		}
		if(Frente==null){
			Frente=Fin=nuevo;
			return true;
		}
		nuevo.setSig(Frente);
		Frente.setAnt(nuevo);
		Frente=nuevo;
		return true;
	}
	
	public boolean InsertaFin(T dato){
		if(dato==null)
			return false;
		NodoDL<T> nuevo;
		try{
			nuevo = new NodoDL<T>(dato);
		}catch(Exception e){
			return false;
		}
		if(Frente==null){
			Frente=Fin=nuevo;
			return true;
		}
		Fin.setSig(nuevo);
		nuevo.setAnt(Fin);
		Fin=nuevo;
		return true;
	}
	
	public boolean InsertaOrdenado(T dato){
		if(dato==null)
			return false;
		NodoDL<T> nuevo;
		try{
			nuevo = new NodoDL<T>(dato);
		}catch(Exception e){
			return false;
		}
		if(Frente==null){
			Frente=Fin=nuevo;
			return true;
		}
		String Idnuevo=nuevo.Info.toString();
		String IdNodo=Frente.Info.toString();
		if(Idnuevo.compareToIgnoreCase(IdNodo)<=0){
			nuevo.setSig(Frente);
			Frente.setAnt(nuevo);
			Frente=nuevo;
			return true;
		}
		IdNodo=Fin.Info.toString();
		if(Idnuevo.compareToIgnoreCase(IdNodo)>=0){
			Fin.setSig(nuevo);
			nuevo.setAnt(Fin);
			Fin=nuevo;
			return true;
		}
		NodoDL<T> Aux=Frente.getSig();
		while(Idnuevo.compareToIgnoreCase(Aux.Info.toString())>0)
			Aux=Aux.getSig();
		nuevo.setAnt(Aux.getAnt());
		nuevo.setSig(Aux);
		Aux.getAnt().setSig(nuevo);
		Aux.setAnt(nuevo);
		return true;
	}
	
	public NodoDL<T> getFrente() {
		return Frente;
	}

	public NodoDL<T> getFin() {
		return Fin;
	}

	public boolean Retira(int pos){
		if(pos>Length())
			return false;
		NodoDL<T> Aux=Frente;
		for(byte i=1;i<pos;Aux=Aux.getSig(),i++);
		EliminaNodo(Aux);
		return true;
	}
	
	public boolean Retira(T dato){
		String IdNodo=dato.toString();
		NodoDL<T> Aux;
		for(Aux=Frente ; Aux!=null && Aux.Info.toString().compareTo(IdNodo)!=0 ; Aux=Aux.getSig());
		if(Aux==null)
			return false;
		EliminaNodo(Aux);
		return true;
	}
	
	private void EliminaNodo(NodoDL<T> Aux){
		Dr=Aux.Info;
		if(Frente==Fin){
			Fin=Frente=null;
			return;
		}
		if(Aux==Frente){
			Aux.getSig().setAnt(null);
			Frente=Aux.getSig();
			return;
		}
		if(Aux==Fin){
			Fin=Aux.getAnt();
			Fin.setSig(null);
			return;
		}
		Aux.getAnt().setSig(Aux.getSig());
		Aux.getSig().setAnt(Aux.getAnt());
	}
	
	public int Length(){
		int c;
		NodoDL<T> Aux=Frente;
		for(c=0;Aux!=null;Aux.getSig(),c++);
		return c;
	}
	
	public boolean Busca(T dato){
	
		return true;
	}
}
