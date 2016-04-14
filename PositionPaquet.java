package Descente;

public class PositionPaquet {
	
	static int[][] place = {{1,292,180}, {2,392,180}, {3,492,180}, {4,592,180}, {5,692,180}, {6,280,50},{7,390,31},{8,520,30},{9,400,444},{10,510,443},{11,640,420},{12,55,102},{13,55,182},{14,55,262},{15,55,342},{16,55,422}};
	
	public static int[] bonnePosistion(int x, int y){
		boolean placex;
		boolean placey;
		for (int i = 0; i < place.length; i++){
			
			placex = ((x > place[i][1]) && (x < place[i][1] + 85));
			placey = ((y > place[i][2]) && (y < place[i][2] + 250));
			if (placex && placey){
				int[] retour = {place[i][0] ,place[i][1]+ 10, place[i][2] };
				return retour;
			} 
		}
		return null;
	}
	
	public static void changementPaquet(Carte carte, int arr){
		int paquetDepart = carte.getPaquet();
		int indiceCarte;
		
		indiceCarte = Descente.paquets[paquetDepart].RechercheIndice(carte);
		//On enlève la carte du paquet
		Carte carteTirer = Descente.paquets[paquetDepart].enleverCarte(indiceCarte);
		// on ajoute la carte au paquet
		Descente.paquets[arr].AjouterCarte(carteTirer);
		// On change le paquet dans l'objet carte
		Descente.paquets[arr].getCartesPaquet().get(Descente.paquets[arr].getCartesPaquet().size() -1).setPaquet(arr);
		
		
		
	}
	
	public static void changementPaquetMulti(Carte carte, int arr[]){
		int indice = Descente.paquets[carte.getPaquet()].RechercheIndice(carte);
    	int paquetCourant = carte.getPaquet();
    	int nbCarte = Descente.paquets[paquetCourant].cartesPaquet.size();
		
		for (int i = indice; i < nbCarte ; i++){
			//On enlève la carte du paquet
			Carte carteTirer = Descente.paquets[paquetCourant].enleverCarte(indice);
			// on ajoute la carte au paquet
			Descente.paquets[arr[0]].AjouterCarte(carteTirer);
			// On change le paquet dans l'objet carte
			Descente.paquets[arr[0]].getCartesPaquet().get(Descente.paquets[arr[0]].getCartesPaquet().size() -1).setPaquet(arr[0]);
			
			Descente.paquets[arr[0]].getCartesPaquet().get(Descente.paquets[arr[0]].getCartesPaquet().size() -1).moveTo(arr[1], arr[2]);
		}
		
		//Changement de main
				if (paquetCourant  == 11 && arr[0] == 10){
					Descente.joueurNord = true;
					Descente.joueurSud = false;
					indice = Panneau.carteAffiche.indexOf(Panneau.mainSud);
					Panneau.carteAffiche.remove(indice);
					Panneau.carteAffiche.add(Panneau.mainNord);
				}
				if (paquetCourant == 6 && arr[0] == 7){
					Descente.joueurNord = false;
					Descente.joueurSud = true;
					indice = Panneau.carteAffiche.indexOf(Panneau.mainNord);
					Panneau.carteAffiche.remove(indice);
					Panneau.carteAffiche.add(Panneau.mainSud);
				}
	}
	
	public static void rangementPaquet(){
		int y;
		int val;
		int indiceCarte;
		for (int i = 1; i < 6; i ++) {
			
			if (Descente.paquets[i].cartesPaquet.size() > 0){
				val = 0;
				if (Descente.paquets[i].cartesPaquet.size() > 6){
					y = 10;
				}else{
					y = 18;
				}
				for (Carte carte : Descente.paquets[i].cartesPaquet){
					indiceCarte = Panneau.carteAffiche.indexOf(carte);
					Panneau.carteAffiche.add(Panneau.carteAffiche.remove(indiceCarte));
					carte.setIy(180 + val);
					val = y + val;
				}
			}
   	 	}
	}
	
	public static void jouerCarteNordSud(int paquet){
		int arr;
		int indiceCarte = -1;
		int indiceCarte1 = -1;
		if (paquet == 8){
			arr = 6;
			indiceCarte = Panneau.carteAffiche.indexOf(Panneau.mainNord);
			indiceCarte1 = Panneau.carteAffiche.indexOf(Panneau.carteDosNord);
		}
		else{
			arr = 11;
			indiceCarte = Panneau.carteAffiche.indexOf(Panneau.mainSud);
			indiceCarte1 = Panneau.carteAffiche.indexOf(Panneau.carteDosSud);
			
		}
		//On enlève la carte du paquet
		Carte carteTirer = Descente.paquets[paquet].TirerCarte();
		// on ajoute la carte au paquet
		Descente.paquets[arr].AjouterCarte(carteTirer);
		// On change le paquet dans l'objet carte
		carteTirer.setPaquet(arr);
		carteTirer.moveTo(place[arr-1][1], place[arr-1][2]); //déplacement carte sur tapis
		Panneau.carteAffiche.add(carteTirer);
		Panneau.carteAffiche.add(Panneau.carteAffiche.remove(indiceCarte));//on place la main devant
		
		if (Descente.paquets[paquet].cartesPaquet.size()==0) {
		    Panneau.carteAffiche.remove(indiceCarte1);
		  }
	}
	
	public static  void maindessus(){
		int indiceCarte = -1;
		indiceCarte = Panneau.carteAffiche.indexOf(Panneau.mainSud);
		if (indiceCarte != -1){
			Panneau.carteAffiche.remove(indiceCarte);
			Panneau.carteAffiche.add(Panneau.mainSud);
		}
		indiceCarte = Panneau.carteAffiche.indexOf(Panneau.mainNord);
		if (indiceCarte != -1){
			Panneau.carteAffiche.remove(indiceCarte);
			Panneau.carteAffiche.add(Panneau.mainNord);
		}
	}
	
	public static boolean mouvenentCarteGauche(){
		
		Carte carte;
		Carte carteDessus;
		for (int i = 1; i < 12; i++){
			if (i != 8 && i != 9){
				carte = Descente.paquets[i].VoirCarteFace();
				if (carte != null){
					if (carte.getCouleur() == 5){
						return mouvementRotation(carte, 16);
					}
					else if (carte.getCouleur() == 4){
						carteDessus = Descente.paquets[16].VoirCarteFace();
						if (carteDessus != null){
							if (carte.getValeur() == 1){
								return mouvementRotation(carte, 16);
							}
							else if (carteDessus.monter(carte)){
								return mouvementRotation(carte, 16);
							}
						}
					}
					else if (carte.getCouleur() == 3){
						carteDessus = Descente.paquets[15].VoirCarteFace();
						if (carteDessus != null){
							if (carteDessus.monter(carte)){
								return mouvementRotation(carte, 15);
							}
						}
						else if (carte.getValeur() == 1){
							return mouvementRotation(carte, 15);
						}
					}
					else if (carte.getCouleur() == 2){
						carteDessus = Descente.paquets[14].VoirCarteFace();
						if (carteDessus != null){
							if (carteDessus.monter(carte)){
								return mouvementRotation(carte, 14);
							}
						}
						else if (carte.getValeur() == 1){
							return mouvementRotation(carte, 14);
						}
					}
					else if (carte.getCouleur() == 1){
						carteDessus = Descente.paquets[13].VoirCarteFace();
						if (carteDessus != null){
							if (carteDessus.monter(carte)){
								return mouvementRotation(carte, 13);
							}
						}
						else if (carte.getValeur() == 1){
							return mouvementRotation(carte, 13);
						}
					}
					else if (carte.getCouleur() == 0){
						carteDessus = Descente.paquets[12].VoirCarteFace();
						if (carteDessus != null){
							if (carteDessus.monter(carte)){
								return mouvementRotation(carte, 12);
							}
						}
						else if (carte.getValeur() == 1){
							return mouvementRotation(carte, 12);
						}
					}
				} 
			}
		}
		return false;
	}
	public static boolean mouvementRotation(Carte carte, int numPlace){
		
		Descente.paquets[numPlace].TirerCarteFace();
		int indiceCarte = Panneau.carteAffiche.indexOf(carte);
		Panneau.carteAffiche.add(Panneau.carteAffiche.remove(indiceCarte));
		
		changementPaquet(carte, numPlace);
		carte.setRotation(true);
		new Thread(new Runnable() {
			int i = 0;
			int departX = carte.getIx();
			int departY = carte.getIy();
			int arriveX = place[numPlace-1][1];
			int arriveY = place[numPlace-1][2];
			
			int indiceX = Math.round((departX - arriveX) / 95.0f);
			int indiceY = Math.round((departY - arriveY) / 95.0f);
			
			public void run() {
				Thread currentThread=Thread.currentThread();
				for(;!currentThread.isInterrupted();) {
					carte.setDegreRotation(i);
					carte.moveTo(carte.getIx() - indiceX, carte.getIy() - indiceY);
					Fenetre.pan.repaint();
					i++;
					
					if (i == 91){
						carte.moveTo(place[numPlace-1][1], place[numPlace-1][2]);
						carte.setFixe(true);
						Fenetre.pan.repaint();
						currentThread.interrupt();
					}
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						currentThread.interrupt();
					}
				}
			}
		}).start();
		return true;
	}
	
	public static void remelangerPaquet(int paq){
		int tour = 0;
		int arr;
		if (paq == 10){
			arr = 9;
		}
		else{
			arr = 8;
		}
		Carte carte = Descente.paquets[paq].TirerCarte();
		
		while (carte != null){
			if (tour == 0){
				Descente.paquets[arr].AjouterCarteDessous(carte);
				carte.setPaquet(arr);
				carte.moveTo(place[arr-1][1], place[arr-1][2]);
				
				//suppression carte affichage
				int indice = Panneau.carteAffiche.indexOf(carte);
				Panneau.carteAffiche.remove(indice);
				tour = 1;
			}
			else if (tour == 1){
				Descente.paquets[arr].AjouterCarte(carte);
				carte.setPaquet(arr);
				carte.moveTo(place[arr-1][1], place[arr-1][2]);
				//suppression carte affichage
				int indice = Panneau.carteAffiche.indexOf(carte);
				Panneau.carteAffiche.remove(indice);
				tour = 0;
			}
			carte = Descente.paquets[paq].TirerCarte();
		}
		
	}
	
}
