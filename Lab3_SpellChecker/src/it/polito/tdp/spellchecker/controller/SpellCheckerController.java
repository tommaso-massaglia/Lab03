package it.polito.tdp.spellchecker.controller;

import it.polito.tdp.spellchecker.model.SpellCheckerModel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SpellCheckerController {

	private SpellCheckerModel model;

	ObservableList<String> lingue = FXCollections.observableArrayList("rsc/English.txt", "rsc/Italian.txt",
			"rsc/Italian_back.txt");

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> menuLingua;

	@FXML
	private TextArea areaInserimento;

	@FXML
	private Button btnControlloOrtografico;

	@FXML
	private TextArea areaErrori;

	@FXML
	private Text numerrori;

	@FXML
	private Button btnPulisci;

	@FXML
	private Text tempocompletamento;

	@FXML
	void doControlloOrtografico(ActionEvent event) {

		long tempoiniziale = System.nanoTime();

		areaErrori.clear();

		String testoinserito = this.areaInserimento.getText();
		testoinserito = testoinserito.replaceAll("[^a-zA-Z0-9\\s_-]", "");
		model.correggi(testoinserito, menuLingua.valueProperty().get());

		for (String s : model.paroleSbagliate())
			areaErrori.appendText(s + "\n");

		if (model.paroleSbagliate().size() == 1)
			this.numerrori.setText("C'è " + model.paroleSbagliate().size() + " parola sbagliata nel testo.");
		else
			this.numerrori.setText("Ci sono " + model.paroleSbagliate().size() + " parole sbagliate nel testo.");
		this.tempocompletamento
				.setText("Tempo impiegato per l'operazione: " + ((System.nanoTime() - tempoiniziale) / 1000000) + "ms");

	}

	@FXML
	void doPulisci(ActionEvent event) {
		
		this.areaErrori.clear();
		this.areaInserimento.clear();
		this.numerrori.setText("");
		this.tempocompletamento.setText("");

	}

	public void setModel(SpellCheckerModel model) {
		this.model = model;
	}

	@FXML
	void initialize() {
		assert menuLingua != null : "fx:id=\"menuLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert areaInserimento != null : "fx:id=\"areaInserimento\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert btnControlloOrtografico != null : "fx:id=\"btnControlloOrtografico\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert areaErrori != null : "fx:id=\"areaErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert numerrori != null : "fx:id=\"numerrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert btnPulisci != null : "fx:id=\"btnPulisci\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert tempocompletamento != null : "fx:id=\"tempocompletamento\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		menuLingua.setItems(lingue);
		menuLingua.setValue(lingue.get(0));
	}
}
