/**
 * 
 */
package controlador;

import vista.VentanaPrincipal;
import modelo.*;

/**
 * @author alejandro
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
	 */
	public MaestroControlador(ListaTexto textoOriginal, ListaTexto textoNuevo){
		this.miTextoOriginal = textoOriginal;
		this.miTextoNuevo = textoNuevo;
	}
	
	/**
	 * 
	 * @param texto
	 */
	public void actualizarTextoOriginal(StringBuilder texto){
		miTextoOriginal.actualizarTexto(texto);
		miTextoNuevo = miTextoOriginal;
	}
	
	/**
	 * 
	 * @return
	 */
	public StringBuilder getTextoNuevo(){
		miTextoNuevo.quitarSignos();
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @return
	 */
	public StringBuilder textoPreposiciones(){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.preposiciones();
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @return
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
	 * @return
	 */
	public StringBuilder textoEliminar(String aEliminar){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.eliminar(aEliminar);;
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @return
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
	 * @return
	 */
	public StringBuilder textoReemplazar(String aReemplazarVieja, String aReemplazarNueva){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.reemplazar(aReemplazarVieja, aReemplazarNueva);;
		return miTextoNuevo.getTexto();
	}
	
	/**
	 * 
	 * @return
	 */
	public StringBuilder textoOrdenar(){
		miTextoNuevo.quitarSignos();
		miTextoNuevo.ordenar();
		return miTextoNuevo.getTexto();
	}
	
}
