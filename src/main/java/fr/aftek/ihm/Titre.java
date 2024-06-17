package fr.aftek.ihm;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Titre extends HBox{
    private ImageView logo;
    private Label label;
    public Titre(String text){
        super();
        this.logo = new ImageView(ImagesProvider.LOGO);
        this.label = new Label(text);
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
