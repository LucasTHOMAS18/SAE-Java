package fr.aftek.ihm.providers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class BackgroundsProvider {
    public static Background MAIN;
    public static Background PAGES;
    public static Background YELLOW;
    public static Background DARK_YELLOW;
    public static void loadBackgrounds(URI uri){
        File file = new File(uri);
        Gson gson = new GsonBuilder().create();
        try {
            BackgroundObject[] backgrounds = gson.fromJson(new FileReader(file), BackgroundObject[].class);
            for(BackgroundObject b : backgrounds){
                Background back = new Background(new BackgroundFill(Color.web(b.getHex()), CornerRadii.EMPTY, Insets.EMPTY));
                switch (b.getName()) {
                    case "main":
                        MAIN = back;
                        break;
                    case "pages":
                        PAGES = back;
                        break;
                    case "yellow":
                        YELLOW = back;
                        break;
                    case "dark_yellow":
                        DARK_YELLOW = back;
                        break;
                    default:
                        break;
                }
            }
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class BackgroundObject{
        private String name;
        private String hex;
        private Integer[] rgb;
        public BackgroundObject(String name, String hex, Integer[] rgb){
            this.name = name;
            this.hex = hex;
            this.rgb = rgb;
        }             
        public String getHex() {
            return hex;
        }
        public String getName() {
            return name;
        }
        public Integer[] getRgb() {
            return rgb;
        }
        public void setHex(String hex) {
            this.hex = hex;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setRgb(Integer[] rgb) {
            this.rgb = rgb;
        }   
    }
}
