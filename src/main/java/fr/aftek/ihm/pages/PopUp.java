package fr.aftek.ihm.pages;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe PopUp qui hérite de la classe Alert de JavaFX.
 * Cette classe est utilisée pour afficher des pop-ups personnalisées avec différentes images en fonction du type d'alerte.
 */
public class PopUp extends Alert {
    
    private String titre; // Titre de l'alerte
    private String header; // En-tête de l'alerte
    private String contenu; // Contenu de l'alerte

    /**
     * Constructeur de la classe PopUp.
     * 
     * @param type Le type de l'alerte (ERROR, INFORMATION, CONFIRMATION, etc.)
     * @param titre Le titre de l'alerte
     * @param header L'en-tête de l'alerte
     * @param contenu Le contenu de l'alerte
     */
    public PopUp(AlertType type, String titre, String header, String contenu) {
        super(type);
        this.titre = titre;
        this.header = header;
        this.contenu = contenu;
        style(); // Applique le style à l'alerte
    }

    /**
     * Constructeur de la classe PopUp sans contenu.
     * 
     * @param type Le type de l'alerte (ERROR, INFORMATION, CONFIRMATION, etc.)
     * @param titre Le titre de l'alerte
     * @param header L'en-tête de l'alerte
     */
    public PopUp(AlertType type, String titre, String header){
        this(type, titre, header, "");
    }

    /**
     * Applique le style à l'alerte en fonction de son type.
     * Ajoute une image spécifique pour chaque type d'alerte.
     */
    private void style() {
        ImageView mascotte = null;
        // Sélectionne l'image en fonction du type de l'alerte
        if (this.getAlertType() == AlertType.ERROR) {
            mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte_triste.png").toString()));
        } else if (this.getAlertType() == AlertType.INFORMATION) {
            mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte_gentille.png").toString()));
        } else if (this.getAlertType() == AlertType.CONFIRMATION) {
            mascotte = new ImageView(new Image(getClass().getClassLoader().getResource("images/mascotte_nerd.png").toString()));
        }

        // Définit la taille de l'image
        mascotte.setFitHeight(150);
        mascotte.setFitWidth(150);

        // Définit les propriétés de l'alerte
        this.setTitle(titre);
        this.setHeaderText(header);
        this.setContentText(contenu);
        this.setGraphic(mascotte); // Ajoute l'image à l'alerte
    }
}
