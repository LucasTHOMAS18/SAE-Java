package fr.aftek;
import java.util.ArrayList;
import java.util.List;

public class SportCollectif extends Sport {
    private int nbEquipes;
    private int nbJoueursParEquipe;
    private List<Equipe> equipes;
    

    /**
     * Constructeur pour initialiser un sport collectif avec ses attributs.
     *
     * @param nomSport Le nom du sport collectif.
     * @param force La force moyenne nécessaire pour ce sport.
     * @param agilite L'agilité moyenne nécessaire pour ce sport.
     * @param endurance L'endurance moyenne nécessaire pour ce sport.
     * @param nbEquipes Le nombre d'équipes participant à ce sport.
     * @param nbJoueursParEquipe Le nombre de joueurs par équipe.
     */
    public SportCollectif(NomSport nomSport, float force, float agilite, float endurance, int nbEquipes, int nbJoueursParEquipe) {
        super(nomSport, force, agilite, endurance);
        this.nbEquipes = nbEquipes;
        this.nbJoueursParEquipe = nbJoueursParEquipe;
        this.equipes = new ArrayList<>();
    }

    /**
     * @return Le nombre d'équipes participant à ce sport collectif.
     */
    public int getNbEquipes() {
        return nbEquipes;
    }

    /**
     * @return Le nombre de joueurs par équipe.
     */
    public int getNbJoueursParEquipe() {
        return nbJoueursParEquipe;
    }

    /**
     * @return Le nombre total de joueurs participant à ce sport collectif.
     */
    public int getNbJoueurs() {
        return nbEquipes * nbJoueursParEquipe;
    }

    /**
     * @return La liste des équipes participant à ce sport collectif.
     */
    public List<Equipe> getEquipes() {
        return equipes;
    }

    /**
     * Calcule les points pour une équipe en fonction de la moyenne de la force, de l'agilité et de l'endurance des athlètes de l'équipe.
     *
     * @param equipe L'équipe pour laquelle les points doivent être calculés.
     * @return Le nombre de points calculé pour l'équipe.
     */
    public int calculePoint(Equipe equipe) {
        int force = 0;
        int agilite = 0;
        int endurance = 0;
        for(Athlete athlete : equipe.getAthletes()){
            force += athlete.getForce();
            agilite += athlete.getAgilite();
            endurance += athlete.getEndurance();
        }
        force = force / equipe.getAthletes().size();
        agilite = agilite / equipe.getAthletes().size();
        endurance = endurance / equipe.getAthletes().size();
        return super.calculePoint(force, agilite, endurance);
    }
}
