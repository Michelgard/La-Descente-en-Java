package Descente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Descente {
	
	static Paquet[] paquets = new Paquet[17];
	
	public static boolean joueurNord = false;
	public static boolean joueurSud = true;
	static Fenetre fenetrePrincipale;
	static JeuDeCarte unJeu;
	
	public static void main(String[] args) throws IOException {
		
		unJeu = new JeuDeCarte();
		unJeu.melanger();
		
		//création des paquets
		for (int i = 1; i < 17 ;i++){
			paquets[i] = new Paquet();
		}
		
		distribuer(unJeu);
		creationFenetre();
		
	}
	
	public static void creationFenetre(){
		fenetrePrincipale = new Fenetre();
		fenetrePrincipale.setVisible(true);
		
	}
	
	public static void demmaragePartie(){
		System.out.println("coucou");
		unJeu = null;
		JeuDeCarte unJeu = new JeuDeCarte();
		unJeu.melanger();
		
		//création des paquets
		for (int i = 1; i < 17 ;i++){
			paquets[i] = new Paquet();
		}
				
		distribuer(unJeu);
		Fenetre.pan = null;
		fenetrePrincipale = null;
		
		fenetrePrincipale = new Fenetre();
		fenetrePrincipale.setVisible(true);
	}
	
	//5 cartes pour paquet du centre
	public static ArrayList<Integer> integerList1(int _size) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int i = 2; i <= _size -1; i++) {
            intList.add(i);
        }
 
        ArrayList<Integer> randomValues = new ArrayList<Integer>();
        Random random = new Random();
        int pos = 0;
 
        while (randomValues.size() < 5) {
            pos = random.nextInt(intList.size());
            randomValues.add(intList.get(pos));
            intList.remove(pos);
        }
        return randomValues;
    }
	
	public static void distribuer(JeuDeCarte unJeu){
		ArrayList<Integer> numList = integerList1(28);
		int numPaquet = 1;
		int tour = 0;
		int debut = 302;

		Collections.sort(numList, Collections.reverseOrder());
		
		for (int j = 0; j < numList.size(); j++){
			paquets[numPaquet].AjouterCarte(unJeu.jeuDeCarte.remove((int)numList.get(j)));
			paquets[numPaquet].cartesPaquet.get(0).PlacerCarte(debut, 180, numPaquet);
	    	debut = debut + 100;
			numPaquet++;
		}
		
		int nbCarte = unJeu.jeuDeCarte.size();
		for (int i= 0; i < nbCarte; i++){
			if (tour == 1){
					paquets[8].AjouterCarte(unJeu.jeuDeCarte.get(i));
					tour = 0;
			}
			else {
					paquets[9].AjouterCarte(unJeu.jeuDeCarte.get(i));
					tour = 1;
			}
		}
	}
}
