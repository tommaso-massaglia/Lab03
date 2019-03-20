package it.polito.tdp.spellchecker.controller;

import it.polito.tdp.spellchecker.model.SpellCheckerModel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			SpellCheckerModel model = new SpellCheckerModel();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SpellChecker.fxml"));
			
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			SpellCheckerController controller = loader.getController();
			controller.setModel(model);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
