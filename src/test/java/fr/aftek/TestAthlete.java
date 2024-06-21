package fr.aftek;

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Batterie de tests
 */
public class TestAthlete 
    extends TestCase
{
    /**
     * Création des tests
     *
     * @param testName nom du test
     */
    public TestAthlete( String testName )
    {
        super( testName );
    }

    /**
     * @return TestSuite testé
     */
    public static Test suite()
    {
        return new TestSuite( TestAthlete.class );
    }

    /**
     * Tests de la class pays
     */
    public void testPays(){
        Pays france = new Pays("France");
        assertEquals("France", france.getNom());
        Pays angleterre = new Pays("Angleterre");
        assertEquals("Angleterre", angleterre.getNom());
        assertNotEquals(france, angleterre);
        Pays france2 = new Pays("France");
        assertEquals(france, france2);
        france.ajouteAthlete(new Athlete("Cochet", "Raphaël", 'f', 20, 2, 5, france, null)); // TODO ajouter sport
        france.ajouteAthlete(new Athlete("Cochet", "Raphaël", 'f', 20, 2, 5, france, null)); // TODO ajouter sport
        assertEquals(france, france2);
    }

    /**
     * Tests de la class Athlete
     */
    public void testAthlete()
    {
        Athlete athlete = new Athlete("Michel", "Jean", 'M', new Pays("France"), null); // TODO ajouter sport
        assertEquals("Jean", athlete.getPrenom());
        assertEquals("Michel", athlete.getNom());
        assertEquals('M', athlete.getSexe());
        assertEquals("France", athlete.getPays().getNom());
        assertTrue(0 <= athlete.getForce() && athlete.getForce() <= 20);
        assertTrue(0 <= athlete.getAgilite() && athlete.getAgilite() <= 20);
        assertTrue(0 <= athlete.getEndurance() && athlete.getEndurance() <= 20);
    }

    /**
     * Tests de la class Equipe
     */
    public void testEquipe(){
        Pays france = new Pays("France");
        Athlete athlete = new Athlete("Michel", "Jean", 'M', france, null); // TODO ajouter sport
        Athlete raphou = new Athlete("Cochet", "Raphaël", 'F', 20, 2, 5, france, null); // TODO ajouter sport
        Equipe equipefr = new Equipe("Equipe de france", null, france);
        assertEquals("Equipe de france", equipefr.getNom());
        assertEquals(france, equipefr.getPays());
        assertEquals(0, equipefr.getAthletes().size());
        equipefr.ajouteAthlete(athlete);
        equipefr.ajouteAthlete(raphou);
        assertEquals(2, equipefr.getAthletes().size());
        assertEquals(Arrays.asList(athlete, raphou), equipefr.getAthletes());
    }
}
