package fr.aftek;
import java.util.ArrayList;
import java.util.List;

public class SportCollectif extends Sport {
    private int nbEquipes;
    private int nbJoueursParEquipe;
    private List<Equipe> equipes;
    
    public SportCollectif(NomSport nomSport, float force, float agilite, float endurance, int nbEquipes, int nbJoueursParEquipe) {
        super(nomSport, force, agilite, endurance);
        this.nbEquipes = nbEquipes;
        this.nbJoueursParEquipe = nbJoueursParEquipe;
        this.equipes = new ArrayList<>();
    }

    public int getNbEquipes() { return nbEquipes; }
    public int getNbJoueursParEquipe() { return nbJoueursParEquipe; }
    public int getNbJoueurs() { return nbEquipes * nbJoueursParEquipe; } 
    
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
