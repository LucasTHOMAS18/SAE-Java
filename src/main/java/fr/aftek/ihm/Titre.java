package fr.aftek.ihm;

import fr.aftek.ihm.providers.ImagesProvider;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Titre extends HBox{
    private ImageView logo;
    private Label label;
    public Titre(String text){
        super();
        this.logo = new ImageView(ImagesProvider.LOGO);
        this.logo.setFitHeight(ImagesProvider.LOGO.getHeight()*0.5);
        this.logo.setFitWidth(ImagesProvider.LOGO.getWidth()*0.5);
        this.label = new Label(text);
        this.label.setFont(Font.font("Sans serif", FontWeight.BOLD, 30));
        this.getChildren().addAll(logo,label);
    }
    public Titre(){
        this("Bienvenue aux jeux IUT'Olympiques");
    }
    public void setText(String text){
        this.label.setText(text);
    }
    public String getText(){
        return label.getText();
    }
}
