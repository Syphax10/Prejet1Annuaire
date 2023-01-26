package fr.isika.cda.Annuaire.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

public class GestionFichiers {

	public static void Lecture() {
		try {
			// objet FR pour lire le fichier (path relatif)
			FileReader fr = new FileReader("src/STAGIAIRES.DON");

			// objet BR pour lire plusieurs caractères à la suite
			BufferedReader br = new BufferedReader(fr);

			String separationStagiaire;
			String nom = "";
			String prenom = "";
			String dept = "";
			String promo = "";
			String Sannee = "";
			int annee = 0;

			while (br.ready()) {
				// stockage du contenu lu dans les 5 variables
				nom = br.readLine();
				prenom = br.readLine();
				dept = br.readLine();
				promo = br.readLine();
				Sannee = br.readLine();
				annee = Integer.valueOf(Sannee);
				separationStagiaire = br.readLine();

				// Attribution de ses var à un objet Stagiaire
				Stagiaire stagiaire = new Stagiaire(nom, prenom, dept, promo, annee);

				// Affichage pour vérif de la méthode
				System.out.println("nom =" + nom);
				System.out.println("prenom =" + prenom);
				System.out.println("dept =" + dept);
				System.out.println("promo =" + promo);
				System.out.println("annee =" + annee);
				System.out.println(stagiaire);
			}

			// Fermeture du flux
			br.close();
			fr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	//Le fihier doit être réécrit à chaque fois qu'on utilise l'arbre binaire
	public void EcritureBinaire() {

		// permet d'écrire les informations contenues dans l'arbre binaire dans un
		// fichier binaire
		// il sera modifié à chaque modification de l'arbre binaire
		// il contient des objets de type Stagiaire

		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile("src/fichiers/stagiaires.bin", "rw");

			// avec ArbreBinaire le nom de la classe de l'arbre binaire et lesStagiaires le
			// nom de l'objet arbre
			for (ArbreBinaire stagiaire : lesStagiaires) {
				// on lui demande d'écrire les attributs de chaque stagiaire
				// écriture du nom (méthode dans classe Stagiaire)
				raf.writeChars(stagiaire.nomLong());
				// idem pour les autres String
				raf.writeChars(stagiaire.prenomLong());
				raf.writeChars(stagiaire.deptLong());
				raf.writeChars(stagiaire.promoLong());
				// écriture de l'année
				raf.writeInt(stagiaire.getAnnee());
			}

			raf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void LectureBinaire(int numStagiaire) {
		// permet d'aller lire et rechercher un stagiaire dans le fichier
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile("src/fichiers/stagiaires.bin", "rw");
			raf.seek((numStagiaire - 1) * Stagiaire.TAILLE_OBJET_OCTET);
			String nomLu = "";
			for (int i = 0; i > Stagiaire.TAILLE_NOMPRENOM_MAX; i++) {
				nomLu += raf.readChar();
			}
			String prenomLu = "";
			for (int i = 0; i > Stagiaire.TAILLE_NOMPRENOM_MAX; i++) {
				prenomLu += raf.readChar();
			}
			String deptLu = "";
			for (int i = 0; i > Stagiaire.TAILLE_DEPT_MAX; i++) {
				deptLu += raf.readChar();
			}
			String promoLue = "";
			for (int i = 0; i > Stagiaire.TAILLE_PROMO_MAX; i++) {
				promoLue += raf.readChar();
			}
			int anneeLue;
			anneeLue = raf.readInt();
			
			
			System.out.println("Le stagiaire numéro " + numStagiaire + " est nom : " + nomLu + ", prénom : " + prenomLu
					+ ", département(ou pays) : " + deptLu + ", promo : " + promoLue + ", année : " + anneeLue
					+ ".");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
