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
	
}