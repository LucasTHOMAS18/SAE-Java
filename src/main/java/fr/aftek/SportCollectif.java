package fr.aftek;

public class SportCollectif extends Sport {
    private int nbEquipes;
    private int nbJoueursParEquipe;
    
    public SportCollectif(NomSport nomSport, int force, int agilite, int endurance, int nbEquipes, int nbJoueursParEquipe) {
        super(nomSport, force, agilite, endurance);
        this.nbEquipes = nbEquipes;
        this.nbJoueursParEquipe = nbJoueursParEquipe;
    }

    public int getNbEquipes() { return nbEquipes; }
    public int getNbJoueursParEquipe() { return nbJoueursParEquipe; }
    public int getNbJoueurs() { return nbEquipes * nbJoueursParEquipe; }
}
