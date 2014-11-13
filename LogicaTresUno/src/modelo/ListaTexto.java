/**
 * 
 */
package modelo;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
	 * Constructor de la clase, inicializa un objeto de tipo Palabra llamado cabeza y otro llamado
	 * ultimo
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
		return cabeza==ultimo;
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
			if(esVacia()){
				return;
			}
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
		eliminarNodoRepetido();
		
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
		setPrimero(Anterior(getPrimero()));
		Palabra x=getUltimo(),aux=null;
		try{
			//System.out.println(Anterior(x).getPal());
			while(Anterior(x)!=cabeza){
				insertarAlfinal(x.getPal());
				aux=x;
				x=Anterior(x);
				eliminarN(aux);
			}
		}catch(Exception NullPointerException){
		}
	}
	
	/**
	 * 
	 */
	public void reemplazar(String aReemplazarVieja, String aReemplazarNueva){
		Palabra x;
		if (esVacia()){
			return;
		}
		do {
			x = buscarPalabra(aReemplazarVieja);
			if (x == null) {
				return;
			}
			x.setPal(new StringBuilder(aReemplazarNueva));
		} while (x != null);
	}
	
	/**
	 * 
	 */
	public void ordenar(){
		if(esVacia()){
			return;
		}
		Palabra i,j;
		StringBuilder aux;
		i=j=getPrimero();
		while(i!=null){
			while(j!=null && j.getSig()!=null){
				try{
					if(j.getPal().toString().compareToIgnoreCase(j.getSig().getSig().toString())>0){
						aux=j.getPal();
						j.setPal(j.getPal());
						j.getSig().setPal(aux);
					}
				}catch ( Exception nullException){
					
				}
				j = j.getSig();
			}
			i = i.getSig();
		}
		
		/**
		eliminarNodoRepetido();
		Palabra i=getPrimero(),j=getPrimero().getSig().getSig(),k;
		StringBuilder aux = null;
		while(i!=null){
			k=i;
			while(j!=null){

				System.out.println(":)");
				if((i.getPal().toString()).compareToIgnoreCase(k.getPal().toString())<0){
					k=j;
				}

				System.out.println(i.getPal().toString()+" "+k.getPal().toString());
				j=j.getSig();
			}
			aux=i.getPal();
			i.setPal(k.getPal());
			k.setPal(aux);
			i=i.getSig();
		}
		*/
		eliminarNodoRepetido();
	}
}
