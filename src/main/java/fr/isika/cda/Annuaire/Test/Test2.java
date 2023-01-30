package fr.isika.cda.Annuaire.Test;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Test2 {

	public static void main(String[] args) {
        DataInputStream inputStream = null;

        try {
            inputStream = new DataInputStream(new FileInputStream("src/main/resources/STAGIAIRE.txt"));

            while (true) {
                try {
                    inputStream.readChar();
                } catch (EOFException eofe) {
                    System.out.println("End of file reached");
                    break;
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
	}
}
        
    
	

