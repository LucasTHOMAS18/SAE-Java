package fr.aftek;

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
        dataProvider.loadCSV("/home/iut45/Etudiants/o22302103/Documents/donnees.csv");
        System.out.println(dataProvider.getManager().getAthletes());
        System.out.println(dataProvider.getManager().getEquipes());
        System.out.println(dataProvider.getManager().getSports());
    }
}