package fr.aftek.ihm.connection;

import fr.aftek.ihm.custom.Boutton;
import fr.aftek.ihm.custom.EntreeText;
import fr.aftek.ihm.providers.BackgroundsProvider;
import fr.aftek.ihm.providers.ImagesProvider;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PageConnexion extends BorderPane{
    private EntreeText identifiantTF;
    private PasswordField passwordPF;
    public PageConnexion(){
        super();
        BorderPane page = initPage();
        this.setLeft(page);
        BorderPane.setMargin(page, new Insets(20));
        ImageView mascotte = new ImageView(ImagesProvider.MASCOTTE);
        mascotte.setFitHeight(ImagesProvider.MASCOTTE.getHeight()*0.5);
        mascotte.setFitWidth(ImagesProvider.MASCOTTE.getWidth()*0.5);
        this.setRight(mascotte);
    }

    public BorderPane initPage(){
        BorderPane pane = new BorderPane();
        VBox page = new VBox(10);
        pane.setPadding(new Insets(20));
        pane.setBackground(BackgroundsProvider.PAGES);

        Label titre = new Label("Merci de vous connecter: ");
        titre.setFont(Font.font("Sans serif", FontWeight.SEMI_BOLD, 20));
        Label identifiant = new Label("Identifiant");
        identifiantTF = new EntreeText();
        Label password = new Label("Identifiant");
        passwordPF = new PasswordField();
        page.getChildren().addAll(
            identifiant,
            identifiantTF,
            password,
            passwordPF
        );
        Boutton connexion = new Boutton("Se connecter");
        pane.setTop(titre);
        pane.setCenter(page);
        pane.setBottom(connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER_RIGHT);
        return pane;
    }
}
