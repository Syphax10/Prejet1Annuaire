package fr.isika.cda.Annuaire.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

public class GestionFichiers {

	private RandomAccessFile raf;

	public GestionFichiers(String cheminFichier) throws FileNotFoundException {
		raf = new RandomAccessFile(cheminFichier, "rw");
	}

	// Méthodes Spécifiques
	public void fermetureAccessFile() throws IOException {
		raf.close();
	}

	public void verificationImportFichierDon(ArbreBinaire arbre) throws IOException {
		if (raf.length() == 0) {
			ecritureAPartirDuFichierDom(arbre);
		}
	}

	public void ecritureAPartirDuFichierDom(ArbreBinaire arbre) throws IOException {

		FileReader reader = new FileReader("src/main/resources/STAGIAIRES.DON");
		BufferedReader br = new BufferedReader(reader);

		String nom = br.readLine().trim();
		String prenom = br.readLine().trim();
		String departement = br.readLine().trim();
		String promo = br.readLine().trim();
		int anneeDeFormation = Integer.parseInt(br.readLine().trim());
		br.readLine();

		new Noeud(new Stagiaire(nom, prenom, departement, promo, anneeDeFormation), -1).writeNoeudBinaire(raf);

		while (reader.ready()) {
			nom = br.readLine().trim();
			prenom = br.readLine().trim();
			departement = br.readLine().trim();
			promo = br.readLine().trim();
			anneeDeFormation = Integer.parseInt(br.readLine().trim());
			br.readLine();

			if (!nom.equals("")) {
				arbre.addStagiare(new Stagiaire(nom, prenom, departement, promo, anneeDeFormation));
			}

		}

		br.close();
		reader.close();
	}

	public Noeud lectureNoeud() throws IOException {
		String nom = lectureAttributStringStagiaire();
		String prenom = lectureAttributStringStagiaire();
		String departement = lectureAttributStringStagiaire();
		String promo = lectureAttributStringStagiaire();
		int anneeDeFormation = raf.readInt();

		int listeChainee = raf.readInt();
		int parent = raf.readInt();
		int filsGauche = raf.readInt();
		int filsDroit = raf.readInt();
		int hauteur = raf.readInt();

		Stagiaire stagiaireLu = new Stagiaire(nom, prenom, departement, promo, anneeDeFormation);
		return new Noeud(stagiaireLu, listeChainee, parent, filsGauche, filsDroit, hauteur);
	}
	
	public static void EcritureBinaire(Noeud noeud, int index) {

		// permet d'écrire les informations contenues dans l'arbre binaire dans un
		// fichier binaire
		// il sera modifié à chaque modification de l'arbre binaire
		// il contient des objets de type Stagiaire

		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile("src/main/resources/STAGIAIRE.txt", "rw");
			// écriture du nom (méthode dans classe Stagiaire)
			raf.seek(index);
			raf.writeChars(noeud.stagiaire.nomLong());// 42 octets
			// idem pour les autres String
			raf.writeChars(noeud.stagiaire.prenomLong()); // 40 octets
			raf.writeChars(noeud.stagiaire.deptLong()); // 6 octets
			raf.writeChars(noeud.stagiaire.promoLong()); // 22 octets
			// écriture de l'année et des index des fils gauche fils droit
			raf.writeInt(noeud.stagiaire.getAnnee()); // 4 octets
			raf.writeInt(noeud.filsGauche); // 4 octets
			raf.writeInt(noeud.filsDroit); // 4 octets
			// soit une taille totale de 122 octets pour chaque noeud

			raf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String lectureAttributStringStagiaire() throws IOException {
		String attribut = "";
		for (int i = 0; i < Stagiaire.TAILLE_OBJET_OCTET; i++) {
			attribut += raf.readChar();
		}
		return attribut;
	}

	// getters && setters
	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	public static void main(String[] args) {
		System.out.println(args);
	}
}
