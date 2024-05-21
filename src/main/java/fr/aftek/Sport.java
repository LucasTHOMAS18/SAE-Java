package fr.aftek;

public class Sport {
    protected NomSport nomSport;
    protected int force;
    protected int agilite;
    protected int endurance;

    public Sport(NomSport nomSport, int force, int agilite, int endurance) {
        this.nomSport = nomSport;
        
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
    }
    
    public NomSport getNomSport() { return nomSport; }
    public int getForce() { return force; }
    public int getAgilite() { return agilite; }
    public int getEndurance() { return endurance; }

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
