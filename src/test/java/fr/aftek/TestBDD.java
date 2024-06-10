package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class TestBDD extends TestCase {
    private static final String NOM_SERVEUR = "servinfo-maria";
    private static final String NOM_BASE = "DBkeskin";
    private static final String USER = "keskin";
    private static final String PASS = "keskin";

    private Connection mysql = null;
    private boolean connecte = false;

    public TestBDD(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestBDD.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Class.forName("org.mariadb.jdbc.Driver");
        connecter(NOM_SERVEUR, NOM_BASE, USER, PASS);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (mysql != null && isConnecte()) {
            close();
        }
    }

    public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
        String url = "jdbc:mysql://" + nomServeur + "/" + nomBase;
        this.mysql = DriverManager.getConnection(url, nomLogin, motDePasse);
        this.connecte = this.mysql != null;
    }

    public void close() throws SQLException {
        this.mysql.close();
        this.connecte = false;
    }

    public boolean isConnecte() {
        return this.connecte;
    }

    public void testAthleteInsertion() {
        try (Connection conn = this.mysql) {
            assertTrue(verifyAthletes(conn));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database connection or query failed.");
        }
    }

    private boolean verifyAthletes(Connection conn) throws SQLException {
        String query = "SELECT A.id_Athlete, A.nom_Athlete, A.id_Equipe, E.nom_Equipe " +
                       "FROM ATHLETE A " +
                       "JOIN EQUIPE E ON A.id_Equipe = E.id_Equipe";

        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        boolean result = true;
        while (rs.next()) {
            int idAthlete = rs.getInt("id_Athlete");
            String nomAthlete = rs.getString("nom_Athlete");
            int idEquipe = rs.getInt("id_Equipe");
            String nomEquipe = rs.getString("nom_Equipe");

            System.out.println("Athlete ID: " + idAthlete + ", Nom: " + nomAthlete + 
                               ", Équipe ID: " + idEquipe + ", Nom de l'Équipe: " + nomEquipe);

            if (nomAthlete == null || nomEquipe == null) {
                result = false;
            }
        }

        return result;
    }
}
