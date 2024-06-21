package fr.aftek.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import fr.aftek.Athlete;
import fr.aftek.Epreuve;
import fr.aftek.Equipe;
import fr.aftek.NomSport;
import fr.aftek.data.ConnexionMySQL;
import fr.aftek.data.DataProvider;
import fr.aftek.ihm.pages.Menu;
import fr.aftek.ihm.pages.Page;
import fr.aftek.ihm.pages.PageAdmin;
import fr.aftek.ihm.pages.PageChoixSport;
import fr.aftek.ihm.pages.PageClassementAthletes;
import fr.aftek.ihm.pages.PageClassementEpreuve;
import fr.aftek.ihm.pages.PageClassementEquipes;
import fr.aftek.ihm.pages.PageClassementResultatEpreuve;
import fr.aftek.ihm.pages.PageConnexion;
import fr.aftek.ihm.pages.PageModifier;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PageModifier.TypeModification;
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
        PROVIDER.loadCSV(getClass().getClassLoader().getResource("donnees.csv").getFile());
        List<Athlete> athletes = PROVIDER.getManager().athletes.stream().collect(Collectors.toList());
        Epreuve ep = PROVIDER.getManager().createEpreuve("Athlètisme", 'F', PROVIDER.getManager().getSport(NomSport.ATHLETISME));
        ep.ajouteAthletes(athletes);
        // Création de la scène
        Page root = new PageConnexion(this);
        this.historique.add(root);
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
        afficherPage(task, "Création du classement des Equipes", "Veuillez patienter...");
    }

    public void choixSport() throws IOException{
        PageChoixSport choixSport = new PageChoixSport(this);
        stage.getScene().setRoot(choixSport);
        this.historique.add(choixSport);
    }

    public void admin() throws IOException {
        PageAdmin admin = new PageAdmin(this);
        stage.getScene().setRoot(admin);
        this.historique.add(admin);
    }

    public void classementEpreuves() {
        final Set<Epreuve> set = ApplicationJO.PROVIDER.getManager().getEpreuves();
        final ApplicationJO application = this;
        Task<PageClassementEpreuve> task = new Task<PageClassementEpreuve>() {
            protected PageClassementEpreuve call() throws Exception {
                return new PageClassementEpreuve(application,set);
            };
        };
        afficherPage(task, "Création du classement des Equipes", "Veuillez patienter...");
    }

    public void afficherResultatsEpreuve(Epreuve e) {
        final ApplicationJO application = this;
        Task<PageClassementResultatEpreuve> task = new Task<PageClassementResultatEpreuve>() {
            protected PageClassementResultatEpreuve call() throws Exception {
                return new PageClassementResultatEpreuve(application,e);
            };
        };
        afficherPage(task, "Création du classement", "Veuillez patienter...");
    }

    public void modifier(TypeModification type){
        final ApplicationJO application = this;
        Task<PageModifier> task = new Task<PageModifier>() {
            protected PageModifier call() throws Exception {
                return new PageModifier(application,type);
            };
        };
        afficherPage(task, "Chargement des données", "Veuillez patienter...");
    }

    public <T extends Page> void afficherPage(Task<T> task, String titre, String header){
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

    public Stage getStage() {
        return stage;
    }

    /**
     * Méthode principale pour lancer l'application.
     * 
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(ApplicationJO.class);
    }

    public void retour() {
        if(this.historique.size() > 1){
            this.stage.getScene().setRoot(this.historique.get(this.historique.size()-2));
            this.historique.remove(this.historique.size()-1);
        }
    }
}
