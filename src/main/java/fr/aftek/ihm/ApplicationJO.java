package fr.aftek.ihm;

import fr.aftek.ihm.providers.BackgroundsProvider;
import fr.aftek.ihm.providers.ImagesProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ApplicationJO extends Application{
    private Scene scene;
    private BorderPane root;

    @Override
    public void init() throws Exception {
        ImagesProvider.loadImages(getClass().getClassLoader().getResource("images").toURI());
        BackgroundsProvider.loadBackgrounds(getClass().getClassLoader().getResource("backgrounds.json").toURI());
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Création d'une instance de FXMLLoader
        FXMLLoader loader = new FXMLLoader (getClass().getResource("/PageAdmin.fxml"));
        
        // Définir ce contrôleur comme celui utilisé par le fichier FXML
        loader.setController(this);
        
        // Chargement du fichier FXML
        BorderPane root = loader.load();

        // Création de la scène
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Jeux IUT'Olympiques");
        stage.setResizable(false);
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
