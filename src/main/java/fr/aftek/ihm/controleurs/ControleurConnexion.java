package fr.aftek.ihm.controleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.pages.PopUp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurConnexion {
    private ApplicationJO application;
    private ConnexionMySQL connexion;

    @FXML
    private TextField urlTxtField;

    @FXML
    private TextField identifiantTxtField;

    @FXML
    private PasswordField mdpPwrdField;

    @FXML
    private Button btnConnection;

    public ControleurConnexion(ApplicationJO application) {
        this.application = application;
        this.connexion = application.getConnexion();
    }

    @FXML
    public void seConnecter() throws IOException, SQLException {
        String identifiant = identifiantTxtField.getText();
        String mdp = mdpPwrdField.getText();
        String serveur = urlTxtField.getText();
        String nomBDD = "JO2024"; 

        try {
            connexion.connecter(serveur, nomBDD, identifiant, mdp);
            new PopUp(AlertType.INFORMATION,"Succès !", "La connexion à la base de données a réussi", "La connexion à la base de donnée à réussi").showAndWait();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            if(e.getErrorCode() == 1049){
                Optional<ButtonType> result = new PopUp(AlertType.CONFIRMATION,"Attention !", "La connexion à la base de donnée a réussi.","Cependant, nous avons détecté que votre base de donnée ne contient pas la structure nécessaire au bon fonctionnement de l'application. Voulez-vous créer toute la structure ?").showAndWait();
                result.ifPresent((r) -> {
                    if(result.get() == ButtonType.OK){
                        try {
                            connexion.connecter(serveur, identifiant, mdp);
                            connexion.creerStructure();
                            new PopUp(AlertType.INFORMATION,"Succès!", "La structure à bien été créer !", "La structure essentiel au bon fonctionnement de l'application a été créer dans la base de donnée: JO2024").showAndWait();
                        } catch (SQLException e1) {
                            System.out.println(e1.getMessage());
                            new PopUp(AlertType.ERROR,"Erreur!", "La connexion à la base de données a échouée","La connexion à la base de données a échouée").showAndWait();
                        } catch(IOException e2){
                            System.out.println(e2.getMessage());
                            new PopUp(AlertType.ERROR,"Erreur!", "Le fichier de création n'a pas été trouvé","Le fichier de création n'a pas été trouvé").showAndWait();
                        }
                    }
                });
            }else{
                new PopUp(AlertType.ERROR,"Erreur !", "La connexion à la base de données a échouée","La connexion à la base de données a échouée").showAndWait();
            }
        }

        if (connexion.isConnecte()) {
            System.out.println("Affichage du menu");
            application.menu();
        }
    }

    @FXML
    public void mdpKeyPressed(KeyEvent event) throws IOException, SQLException {
        if (event.getCode().toString().equals("ENTER")) {
            seConnecter();
        }
    }
}
