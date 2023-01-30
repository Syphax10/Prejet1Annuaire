package fr.isika.cda.Annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Stagiaire {

	// Attributs
	private String nom;
	private String prenom;
	private String dept;
	private String promo;
	private int annee;

	// Constante de taille pour le fichier binaire
	public final static int TAILLE_NOMPRENOM_MAX = 16;
	public final static int TAILLE_DEPT_MAX = 5;
	public final static int TAILLE_PROMO_MAX = 10;
	// taille de l'objet stagiaire en octets 4*4 correspond à la taille de
	// l'attribut annee en octet
	public final static int TAILLE_OBJET_OCTET = (2 * TAILLE_NOMPRENOM_MAX + TAILLE_DEPT_MAX + TAILLE_PROMO_MAX) * 2
			+ 4 * 4;

	// Constructeur

	public void writeStagiaireBinaire(RandomAccessFile raf) throws IOException {
		raf.writeChars(nom);
		raf.writeChars(prenom);
		raf.writeChars(dept);
		raf.writeChars(promo);
		raf.writeInt(annee);
	}

	public Stagiaire(String nom, String prenom, String dept, String promo, int annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.dept = dept;
		this.promo = promo;
		this.annee = annee;
	}

	// méthodes pour l'écriture dans le fichier binaire

	public int compareTo(Stagiaire myStagiaire) {
		if (myStagiaire.nom.compareToIgnoreCase(this.nom) == 0) {
			if (myStagiaire.prenom.compareToIgnoreCase(this.prenom) == 0) {
				if (myStagiaire.dept.compareToIgnoreCase(this.dept) == 0) {
					if (myStagiaire.promo.compareToIgnoreCase(this.promo) == 0) {
						return myStagiaire.getAnnee() - this.annee;
					} else {
						return myStagiaire.promo.compareToIgnoreCase(this.promo);
					}
				} else {
					return myStagiaire.dept.compareToIgnoreCase(this.dept);
				}
			} else {
				return myStagiaire.prenom.compareToIgnoreCase(this.prenom);
			}
		} else {
			return myStagiaire.nom.compareToIgnoreCase(this.nom);
		}
	}

	public String nomLong() {
		String nomLong = "";
		if (nom.length() < TAILLE_NOMPRENOM_MAX) {
			nomLong = nom;
			for (int i = nom.length(); i < TAILLE_NOMPRENOM_MAX; i++) {
				nomLong += " ";
			}
		} else {
			nomLong = nom.substring(0, TAILLE_NOMPRENOM_MAX);
		}
		return nomLong;
	}

	public String prenomLong() {
		String prenomLong = "";
		if (prenom.length() < TAILLE_NOMPRENOM_MAX) {
			prenomLong = prenom;
			for (int i = prenom.length(); i < TAILLE_NOMPRENOM_MAX; i++) {
				prenomLong += " ";
			}
		} else {
			prenomLong = prenom.substring(0, TAILLE_NOMPRENOM_MAX);
		}
		return prenomLong;
	}

	public String deptLong() {
		String deptLong = "";
		if (dept.length() < TAILLE_DEPT_MAX) {
			deptLong = dept;
			for (int i = dept.length(); i < TAILLE_DEPT_MAX; i++) {
				deptLong += " ";
			}
		} else {
			deptLong = dept.substring(0, TAILLE_DEPT_MAX);
		}
		return deptLong;
	}

	public String promoLong() {
		String promoLong = "";
		if (promo.length() < TAILLE_PROMO_MAX) {
			promoLong = promo;
			for (int i = promo.length(); i < TAILLE_PROMO_MAX; i++) {
				promoLong += " ";
			}
		} else {
			promoLong = promo.substring(0, TAILLE_PROMO_MAX);
		}
		return promoLong;
	}

	// Getters et Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	// Méthode toString()
	public String toString() {
		return "nom : " + nom + ", prénom : " + prenom + ", département : " + dept + ", promotion : " + promo
				+ ", année : " + annee + "\n";
	}

}
