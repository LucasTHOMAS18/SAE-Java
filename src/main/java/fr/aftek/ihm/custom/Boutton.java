package fr.aftek.ihm.custom;

import fr.aftek.ihm.providers.BackgroundsProvider;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Boutton extends Button{
    public Boutton(String text, Node node){
        super(text,node);
        styling();
    }
    public Boutton(String text){
        this(text, null);
    }
    public Boutton(){
        this(null,null);
    }
    
    private void styling(){
        this.setBackground(BackgroundsProvider.YELLOW);
        this.fontProperty().set(Font.font("Sans serif", FontWeight.SEMI_BOLD, 15));
        this.setPadding(new Insets(10));
        this.setOnMousePressed((e) -> mousePressed());
        this.setOnMouseReleased((e) -> mouseReleased());
    }

    private void mousePressed(){
        this.setBackground(BackgroundsProvider.DARK_YELLOW);
    }
    private void mouseReleased(){
        this.setBackground(BackgroundsProvider.YELLOW);
    }
}
