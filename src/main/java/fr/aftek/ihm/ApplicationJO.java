package fr.aftek.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import fr.aftek.Athlete;
import fr.aftek.Equipe;
import fr.aftek.data.ConnexionMySQL;
import fr.aftek.data.DataProvider;
import fr.aftek.ihm.pages.Menu;
import fr.aftek.ihm.pages.Page;
import fr.aftek.ihm.pages.PageChoixSport;
import fr.aftek.ihm.pages.PageClassementAthletes;
import fr.aftek.ihm.pages.PageClassementEquipes;
import fr.aftek.ihm.pages.PageConnexion;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Classe principale de l'application des Jeux Olympiques.
 * Cette classe étend la classe Application de JavaFX.
 */
public class ApplicationJO extends Application{
    private Scene scene;
    private ConnexionMySQL connexion;
    public static final DataProvider PROVIDER = new DataProvider();
    private List<Page> historique = new ArrayList<>();

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        // Connexion à la base de données
        this.connexion = new ConnexionMySQL();
        PROVIDER.loadCSV("donnees1.csv");
        // Création de la scène
        Page root = new PageConnexion(this);
        this.historique.add(root);
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
        Menu menu = new Menu(this, connexion.getRole().equals("admin"));
        stage.getScene().setRoot(menu);
        this.historique.add(menu);
    }

    public void classementAthletes() throws IOException, InterruptedException{
        final Set<Athlete> set = ApplicationJO.PROVIDER.getManager().getAthletes();
        final ApplicationJO application = this;
        Task<PageClassementAthletes> task = new Task<PageClassementAthletes>() {
            protected PageClassementAthletes call() throws Exception {
                return new PageClassementAthletes(application,set);
            };
        };
        afficherPage(task, "Création du classement des athlètes", "Veuillez patienter...");
    }

    public void classementEquipes() throws IOException{
        final Set<Equipe> set = ApplicationJO.PROVIDER.getManager().getEquipes();
        final ApplicationJO application = this;
        Task<PageClassementEquipes> task = new Task<PageClassementEquipes>() {
            protected PageClassementEquipes call() throws Exception {
                return new PageClassementEquipes(application,set);
            };
        };
        afficherPage(task);
    }

    public void choixSport() throws IOException{
        PageChoixSport choixSport = new PageChoixSport(this);
        stage.getScene().setRoot(choixSport);
        this.historique.add(choixSport);
    }

    private <T extends Page> void afficherPage(Task<T> task, String titre, String header){
        PopUp<ButtonType> popUp = new PopUp<>(PopUpType.PROGRESS, titre, header);
        task.setOnSucceeded((wse)->{
            stage.getScene().setRoot(task.getValue());
            this.historique.add(task.getValue());
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
        if(this.historique.size() > 1){
            this.stage.getScene().setRoot(this.historique.get(this.historique.size()-2));
            this.historique.remove(this.historique.size()-1);
        }
    }
}
