/**
 * 
 */
package controlador;

import modelo.ListaTexto;
import vista.VentanaPrincipal;

/**
 * @author alejandro & alexis
 * 
 *Clase principal del sistema que contiene el metodo main que crea un objeto controlador y 2 listas
 *para representar en el modelo el texto en el que se escribe y en el que aparecen los resultados
 *
 */
public class Principal {

	/**
	 * 
	 * ventanaP objeto del tipo VentanaPrincipal
	 * textoOriginal objeto del tipo ListTexto
	 * textoNuevo objeto del tipo ListTexto
	 * 
	 */
	private VentanaPrincipal ventanaP;
	private ListaTexto textoOriginal;
	private ListaTexto textoNuevo;
	
	public Principal(){
		textoOriginal = new ListaTexto();
		textoNuevo = new ListaTexto();
		ventanaP = new VentanaPrincipal(textoOriginal,textoNuevo);
		ventanaP.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Principal p = new Principal();
	}

}
