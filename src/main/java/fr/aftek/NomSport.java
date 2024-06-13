package fr.aftek;

public enum NomSport {
    NATATION("Natation 100 brasse", false, 10, 15, 12), 
    ESCRIME_FLEURET("Escrime fleuret", false, 6, 10, 17), 
    ESCRIME_EPEE("Escrime épée", false, 7, 10, 16), 
    ATHLETISME("Athétisme 110 haies", false, 10, 18, 12), 
    ATHLETISME_RELAIS("Athlétisme relais 400m", true, 12, 17, 12),
    NATATION_RELAIS("Natation relais libre", true, 11, 15, 13), 
    HANDBALL("Handball", true, 13, 10, 14), 
    VOLLEY_BALL("Volley-Ball", true, 11, 9, 16);

    private String nom;
    private boolean equipe;
    private float force;
    private float endurance;
    private float agilite;

    NomSport(String nom, boolean equipe, float force, float endurance, float agilite) {
        this.nom = nom;
        this.equipe = equipe;
        this.force = force;
        this.endurance = endurance;
        this.agilite = agilite;
    }

    public String getNom() {
        return nom;
    }

    public boolean estEquipe(){
        return equipe;
    }
    public float getForce() {
        return force;
    }
    public float getEndurance() {
        return endurance;
    }
    public float getAgilite() {
        return agilite;
    }
    public static float[] getStats(String nom){
        for(NomSport ns : NomSport.values()){
            if(ns.getNom().equals(nom)){
                return new float[]{ns.getForce(), ns.getEndurance(), ns.getAgilite()};
            }
        }
        return null;
    }
    public static NomSport getNomSport(String nom){
        for(NomSport ns : NomSport.values()){
            if(ns.getNom().equals(nom)){
                return ns;
            }
        }
        return null;
    }
}
