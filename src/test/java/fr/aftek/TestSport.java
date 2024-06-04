package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSport extends TestCase {
    public TestSport( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestSport.class );
    }

    public void testSport() {
        Sport athletisme = new Sport(NomSport.ATHLETISME, 5, 10, 20);
        
        assertEquals(5.0f, athletisme.getForce());
        assertEquals(10.0f, athletisme.getAgilite());
        assertEquals(20.0f, athletisme.getEndurance());
        assertEquals(NomSport.ATHLETISME, athletisme.getNomSport());

        
        SportCollectif handball = new SportCollectif(NomSport.HANDBALL, 5.0f, 10.0f, 20.0f, 2, 11);
        assertEquals(5.0f, handball.getForce());
        assertEquals(10.0f, handball.getAgilite());
        assertEquals(20.0f, handball.getEndurance());
        assertEquals(NomSport.HANDBALL, handball.getNomSport());
        assertEquals(2, handball.getNbEquipes());
        assertEquals(11, handball.getNbJoueursParEquipe());
        assertEquals(22, handball.getNbJoueurs());
    }
}
