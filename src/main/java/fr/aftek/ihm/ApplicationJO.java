package fr.aftek.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.data.DataProvider;
import fr.aftek.ihm.pages.*;
import fr.aftek.ihm.pages.PageChoixSport;
import fr.aftek.ihm.pages.PageConnexion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Classe principale de l'application des Jeux Olympiques.
 * Cette classe étend la classe Application de JavaFX.
 */
public class ApplicationJO extends Application{
    
    private Scene scene; // Scène principale de l'application
    private ConnexionMySQL connexion; // Connexion à la base de données MySQL
    private Stage stage; // Fenêtre principale de l'application
    public static final DataProvider PROVIDER = new DataProvider();

    /**
     * Point d'entrée de l'application.
     * 
     * @param stage La fenêtre principale de l'application
     * @throws Exception Si une erreur survient lors du lancement
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Connexion à la base de données
        this.connexion = new ConnexionMySQL();
        PROVIDER.loadCSV("donnees1.csv");
        // Création de la scène
        BorderPane root = new PageClassementAthletes(this, ApplicationJO.PROVIDER.getManager().getAthletes());
        this.scene = new Scene(root, 900, 600);
        this.stage = stage;
        stage.setScene(scene);
        stage.setTitle("Jeux IUT'Olympiques"); // Titre de la fenêtre
        stage.setResizable(false); // Empêche le redimensionnement de la fenêtre
        stage.show(); // Affiche la fenêtre
        // Définit l'action à réaliser lors de la fermeture de la fenêtre
        stage.setOnCloseRequest((e)->System.exit(0));
        //choixSport();
    }

    /**
     * Retourne la connexion à la base de données.
     * 
     * @return ConnexionMySQL La connexion à la base de données
     */
    public ConnexionMySQL getConnexion() {
        return connexion;
    }
    
    /**
     * Affiche le menu principal de l'application.
     * 
     * @throws IOException Si une erreur d'entrée/sortie survient
     * @throws SQLException Si une erreur SQL survient
     */
    public void menu() throws IOException, SQLException {
        stage.getScene().setRoot(new Menu(this));

        // Récupère le rôle de l'utilisateur connecté
        String role = connexion.getRole();
        System.out.println(role);
        if (role == "admin") {
            // Affiche un message si l'utilisateur est un administrateur
            System.out.println("Il faut afficher le bouton admin");
        }
    }

    public void choixSport() throws IOException{
        stage.getScene().setRoot(new PageChoixSport(this));
    }

    /**
     * Méthode principale pour lancer l'application.
     * 
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(ApplicationJO.class);
    }

    public void retourAcceuil() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retourAcceuil'");
    }

    public void retourAccueil() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retourAccueil'");
    }
}
