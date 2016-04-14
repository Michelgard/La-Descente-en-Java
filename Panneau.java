package Descente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panneau extends JPanel  implements MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	
	private Carte   currentCard = null; 
	static ArrayList<Carte> carteAffiche = new ArrayList<Carte>(); 
	ClassLoader cldr = this.getClass().getClassLoader();
	
	private int dragFromX = 0;  
	private int dragFromY = 0;
	
	private int posCarteX = 0;  
	private int posCarteY = 0;
	
	static Carte mainNord;
	static Carte mainSud;
	
	static Carte carteDosSud;
	static Carte carteDosNord;
	
	static ImageIcon imgTapis;
	static ImageIcon imgCarteBlanche;
	
	static ArrayList<ImageIcon> imgListeImageVierges = new ArrayList<ImageIcon>();
	 
	Panneau(){ 
		addMouseListener(this);
		addMouseMotionListener(this);
		
		for (int i = 1; i < 6; i++){
	        carteAffiche.add(Descente.paquets[i].cartesPaquet.get(0));
	    }
		String path ="images/tapis.jpg";
	    URL imageURL = cldr.getResource(path);
	    imgTapis = new ImageIcon(imageURL);
	     
	    path ="images/carte-blanche.gif";
	  	imageURL = cldr.getResource(path);
	  	imgCarteBlanche = new ImageIcon(imageURL);
	  	
	  	String listeImagesVierges[] = {"images/coeur-vierge.gif", "images/trefle-vierge.gif", "images/carreau-vierge.gif", "images/pique-vierge.gif", "images/atout-vierge.gif" };
	  	for (int i = 0; i < 5; i++){
		  	path = listeImagesVierges[i];
	  		imageURL = cldr.getResource(path);
	  		imgListeImageVierges.add(new ImageIcon(imageURL));
	  	}
  		
		 path ="images/dos-carte.jpg";
		 imageURL = cldr.getResource(path);
		 ImageIcon img = new ImageIcon(imageURL);
		 carteDosNord = new Carte(img, true);
		 carteDosSud = new Carte(img, true);
		 
		 carteDosNord.PlacerCarte(520, 30, 8);
		 carteDosSud.PlacerCarte(400, 444, 9);
		 
		 carteAffiche.add(carteDosNord);
		 carteAffiche.add(carteDosSud);
		 
		 path ="images/main-nord.gif";
		 imageURL = cldr.getResource(path);
		 img = new ImageIcon(imageURL);
		 mainNord = new Carte(img, true);
		 
		 path ="images/main-sud.gif";
		 imageURL = cldr.getResource(path);
		 img = new ImageIcon(imageURL);
		 mainSud = new Carte(img, true);
		 
		 mainSud.PlacerCarte(670, 522, 0);
		 mainNord.PlacerCarte(230, 0, 0);
		 
		 carteAffiche.add(mainSud);
	 }
	
	public void paintComponent(Graphics g){
		
		int width = getWidth();
        int height = getHeight();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        
        imgTapis.paintIcon(this, g, 50, 25);
      
        int debut = 302;
        for (int i = 0; i < 5; i++){
        
        	imgCarteBlanche.paintIcon(this, g, debut, 180);
	  		debut = debut + 100;
        }
      
        debut = 102;
        
        for (int i = 0; i < 5; i++){
        	imgListeImageVierges.get(i).paintIcon(this, g, 55, debut);
      		debut = debut + 80;
        }
	   
        for (Carte c : carteAffiche) {
            c.draw(g, this);
        }
        
        while (PositionPaquet.mouvenentCarteGauche()){
			this.repaint();
		}
	 }
	
	public void replacerImage(){
		
		if (Descente.paquets[6].cartesPaquet.size() == 0 && Descente.paquets[7].cartesPaquet.size() == 0 && Descente.paquets[8].cartesPaquet.size() == 0){
			JOptionPane.showMessageDialog(null, "Le joueur Nord gagne !!!", "Un Gagnant !", JOptionPane.INFORMATION_MESSAGE);
			Descente.demmaragePartie();
			replacerImage();
		}
		if (Descente.paquets[9].cartesPaquet.size() == 0 && Descente.paquets[10].cartesPaquet.size() == 0 && Descente.paquets[11].cartesPaquet.size() == 0){
			JOptionPane.showMessageDialog(null, "Le joueur Sud gagne !!!", "Un Gagnant !", JOptionPane.INFORMATION_MESSAGE);
			Descente.demmaragePartie();
			replacerImage();
		}
		
		while (PositionPaquet.mouvenentCarteGauche()){
			this.repaint();
		}
		
		this.repaint(); 
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		 int x = e.getX();  
	     int y = e.getY();   
	     
	     currentCard = null; 
	     for (int crd=carteAffiche.size()-1; crd>=0; crd--) {
	    	 Carte testCarte = carteAffiche.get(crd);
	    	 
	    	 if (testCarte.contient(x, y) && !testCarte.isFixe()) {
	    		 
	    		 posCarteX = testCarte.getIx();
	    		 posCarteY = testCarte.getIy();
	    		 
	    		 dragFromX = x - testCarte.getIx(); 
	             dragFromY = y - testCarte.getIy();
	             currentCard = testCarte; 
	             
	             if (Descente.paquets[currentCard.getPaquet()].cartesPaquet.size() > 1  && currentCard.getPaquet() < 6){
	             	int indice = Descente.paquets[currentCard.getPaquet()].RechercheIndice(currentCard);
	             	if (indice < Descente.paquets[currentCard.getPaquet()].cartesPaquet.size()){
	            		for (int i = indice ; i < Descente.paquets[currentCard.getPaquet()].cartesPaquet.size() ; i++){
	            			indice = carteAffiche.indexOf(Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i));
	            			carteAffiche.add(Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i));
	       	             	carteAffiche.remove(indice);
	            		}
	             	}
	             }
	             else{
	              carteAffiche.add(testCarte);
	             carteAffiche.remove(crd);
	             }
	             
	             break; 
	    	 }
	     }
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

		 if (currentCard != null  && !currentCard.isFixe()) {  
			
			int decalage = 15;
			
	        int newX = e.getX() - dragFromX;
	        int newY = e.getY() - dragFromY;
	            
            newX = Math.max(newX, 0);
            newX = Math.min(newX, getWidth() - currentCard.getImage().getIconWidth());
            
            newY = Math.max(newY, 0);
            newY = Math.min(newY, getHeight() - currentCard.getImage().getIconHeight());
            
            currentCard.moveTo(newX, newY);
            
            if (Descente.paquets[currentCard.getPaquet()].cartesPaquet.size() > 0  && currentCard.getPaquet() < 6){
            	int indice = Descente.paquets[currentCard.getPaquet()].RechercheIndice(currentCard);
            	if (indice + 1 < Descente.paquets[currentCard.getPaquet()].cartesPaquet.size()){
            		for (int i = indice + 1; i < Descente.paquets[currentCard.getPaquet()].cartesPaquet.size(); i++){
            			newX = Math.max(newX, 0);
                        newX = Math.min(newX, getWidth() - Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i).getImage().getIconWidth());
                        
                        newY = Math.max(newY, 0);
                        newY = Math.min(newY, getHeight() - Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i).getImage().getIconHeight());
                        
                        Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i).moveTo(newX, newY  + decalage);
                        decalage = decalage + 15;
                        
            		}	
            	}
            }
            
            
            this.repaint();  
	       }	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if (currentCard != null){
			//currentCard.moveTo(posCarteX, posCarteY);
			
			  if (Descente.paquets[currentCard.getPaquet()].cartesPaquet.size() > 0   && currentCard.getPaquet() < 6){
				  int indice = Descente.paquets[currentCard.getPaquet()].RechercheIndice(currentCard);
	            	
	            		for (int i = indice; i < Descente.paquets[currentCard.getPaquet()].cartesPaquet.size(); i++){
	                        Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i).moveTo(posCarteX, posCarteY);
	            		}	
	            	
	            }
			
			PositionPaquet.rangementPaquet();
			PositionPaquet.maindessus();
			this.repaint(); 
			currentCard = null;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		 int x = e.getX();  
	     int y = e.getY();
	     int[] place = null;
	     Carte carteDessous = null;
	     boolean vide = true;
	     
	     place = PositionPaquet.bonnePosistion(x, y);
	     if (place != null){
	    	 carteDessous = Descente.paquets[place[0]].VoirCarteFace();
	    	 if (carteDessous == null && place[0] == 7 && Descente.joueurSud){ // pas de carte sur paquet advense vide
		    	 vide = false;
		     }
		     if (carteDessous == null && place[0] == 10 && Descente.joueurNord){ // pas de carte sur paquet advense vide
		    	 vide = false;
		     }
		     if (carteDessous != null){ // regle pour l'excuse
			     if (carteDessous.getCouleur() == 4 && place[0] == 7 && Descente.joueurSud && Descente.paquets[16].cartesPaquet.size() == 0){
			    	 vide = false;
			     }
			     if (carteDessous.getCouleur() == 4 && place[0] == 10 && Descente.joueurNord && Descente.paquets[16].cartesPaquet.size() == 0){
			    	 vide = false;
			     }
		     }
	     }
	     else if (currentCard != null){
	    	 int indice = Descente.paquets[currentCard.getPaquet()].RechercheIndice(currentCard);
	         for (int i = indice; i < Descente.paquets[currentCard.getPaquet()].cartesPaquet.size(); i++){
	           	Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i).moveTo(posCarteX, posCarteY);
	         }	
	    	 PositionPaquet.rangementPaquet();
	    	 PositionPaquet.maindessus();
	     }
	     if (currentCard != null  && !currentCard.isFixe() && place != null) {
		     if(place[0] != 10 && place[0] != 7 && place[0] > 5){
		    	 int indice = Descente.paquets[currentCard.getPaquet()].RechercheIndice(currentCard);
		         for (int i = indice; i < Descente.paquets[currentCard.getPaquet()].cartesPaquet.size(); i++){
		           	Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i).moveTo(posCarteX, posCarteY);
		         }	
		    	 PositionPaquet.rangementPaquet();
		    	 PositionPaquet.maindessus();
		     }
		     else if ((carteDessous == null|| carteDessous.descente(currentCard) || place[0] == 10) && Descente.joueurSud && vide) {
		         PositionPaquet.changementPaquetMulti(currentCard, place);
		    	 PositionPaquet.rangementPaquet();
		    }
		     else if ((carteDessous == null || carteDessous.descente(currentCard) || place[0] == 7) && Descente.joueurNord && vide) {
		    	 PositionPaquet.changementPaquetMulti(currentCard, place);
		    	 PositionPaquet.rangementPaquet();
		    }
		else{
			int indice = Descente.paquets[currentCard.getPaquet()].RechercheIndice(currentCard);
	        for (int i = indice; i < Descente.paquets[currentCard.getPaquet()].cartesPaquet.size(); i++){
	           	Descente.paquets[currentCard.getPaquet()].cartesPaquet.get(i).moveTo(posCarteX, posCarteY);
	         }	
		    	 PositionPaquet.rangementPaquet();
		    	 PositionPaquet.maindessus();
	    	}
	     }
		    
			 
	    /*for (int i = 1; i < 6; i ++) {
		    for (Carte c : Descente.paquets[i].getCartesPaquet()) {
		    	c.AffichageCarte();
		    }
		    System.out.println("---");
	    }
	    System.out.println("FIN");*/
	      System.out.println("cartes NORD : ");
	    System.out.println("Le 6 : "+ Descente.paquets[6].cartesPaquet.size());
	    System.out.println("Le 7 : "+ Descente.paquets[7].cartesPaquet.size());
	    System.out.println("Le 8 : "+ Descente.paquets[8].cartesPaquet.size());
	    System.out.println("---");
	    System.out.println("cartes SUD : ");
	    System.out.println("Le 9 : "+ Descente.paquets[9].cartesPaquet.size());
	    if (Descente.paquets[9].cartesPaquet.size() !=0){
	    	Descente.paquets[9].cartesPaquet.get(0).AffichageCarte();
	    }
	    System.out.println("Le 10 : "+ Descente.paquets[10].cartesPaquet.size());
	    System.out.println("Le 11 : "+ Descente.paquets[11].cartesPaquet.size());
	    if (Descente.paquets[11].cartesPaquet.size() !=0){
	    	Descente.paquets[11].cartesPaquet.get(0).AffichageCarte();
	    }
	    System.out.println("---");
	    
	    replacerImage(); 
	   
	     
		currentCard = null;
	}   
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		int x = e.getX();  
	    int y = e.getY();
	    int[] place = PositionPaquet.bonnePosistion(x, y);
	    
	    if (place != null && e.getButton() == 1){
		    if (Descente.joueurSud && place[0] == 9 && Descente.paquets[9].cartesPaquet.size()!=0 && Descente.paquets[11].cartesPaquet.size() == 0 ){
		    	PositionPaquet.jouerCarteNordSud(9);
		    	replacerImage();
		    }
		    if (Descente.joueurNord && place[0] == 8 && Descente.paquets[8].cartesPaquet.size()!=0 && Descente.paquets[6].cartesPaquet.size() == 0 ){
		    	PositionPaquet.jouerCarteNordSud(8);
		    	replacerImage(); 
		    }   
	    }
	    
	   if (place != null && e.getButton() == 3){
		   if (Descente.joueurSud && place[0] == 10 && Descente.paquets[9].cartesPaquet.size() == 0 && Descente.paquets[11].cartesPaquet.size() == 0 ){
			  PositionPaquet.remelangerPaquet(10);
			  Panneau.carteDosSud.PlacerCarte(400, 444, 9);
			  Panneau.carteAffiche.add(Panneau.carteDosSud);
		   }
		   if (Descente.joueurNord && place[0] == 7 && Descente.paquets[8].cartesPaquet.size() == 0 && Descente.paquets[6].cartesPaquet.size() == 0 ){
			   PositionPaquet.remelangerPaquet(7);
			   Panneau.carteDosNord.PlacerCarte(520, 30, 8); 
			   Panneau.carteAffiche.add(Panneau.carteDosNord);
		   }
	   }
	   
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
