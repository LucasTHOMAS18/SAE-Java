package fr.aftek.ihm.pages;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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

public class PopUp extends Stage{
    private String titre;
    private String header;
    private String contenu;
    private VBox root;
    public PopUp(String titre, String header, String contenu) {
        super();
        this.titre = titre;
        this.header = header;
        this.contenu = contenu;
        style();
    }
    public PopUp(String titre, String header){
        this(titre,header,"");
    }

    private void style(){
        this.root = new VBox(10);
        Label head = new Label(this.header);
        head.setWrapText(true);
        head.setBackground(new Background(new BackgroundFill(Color.web("FFCF54"),CornerRadii.EMPTY,Insets.EMPTY)));
        head.setFont(Font.font("Sans serif",FontWeight.BOLD, 30));
        head.setMaxWidth(Double.MAX_VALUE);
        head.setPadding(new Insets(10));

        Label cont = new Label(this.contenu);
        cont.setWrapText(true);
        cont.setMaxWidth(Double.MAX_VALUE);
        cont.setFont(Font.font("Sans serif", 20));
        cont.setPadding(new Insets(10));

        this.root.getChildren().addAll(head,cont);

        this.setTitle(this.titre);
        this.setScene(new Scene(root, 600, 400));
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.initModality(Modality.APPLICATION_MODAL);
    }

    @Override
    public void showAndWait() {
        this.centerOnScreen();
        this.sizeToScene();
        super.showAndWait();
    }
}
