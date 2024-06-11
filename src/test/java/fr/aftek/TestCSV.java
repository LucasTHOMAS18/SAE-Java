package fr.aftek;

import java.io.FileNotFoundException;

import fr.aftek.data.DataProvider;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCSV extends TestCase{
    public TestCSV( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestCSV.class );
    }

    public void testCSV(){
        DataProvider dataProvider = new DataProvider();
        try {
            dataProvider.loadCSV("../donnees.csv");
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException");
        }
        assertEquals(dataProvider.getManager().getAthletes().size(), 400);
        assertEquals(dataProvider.getManager().getEquipes().size(), 0); // Aucune equipe n'est créer à partie d'un fichier CSV
        assertEquals(dataProvider.getManager().getSports().size(), 8);
    }
}