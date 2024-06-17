package fr.aftek.ihm;

import fr.aftek.ihm.connection.PageConnexion;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ApplicationJO extends Application{
    private Scene scene;
    private BorderPane root;
    @Override
    public void init() throws Exception {
        ImagesProvider.loadImages(getClass().getClassLoader().getResource("images").toURI());
    }

    @Override
    public void start(Stage stage) throws Exception {
        initRoot();
        this.root.setCenter(new PageConnexion());
        scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Jeux IUT'Olympiques");
        stage.show();
    }

    private void initRoot(){
        if(this.root == null){
            this.root = new BorderPane();
        }else{
            this.root.getChildren().clear();
        }
        this.root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.root.setTop(new Titre());
    }

    @Override
    public void stop() throws Exception {
        
    }
    public static void main(String[] args) {
        launch(ApplicationJO.class);
    }
}
