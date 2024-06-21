package fr.aftek.ihm.controleurs;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.aftek.Athlete;
import fr.aftek.Pays;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.FlagProvider;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ControleurClassementPays extends Controleur {
    @FXML
    private TableView<PaysLigne> table;
    @FXML
    private TableColumn<PaysLigne, ImageView> pays;
    @FXML
    private TableColumn<PaysLigne, Integer> or;
    @FXML
    private TableColumn<PaysLigne, Integer> argent;
    @FXML
    private TableColumn<PaysLigne, Integer> bronze;
    @FXML
    private TableColumn<PaysLigne, Integer> total;

    public ControleurClassementPays(ApplicationJO appli) {
        this.application = appli;
    }

    public void init(Collection<Pays> liste) {
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        pays.setComparator((o1, o2) -> o1.getAccessibleText().compareTo(o2.getAccessibleText()));
        or.setCellValueFactory(new PropertyValueFactory<>("or"));
        argent.setCellValueFactory(new PropertyValueFactory<>("argent"));
        bronze.setCellValueFactory(new PropertyValueFactory<>("bronze"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));

        Map<String, int[]> medalCounts = new HashMap<>();
        System.out.println(liste);
        for (Pays p : liste) {
            System.out.println(p);
            String countryName = p.getNom();
            int[] medals = medalCounts.getOrDefault(countryName, new int[3]);
            
            for (Athlete athlete : p.getAthletes()) {
                int[] athleteMedals = ApplicationJO.PROVIDER.getManager().getNbMedailles(athlete.getNom(), athlete.getPrenom());
                medals[0] += athleteMedals[0]; // or
                medals[1] += athleteMedals[1]; // argent
                medals[2] += athleteMedals[2]; // bronze
            }
            
            medalCounts.put(countryName, medals);
        }

        for (Map.Entry<String, int[]> entry : medalCounts.entrySet()) {
            String countryName = entry.getKey();
            int[] medals = entry.getValue();
            table.getItems().add(new PaysLigne(countryName, medals[0], medals[1], medals[2]));
        }
    }

    public class PaysLigne {
        private ImageView pays;
        private int or;
        private int argent;
        private int bronze;
        private int total;

        public PaysLigne(String nomPays, int or, int argent, int bronze) {
            this.pays = new ImageView(FlagProvider.getFlag(nomPays));
            this.pays.setAccessibleHelp(nomPays);
            this.pays.setAccessibleText(nomPays);
            Tooltip.install(this.pays, new Tooltip(nomPays));
            this.or = or;
            this.argent = argent;
            this.bronze = bronze;
            this.total = or + argent + bronze;
        }

        public int getOr() {
            return or;
        }

        public int getArgent() {
            return argent;
        }

        public int getBronze() {
            return bronze;
        }

        public int getTotal() {
            return total;
        }

        public ImageView getPays() {
            return pays;
        }
    }
}
