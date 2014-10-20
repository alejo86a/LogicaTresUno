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
	
	private ListaTexto miTextoOriginal;
	private ListaTexto miTextoNuevo;
	
	public MaestroControlador(ListaTexto textoOriginal, ListaTexto textoNuevo){
		this.miTextoOriginal = textoOriginal;
		this.miTextoNuevo = textoNuevo;
	}
	
	public void actualizarTextoOriginal(StringBuilder texto){
		miTextoOriginal.actualizarTexto(texto);
		miTextoNuevo = miTextoOriginal;
	}
	
	public StringBuilder getTextoNuevo(){
		miTextoNuevo.quitarSignos();
		return miTextoNuevo.getTexto();
	}
	
}
