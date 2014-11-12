package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import modelo.ListaTexto;

import com.jgoodies.forms.factories.Borders.EmptyBorder;

import controlador.MaestroControlador;

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
	private JEditorPane jEPIzq, jEPDer;
	private JScrollPane jScrollText1, jScrollText2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private MaestroControlador miMaestroControlador;
	private JButton btnAbrir, btnEliminarPalabra, btnReemplazarPalabra, btnCambiarSentido;
	private JRadioButton rdbtnPreposiciones, rdbtnTildadas, rdbtnInverso, rdbtnAlfabeticamente, rdbtnNinguno;
	private ButtonGroup grupoRBotones;
	
	/**
	 * 
	 */
	private JFileChooser fc;
	private int respuesta;
	private File archivo;
	private FileReader fr = null;
    private BufferedReader br = null;
	
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
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth(), (int) d.getHeight()-39);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 100, 658, 568);
        contentPane.add(scrollPane);
        
        //iniciar atributos del frame
        jEPIzq = new JEditorPane();
        jEPIzq.setText("");
        jEPIzq.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		actualizarTextoOriginal(e);        		
        	}
        });
        scrollPane.setViewportView(jEPIzq);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(688, 100, 658, 568);
        contentPane.add(scrollPane_1);
        jEPDer = new JEditorPane();
        jEPDer.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		e.consume();
        	}
        });
        scrollPane_1.setViewportView(jEPDer);
        grupoRBotones = new ButtonGroup();
        btnAbrir = new JButton("Abrir");
        btnAbrir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				abrirArchivo();
				try {
			         archivo = new File (fc.getSelectedFile().toString());
			         fr = new FileReader (archivo);
			         br = new BufferedReader(fr);
			         int i = 0;
			         String linea;
			         
			         while((linea=br.readLine())!=null){
			        	 if(i ==0){
			        		 jEPIzq.setText(linea);
			        		 i++;
			        	 }
			        	 else
			        		 jEPIzq.setText(jEPIzq.getText()+"\n"+linea);
			         }
			      }
			      catch(Exception ex){
			         ex.printStackTrace();
			      }finally{
			         try{                   
			            if( null != fr ){
			            	fr.close();
			            	}                 
			         }catch (Exception e2){
			            e2.printStackTrace();
			         }
			      }
				miMaestroControlador.actualizarTextoOriginal(new StringBuilder(jEPIzq.getText().toString()));
				jEPDer.setText(miMaestroControlador.getTextoNuevo().toString());;
			   }
		});
        	
        btnAbrir.setBounds(10, 31, 89, 23);
        contentPane.add(btnAbrir);
        
        rdbtnPreposiciones = new JRadioButton("Mostrar preposiciones");
        rdbtnPreposiciones.setBounds(120, 31, 165, 23);
        contentPane.add(rdbtnPreposiciones);
        grupoRBotones.add(rdbtnPreposiciones);
        
        rdbtnTildadas = new JRadioButton("Palabras t\u00EDldadas");
        rdbtnTildadas.setBounds(308, 31, 138, 23);
        contentPane.add(rdbtnTildadas);
        grupoRBotones.add(rdbtnTildadas);
        
        rdbtnInverso = new JRadioButton("Orden inverso");
        rdbtnInverso.setBounds(653, 31, 109, 23);
        contentPane.add(rdbtnInverso);
        grupoRBotones.add(rdbtnInverso);
        
        rdbtnAlfabeticamente = new JRadioButton("Ordenar alfabeticamente");
        rdbtnAlfabeticamente.setBounds(993, 31, 183, 23);
        contentPane.add(rdbtnAlfabeticamente);
        grupoRBotones.add(rdbtnAlfabeticamente);
        btnCambiarSentido = new JButton("<=");
        btnCambiarSentido.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		jEPIzq.setText(jEPDer.getText());
        	}
        });
        btnCambiarSentido.setBounds(653, 72, 49, 23);
        contentPane.add(btnCambiarSentido);
        
        btnEliminarPalabra = new JButton("Eliminar palabra");
        btnEliminarPalabra.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String aEliminar = JOptionPane.showInputDialog(null, "�Cu�l eliminar?","Opcion Eliminar",JOptionPane.QUESTION_MESSAGE);
        		if(!aEliminar.toString().equals("")){
        			jEPDer.setText(miMaestroControlador.textoEliminar(aEliminar).toString());
        		}
        	}
        });
        btnEliminarPalabra.setBounds(476, 31, 145, 23);
        contentPane.add(btnEliminarPalabra);
        
        btnReemplazarPalabra = new JButton("Reemplazar palabra");
        btnReemplazarPalabra.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String aReemplazarVieja = JOptionPane.showInputDialog(null, "�Cu�l reemplazar?","Opcion Reemplazar",JOptionPane.QUESTION_MESSAGE);
        		String aReemplazarNueva = JOptionPane.showInputDialog(null, "Reemplazar con","Opcion Reemplazar",JOptionPane.QUESTION_MESSAGE);
        		miMaestroControlador.textoReemplazar(aReemplazarVieja, aReemplazarNueva);
        	}
        });
        btnReemplazarPalabra.setBounds(793, 31, 165, 23);
        contentPane.add(btnReemplazarPalabra);
        
        rdbtnNinguno = new JRadioButton("Ninguno");
        rdbtnNinguno.setBounds(1210, 31, 109, 23);
        rdbtnNinguno.setSelected(true);
        contentPane.add(rdbtnNinguno);
        grupoRBotones.add(rdbtnNinguno);
	}
	
	/**
	 * metodo que actualiza el texto original si se escribe algo en el textArea izquierdo
	 */
	public void actualizarTextoOriginal(KeyEvent e){
		miMaestroControlador.actualizarTextoOriginal(new StringBuilder(jEPIzq.getText().toString()+e.getKeyChar()));
		if(rdbtnNinguno.isSelected()){
			jEPDer.setText(miMaestroControlador.getTextoNuevo().toString());;
		}else if(rdbtnPreposiciones.isSelected()){
			jEPDer.setText(miMaestroControlador.textoPreposiciones().toString());
		}else if(rdbtnTildadas.isSelected()){
			jEPDer.setText(miMaestroControlador.textoTildadas().toString());
		}else if(rdbtnInverso.isSelected()){
			jEPDer.setText(miMaestroControlador.textoInverso().toString());
		}else if(rdbtnAlfabeticamente.isSelected()){
			jEPDer.setText(miMaestroControlador.textoOrdenar().toString());
		}
	}
	
	public void abrirArchivo(){
		fc = new JFileChooser();
		respuesta = fc.showOpenDialog(this);
	}
}
