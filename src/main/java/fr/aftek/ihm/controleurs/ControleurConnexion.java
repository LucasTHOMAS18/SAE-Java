package fr.aftek.ihm.controleurs;

import java.sql.SQLException;

import fr.aftek.data.ConnexionMySQL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurConnexion {
    private ConnexionMySQL connexion;

    @FXML
    private TextField urlTxtField;

    @FXML
    private TextField identifiantTxtField;

    @FXML
    private PasswordField mdpPwrdField;

    @FXML
    private Button btnConnection;

    public ControleurConnexion(ConnexionMySQL connexion) {
        this.connexion = connexion;
    }

    @FXML
    public void seConnecter() {
        String identifiant = identifiantTxtField.getText();
        String mdp = mdpPwrdField.getText();
        String serveur = "servinfo-maria";
        String nomBDD = "DB" + identifiant; 

        try {
            connexion.connecter(serveur, nomBDD, identifiant, mdp);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données");
            System.out.println(e.getMessage());
        }

        if (connexion.isConnecte()) {
            System.out.println("Connexion réussie");
        } else {
            System.out.println("Connexion échouée");
        }
    }

    @FXML
    public void mdpKeyPressed(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            seConnecter();
        }
    }
}
