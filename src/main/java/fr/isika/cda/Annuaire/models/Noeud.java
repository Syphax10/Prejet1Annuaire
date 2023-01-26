package fr.isika.cda.Annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Noeud {
	
	// Les attributs
	private int parent;
	private int filsDroit;
	private int filsGauche;
	private int hauteur;
	private int listeChainee;
	private Stagiaire stagiaire;
	private static final int LISTE_VIDE = -1;
	private static final int FILS_NUL = -1;
	public static final int TAILLE_INT_OCTETS = 4;
	
	public static final int TAILLE_NOEUD_OCTETS = Stagiaire.TAILLE_STAGIAIRE_OCTETS + TAILLE_INT_OCTETS*5;
	
	//Constructeur
	public Noeud(Stagiaire stagiaire, int parent, int filsDroit, int filsGauche, int listeChainee, int hauteur) {
		super();
		this.stagiaire = stagiaire;
		this.listeChainee = listeChainee;
		this.parent = parent;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.hauteur = hauteur;
	}
	
	public Noeud(Stagiaire stagiaire, int parent, int hauteur) {
		super();
		this.stagiaire = stagiaire;
		this.listeChainee = LISTE_VIDE;
		this.parent = parent;
		this.filsGauche = FILS_NUL;
		this.filsDroit = FILS_NUL;
		this.hauteur = hauteur;
	}
	
	// Methodes
	@Override
	public String toString() {
		return "Noeud{" + "stagiaire=" + stagiaire + ", listeChainee=" + listeChainee + ", parent=" + parent + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + ", hauteur=" + hauteur + "}";
	}
	
	public void writeNoeudBinaire(RandomAccessFile raf) throws IOException {
		this.stagiaire.writeStagiaireBinaire(raf);
		raf.writeInt(listeChainee);
		raf.write(parent);
		raf.writeInt(filsGauche);
		raf.writeInt(filsDroit);
		raf.writeInt(hauteur);
	}
	
	public void addNoeud(Stagiaire stagiaireToAdd, GestionFichiers rafFichierDom) throws IOException {
		
		RandomAccessFile raf = rafFichierDom.getRaf();
		
		if (this.stagiaire.getNom().compareToIgnoreCase(stagiaireToAdd.getNom()) == 0) {
			//Stagiaire -> arbre binaire
			if (this.stagiaire.compareTo(stagiaireToAdd) == 0) {
				System.out.println("Il existe deja dans la liste");
			} else {
				//Stagiaire -> liste chainee
				Noeud noeudDeLaListeChainee = this;
				// raf dans la liste chainee pour avoir une fin
				while (noeudDeLaListeChainee.listeChainee != LISTE_VIDE) {
					// se mettre sur le stagiaire suivant de la liste
					raf.seek((long) noeudDeLaListeChainee.listeChainee * TAILLE_NOEUD_OCTETS);
					noeudDeLaListeChainee = rafFichierDom.readNoeud();
				}
				
				// Add stagiaire
				ajoutDansFichierBinaire(raf, 5, stagiaireToAdd);
			}
			//Recherche de l'emplacement pour ajouter
		} else if (this.stagiaire.getNom().compareToIgnoreCase(stagiaireToAdd.getNom()) > 0) {
			if (this.filsGauche == FILS_NUL) {
				ajouterDansFichierBinaire(raf, 3, stagiaireToAdd);
			} else {
				raf.seek((long) this.filsGauche * TAILLE_NOEUD_OCTETS);
				Noeud noeudFilsGauche = rafFichierDom.readNoeud();
				noeudFilsGauche.addNoeud(stagiaireToAdd, rafFichierDom);
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
