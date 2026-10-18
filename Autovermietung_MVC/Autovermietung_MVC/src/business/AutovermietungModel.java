package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AutovermietungModel {

	private Auto auto;

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public void leseAutoAusCsvDatei() throws IOException {
		try (BufferedReader ein = new BufferedReader(new FileReader("Auto.csv"))) {
			String[] zeile = ein.readLine().split(";");
			this.auto = new Auto(zeile[0],
					zeile[1],
					zeile[2],
					Float.parseFloat(zeile[3]),
					zeile[4].split("_"));
		}
	}

	public void schreibeAutoInCsvDatei() throws IOException {
		try (BufferedWriter aus = new BufferedWriter(
				new FileWriter("AutosAusgabe.csv", true))) {
			aus.write(this.auto.gibAutoZurueck(';'));
		}
	}
}
