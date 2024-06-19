package fr.aftek.ihm;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class FlagProvider {
    private static final Map<String, String> ALL_FLAGS = Map.of(
        "USA","US",
        "Chine","CN",
        "Japon","JP",
        "Kenya","KE",
        "France","FR",
        "Maroc","MA",
        "Allemagne","DE",
        "Australie","AU",
        "Brésil","BR",
        "Turquie","TR"
    );

    private static final Map<String, Image> ALL_FLAGS_IMAGES = new HashMap<>();

    private static String getCode(String country) {
        return ALL_FLAGS.get(country);
    }

    public static Image getFlag(String country){
        if(ALL_FLAGS_IMAGES.containsKey(country)){
            return ALL_FLAGS_IMAGES.get(country);
        }
        String countryCode = getCode(country);
        Image image = new Image("https://flagsapi.com/"+countryCode+"/flat/64.png");
        ALL_FLAGS_IMAGES.put(country, image);
        return image;
    }
}
