package fr.aftek.ihm;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.ihm.pages.PageConnection;
import fr.aftek.ihm.pages.PopUp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ApplicationJO extends Application{
    private Scene scene;
    private ConnexionMySQL connexion;


    @Override
    public void start(Stage stage) throws Exception {
        // Connexion à la base de données
        this.connexion = new ConnexionMySQL();
        // Création de la scène
        BorderPane root = new PageConnection(connexion);
        this.scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Jeux IUT'Olympiques");
        stage.setResizable(false);
        stage.show();
        //new PopUp("Erreur détectée !","Attention mon reuf, je crois que t'as un bug dans ton appli faut que tu le règle","On a détécté un bug dans ton appli faudrait peut-être le réglé quand même c'est important").showAndWait();
    }
    
    public static void main(String[] args) {
        launch(ApplicationJO.class);
    }
}
