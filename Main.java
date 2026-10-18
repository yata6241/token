package main;

import gui.AutovermietungAnwendungssystem;
import gui.AutovermietungControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new AutovermietungControl(primaryStage);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}
