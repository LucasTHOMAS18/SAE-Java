package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestEpreuve extends TestCase{
    public TestEpreuve( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestEpreuve.class );
    }

    public void testEpreuve1(){
        /*
         * Epreuve d'athletisme feminin avec les participantes:
         * Marie-José Pérec: Athlète francaise: force: 18, agilite: 17, endurance: 10
         * Kathrine Switzer: Athlète américaine: force: 17, agilite: 19, endurance: 13
         * Nezha Bidouane: Athlète marocaine: force: 15, agilite: 15, endurance; 12
         */
        Athlete marie = new Athlete("Pérec", "Marie-José", 'F', 18f, 17f, 10f, new Pays("France"));
        Athlete kathrine = new Athlete("Switzer", "Kathrine", 'F', 17f, 19f, 13f, new Pays("Etats-Unis"));
        Athlete nezha = new Athlete("Bidouane", "Nezha", 'F', 15f, 15f, 12f, new Pays("Maroc"));
        Sport athletisme = new Sport(NomSport.ATHLETISME, 15, 15, 15);
        Epreuve epreuve = new Epreuve("Athlétisme féminin", 'F', athletisme);
        epreuve.ajouteAthlete(marie);
        epreuve.ajouteAthlete(kathrine);
        epreuve.ajouteAthlete(nezha);
        System.out.println(epreuve);
        epreuve.simuleEpreuve();
        System.out.println(epreuve);
    }
}
