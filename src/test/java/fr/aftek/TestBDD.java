package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestBDD extends TestCase {
    // Assurez-vous que l'URL inclut le protocole JDBC, le nom d'hôte et le nom de la base de données
    private static final String DB_URL = "jdbc:mysql://servinfo-maria/DBkeskin";
    private static final String USER = "keskin";
    private static final String PASS = "keskin";

    public TestBDD(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestBDD.class);
    }

    public void testAthleteInsertion() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
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

            // Here you can add more specific assertions to check athlete data
            if (nomAthlete == null || nomEquipe == null) {
                result = false;
            }
        }

        return result;
    }
}
