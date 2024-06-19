package fr.aftek.ihm.pages;

import java.awt.Button;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PopUp extends Alert{
    private String titre;
    private String header;
    private String contenu;
    public PopUp(AlertType type, String titre, String header, String contenu) {
        super(type);
        this.titre = titre;
        this.header = header;
        this.contenu = contenu;
        style();
    }
    public PopUp(AlertType type, String titre, String header){
        this(type, titre,header,"");
    }

    private void style(){
        ImageView mascotte = null;
        if(this.getAlertType() == AlertType.ERROR){
            mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte_triste.png").toString()));
        }else if(this.getAlertType() == AlertType.INFORMATION){
            mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte gentille.png").toString()));
        }else if(this.getAlertType() == AlertType.CONFIRMATION){
            mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte nerd.png").toString()));
        }
        mascotte.setFitHeight(150);
        mascotte.setFitWidth(150);
        this.setTitle(titre);
        this.setHeaderText(header);
        this.setContentText(contenu);
        this.setGraphic(mascotte);
    }
}
