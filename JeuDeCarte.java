package Descente;

import java.util.ArrayList;
import java.util.Collections;
import java.net.URL;


import javax.swing.ImageIcon;

public class JeuDeCarte {
	
	ClassLoader cldr = this.getClass().getClassLoader();
	ArrayList<Carte> jeuDeCarte = new ArrayList<Carte>();
	
	public JeuDeCarte(){
		for (int val = 1; val < 5; val ++){
			for (int coul = 0; coul < 4; coul ++){
				String path ="images/Carte-" +  Integer.toString(val) + "-" +   Integer.toString(coul) +".gif";
				URL imageURL = cldr.getResource(path);
				ImageIcon img = new ImageIcon(imageURL);
				jeuDeCarte.add(new Carte(img, val, coul));
			}
		}
		
		for (int val = 1; val < 12; val++){
			String path ="images/Carte-" +  Integer.toString(val) + "-" +   Integer.toString(4) +".gif";
			URL imageURL = cldr.getResource(path);
			ImageIcon img = new ImageIcon(imageURL);
			jeuDeCarte.add(new Carte(img, val, 4));
		}
		
		String path ="images/Carte-" +  Integer.toString(0) + "-" +   Integer.toString(5) +".gif";
		URL imageURL = cldr.getResource(path);
		ImageIcon img = new ImageIcon(imageURL);

		jeuDeCarte.add(new Carte(img, 1, 5));
	}

	public void melanger(){
		Collections.shuffle(jeuDeCarte);
	}
}
