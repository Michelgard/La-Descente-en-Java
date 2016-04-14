package Descente;

import java.util.ArrayList;

public class Paquet  {
	
	ArrayList<Carte> cartesPaquet = new ArrayList<Carte>();

	public Paquet()  {
	}
	
	public void AjouterCarte(Carte carte){
		cartesPaquet.add(carte);
	}
	
	public void AjouterCarteDessous(Carte carte){
		cartesPaquet.add(0, carte);
	}
	
	public ArrayList<Carte> getCartesPaquet(){
		return cartesPaquet;
		
	}
	
	public Carte TirerCarte(){
		if (cartesPaquet.size() != 0){
			return cartesPaquet.remove(0);
		}
		else {
			return null;
		}
	}
	public Carte TirerCarteFace(){
			if (cartesPaquet.size() != 0){
				return cartesPaquet.remove(cartesPaquet.size() -1);
			}
			else {
				return null;
			}
	}
	
	public Carte VoirCarte(){
		if (cartesPaquet.size() != 0){
			return cartesPaquet.get(0);
		}
		else {
			return null;
		}
	}
	
	public Carte VoirCarteFace(){
		if (cartesPaquet.size() != 0){
			return cartesPaquet.get(cartesPaquet.size() -1);
		}
		else {
			return null;
		}
	}
	
	public int RechercheIndice(Carte carte){
		return cartesPaquet.indexOf(carte);
	}
	
	public Carte enleverCarte(int indice){
		return cartesPaquet.remove(indice);
	}
}
