package fr.aftek.ihm;

import java.io.File;
import java.net.URI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImagesProvider {
    public static Image MASCOTTE;
    public static Image LOGO;
    public static void loadImages(URI uri){
        File folder = new File(uri);
        for(File f : folder.listFiles()){
            switch (f.getName()) {
                case "mascotte.png":
                    MASCOTTE = new Image("file:"+f.getPath());
                    break;
                case "logo.png":
                    LOGO = new Image("file:"+f.getPath());
                    break;
                default:
                    break;
            }
        }
    }
}
