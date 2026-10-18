package gui;
   
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Auto;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class AutovermietungAnwendungssystem {
	  
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     					= new  Pane();
    private Label lblEingabe    	 		= new Label("Eingabe");
    private Label lblAnzeige   	 	    	= new Label("Anzeige");
    private Label lblKennzeichen 			= new Label("Kennzeichen:");
    private Label lblTyp   		 			= new Label("Typ:");
    private Label lblModell  	 			= new Label("Modell:");
    private Label lblTagespreis   			= new Label("Tagespreis:");
    private Label lblVermietetVonBis  		= new Label("Vermietet von-bis:");
    private TextField txtKennzeichen 	 	= new TextField();
    private TextField txtTyp				= new TextField();
    private TextField txtModell				= new TextField();
    private TextField txtTagespreis			= new TextField();
    private TextField txtVermietetVonBis		= new TextField();
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnEingabe 		 		= new Button("Eingabe");
    private Button btnAnzeige 		 		= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  		= new MenuBar();
    private Menu mnDatei             		= new Menu("Datei");
    private MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");    
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    // speichert temporaer ein Objekt vom Typ Auto
    private Auto auto;
    
    public AutovermietungAnwendungssystem(Stage primaryStage){
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung einer Autovermietung");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblKennzeichen.setLayoutX(20);
    	lblKennzeichen.setLayoutY(90);
    	lblTyp.setLayoutX(20);
    	lblTyp.setLayoutY(130);
    	lblModell.setLayoutX(20);
    	lblModell.setLayoutY(170);
    	lblTagespreis.setLayoutX(20);
    	lblTagespreis.setLayoutY(210);
    	lblVermietetVonBis.setLayoutX(20);
    	lblVermietetVonBis.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblKennzeichen, lblTyp, lblModell,
       		lblTagespreis, lblVermietetVonBis);
    
    	// Textfelder
     	txtKennzeichen.setLayoutX(170);
    	txtKennzeichen.setLayoutY(90);
    	txtKennzeichen.setPrefWidth(200);
    	txtTyp.setLayoutX(170);
    	txtTyp.setLayoutY(130);
    	txtTyp.setPrefWidth(200);
    	txtModell.setLayoutX(170);
    	txtModell.setLayoutY(170);
    	txtModell.setPrefWidth(200);
      	txtTagespreis.setLayoutX(170);
    	txtTagespreis.setLayoutY(210);
    	txtTagespreis.setPrefWidth(200);
    	txtVermietetVonBis.setLayoutX(170);
    	txtVermietetVonBis.setLayoutY(250);
    	txtVermietetVonBis.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtKennzeichen, txtTyp, txtModell,
     		txtTagespreis, txtVermietetVonBis);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    nehmeAutoAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeAutosAn();
	        } 
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				schreibeAutoInCsvDatei();
			}	
	    });
    }
    
    private void nehmeAutoAuf(){
    	try{ 
    		this.auto = new Auto(
    			txtKennzeichen.getText(), 
   	            txtTyp.getText(),
   	            txtModell.getText(),
   	            Float.parseFloat(txtTagespreis.getText()),
    		    txtVermietetVonBis.getText().split(";"));
    		zeigeInformationsfensterAn("Das Auto wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    private void zeigeAutosAn(){
    	if(this.auto != null){
    		txtAnzeige.setText(
    			this.auto.gibAutoZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde kein Auto aufgenommen!");
    	}
    }    
		  
    private void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Auto.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.auto = new Auto(zeile[0], 
      				zeile[1], 
      				zeile[2], 
      				Float.parseFloat(zeile[3]), 
      				zeile[4].split("_"));
      				ein.close();
      	  			zeigeInformationsfensterAn(
      	  	   			"Das Auto wurde gelesen!");
      		}
       		else{
	   			zeigeInformationsfensterAn(
	   				"Noch nicht implementiert!");
	   		}
		}
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}
		
	private void schreibeAutoInCsvDatei() {
		try {
			BufferedWriter aus 
				= new BufferedWriter(new FileWriter("AutosAusgabe.csv", true));
			aus.write(auto.gibAutoZurueck(';'));
			aus.close();
   			zeigeInformationsfensterAn(
	   			"Die Autos wurden gespeichert!");
		}	
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}

    private void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
