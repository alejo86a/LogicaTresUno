/**
 * 
 */
package modelo;

/**
 * @author alejandro
 *
 */
public class Palabra {
	/**
	 * Se declaran los atributos de la clase
	 * 'pal' un StringBuilder que es un campo de texto
	 * 'sig' un objeto palabra que es un campo que la siguiente palabra
	 * 
	 * Se utiliza encapsulamiento en esta clase, y se accede a sus atributos 
	 * mediante getters y stters
	 */
	private StringBuilder pal;
	private Palabra sig;
	
	
	/**
	 * Constructor que crea un objeto palabra solo con el campo
	 * texto y el otro en null
	 * 
	 * @param 'pal' campo texto 
	 */
	public Palabra(StringBuilder pal) {
		this.pal = pal;
		this.sig = null;
	}
	/**
	 * Constructor que crea un objeto palabra con el campo
	 * texto y una referencia a la siguiente palabra
	 * 
	 * @param 'pal' campo de texto
	 * @param 'sig' referencia a la siguiente palabra
	 */
	public Palabra(StringBuilder pal, Palabra sig) {
		this.pal = pal;
		this.sig = sig;
	}
	/**
	 * @return el campo texto del nodo
	 */
	protected StringBuilder getPal() {
		return pal;
	}
	/**
	 * @param Recibe un StringBuilder 'pal' y reemplaza el campo texto por este
	 */
	protected void setPal(StringBuilder pal) {
		this.pal = pal;
	}
	/**
	 * @return el nodo siguiente
	 */
	protected Palabra getSig() {
		return sig;
	}
	/**
	 * @param Recibe un objeto palabra llamado 'sig' y reemplaza el campo
	 * sig por este
	 */
	protected void setSig(Palabra sig) {
		this.sig = sig;
	}
}
