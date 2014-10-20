/**
 * 
 */
package controlador;

import modelo.ListaTexto;
import vista.VentanaPrincipal;

/**
 * @author alejandro
 *
 */
public class Principal {

	/**
	 * @param args
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
