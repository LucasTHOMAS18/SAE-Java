package fr.aftek.ihm;

import java.io.IOException;
import fr.aftek.data.ConnexionMySQL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PageConnection extends BorderPane {
    private ConnexionMySQL connexion;

    public PageConnection(ConnexionMySQL connexion) throws IOException {
        this.connexion = connexion;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageAccueil.fxml"));
        loader.setController(new ControleurConnexion(connexion));
        loader.setRoot(this);
        loader.load();
    }
}
