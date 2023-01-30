package fr.isika.cda.Annuaire.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire {

	private GestionFichiers rafFichierDom;

	public ArbreBinaire(String nomFichier) throws FileNotFoundException {
		rafFichierDom = new GestionFichiers(nomFichier);
	}

	// Les methodes
	public void addStagiare(Stagiaire stagiaireToAdd) throws IOException {
		if (rafFichierDom.getRaf().length() != 0) {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = rafFichierDom.lectureNoeud();
			firstNoeud.addNoeud(stagiaireToAdd, rafFichierDom);
		} else {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = new Noeud(stagiaireToAdd, -1);
			firstNoeud.writeNoeudBinaire(rafFichierDom.getRaf());
		}
	}

	public void ordreAlphabetique(List<Stagiaire> stagiaireList) throws IOException {
		if (rafFichierDom.getRaf().length() != 0) {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = rafFichierDom.lectureNoeud();
			firstNoeud.ordreAlphabetique(stagiaireList, rafFichierDom);
		}
	}

	// Recherche Stagiaire
	public void searchStagiaire(List<Stagiaire> resultatList, Stagiaire stagiaireSearch) throws IOException {
		if (rafFichierDom.getRaf().length() != 0) {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = rafFichierDom.lectureNoeud();
			firstNoeud.searchStagiere(resultatList, stagiaireSearch, rafFichierDom);
		} else {
			System.out.println("Ce fichier ne contient aucun stagiaire");
		}
	}
	
	  

	// Supprimer stagiaire
	public void deleteStagiaire(Stagiaire stagiaireToDelete) throws IOException {
		if (rafFichierDom.getRaf().length() != 0) {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = rafFichierDom.lectureNoeud();
			firstNoeud.deleteNoeud(stagiaireToDelete, rafFichierDom);
		}
	}

	public void modification(Stagiaire stagiaireToEdit, Stagiaire stagiaireUpdated) throws IOException {

		if (stagiaireToEdit.compareTo(stagiaireUpdated) != 0) {
			if (rafFichierDom.getRaf().length() != 0) {
				addStagiare(stagiaireUpdated);
				deleteStagiaire(stagiaireToEdit);
			}
		}

	}

	public GestionFichiers getRafFichierDom() {
		return rafFichierDom;
	}

	public void setRafFichierDom(GestionFichiers rafFichierDom) {
		this.rafFichierDom = rafFichierDom;
	}
	

}
