package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import controlador.MaestroControlador;
import modelo.ListaTexto;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	
	/**
	 * creacion de variables que guardaran las instancian de las listas
	 * textoOriginal y textoNuevo;
	 * 
	 */
	private ListaTexto miTextoOriginal;
	private ListaTexto miTextoNuevo;
	
	/**
	 * creacion de los elementos de la ventana
	 */
	private JEditorPane jTAIzq, jTADer;
	private JScrollPane jScrollText1, jScrollText2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private MaestroControlador miMaestroControlador;
	
	
	/**
	 * Creacion del frame.
	 */
	public VentanaPrincipal(ListaTexto textoOriginal, ListaTexto textoNuevo) {
		//iniciar Objetos
		this.miTextoOriginal = textoOriginal;
		this.miTextoNuevo = textoNuevo;
		miMaestroControlador = new MaestroControlador(miTextoOriginal, miTextoNuevo);
		
		//iniciar propiedades del frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth(), (int) d.getHeight()-39);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 100, 658, 568);
        contentPane.add(scrollPane);
        
        //iniciar atributos del frame
        jTAIzq = new JEditorPane();
        jTAIzq.setText("");
        jTAIzq.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		actualizarTextoOriginal(e);        		
        	}
        });
        scrollPane.setViewportView(jTAIzq);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(688, 100, 658, 568);
        contentPane.add(scrollPane_1);
        jTADer = new JEditorPane();
        jTADer.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		e.consume();
        	}
        });
        scrollPane_1.setViewportView(jTADer);
	}
	
	/**
	 * metodo que actualiza el texto original si se escribe algo en el textArea izquierdo
	 */
	public void actualizarTextoOriginal(KeyEvent e){
		miMaestroControlador.actualizarTextoOriginal(new StringBuilder(jTAIzq.getText().toString()+e.getKeyChar()));
		jTADer.setText(miMaestroControlador.getTextoNuevo().toString());;
		
	}
}
