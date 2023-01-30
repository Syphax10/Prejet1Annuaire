package fr.isika.cda.Annuaire.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import fr.isika.cda.Annuaire.models.ArbreBinaire;
import fr.isika.cda.Annuaire.models.GestionFichiers;
import fr.isika.cda.Annuaire.models.Noeud;
import fr.isika.cda.Annuaire.models.Stagiaire;

public class Test {

	public static void main(String[] args) {
	
		try 
	        {
			GestionFichiers rafFichierDom = new GestionFichiers("src/main/resources/STAGIAIRE.txt");
            ArbreBinaire arbre = new ArbreBinaire("src/main/resources/STAGIAIRE.txt");

            arbre.addStagiare(new Stagiaire("Hurlin", "Nathalie", "05", "CDA 21", 2022));
            arbre.addStagiare(new Stagiaire("Lacroix", "Charles", "23", "AL10", 2018));
            arbre.addStagiare(new Stagiaire("Boucher", "Caroline", "19", "BOBI 4", 2016));
            arbre.addStagiare(new Stagiaire("Bernaudon", "Jean-Marc", "76", "ARD 4", 2015));
            arbre.addStagiare(new Stagiaire("Pasquali", "Séverine", "56", "GHI 12", 2017));
            arbre.addStagiare(new Stagiaire("Lacroix", "Antoine", "23", "BOBI 14", 2010));
            arbre.addStagiare(new Stagiaire("Crocq", "Camille", "01", "BOBI 12", 2017));
            arbre.addStagiare(new Stagiaire("Rulof", "Mélanie", "13", "ARD 12", 2017));
            arbre.addStagiare(new Stagiaire("Lalonde", "Tiphaine", "75", "CDA 21", 2017));
            arbre.addStagiare(new Stagiaire("Vallin", "Jerome", "67", "GHI 12", 2017));
            arbre.addStagiare(new Stagiaire("Lacroix", "Edgard", "18", "ARD 8", 2013));
            arbre.addStagiare(new Stagiaire("Rouget", "Dara", "46", "BOBI 3", 2008));
            arbre.addStagiare(new Stagiaire("Touch", "Antoine", "09", "AI 56", 2010));
            arbre.addStagiare(new Stagiaire("Ragout", "Emma", "41", "CDA 3", 2008));

            rafFichierDom.getRaf().seek(0);
            for (int i = 0; i < (rafFichierDom.getRaf().length() / Noeud.TAILLE_NOEUD_OCTETS); i++) {
                System.out.println(rafFichierDom.lectureNoeud());
            }


            rafFichierDom.fermetureAccessFile();
            
	        }catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	}
}


        
	        

	


