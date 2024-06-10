package src;

import java.util.Scanner;

class UIDatabaseConnection {    
    public static void main(String[] args) {
        AppDatabaseConnection app = new AppDatabaseConnection();
        app.run();
    }
}

class AppDatabaseConnection {
    boolean quitter;
    
    AppDatabaseConnection() {
        this.quitter = false;
    }

    public void run() {
        bienvenue();
        while(!quitter) {
            menu();
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        
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

    public void bienvenue() {
        System.out.println("╭────────────────────────────────────────────────────────────────────────────────────╮");
        System.out.println("│ Bienvenue! Veuillez entrer les informations de connexion à la base de données.      │");
        System.out.println("╰────────────────────────────────────────────────────────────────────────────────────╯");
    }

}
