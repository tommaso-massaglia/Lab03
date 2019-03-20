package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SpellCheckerModel {

	private Map<String, String> dizionario;
	private List<RichWord> output;
	private String ultimodizionarioaggiunto;

	public SpellCheckerModel() {
		dizionario = new HashMap<String, String>();
		output = new ArrayList<RichWord>();
		ultimodizionarioaggiunto = "";
	}

	/**
	 * Esegue la correzione dell'input e genera un output nel modello
	 * 
	 * @param input    una stringa contenente le parole separate da uno spazio
	 * @param nomefile il nome del file da cui prendere il dizionario
	 */
	public void correggi(String input, String nomefile) {

		output.clear();

		if (!ultimodizionarioaggiunto.equals(nomefile)) {
			dizionario.clear();
			this.aggiungidizionario(nomefile);
		}

		String[] elencoparole = input.split(" ");
		for (String s : elencoparole) {
			if (dizionario.containsKey(s))
				output.add(new RichWord(s, true));
			if (!dizionario.containsKey(s))
				output.add(new RichWord(s, false));
		}

	}

	/**
	 * Carica nel modello un nuovo dizionario
	 * 
	 * @param nomefile nome del file da cui prendere il dizionario
	 */
	private void aggiungidizionario(String nomefile) {
		ultimodizionarioaggiunto = nomefile;
		try {
			FileReader fr = new FileReader(nomefile);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.put(word, word);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}

	/**
	 * Restituisce una lista contenente le parole giuste dell'ultimo controllo effettuato
	 * @return	List contenente le parole
	 */
	public List<String> paroleCorrette() {
		List<String> parolecorrette = new LinkedList<String>();
		for (RichWord r : this.output) {
			if (r.isCorretta())
				parolecorrette.add(r.getParola());
		}
		return parolecorrette;
	}

	/**
	 * Restituisce una lista contenente le parole sbagliate dell'ultimo controllo effettuato
	 * @return	List contenente le parole
	 */
	public List<String> paroleSbagliate() {
		List<String> parolesbagliate = new LinkedList<String>();
		for (RichWord r : this.output) {
			if (!r.isCorretta())
				parolesbagliate.add(r.getParola());
		}
		return parolesbagliate;
	}
	
	public void clear() {
		this.dizionario.clear();
		this.output.clear();
		this.ultimodizionarioaggiunto="";
	}
}
