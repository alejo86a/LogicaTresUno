/**
 * 
 */
package controlador;

import vista.VentanaPrincipal;
import modelo.*;

/**
 * @author alejandro & alexis
 * 
 * Clase controlador que se encarga de hacer la conexion entre el modelo y la vista mediante
 * una unica instancia del textoOriginal y textoNuevo y metodos accesores a la clase listaTexto
 *
 */
public class MaestroControlador {
	/**
	 * 
	 */
	private ListaTexto miTextoOriginal;
	private ListaTexto miTextoNuevo;
	
	/**
	 * 
	 * @param textoOriginal
	 * @param textoNuevo
	 * 
	 * Contructor de la clase recibe las instancias UNICAS  de dos listaTexto
	 */
	public MaestroControlador(ListaTexto textoOriginal, ListaTexto textoNuevo){
		this.miTextoOriginal = textoOriginal;
		this.miTextoNuevo = textoNuevo;
	}
	
	/**
	 * 
	 * @param texto
	 * 
	 */
	public void actualizarTextoOriginal(StringBuilder texto){
		miTextoOriginal.actualizarTexto(texto);
		miTextoNuevo = miTextoOriginal;
	}
	
	/**
	 * 
	 * @return texto
	 */
	public StringBuilder getTextoNuevo(){
		miTextoNuevo.quitarSignos();
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @return texto
	 */
	public StringBuilder textoPreposiciones(){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.preposiciones();
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @return texto
	 */
	public StringBuilder textoTildadas(){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.tildadas();
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * 
	 * @param aEliminar
	 * @return texto
	 */
	public StringBuilder textoEliminar(String aEliminar){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.eliminar(aEliminar);;
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @return texto
	 */
	public StringBuilder textoInverso(){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.inverso();
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @param aReemplazarVieja
	 * @param aReemplazarNueva
	 * @return texto 
	 */
	public StringBuilder textoReemplazar(String aReemplazarVieja, String aReemplazarNueva){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.reemplazar(aReemplazarVieja, aReemplazarNueva);;
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @return texto
	 */
	public StringBuilder textoOrdenar(){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.ordenar();
		return miTextoNuevo.getTexto();
	}
	
}
