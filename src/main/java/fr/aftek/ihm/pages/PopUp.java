package fr.aftek.ihm.pages;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe PopUp qui hérite de la classe Alert de JavaFX.
 * Cette classe est utilisée pour afficher des pop-ups personnalisées avec différentes images en fonction du type d'alerte.
 */
public class PopUp<T> extends Dialog<T> {
    
    private String titre; // Titre de l'alerte
    private String header; // En-tête de l'alerte
    private String contenu; // Contenu de l'alerte
    private PopUpType type; // Type de l'alerte
    private TextField tf;

    /**
     * Constructeur de la classe PopUp.
     * 
     * @param type Le type de l'alerte (ERROR, INFORMATION, CONFIRMATION, etc.)
     * @param titre Le titre de l'alerte
     * @param header L'en-tête de l'alerte
     * @param contenu Le contenu de l'alerte
     */
    public PopUp(PopUpType type, String titre, String header, String contenu) {
        super();
        this.titre = titre;
        this.header = header;
        this.contenu = contenu;
        this.type = type;
        this.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        style(); // Applique le style à l'alerte
    }

    /**
     * Constructeur de la classe PopUp sans contenu.
     * 
     * @param type Le type de l'alerte (ERROR, INFORMATION, CONFIRMATION, etc.)
     * @param titre Le titre de l'alerte
     * @param header L'en-tête de l'alerte
     */
    public PopUp(PopUpType type, String titre, String header){
        this(type, titre, header, "");
    }

    /**
     * Applique le style à l'alerte en fonction de son type.
     * Ajoute une image spécifique pour chaque type d'alerte.
     */
    private void style() {
        ImageView mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte_gentille.png").toString()));
        // Sélectionne l'image en fonction du type de l'alerte
        if (this.getType() == PopUpType.ERREUR) {
            mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte_triste.png").toString()));
        } else if (this.getType() == PopUpType.CONFIRMATION || this.getType() == PopUpType.PROGRESS) {
            mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte_nerd.png").toString()));
        }

        // Définit la taille de l'image
        mascotte.setFitHeight(64);
        mascotte.setFitWidth(64);

        // Définit les propriétés de l'alerte
        this.setTitle(titre);
        this.setHeaderText(header);
        this.setGraphic(mascotte); // Ajoute l'image à l'alerte
        

        if(this.getType() == PopUpType.PROGRESS){
            this.getDialogPane().getButtonTypes().remove(0);
            this.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            ProgressBar pb = new ProgressBar();
            this.getDialogPane().setContent(pb);
        }else if(this.getType() == PopUpType.CONFIRMATION){
            this.getDialogPane().getButtonTypes().remove(0);
            this.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
            this.setContentText(contenu);
        }else if(this.getType() == PopUpType.DEMANDER){
            tf = new TextField();
            this.getDialogPane().setContent(tf);
        } else {
            this.setContentText(contenu);
        }

        // Définit le style de l'alerte
        this.getDialogPane().setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 50px;");
        Button ok = (Button) this.getDialogPane().lookupButton(ButtonType.OK);
        Button cancel = (Button) this.getDialogPane().lookupButton(ButtonType.CANCEL);
        if (ok != null) {
            ok.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 50px;");
        }
        if (cancel != null) {
            cancel.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 50px;");
        }
    }

    public PopUpType getType() {
        return type;
    }

    public TextField getTf() {
        return tf;
    }

    public enum PopUpType{
        INFORMATION,
        CONFIRMATION,
        ERREUR,
        PROGRESS,
        DEMANDER
    }
}
