package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SportTest extends TestCase {
    public SportTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    public void testApp() {
        Sport athletisme = new Sport(NomSport.ATHLETISME, 5, 10, 20);
        
        assertEquals(5, athletisme.getForce());
        assertEquals(10, athletisme.getAgilite());
        assertEquals(20, athletisme.getEndurance());
        assertEquals(NomSport.ATHLETISME, athletisme.getNomSport());

        
        SportCollectif handball = new SportCollectif(NomSport.HANDBALL, 5, 10, 20, 2, 11);

        assertEquals(5, handball.getForce());
        assertEquals(10, handball.getAgilite());
        assertEquals(20, handball.getEndurance());
        assertEquals(NomSport.HANDBALL, handball.getNomSport());
        assertEquals(2, handball.getNbEquipes());
        assertEquals(11, handball.getNbJoueursParEquipe());
        assertEquals(22, handball.getNbJoueurs());
    }
}
