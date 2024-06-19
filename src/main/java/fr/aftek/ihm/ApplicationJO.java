package fr.aftek.ihm;

import java.io.IOException;
import java.sql.SQLException;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.ihm.pages.Menu;
import fr.aftek.ihm.pages.PageConnexion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ApplicationJO extends Application{
    private Scene scene;
    private ConnexionMySQL connexion;

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        // Connexion à la base de données
        this.connexion = new ConnexionMySQL();
        // Création de la scène
        BorderPane root = new PageConnexion(this);
        this.scene = new Scene(root, 900, 600);
        this.stage = stage;
        stage.setScene(scene);
        stage.setTitle("Jeux IUT'Olympiques");
        stage.setResizable(false);
        stage.show();
        //new PopUp("Erreur détectée !","Attention mon reuf, je crois que t'as un bug dans ton appli faut que tu le règle","On a détécté un bug dans ton appli faudrait peut-être le réglé quand même c'est important").showAndWait();
    }

    public ConnexionMySQL getConnexion() {
        return connexion;
    }
    
    public void menu() throws IOException, SQLException {
        stage.setScene(new Scene(new Menu(this), 900, 600));

        String role = connexion.getRole();
        if (role == "admin") {
            System.out.println("Il faut afficher le bouton admin");
        }
    }

    public static void main(String[] args) {
        launch(ApplicationJO.class);
    }
}
