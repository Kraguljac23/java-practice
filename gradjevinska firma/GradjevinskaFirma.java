package gradjevinskaFirma;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GradjevinskaFirma {

	private StambeniObjekat[] nizObjekata;

	private final String fajl = "nekretnine.txt";

	public void ucitajIzFajla(BufferedReader br) throws FileNotFoundException, IOException, NemaMesta {
		br = new BufferedReader(new FileReader(fajl));

		int brojNekretnina = Integer.parseInt(br.readLine().trim());
		nizObjekata = new StambeniObjekat[brojNekretnina];

		for (int i = 0; i < brojNekretnina; i++) {
			String[] elementiLinije = br.readLine().split(",");
			double cena = Double.parseDouble(elementiLinije[0].trim());
			int brSoba = Integer.parseInt(elementiLinije[1].trim());
			if (elementiLinije.length == 2)
				nizObjekata[i] = new Kuca(cena, brSoba);
			else if (elementiLinije.length == 3)
				nizObjekata[i] = new Stan(cena, brSoba, elementiLinije[2].trim());
			else
				throw new IOException("Greska u " + i + "liniji");
		}
		for (int i = 0; i < brojNekretnina; i++) {
			for (int j = 0; j < nizObjekata[i].getBrojSoba(); j++) {
				int kvadratura = Integer.parseInt(br.readLine());
				nizObjekata[i].dodajSobu(kvadratura, j);
			}
		}
	}
	
	@Override
	public String toString() {
		return "GradjevinskaFirma:" + Arrays.toString(nizObjekata);
	}

	
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			GradjevinskaFirma firma = new GradjevinskaFirma();
			try {
				firma.ucitajIzFajla(br);
				System.out.println(firma.toString());
				
				System.out.println("------");
				
				firma.nizObjekata[0].pregradiSobu(10);
				
				System.out.println("------");
				
				System.out.println(firma.toString());
				
			} catch (IOException | NemaMesta e) {
				System.out.println(e.getMessage());
			}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
}
