package fr.aftek.ihm.pages;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
