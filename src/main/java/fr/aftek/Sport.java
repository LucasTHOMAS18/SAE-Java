package fr.aftek;

public class Sport {
    protected NomSport nomSport;
    protected float force;
    protected float agilite;
    protected float endurance;

    public Sport(NomSport nomSport, float force, float agilite, float endurance) {
        this.nomSport = nomSport;
        
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
    }
    
    public NomSport getNomSport() { return nomSport; }
    public float getForce() { return force; }
    public float getAgilite() { return agilite; }
    public float getEndurance() { return endurance; }

    public void setForce(int force) { this.force = force; }
    public void setAgilite(int agilite) { this.agilite = agilite; }
    public void setEndurance(int endurance) { this.endurance = endurance; }

    public int calculePoint() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String toString() { return nomSport.toString(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Sport other = (Sport) obj;
        return nomSport == other.nomSport;
    }

    @Override
    public int hashCode() { return nomSport.hashCode(); }
    
}
