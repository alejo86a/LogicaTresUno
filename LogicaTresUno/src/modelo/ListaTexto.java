/**
 * 
 */
package modelo;

import java.util.StringTokenizer;

/**
 * @author alejandro
 *
 */
public class ListaTexto {
	
	private Palabra cabeza, ultimo;
	String[] prepo = {"a", "ante", "bajo", "con", "de", "desde", "durante", "en", "entre", "excepto",
			"hacia", "hasta", "mediante", "para", "por", "salvo", "según", "sin", "sobre", "tras"};

	/**
	 * 
	 */
	public ListaTexto() {
		cabeza = new Palabra(new StringBuilder(0));
		ultimo = cabeza;
	}
	
	/**
	 * @param texto
	 */
	public void actualizarTexto(StringBuilder texto) {
		StringTokenizer textoTokenizer = new StringTokenizer(texto.toString(), " ");
		ultimo = cabeza;
		cabeza.setPal(new StringBuilder((textoTokenizer.countTokens())));
		while(textoTokenizer.hasMoreTokens()){
			insertarAlfinal(new StringBuilder(textoTokenizer.nextToken()));
		}
	}
	
	private void insertarAlfinal(StringBuilder pal) {
		// TODO Auto-generated method stub
		Palabra x = getPrimero();
		Palabra palabraNueva = new Palabra(pal);
		ultimo.setSig(palabraNueva);
		ultimo = palabraNueva;
	}

	/**
	 * @return
	 * 
	 */
	public Palabra getPrimero(){
		return cabeza.getSig();
	}
	
	public void setPrimero(Palabra p){
		cabeza.setSig(p);
	}
	
	public Palabra getUltimo(){
		return ultimo;
	}
	
	/**
	 * @return 
	 */
	public boolean esVacia(){
		return cabeza==null;
	}
	
	/**
	 * @return
	 */
	public StringBuilder getTexto(){
		StringBuilder texto = new StringBuilder();
		Palabra x = getPrimero();
		while(x!=null){
			texto.append(" "+x.getPal());
			x= x.getSig();
		}
		texto.replace(0, 1, "");
		return texto;
	}
	
	/**
	 * 
	 */
	public void quitarSignos(){
		StringBuilder texto;
		Palabra x = getPrimero();
		while(x!=null){
			texto =new StringBuilder(x.getPal());
			for(int i=0;i<x.getPal().length();i++){
				if(!(
						(x.getPal().charAt(i)>=65 && x.getPal().charAt(i)<=89) ||
						(x.getPal().charAt(i)>=97 && x.getPal().charAt(i)<=122) ||
						(x.getPal().charAt(i)>=160 && x.getPal().charAt(i)<=165))){
					x.setPal(texto.replace(i, i+1, ""));
					i--;
				}
			}
			
			x = x.getSig();
		}
	}
	
	/**
	 * 
	 */
	
	private Palabra buscarPalabra(String pal) {
		Palabra x;
		x=getPrimero();
		while(x!=null && !(x.getPal().toString()).equals(pal.toString())){
			x= x.getSig();
		}
		return x;
	}
	
	/**
	 * 
	 */
	private Palabra Anterior(Palabra x) {
		Palabra p, ant;
		p = getPrimero();
		ant = null;
		while(p != x){
		ant = p;
		p = p.getSig();
		}
		return ant;
		}
	
	/**
	 * 
	 */
	public void preposiciones() {
		Palabra p;
		p = getPrimero().getSig();
		String aux;
		boolean bandera;

		while (p != null) {
			aux = p.getPal().toString();
			bandera = false;

			for (int i = 0; i < prepo.length; i++) {
				if (aux.equals(prepo[i])) {
					bandera = false;
					break;
				} else {
					bandera = true;
				}
			}
			if (bandera == true) {
				p = p.getSig();
				eliminar(aux);
			} else {
				p = p.getSig();
			}
		}
		eliminarNodoRepetido();
		p = getPrimero();
		p.setPal(new StringBuilder("Preposiciones:"));
		}

		public void eliminarNodoRepetido(){
		Palabra p, aux;
		p = getPrimero().getSig();
		while(p != null){
		aux = p.getSig();
		while(aux != null){
		if(p.getPal().toString().equals(aux.getPal().toString())){
		aux = aux.getSig();
		eliminarN(Anterior(aux));
		} else
		aux = aux.getSig();
		}
		p = p.getSig();
		}
		}

		public void eliminarN(Palabra p){
		if(esVacia())
		return;
		Anterior(p).setSig(p.getSig());
		p.setSig(null);
		}
	
	/**
	 * 
	 */
	public void tildadas(){
		
	}
	
	/**
	 * 
	 */
	public void eliminar(String aEliminar) {
		Palabra x, ant;
		if (esVacia()){
		return;
		}
		do {
		x = buscarPalabra(aEliminar);
		if (x == null) {
		return;
		}
		if(x == getPrimero()){
		setPrimero(x.getSig());
		}else{
		ant = Anterior(x);
		ant.setSig(x.getSig());
		if (x == ultimo) {
		ultimo = ant;
		}
		}
		} while (x != null);
		}

	/**
	 * 
	 */
	public void inverso(){
		if(esVacia()){
			return;
		}
		try{
			Palabra x=getUltimo();
			System.out.println(Anterior(x).getPal());
			while(Anterior(x)!=cabeza){
				insertarAlfinal(x.getPal());
			}
		}catch(Exception NullPointerException){
			System.out.println("esta vacia");
		}
	}
	
	/**
	 * 
	 */
	public void reemplazar(String aReemplazarVieja, String aReemplazarNueva){
		
	}
	
	/**
	 * 
	 */
	public void ordenar(){
		
	}
}
