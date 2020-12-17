import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import humanResources.ChefBrico;
import humanResources.ChefStock;
import humanResources.Ouvrier;
import stockManagement.Entrepot;
import stockManagement.Rangee;

public class Main {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static void main(String[] args) {
		Ouvrier o1 = new Ouvrier("Ouvrier 1", "-1", "specialite 1");
		Ouvrier o2 = new Ouvrier("Ouvrier 2", "-2", "specialite 2");
		Ouvrier o3 = new Ouvrier("Ouvrier 3", "-3", "specialite 3");

		ChefStock chefS1 = new ChefStock("ChefStrock-1", "-1");

		ChefBrico chefB1 = new ChefBrico("ChefBrico-1", "-1");
		ChefBrico chefB2 = new ChefBrico("ChefBrico-2", "-2");

		// System.out.println(o1.toString());
		// System.out.println(o2.toString());
		// System.out.println(o3.toString());

		Entrepot.longueur = 5;
		Rangee r1 = new Rangee();
		Rangee r2 = new Rangee();

		Entrepot.rangees.add(r1);
		Entrepot.rangees.add(r2);

		Entrepot.personnels.add(o1);
		Entrepot.personnels.add(o2);
		Entrepot.personnels.add(o3);
		Entrepot.personnels.add(chefS1);
		Entrepot.personnels.add(chefB1);
		Entrepot.personnels.add(chefB2);

		System.out.println(ANSI_BLUE + "Votre System de gestion d'entrepot est Active" + ANSI_RESET);
		System.out.println(ANSI_CYAN + "/*******************************************/" + ANSI_RESET);

		boolean choixValide = false;

		do {
			System.out.println(ANSI_GREEN + "1-Mode Console" + ANSI_RESET);
			System.out.println(ANSI_PURPLE + "2-Mode File" + ANSI_RESET);
			System.out.println(ANSI_RED + "3-Exit" + ANSI_RESET);
			System.out.print("Choisir Un mode (1,3) : ");
			int choix = Integer.parseInt(System.console().readLine());
			switch (choix) {
				case 1:
					traitementParCommande();
					choixValide = true;
					break;
				case 2:
					System.out.println("Votre Choix est File");
					traitementParFichier();
					choixValide = true;
					break;
				case 3:
					System.out.println("Bye Bye");
					return;
				default:
					System.out.println(ANSI_RED + "choix Incorrect" + ANSI_RESET);
					break;
			}
		} while (!choixValide);

	}

	private static void traitementParFichier() {
		System.out.print(ANSI_PURPLE + "Please Enter The absolute path of your file + fileName : " + ANSI_GREEN);
		String path = System.console().readLine();
		List<String> commandes = new ArrayList<>();
		try {
			File file = new File(path);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				commandes.add(reader.nextLine());
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(ANSI_RED + "File Not Found." + ANSI_RESET);
			e.printStackTrace();
		}
		for (String commande : commandes) {
			traiterCommandeFromFile(commande);
		}
	}

	private static void traiterCommandeFromFile(String commande) {
		System.out.println(ANSI_YELLOW + "Current Commande Is " + commande + ANSI_RESET);
		try {
			String[] commandeArray = commande.split(" ");
			Integer.parseInt(commandeArray[0]);
			switch (commandeArray[1].toLowerCase()) {
				case "lot":
					Entrepot.traiterNouveauLot(commandeArray);
					break;
				case "state":
					Entrepot.showState();
					break;
				case "meuble":
					Entrepot.ConstructionNouvelleCommande(commandeArray);
					break;
				case "hr":
					Entrepot.hrCommande();
					break;
				case "rien":
					break;
				default:
					System.out.println(ANSI_RED + " Commande Introuvable " + ANSI_RESET);
					break;
			}
		} catch (Exception e) {
			System.out.println(ANSI_RED + " Commande Must Start with an ID " + ANSI_RESET);
		}
	}

	private static void traitementParCommande() {
		System.out.println(ANSI_GREEN + "Votre Choix est Commande" + ANSI_RESET);
		while (true) {
			System.out
					.println(ANSI_GREEN + "Choices You Have <lot> | <state> | <meuble> | <hr> | <rien> " + ANSI_RESET);
			System.out.print("Tappez la Commande : ");
			String commande = System.console().readLine();
			String[] commandeStrings = commande.split(" ");
			try {
				Integer.parseInt(commandeStrings[0]);
				switch (commandeStrings[1].toLowerCase()) {
					case "lot":
						Entrepot.traiterNouveauLot(commandeStrings);
						break;
					case "state":
						Entrepot.showState();
						break;
					case "meuble":
						Entrepot.ConstructionNouvelleCommande(commandeStrings);
						break;
					case "hr":
						Entrepot.hrCommande();
						break;
					case "rien":
						break;
					default:
						System.out.println(ANSI_RED + " Commande Introuvable " + ANSI_RESET);
						break;
				}
			} catch (Exception e) {
				System.out.println(ANSI_RED + " Commande Must Start with an ID " + ANSI_RESET);
			}

		}
	}

}
