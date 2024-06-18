package fr.aftek;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Iterator;

import org.junit.Rule;
import org.junit.jupiter.api.io.TempDir;
import org.junit.rules.TemporaryFolder;

import fr.aftek.data.DataProvider;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCSV extends TestCase{
    @Rule
    private TemporaryFolder temporaryFolder = new TemporaryFolder();
    public TestCSV( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestCSV.class );

    }

    @org.junit.Test
    public void testLoadCSV() throws IOException{
        DataProvider provider = new DataProvider();
        provider.loadCSV(getClass().getClassLoader().getResource("donnees.csv").getFile());
        assertEquals(provider.getManager().getAthletes().size(), 400);
        assertEquals(provider.getManager().getEquipes().size(), 0); // Aucune equipe n'est créer à partie d'un fichier CSV
        assertEquals(provider.getManager().getSports().size(), 8);
    }

    @org.junit.Test
    public void testSaveCSV(@TempDir Path tempdir) throws IOException{
        Path testSave = tempdir.resolve("testCSV.csv");
        DataProvider provider = new DataProvider();
        provider.loadCSV(getClass().getClassLoader().getResource("donnees.csv").getFile());
        provider.saveCSV(testSave.toAbsolutePath().toString());
        DataProvider provider1 = new DataProvider();
        provider1.loadCSV(testSave.toAbsolutePath().toString());
        assertEquals(provider, provider1);

    }
}