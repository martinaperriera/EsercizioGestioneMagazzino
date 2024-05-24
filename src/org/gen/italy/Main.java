package org.gen.italy;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.gen.italy.model.Movimenti;

public class Main {

	public static void main(String[] args) {
		/*
		 * Scrivere un programma per la gestione dei movimenti di un magazzino.
		 * All'avvio del programma inizializzare delle tabelle codice/descrizione
		 * (HashMap), in particolare: fornitori (cod, nome) clienti (cod, nome) prodotti
		 * (cod, descrizione) tipologieMovimento (cod, descrizione) E01: acquisto da
		 * fornitore E02: reso da cliente E03: produzione interna E04: spostamento da
		 * altro magazzino U01: vendita a cliente U02: reso a fornitore U03:
		 * sostituzione in garanzia U04: spostamento a altro magazzino Il programma deve
		 * proporre un menu con le seguenti operazioni: inserimento movimento in
		 * entrata: viene chiesto all'utente di inserire i seguenti dati: data codice
		 * prodotto quantità prodotto codice movimento riferimento (opzionale - cod
		 * fornitore (movimento E01), cod. cliente (movimento E02)) inserimento
		 * movimento in uscita: viene chiesto all'utente di inserire i seguenti dati:
		 * data codice prodotto quantità prodotto codice movimento riferimento
		 * (opzionale - cod cliente (movimento U01), cod. cliente (movimento U02))
		 * visualizzazione movimenti in entrata (vengono mostrate le informazioni
		 * inserite nel punto 1. decodificando i codici) visualizzazione movimenti in
		 * uscita (vengono mostrate le informazioni inserite nel punto 2. decodificando
		 * i codici) giacenza prodotto: viene chiesto di inserire il codice di un
		 * prodotto e ne viene calcolata la giacenza (differenza tra le quantità in
		 * entrata e le quantità in uscita) NB: ad ogni nuovo movimento viene assegnato
		 * un codice univoco autoincrementante
		 */
		Scanner sc = new Scanner(System.in);
		ArrayList<Movimenti> elencoMovimenti = new ArrayList<Movimenti>();
		Movimenti m;
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String scelta;

		HashMap<String, String> fornitori = new HashMap<String, String>() {
			{
				put("1", "Microsoft");
				put("2", "Apple");
				put("3", "Intel");
			}
		};

		HashMap<String, String> clienti = new HashMap<String, String>() {
			{
				put("23", "Cinzia");
				put("45", "Carlotta");
				put("54", "Manuel");
			}
		};

		HashMap<String, String> prodotti = new HashMap<String, String>() {
			{
				put("01", "PC");
				put("02", "Smartphone");
				put("03", "Monitor");

			}
		};

		HashMap<String, String> tipologieMovimento = new HashMap<String, String>() {
			{
				put("E01", "acquisto da fornitore");
				put("E02", "reso da cliente");
				put("E03", "produzione interna");
				put("E04", "spostamento da altro magazzino");
				put("U01", "vendita a cliente");
				put("U02", "reso a fornitore");
				put("U03", "sostituzione in garanzia");
				put("U04", "spostamento a altro magazzino");
			}
		};

		System.out.println("**** GESTIONE MAGAZZINO ****\n");

		do {
			System.out.println("Quale operazione vuoi fare? ");
			System.out.println("1. Inserisci movimento in entrata");
			System.out.println("2. Inserisci movimento in uscita");
			System.out.println("3. Visualizzazione movimento in entrata");
			System.out.println("4. Visualizzazione movimento in uscita");
			System.out.println("5. Calcolo giacenza prodotto \n");
			System.out.println("6. Esci");

			System.out.println("\n\nInserisci la tua scelta: ");
			scelta = sc.nextLine();

			/*
			 * //prima possibilità if - else if. if (scelta.equals("1")) { // inserimento
			 * movimento in entrata m = new Movimenti(); // creo nuovo movimento
			 * System.out.println("Inserisci la data: "); m.data =
			 * LocalDate.parse(sc.nextLine(),df);
			 * System.out.println("Inserisci il codice prodotto: "); m.codProdotto =
			 * sc.nextLine(); sc.nextLine();
			 * System.out.println("Inserisci uantità prodotto: "); m.qntProdotto =
			 * sc.nextInt(); sc.nextLine();
			 * System.out.println("Inserisci codice Movimento: "); m.codMovimento =
			 * sc.nextLine(); System.out.println("Inserisci codice Fornitore: ");
			 * m.codFornitore=sc.nextLine();
			 * System.out.println("Inserisci codice Cliente: "); m.codCliente=sc.nextLine();
			 * 
			 * if (m.codMovimento.equals("E01") || m.codMovimento.equals("E02")) {
			 * System.out.println("Inserisci il riferimento: "); m.riferimento =
			 * sc.nextLine(); } elencoMovimenti.add(m); // aggiungo il movimento alla lista
			 * in entrata } if (scelta.equals("2")) { // visualizzazione movimenti in uscita
			 * m = new Movimenti(); // creo nuovo movimento
			 * System.out.println("Inserisci la data: "); m.data =
			 * LocalDate.parse(sc.nextLine(),df);//df descrive il formato della data
			 * System.out.println("Inserisci il codice prodotto: "); m.codProdotto =
			 * sc.nextLine(); sc.nextLine();
			 * System.out.println("Inserisci uantità prodotto: "); m.qntProdotto =
			 * sc.nextInt(); sc.nextLine();
			 * System.out.println("Inserisci codice Movimento: "); m.codMovimento =
			 * sc.nextLine(); System.out.println("Inserisci codice Fornitore: ");
			 * m.codFornitore=sc.nextLine();
			 * System.out.println("Inserisci codice Cliente: "); m.codCliente=sc.nextLine();
			 * 
			 * if (m.codMovimento.equals("U01") || m.codMovimento.equals("U02")) {
			 * System.out.println("Inserisci il riferimento: "); m.riferimento =
			 * sc.nextLine(); } elencoMovimenti.add(m); // aggiungo il movimento alla lista
			 * in uscita } //visualizzazione movimenti in entrata if (scelta.equals("3")) {
			 * for (int i = 0; i < elencoMovimenti.size(); i++) { m=elencoMovimenti.get(i);
			 * //si riduce elencoMovimenti a m (cosi poi posso fare m.data ecc) if
			 * (m.codMovimento.startsWith("E")) { //se la stringa inizia per E - si possono
			 * evitare le ripetizioni di (m.codMovimento.equals("E01")) ecc
			 * System.out.println("Data:" +m.data); System.out.println("Prodotto:"
			 * +prodotti.get(m.codProdotto)); //stessa cosa di giù
			 * System.out.println("Quantità:" +m.qntProdotto);
			 * System.out.println("Movimento:" +tipologieMovimento.get(m.codMovimento));
			 * //di tipologie movimento voglio stampare m.codMovimento
			 * System.out.println("Fornitore: " +fornitori.get(m.codFornitore));
			 * 
			 * } } //visualizzazione movimenti in uscita if (scelta==4) { for (int i = 0; i
			 * < elencoMovimenti.size(); i++) { m=elencoMovimenti.get(i); //si riduce
			 * elencoMovimenti a m (cosi poi posso fare m.data ecc) if
			 * (m.codMovimento.startsWith("U")) { //se la stringa inizia per E - si possono
			 * evitare le ripetizioni di (m.codMovimento.equals("E01")) ecc
			 * System.out.println("Data:" +m.data); System.out.println("Prodotto:"
			 * +prodotti.get(m.codProdotto)); //stessa cosa di giù
			 * System.out.println("Quantità:" +m.qntProdotto);
			 * System.out.println("Movimento:" +tipologieMovimento.get(m.codMovimento));
			 * //di tipologie movimento voglio stampare m.codMovimento
			 * System.out.println("Cliente: " +clienti.get(m.codCliente)) }}
			 * 
			 * } if (scelta.equals("5")) { for(int i = 0; i < elencoMovimenti.size(); i++) {
			 * /* String= if (elencoMovimenti.get(i).codProdotto.equals("")) //ci interessa
			 * il cod prodotto di elenco movimenti
			 */

			// SI PUO FARE ANCHE CON LO SWITCH AL POSTO DELL'IF ELSE
			switch (scelta) {
			case "1":
				// codice
				System.out.println("Inserimento movimento in entrata");
				m = new Movimenti(); // creo un nuovo oggetto movimento (legato alla classe .model
				System.out.print("Inserisci la data: ");
				m.data = LocalDate.parse(sc.nextLine(), df); // leggo la stringa LocalDate e la converto in una data
																// (.parse)

				m.codProdotto = verificaCodice(prodotti, sc, "Inserisci il codice prodotto: "); // se il codice è valido
																								// lo restituisce come																				// valore di ritorno

				System.out.print("Inserisci la quantità");
				m.qntProdotto = sc.nextInt();
				sc.nextLine();

				m.codMovimento = verificaCodice(tipologieMovimento, sc, "Inserisci il codice movimento");

				if (m.codMovimento.equalsIgnoreCase("E01")) {
					m.codFornitore = verificaCodice(fornitori, sc, "Inserisci il codice fornitore: ");
				} else if (m.codMovimento.equalsIgnoreCase("E02")) {
					m.codCliente = verificaCodice(clienti, sc, "Inserisci il codice cliente: ");
				}
				elencoMovimenti.add(m);

				break;
			case "2":
				// codice
				System.out.println("Inserimento movimento in uscita");
				m = new Movimenti(); // creo un nuovo oggetto movimento (legato alla classe .model
				System.out.print("Inserisci la data");
				m.data = LocalDate.parse(sc.nextLine(), df); // leggo la stringa LocalDate e la converto in una data
																// (.parse)

				m.codProdotto = verificaCodice(prodotti, sc, "Inserisci il codice prodotto: "); // se il codice è valido
																								// lo restituisce come																				// valore di ritorno

				System.out.print("Inserisci la quantità");
				m.qntProdotto = sc.nextInt();
				sc.nextLine();

				m.codMovimento = verificaCodice(tipologieMovimento, sc, "Inserisci il codice movimento");

				if (m.codMovimento.equalsIgnoreCase("U01")) {
					m.codCliente = verificaCodice(clienti, sc, "Inserisci il codice cliente: ");
				} else if (m.codMovimento.equalsIgnoreCase("U02")) {
					m.codFornitore = verificaCodice(clienti, sc, "Inserisci il codice cliente: ");
				}
				elencoMovimenti.add(m);

				break;
			case "3":
				// codice
				System.out.println("Visualizzazione movimento in entrata");
				System.out.println("I movimenti in entrata sono ");
				for (int i = 0; i < elencoMovimenti.size(); i++) { // gira per la grandezza di elencoMovimenti e a ogni
																	// giro aumenta di uno
					if (elencoMovimenti.get(i).codMovimento.startsWith("E")) { // controllo se il movimento è in entrata
						System.out.println("Codice Fornitore: " + elencoMovimenti.get(i).codFornitore); // mi devi
																										// prendere il
																										// valore di i
																										// in
																										// elencoMovimenti
																										// per ogni
																										// codice
						System.out.println("Codice Movimento: " + elencoMovimenti.get(i).codMovimento);
						System.out.println("Codice Prodotto: " + elencoMovimenti.get(i).codProdotto);
						System.out.println("Quantità prodotto: " + elencoMovimenti.get(i).qntProdotto);
						System.out.println("Data :" + elencoMovimenti.get(i).data);
					}
				}
				break;

			case "4":
				// codice
				System.out.println("Visualizzazione movimento in uscita");
				System.out.println("I movimenti in entrata sono ");
				for (int i = 0; i < elencoMovimenti.size(); i++) { // gira per la grandezza di elencoMovimenti e a ogni
																	// giro aumenta di uno
					if (elencoMovimenti.get(i).codMovimento.startsWith("U")) { // controllo se il movimento è in uscita
						System.out.println("Codice cliente: " + elencoMovimenti.get(i).codCliente); // mi devi prendere
																									// il valore di i in
																									// elencoMovimenti
																									// per ogni codice
						System.out.println("Codice Movimento: " + elencoMovimenti.get(i).codMovimento);
						System.out.println("Codice prodotto: " + elencoMovimenti.get(i).codProdotto);
						System.out.println("Quantità prodotto: " + elencoMovimenti.get(i).qntProdotto);
						System.out.println("Data: " + elencoMovimenti.get(i).data);
					}
				}
				break;
			case "5":
				// codice
				System.out.println("Calcolo giacenza prodotto");
				System.out.println("Inserisci il codice prodotto: ");
				String codProdotto = sc.nextLine();
				int giacenza = 0;
				for (int i = 0; i < elencoMovimenti.size(); i++) {
					if (elencoMovimenti.get(i).codProdotto == codProdotto) { // verifichiamo se il codice prodotto del
																				// movimento corrente è uguale al codice
																				// inserit dall'utente
						if (elencoMovimenti.get(i).codMovimento.startsWith("E")) {
							giacenza += elencoMovimenti.get(i).qntProdotto;
						} else if (elencoMovimenti.get(i).codMovimento.startsWith("U")) {
							giacenza -= elencoMovimenti.get(i).qntProdotto;

						}
					}
				}
				break;
			case "6":
				// codice
				System.out.println("ARRIVEDERCI!");
				break;
			default: // tutti gli altri casi verranno gestiti qui
				// codice per gestire tutti gli altri casi
				System.out.println("Scelta non valida");
			}
			System.out.println("Premi invio per continuare");
			sc.nextLine();

		} while (!scelta.equals("6")); // torno indietro se la scelta è diversa da 6
		sc.close();
	}

	/*
	 * il metodo verificacodice viene usato per verificare che un codice inserito
	 * esista in un determinato hashmap e restituisce se il codice inserito è valido
	 * se il codice non è valido, il metodo continua a richiedere un codice finché
	 * non viene inserito uno valido. dato un generico hashmap codice-descrizione,
	 * verifica se il codice inserito dall'utente esiste nell'hashmap se non esiste
	 * ripete l'inserimento, se esiste mostra la descrizione corrispondente
	 */
	private static String verificaCodice(HashMap<String, String> tipologieMovimento, Scanner sc, String messaggio) {
		String codice;
		do {
			System.out.print(messaggio);
			codice = sc.nextLine();
			if (!tipologieMovimento.containsKey(codice))
				System.out.println("Codice non valido");
		} while (!tipologieMovimento.containsKey(codice)); // torno indietro se la chiave non esiste nell'hashmap
		System.out.println("Hai selezionato: " + tipologieMovimento.get(codice));
		return codice; // dopo aver eseguito le istruzioni restituisco il valore del codice
	}
}
