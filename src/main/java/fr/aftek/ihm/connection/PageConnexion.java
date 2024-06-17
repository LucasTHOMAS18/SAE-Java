package fr.aftek.ihm.connection;

import fr.aftek.ihm.ImagesProvider;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PageConnexion extends BorderPane{
    private TextField identifiantTF;
    private PasswordField passwordPF;
    public PageConnexion(){
        super();
        VBox page = initPage();
        this.setLeft(page);
        ImageView mascotte = new ImageView(ImagesProvider.MASCOTTE);
        
        this.setRight(mascotte);
    }

    public VBox initPage(){
        VBox page = new VBox();
        Label identifiant = new Label("Identifiant");
        identifiantTF = new TextField();
        Label password = new Label("Identifiant");
        passwordPF = new PasswordField();
        page.getChildren().addAll(
            identifiant,
            identifiantTF,
            password,
            passwordPF
        );
        return page;
    }
}
