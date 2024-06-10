package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestDataProvider extends TestCase{
    public TestDataProvider( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestDataProvider.class );
    }

    public void testCSV(){
        
    }
}