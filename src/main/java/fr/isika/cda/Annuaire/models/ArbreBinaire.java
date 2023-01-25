package fr.isika.cda.Annuaire.models;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class ArbreBinaire {
	
	private GestionFichiers rafFichierDom ;
	
	public ArbreBinaire(String nomFichier) throws FileNotFoundException {
		rafFichierDom = new GestionFichiers(nomFichier);
	}
	
	// Les methodes
	public void addStagiare(Stagiaire stagiaireToAdd) throws IOException {
		if (rafFichierDom.getRaf().length() != 0) {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = rafFichierDom.readNoeud();
			firstNoeud.addNoeud(stagiaireToAdd, rafFichierDom);
		} else {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = new Noeud(stagiaireToAdd, -1, 0);
			firstNoeud.writeNoeudBinaire(rafFichierDom.getRaf());
		}
	}
	
	public void odreAlphabetique(List<Stagiaire> stagiaireList) throws IOException {
		if (rafFichierDom.getRaf().length() != 0) {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = rafFichierDom.readNoeud();
			firstNoeud.ordreAlphabetique(stagiaireList, rafFichierDom);
		}
	}
	
	//Recherche Stagiaire 
	public void searchStagiaire(List<Stagiaire>resultatList,Stagiaire stagiaireSearch) throws IOException {
		if (rafFichierDom.getRaf().length() != 0) {
			rafFichierDom.getRaf().seek(0);
			Noeud firstNoeud = rafFichierDom.readNoeud();
			firstNoeud.searchStagiaire(resultatList,stagiaireSearch, rafFichierDom);
		} else {
			System.out.println("Ce fichier ne contient aucun stagiaire");
		}
	}
	
	 public void dbtRechAv(List<Stagiaire> searchAvancee ,Stagiaire stagiaireToFind) throws IOException {
	        List<Stagiaire> ordreAlph = new ArrayList<>();

	        if (rafFichierDom.getRaf().length() != 0) {
	            rafFichierDom.getRaf().seek(0);
	            Noeud firstNoeud = rafFichierDom.readNoeud();
	            firstNoeud.ordreAlphabetique(ordreAlph, rafFichierDom);
	            for(Stagiaire stagiaire : ordreAlph) {
	                stagiaire.searchAvancee(searchAvancee, stagiaireToFind);
	            }
	        } else {
	            //retour visuel, fichier vide
	            System.out.println("Aucun stagiaire ne correspond aux infomation fourni.");
	        }
	    }
	 //Supprimer stagiaire
	    public void supprimerUnStagiaire(Stagiaire stagiaireToDelete) throws IOException {
	        if (rafFichierDom.getRaf().length() != 0) {
	            rafFichierDom.getRaf().seek(0);
	            Noeud firstNoeud = rafFichierDom.readNoeud();
	            firstNoeud.deleteNoeud(stagiaireToDelete, rafFichierDom);
	        }
	    }

	    public  void modification(Stagiaire stagiaireToEdit, Stagiaire stagiaireUpdated)
	            throws IOException {

	        if (stagiaireToEdit.compareTo(stagiaireUpdated) != 0) {
	            if (rafFichierDom.getRaf().length() != 0) {
	                addStagiaire(stagiaireUpdated);
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



