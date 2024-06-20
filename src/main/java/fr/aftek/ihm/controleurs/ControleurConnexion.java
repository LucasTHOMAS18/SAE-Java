package fr.aftek.ihm.controleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
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

    /**
     * Constructeur du contrôleur de connexion.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     */
    public ControleurConnexion(ApplicationJO application) {
        this.application = application;
        this.connexion = application.getConnexion();
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
        String nomBDD = "JO2024"; 

        try {
            connexion.connecter(serveur, nomBDD, identifiant, mdp);
            new PopUp<ButtonType>(PopUpType.INFORMATION,"Succès !", "La connexion à la base de données a réussi", "La connexion à la base de donnée a réussi").showAndWait();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            if(e.getErrorCode() == 1049){
                Optional<ButtonType> result = new PopUp<ButtonType>(PopUpType.CONFIRMATION,"Attention !", "La connexion à la base de donnée a réussi.","Cependant, nous avons détecté que votre base de donnée ne contient pas la structure nécessaire au bon fonctionnement de l'application. Voulez-vous créer toute la structure ?").showAndWait();
                result.ifPresent((r) -> {
                    if(result.get() == ButtonType.OK){
                        try {
                            connexion.connecter(serveur, identifiant, mdp);
                            connexion.creerStructure();
                            new PopUp<ButtonType>(PopUpType.INFORMATION,"Succès!", "La structure à bien été créer !", "La structure essentielle au bon fonctionnement de l'application a été créée dans la base de donnée: JO2024").showAndWait();
                        } catch (SQLException e1) {
                            System.out.println(e1.getMessage());
                            new PopUp<ButtonType>(PopUpType.ERREUR,"Erreur!", "La connexion à la base de données a échouée","La connexion à la base de données a échouée").showAndWait();
                        } catch(IOException e2){
                            System.out.println(e2.getMessage());
                            new PopUp<ButtonType>(PopUpType.ERREUR,"Erreur!", "Le fichier de création n'a pas été trouvé","Le fichier de création n'a pas été trouvé").showAndWait();
                        }
                    }
                });
            } else {
                new PopUp<ButtonType>(PopUpType.ERREUR,"Erreur !", "La connexion à la base de données a échouée","La connexion à la base de données a échouée").showAndWait();
            }
        }

        if (connexion.isConnecte()) {
            System.out.println("Affichage du menu");
            application.menu();
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
