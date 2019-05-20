package utilidades;

public class NodoDL<T> {
	private NodoDL<T> Ant;
	public T Info;
	private NodoDL<T> Sig;
	
	public NodoDL(T dato){
		Info=dato;
		Ant=Sig=null;
	}

	public NodoDL<T> getAnt() {
		return Ant;
	}

	public void setAnt(NodoDL<T> ant) {
		Ant = ant;
	}

	public NodoDL<T> getSig() {
		return Sig;
	}

	public void setSig(NodoDL<T> sig) {
		Sig = sig;
	}
	
	
}
