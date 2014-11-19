package modelo;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author alejandro & alexis
 * 
 * clase lista que se encarga de convetir un texto en una lista en la cual cada palabra se guarda en un
 * objeto Palabra que tiene una referencia a la siguiente palabra dentro del texto y que no guarda textos
 *
 */
public class ListaTexto {
	
	private Palabra cabeza, ultimo;
	String[] prepo = {"a", "ante", "bajo", "con", "de", "desde", "durante", "en", "entre", "excepto",
			"hacia", "hasta", "mediante", "para", "por", "salvo", "según", "sin", "sobre", "tras"};

	/**
	 * Constructor de la clase, inicializa un objeto de tipo Palabra llamado cabeza y otro llamado
	 * ultimo, los iguala lo que denota que la lista esta vacia.
	 */
	public ListaTexto() {
		cabeza = new Palabra(new StringBuilder(0));
		ultimo = cabeza;
	}
	
	/**
	 * @param texto
	 * 
	 * metodo que se encarga de convetir un texto en la lista cojiendo cada palabra de texto mediante 
	 * el uso de laclase StringTokenizer y que identifica las palabras por que estan separadas por un espacio.
	 */
	public void actualizarTexto(StringBuilder texto) {
		StringTokenizer textoTokenizer = new StringTokenizer(texto.toString(), " ");
		ultimo = cabeza;
		cabeza.setPal(new StringBuilder((textoTokenizer.countTokens())));
		while(textoTokenizer.hasMoreTokens()){
			insertarAlfinal(new StringBuilder(textoTokenizer.nextToken()));
		}
	}
	
	/**
	 * 
	 * @param pal
	 * 
	 * inserta una nueva palabra en la lista texto recibiendo un stringBuilder que es loq eu va a contener la palabra
	 * en su campo texto
	 */
	private void insertarAlfinal(StringBuilder pal) {
		// TODO Auto-generated method stub
		Palabra x = getPrimero();
		Palabra palabraNueva = new Palabra(pal);
		ultimo.setSig(palabraNueva);
		ultimo = palabraNueva;
	}

	/**
	 * @return Palabra
	 * 
	 * retorna la primera palabra del texto
	 */
	public Palabra getPrimero(){
		return cabeza.getSig();
	}
	
	/**
	 * 
	 * @param p
	 * 
	 * cambia la referencia a la primera palabra por la que recibe en el parametro
	 */
	public void setPrimero(Palabra p){
		cabeza.setSig(p);
	}
	
	/**
	 * 
	 * @return Palabra
	 * 
	 * retorna la ultima palabra del texto
	 */
	public Palabra getUltimo(){
		return ultimo;
	}
	
	/**
	 * @return booleano
	 * 
	 * retorna si la lista es vacia o no
	 */
	public boolean esVacia(){
		return cabeza==ultimo;
	}
	
	/**
	 * @return texto
	 * 
	 * Asi como la se convierte un texto en lista la lista en este metodo es nuevamente retornada como un texto completo en
	 * un stringbuilder
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
						
						(x.getPal().charAt(i)==193 || x.getPal().charAt(i)==201 ||
						x.getPal().charAt(i)==205 || x.getPal().charAt(i)==211 ||
						x.getPal().charAt(i)==218 || x.getPal().charAt(i)==225 ||
						x.getPal().charAt(i)==233 || x.getPal().charAt(i)==237 || 
						x.getPal().charAt(i)==243 || x.getPal().charAt(i)==250))){
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
		if(esVacia()){
			return;
		}
		Anterior(p).setSig(p.getSig());
		p.setSig(null);
	}
	
	/**
	 * 
	 */
	public void tildadas() {
		Palabra p;
		p = getPrimero().getSig();
		String aux;
		boolean bandera;

		while (p != null) {
			aux = p.getPal().toString();
			bandera = false;

			for (int i = 0; i < aux.length(); i++) {
				if (!((aux.charAt(i)==193 || aux.charAt(i)==201 ||
						aux.charAt(i)==205 || aux.charAt(i)==211 ||
						aux.charAt(i)==218 || aux.charAt(i)==225 ||
						aux.charAt(i)==233 || aux.charAt(i)==237 || 
						aux.charAt(i)==243 || aux.charAt(i)==250))) {
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
		p.setPal(new StringBuilder("tildadas:"));
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
	public void inverso() {
		if (esVacia()) {
			return;
		}
		Palabra x;
		x = getUltimo();
		while(x != getPrimero()){
			x.setSig(Anterior(x));
			x = Anterior(x);
		}
		x.setSig(null);
		cabeza.setSig(ultimo);
		ultimo = x;
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
	public void ordenar() {
		Palabra x, y;
		x = getPrimero();
		y = x.getSig();

		if (esVacia() || (getPrimero() == getUltimo())) {
			return;
		}

		while (y != null) {
			if ((x.getPal().toString()).compareTo(y.getPal().toString()) > 0) {
				if (x == getPrimero()) {
					x.setSig(y.getSig());
					y.setSig(x);
					cabeza.setSig(y);
					if (x == getPrimero())
						setPrimero(y);
					x = getPrimero();
					y = x.getSig();
				} else {
					Anterior(x).setSig(y);
					x.setSig(y.getSig());
					y.setSig(x);
					if (y == getUltimo())
						ultimo = x;
					x = getPrimero();
					y = x.getSig();
				}
			} else {
				x = y;
				y = x.getSig();
			}
		}
		eliminarNodoRepetido();
	}
}
