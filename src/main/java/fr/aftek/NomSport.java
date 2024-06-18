package fr.aftek;

public enum NomSport {
    NATATION("Natation 100 brasse", false, 0, 0, 10, 15, 12), 
    ESCRIME_FLEURET("Escrime fleuret", false, 0, 0, 6, 10, 17), 
    ESCRIME_EPEE("Escrime épée", false, 0, 0, 7, 10, 16), 
    ATHLETISME("Athétisme 110 haies", false, 0, 0, 10, 18, 12), 
    ATHLETISME_RELAIS("Athlétisme relais 400m", true, 5, 5, 12, 17, 12),
    NATATION_RELAIS("Natation relais libre", true, 5, 2, 11, 15, 13), 
    HANDBALL("Handball", true, 2, 12, 13, 10, 14), 
    VOLLEY_BALL("Volley-Ball", true, 2, 6, 11, 9, 16);

    private String nom;
    private boolean equipe;
    private int nbEquipes;
    private int nbJoueursParEquipe;
    private float force;
    private float endurance;
    private float agilite;

    /**
     * Constructeur pour initialiser un sport avec ses attributs.
     *
     * @param nom Le nom du sport.
     * @param equipe Indique si le sport est pratiqué en équipe.
     * @param force Le niveau de force requis pour ce sport.
     * @param endurance Le niveau d'endurance requis pour ce sport.
     * @param agilite Le niveau d'agilité requis pour ce sport.
     */
    NomSport(String nom, boolean equipe, int nbEquipes, int nbJoueursParEquipe, float force, float endurance, float agilite) {
        this.nom = nom;
        this.equipe = equipe;
        this.nbEquipes = nbEquipes;
        this.nbJoueursParEquipe = nbJoueursParEquipe;
        this.force = force;
        this.endurance = endurance;
        this.agilite = agilite;
    }

    /**
     * @return Le nom du sport.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Vrai si le sport est pratiqué en équipe, sinon faux.
     */
    public boolean estEquipe() {
        return equipe;
    }

    /**
     * @return nombre d'équipes sur le terrain
     */
    public int getNbEquipes() {
        return nbEquipes;
    }

    /**
     * @return Le nombre de joueurs par équipe sur le terrain.
     */
    public int getNbJoueursParEquipe() {
        return nbJoueursParEquipe;
    }

    /**
     * @return Le niveau de force requis pour ce sport.
     */
    public float getForce() {
        return force;
    }

    /**
     * @return Le niveau d'endurance requis pour ce sport.
     */
    public float getEndurance() {
        return endurance;
    }

    /**
     * @return Le niveau d'agilité requis pour ce sport.
     */
    public float getAgilite() {
        return agilite;
    }

    /**
     * Retourne les statistiques (force, endurance, agilité) pour un sport donné par son nom.
     *
     * @param nom Le nom du sport.
     * @return Un tableau contenant les valeurs de force, endurance et agilité pour le sport.
     */
    public static float[] getStats(String nom){
        for(NomSport ns : NomSport.values()){
            if(ns.getNom().equals(nom)){
                return new float[]{ns.getForce(), ns.getEndurance(), ns.getAgilite()};
            }
        }
        return null;
    }

    /**
     * Retourne l'énumération NomSport correspondant à un nom de sport donné.
     *
     * @param nom Le nom du sport.
     * @return L'énumération NomSport correspondante, ou null si aucun sport ne correspond au nom donné.
     */
    public static NomSport getNomSport(String nom){
        for(NomSport ns : NomSport.values()){
            if(ns.getNom().equals(nom)){
                return ns;
            }
        }
        return null;
    }
}
