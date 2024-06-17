package fr.aftek.ihm.custom;

import fr.aftek.ihm.providers.BackgroundsProvider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EntreeText extends TextField{
    private Rectangle line;
    public EntreeText(){
        super();
        styling();
    }

    private void styling(){
        line = new Rectangle(this.getMaxWidth(), this.getMaxHeight());
        line.setFill(BackgroundsProvider.YELLOW.getFills().get(0).getFill());
        this.getChildren().add(line);
    }
}
