package fr.aftek.ihm;

import java.io.IOException;
import java.sql.SQLException;

import fr.aftek.data.ConnexionMySQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class PageConnection extends BorderPane {
    private ConnexionMySQL connexion;
    
    @FXML
    private TextField identifiantTxtField;

    @FXML
    private PasswordField mdpPwrdField;
    
    @FXML
    private Button btnConnection;
    
    public PageConnection(ConnexionMySQL connexion) throws IOException {
        this.connexion = connexion;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageAccueil.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
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