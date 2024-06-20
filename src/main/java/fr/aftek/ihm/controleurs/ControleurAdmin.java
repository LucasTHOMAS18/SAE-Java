package fr.aftek.ihm.controleurs;

import java.io.File;
import java.util.Optional;

import fr.aftek.data.DataProvider;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.pages.PageClassementEquipes;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;

public class ControleurAdmin extends Controleur{
    @FXML
    private Button btnChargerCSV;
    @FXML
    private Button btnShowUsers;
    @FXML
    private Button btnExportData;
    @FXML
    private RadioButton btnAjouterEpreuve;
    @FXML
    private RadioButton btnAjouterAthlete;
    @FXML
    private RadioButton btnAjouterPays;
    @FXML
    private Button btnAjouterData;
    
    @FXML
    private RadioButton btnModifierEpreuve;
    @FXML
    private RadioButton btnModifierAthlete;
    @FXML
    private RadioButton btnModifierPays;
    @FXML
    private Button btnModifierData;

    public ControleurAdmin(ApplicationJO appli) {
        this.application = appli;
    }

    @FXML
    private void chargerCSV() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT","*.txt"));
        File file = chooser.showOpenDialog(application.getStage());
        if(file!= null) {
            final DataProvider provider = ApplicationJO.PROVIDER;
            Task<Void> task = new Task<Void>() {
                protected Void call() throws Exception {
                    provider.loadCSV(file.getAbsolutePath());
                    return null;
                };
            };
            PopUp<ButtonType> popUp = new PopUp<>(PopUpType.PROGRESS, "Chargement", "Chargement des données...");
            task.setOnSucceeded((wse)->{
                popUp.close();
                Platform.runLater(() -> {
                new PopUp<>(PopUpType.INFORMATION, "Succès !" , "Le chargement des données à été effectué !").showAndWait();
                });
            });
            task.setOnFailed((wse)->{
                popUp.close();
                wse.getSource().getException().printStackTrace();
            });
            Thread th = new Thread(task,"Progress");
            th.start();
            Optional<ButtonType> res = popUp.showAndWait();
            res.ifPresent((e)-> {
                if(e == ButtonType.CANCEL){
                    th.interrupt();
                }
            });
        }
    }

    @FXML
    private void showUsers() {
    }

    @FXML
    private void exportData() {
    }

    @FXML
    private void ajouterEpreuve() {
    }

    @FXML
    private void ajouterAthlete() {
    }

    @FXML
    private void ajouterPays() {
    }

    @FXML
    private void ajouterData() {
    }

    @FXML
    private void modifierEpreuve() {
    }

    @FXML
    private void modifierAthlete() {
    }

    @FXML
    private void modifierPays() {
    }

    @FXML
    private void modifierData() {
    }
}
