package fr.aftek.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

import fr.aftek.Athlete;
import fr.aftek.data.ConnexionMySQL;
import fr.aftek.data.DataManager;
import fr.aftek.data.DataProvider;
import fr.aftek.ihm.pages.Menu;
import fr.aftek.ihm.pages.PageClassementAthletes;
import fr.aftek.ihm.pages.PageConnexion;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Classe principale de l'application des Jeux Olympiques.
 * Cette classe étend la classe Application de JavaFX.
 */
public class ApplicationJO extends Application{
    private Scene scene;
    private ConnexionMySQL connexion;
    public static final DataProvider PROVIDER = new DataProvider();
    public static final DataManager MANAGER = PROVIDER.getManager();

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        // Connexion à la base de données
        this.connexion = new ConnexionMySQL();
        PROVIDER.loadCSV("donnees1.csv");
        // Création de la scène
        BorderPane root = new PageConnexion(this);
        PROVIDER.loadCSV("donnees.csv");
        System.out.println(PROVIDER.getManager().getAthletes().size());
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
        stage.getScene().setRoot(new Menu(this, connexion.getRole() == "admin"));
    }

    public void classementAthletes() throws IOException, InterruptedException{
        final Set<Athlete> set = MANAGER.getAthletes();
        Task<PageClassementAthletes> task = new Task<PageClassementAthletes>() {
            protected PageClassementAthletes call() throws Exception {
                return new PageClassementAthletes(set);
            };
        };
        PopUp<ButtonType> popUp = new PopUp<>(PopUpType.PROGRESS, "Création du classement", "Le classement est en cours de création...");
        task.setOnSucceeded((wse)->{
            stage.getScene().setRoot(task.getValue());
            popUp.close();
        });
        task.setOnFailed((wse)->{
            popUp.close();
            wse.getSource().getException().printStackTrace();
        });
        Thread th = new Thread(task,"Progress");
        th.start();
        Optional<ButtonType> res = popUp.showAndWait();
        res.ifPresent((e)-> {
            if(e == ButtonType.CANCEL){
                th.interrupt();
            }
        });
    }

    /**
     * Méthode principale pour lancer l'application.
     * 
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(ApplicationJO.class);
    }

    public void retourAccueil() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retourAccueil'");
    }
}
