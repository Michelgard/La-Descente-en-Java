package Descente;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Fenetre  extends JFrame {

	private static final long serialVersionUID = 1L;
	
	ClassLoader cldr = this.getClass().getClassLoader();
	static Panneau pan = new Panneau();

	public Fenetre(){
		setTitle("La Descente");
		setSize (1000,625);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		String path ="images/icone.gif";
		URL imageURL = cldr.getResource(path);
		setIconImage(new ImageIcon(imageURL).getImage());
		
	    setContentPane(pan);
		setVisible(true);
	}
}


