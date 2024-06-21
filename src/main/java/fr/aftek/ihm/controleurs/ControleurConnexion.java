package fr.aftek.ihm.controleurs;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Contrôleur pour la page de connexion.
 * Gère les interactions de l'utilisateur pour se connecter à la base de données.
 */
public class ControleurConnexion extends Controleur {
    private ConnexionMySQL connexion;

    @FXML
    private TextField urlTxtField;

    @FXML
    private TextField identifiantTxtField;

    @FXML
    private PasswordField mdpPwrdField;

    @FXML
    private Button btnConnection;

    private String nomBDD = "JO2024";

    /**
     * Constructeur du contrôleur de connexion.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     */
    
    public ControleurConnexion(ApplicationJO application) {
        this.application = application;
        this.connexion = application.getConnexion();
    }
    @SuppressWarnings("deprecation")
    public void init(){
        try{
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(new FileReader(new File(getClass().getClassLoader().getResource("credentials.json").toURI()))).getAsJsonObject();
            identifiantTxtField.setText(json.get("id").getAsString());
            mdpPwrdField.setText(json.get("pass").getAsString());
            urlTxtField.setText(json.get("host").getAsString());
            this.nomBDD = json.get("bd").getAsString();
        }catch(Exception e){e.printStackTrace();}
    }

    /**
     * Méthode appelée lors de la tentative de connexion.
     * Essaie de se connecter à la base de données et gère les erreurs éventuelles.
     * 
     * @throws IOException Si une erreur d'entrée/sortie survient.
     * @throws SQLException Si une erreur SQL survient.
     */
    @FXML
    public void seConnecter() throws IOException, SQLException {
        String identifiant = identifiantTxtField.getText();
        String mdp = mdpPwrdField.getText();
        String serveur = urlTxtField.getText();

        try {
            connexion.connecter(serveur, nomBDD, identifiant, mdp);
            chargerDonnees();
            new PopUp<>(PopUpType.INFORMATION, "Succès !", "Le chargement des données a réussi").showAndWait();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            new PopUp<ButtonType>(PopUpType.ERREUR,"Erreur !", "La connexion à la base de données a échouée","La connexion à la base de données a échouée.\n"+e.getMessage()).showAndWait();
        }

        if (connexion.isConnecte()) {
            System.out.println("Affichage du menu");
            application.menu();
        }
    }

    private void chargerDonnees(){
        try{
            ApplicationJO.PROVIDER.loadSQL(connexion);
        }catch (SQLException e){
            e.printStackTrace();
            new PopUp<ButtonType>(PopUpType.ERREUR,"Erreur !", "Le chargement des données a échouée","Le chargement des données a échouée.\n"+e.getMessage()).showAndWait();
        }
    }

    /**
     * Méthode appelée lors de la pression d'une touche dans le champ de mot de passe.
     * Lance la tentative de connexion si la touche "ENTER" est pressée.
     * 
     * @param event L'événement de pression de touche.
     * @throws IOException Si une erreur d'entrée/sortie survient.
     * @throws SQLException Si une erreur SQL survient.
     */
    @FXML
    public void mdpKeyPressed(KeyEvent event) throws IOException, SQLException {
        if (event.getCode().toString().equals("ENTER")) {
            seConnecter();
        }
    }
}
