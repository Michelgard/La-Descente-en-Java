package Descente;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Carte {
	
	private ImageIcon image;
	private int valeur;
	private int couleur;
	private int ix;
	private int iy;
	private boolean fixe;
	private int paquet;
	private boolean rotation = false;
	private int degreRotation = 0;
	

	public Carte(ImageIcon img,  int val, int coul) {
	        setImage(img);
	        valeur = val;
	        couleur = coul;
	        setFixe(false);
	 }
	
	public Carte(ImageIcon img, boolean fixe) {
        setImage(img);
        valeur = -1;
        couleur = -1;
        this.setFixe(fixe);
	}
	
	public int getCouleur(){
		return couleur;
	}
	
	public int getValeur(){
		return valeur;
	}
	public boolean getRotation(){
		return rotation;
	}
	
	public void setRotation(boolean rot){
		this.rotation = rot;
	}
	
	
	public void PlacerCarte(int x, int y, int paq){
		  setIx(x);
	      setIy(y);
	      setPaquet(paq);
	      
	}
	
	public void setPaquet(int paquet) {
		this.paquet = paquet;
		
	}
	public int getPaquet(){
		return this.paquet;	
	}

	public boolean contient(int x, int y) {
		return (x > getIx() && x < (getIx() + getImage().getIconWidth()) && 
	                y > getIy() && y < (getIy() + getImage().getIconHeight()));
	    }
	
	public void AffichageCarte(){	
		System.out.println(valeur + " de " + couleur);
	}

	public void draw(Graphics g, Component c) {
		
		if (getRotation()){
			int cWidth = image.getIconWidth() / 2;
			int cHeight = image.getIconHeight() / 2;
			int yAdjustment = (image.getIconHeight() % 2) == 0 ? 0 : -1;
			
			Graphics2D g2 = (Graphics2D)g.create();
			g2.translate(getIx() + cHeight, getIy() + cWidth);
			g2.rotate(Math.toRadians(-degreRotation));
			
			image.paintIcon(c, g2, -cWidth, yAdjustment - cHeight);
		}
		else{
			 image.paintIcon(c, g, getIx(), getIy());
		}
    }
	
	 public void moveTo(int x, int y) {
		 if (fixe == false){   
			 setIx(x);
			 setIy(y);
		 }
	 }

	public ImageIcon getImage() {
		return image;
	}


	public void setImage(ImageIcon img) {
		this.image = img;
	}
	

	public int getIx() {
		return ix;
	}
	public int getIy() {
		return iy;
	}

	public void setIx(int x) {
		this.ix = x;
	}
	public void setIy(int y) {
		this.iy = y;
	}

	public boolean isFixe() {
		return fixe;
	}

	public void setFixe(boolean fixe) {
		this.fixe = fixe;
	}
	
	public boolean monter(Carte carteDepart){
		if (carteDepart.getCouleur() != getCouleur()){
			return false;
		}
		else if (getValeur() == carteDepart.getValeur() - 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean descente(Carte carteDepart){
		int couleurDepart = carteDepart.getCouleur();
		int valeurDepart = carteDepart.getValeur();
		
		if (couleur == 4 && couleurDepart == 4){
			if (valeur == (valeurDepart + 1)){
				return true;
			}
		}
		if (couleur == 0 && ((couleurDepart - 1) == 0 || (couleurDepart - 3) == 0)){
			if (valeur == (valeurDepart + 1)){
				return true;
			}
		}
		if (couleur == 1 && ((couleurDepart - 1) == 1 || (couleurDepart + 1) == 1)){
			if (valeur == (valeurDepart + 1)){
				return true;
			}
		}
		if (couleur == 2 && ((couleurDepart - 1) == 2 || (couleurDepart + 1) == 2)){
			if (valeur == (valeurDepart + 1)){
				return true;
			}
		}
		if (couleur == 3 && ((couleurDepart + 3) == 3 || (couleurDepart + 1) == 3)){
			if (valeur == (valeurDepart + 1)){
				return true;
			}
		}
		return false;
	}

	public int getDegreRotation() {
		return degreRotation;
	}

	public void setDegreRotation(int degreRotation) {
		this.degreRotation = degreRotation;
	}
}
