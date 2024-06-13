package fr.aftek;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fr.aftek.data.DataProvider;

class ExecutableTerminal {  
    private boolean quitter;  
    private final int largeur = 50;
    private Scanner scanner;
    private Menus menus;
    private DataProvider provider;
    public static void main(String[] args) {
        ExecutableTerminal app = new ExecutableTerminal();
        app.run();
    }
    
    ExecutableTerminal() {
        this.quitter = false;
        this.scanner = new Scanner(System.in);
        this.menus = new Menus();
        this.provider = new DataProvider();
    }

    public void run() {
        afficher("Bienvenue sur notre application !");
        while(!quitter) {
            menu();
        }
    }

    public void menu(){
        String[] menuPrincipal = new String[] {
            "BD: Menu base de donnée",
            "AT: Menu athlètes",
            "EP: Menu epreuves",
            "PA: Menu pays",
            "SP: Menu sport",
            "CA: Charger données",
            "Q: quitter"
        };
        afficher("Que voulez vous faire?", menuPrincipal);
        String commande = scanner.nextLine().strip().toUpperCase();
        if(commande.equals("Q")){
            this.quitter = true;
            afficher("A bientot !");
        }else if(commande.isBlank()){
            afficher("Commande "+commande+" inconnue !");
        }
        else{
                try {
                    Method method = Menus.class.getDeclaredMethod("menu"+commande);
                    method.invoke(menus);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    afficher("Commande "+commande+" inconnue !");
                    e.printStackTrace();
                }
        }
    }

    public void bd() {
        
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();

        System.out.print("Voulez-vous changer le serveur par défaut (servinfo-maria) ? (oui/non): ");
        String changerServeur = scanner.nextLine();
        String nomServeur;
        
        if (changerServeur.equalsIgnoreCase("oui")) {
            System.out.print("Nom du serveur: ");
            nomServeur = scanner.nextLine();
        } else {
            nomServeur = "servinfo-maria";
        }

        System.out.print("Nom de la base de donnée: ");
        String nomBaseDeDonnee = scanner.nextLine();

        System.out.println("Connexion à la base de donnée avec les informations suivantes:");
        System.out.println("Login: " + login);
        System.out.println("Mot de passe: " + motDePasse);
        System.out.println("Nom du serveur: " + nomServeur);
        System.out.println("Nom de la base de donnée: " + nomBaseDeDonnee);
        
        // Ici, vous pouvez ajouter le code pour vous connecter à la base de données.
        
        System.out.println("C'est fini !!");
        quitter = true;
    }

    public void afficher(String titre, List<String> contenu) {
		StringBuilder sb = new StringBuilder();
		sb.append("╭─" + "─".repeat(largeur) + "─╮\n");
        String[] phrases = titre.split("\n");
        for (String phrase : phrases) {
            String[] decoupage = decouper(phrase);
            for(String decoupe : decoupage) {
                sb.append("│ " + decoupe + " ".repeat(largeur - decoupe.length()) + " │\n");
            }
        }
		if (contenu == null)
			contenu = new ArrayList<String>();
		if (contenu.size() == 0) {
			sb.append("╰─" + "─".repeat(largeur) + "─╯\n");
			System.out.println(sb.toString());
			return;
		}
		sb.append("├─" + "─".repeat(largeur) + "─┤\n");
		for (String elem : contenu) {
            String[] elemPhrases = elem.split("\n");
            for (String phrase : elemPhrases) {
                String[] decoupage = decouper(phrase);
                for(String decoupe : decoupage) {
			        sb.append("│ " + decoupe + " ".repeat(largeur - decoupe.length()) + " │\n");
                }
            }
		}
		sb.append("╰─" + "─".repeat(largeur) + "─╯");
		System.out.println(sb.toString());
	}

	public void afficher(String titre, String[] contenu) {
		afficher(titre, Arrays.asList(contenu));
	}

	public void afficher(String text) {
		afficher(text, new String[0]);
	}

    private String[] decouper(String str){
        String[] mots = str.split(" ");
        List<String> res = new ArrayList<>();
        String lastString = "";
        for (String mot : mots) {
            if((lastString+mot).length()+1 >= largeur){
                res.add(lastString);
                lastString = "";
            }
            lastString += mot + " ";
        }
        if(!lastString.isBlank()){
            res.add(lastString);
        }
        return res.toArray(new String[0]);
    }

    public class Menus {
        public void menuBD(){
            afficher("Ce menu n'est pas necore implémenté.\nIl le sera pendant la semaine de projet :)");
        }
        public void menuAT(){
            String[] menuAthlete = new String[] {
                "LI: Liste des athlète",
                "CR: Créer un athlète",
                "SU: Supprime un athlète",
                "Q: quitter"
            };
            boolean quitter = false;
            while(!quitter){
                afficher("Que voulez vous faire?", menuAthlete);
                String commande = scanner.nextLine().strip().toUpperCase();
                if(commande.equals("Q")){
                    quitter = true;
                }else if(commande.equals("LI")){
                    afficher("Voici la liste des athlète:", provider.getManager().getAthletes().stream().map((a)->a.getPrenom()+" "+a.getNom()+": "+a.getPays().getNom()).sorted().collect(Collectors.toList()));
                }else if(commande.equals("CR")){
                    String[] res = this.saisirInfosAthlete();
                    if(res != null){
                        Pays pays = provider.getManager().getPays(res[3]);
                        if(pays == null) pays = provider.getManager().addPays(new Pays(res[3]));
                        provider.getManager().addAthlete(new Athlete(res[0], res[1], res[2].charAt(0), provider.getManager().getPays(res[3]), null)); //TODO ajouter sport
                        afficher("L'athlète "+res[1]+" "+res[0]+(res[2].charAt(0) == 'F' ? " femme " : " homme ")+"de "+res[3]+" ajouté !");
                    }
                }else if(commande.equals("SU")){
                    Athlete athlete = null;
                    while(athlete == null){
                        String[] res = this.saisirNomPrenom();
                        if(res != null){
                            athlete = provider.getManager().getAthlete(res[0], res[1]);
                            if(athlete == null) afficher("L'athlète n'existe pas !");
                        }
                    }
                    provider.getManager().getAthletes().remove(athlete);
                    afficher("L'athlète "+athlete.getPrenom()+" "+athlete.getNom()+(athlete.getSexe() == 'F' ? " femme " : " homme ")+"de "+athlete.getPays()+" ajouté !");
                }
                else{
                    afficher("Commande "+commande+" inconnue !");
                }
            }
        }
        public void menuEP(){

        }
        public void menuPA(){

        }
        public void menuSP(){

        }
        public void menuCA(){
            boolean quitter = false;
            while(!quitter){
                boolean exception = false;
                try{
                    String res = saisir("Choisissez le fichier: ");
                    if(res.equals("Q") || res.equals("q")) return;
                    provider.loadCSV(res);
                }catch(Exception e){
                    exception = true;
                    System.out.println("Erreur lors du chargement !");
                }
                if(!exception){
                    quitter = true;
                    System.out.println("Fichier chargé avec succès !");
                }
            }
        }
        private String[] saisirNomPrenom(){
            afficher("Merci de saisir les informations suivantes:\n(tapez q pour quitter)");
            String prenom = saisir("Prénom: ");
            if(prenom == null) return null;
            String nom = saisir("Nom: ");
            if(nom == null) return null;
            return new String[]{nom, prenom};
        }
        private String[] saisirInfosAthlete(){
            afficher("Merci de saisir les informations suivantes:\n(tapez q pour quitter)");
            String prenom = saisir("Prénom: ");
            if(prenom == null) return null;
            String nom = saisir("Nom: ");
            if(nom == null) return null;
            String pays = saisir("Pays: ");
            if(pays == null) return null;
            if(provider.getManager().getPays(pays) == null){
                String reponse = "";
                while(!reponse.equals("O") && !reponse.equals("N")){
                    reponse = saisir("Ce pays n'existe pas, voulez-vous le créer ? (O ou N)").toUpperCase();
                    if(reponse == null || reponse.equals("N")) return null;
                }
            }
            String sexe = "";
            while(!sexe.equals("F") && !sexe.equals("M") && sexe != null){
                sexe = saisir("Sexe (F ou M): ").toUpperCase();
            }
            return new String[]{nom, prenom, sexe, pays};
        }
        private String saisir(String texte){
            String res = "";
            while(res.isBlank()){
                System.out.print(texte);
                res = scanner.nextLine();
            }
            if(res.equals("Q") || res.equals("q")) return null;
            return res;
        }
    }
}
