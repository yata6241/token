package gui;

import java.io.IOException;

import business.Auto;
import business.AutovermietungModel;
import javafx.stage.Stage;

public class AutovermietungControl {

	private AutovermietungModel model;
	private AutovermietungView view;

	public AutovermietungControl(Stage primaryStage) {
		this.model = new AutovermietungModel();
		this.view = new AutovermietungView(this, primaryStage);
	}

	void nehmeAutoAuf(String kennzeichen, String typ, String modell,
			String tagespreis, String vermietetVonBis) {
		try {
			this.model.setAuto(new Auto(kennzeichen, typ, modell,
					Float.parseFloat(tagespreis), vermietetVonBis.split(";")));
			this.view.zeigeInformationsfensterAn("Das Auto wurde aufgenommen!");
		} catch (Exception exc) {
			this.view.zeigeFehlermeldungsfensterAn(
					"Bitte alle Felder korrekt ausfuellen!");
		}
	}

	void zeigeAutosAn() {
		if (this.model.getAuto() != null) {
			this.view.zeigeAutosAn(this.model.getAuto().gibAutoZurueck(' '));
		} else {
			this.view.zeigeInformationsfensterAn(
					"Bisher wurde kein Auto aufgenommen!");
		}
	}

	void leseAusDatei(String typ) {
		try {
			if ("csv".equals(typ)) {
				this.model.leseAutoAusCsvDatei();
				this.view.zeigeInformationsfensterAn("Das Auto wurde gelesen!");
			} else {
				this.view.zeigeInformationsfensterAn("Noch nicht implementiert!");
			}
		} catch (IOException exc) {
			this.view.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
		} catch (Exception exc) {
			this.view.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
		}
	}

	void schreibeAutoInCsvDatei() {
		try {
			if (this.model.getAuto() == null) {
				this.view.zeigeInformationsfensterAn(
						"Bisher wurde kein Auto aufgenommen!");
				return;
			}
			this.model.schreibeAutoInCsvDatei();
			this.view.zeigeInformationsfensterAn("Die Autos wurden gespeichert!");
		} catch (IOException exc) {
			this.view.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
		} catch (Exception exc) {
			this.view.zeigeFehlermeldungsfensterAn(
					"Unbekannter Fehler beim Speichern!");
		}
	}
}
