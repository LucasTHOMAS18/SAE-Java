package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class TestBDD extends TestCase {
    private static final String NOM_SERVEUR = "servinfo-maria";
    private static final String NOM_BASE = "DBcochet";
    private static final String USER = "cochet";
    private static final String PASS = "cochet";

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

    public void creerBDD() {
        String[] tableDrops = {
            "DROP TABLE IF EXISTS ParticipeCollectif;",
            "DROP TABLE IF EXISTS Participe;",
            "DROP TABLE IF EXISTS Athlete;",
            "DROP TABLE IF EXISTS Epreuve;",
            "DROP TABLE IF EXISTS Equipe;",
            "DROP TABLE IF EXISTS Sport;",
            "DROP TABLE IF EXISTS Pays;"
        };
    
        String[] tableCreates = {
            "CREATE TABLE Pays (" +
            "nomPays VARCHAR(25) PRIMARY KEY NOT NULL" +
            ");",
    
            "CREATE TABLE Sport (" +
            "idSport INT PRIMARY KEY NOT NULL," +
            "nomSport VARCHAR(25)," +
            "forcesRequis INT," +
            "agiliteRequis INT," +
            "enduranceRequis INT," +
            "collectif BOOLEAN" +
            ");",
    
            "CREATE TABLE Equipe (" +
            "idEquipe INT PRIMARY KEY NOT NULL," +
            "nomEquipe VARCHAR(25)," +
            "nomPays VARCHAR(25)," +
            "FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)" +
            ");",
    
            "CREATE TABLE Epreuve (" +
            "idEpreuve INT PRIMARY KEY," +
            "nomEpreuve VARCHAR(25)," +
            "sexeEpreuve CHAR(1)," +
            "idSport INT," +
            "collective BOOLEAN," +
            "FOREIGN KEY (idSport) REFERENCES Sport(idSport)" +
            ");",
    
            "CREATE TABLE Athlete (" +
            "idAthlete INT PRIMARY KEY NOT NULL," +
            "nomAthlete VARCHAR(25)," +
            "sexe CHAR(1)," +
            "forces INT," +
            "agilite INT," +
            "endurance INT," +
            "nomPays VARCHAR(25)," +
            "idEquipe INT," +
            "FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe)," +
            "FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)" +
            ");",
    
            "CREATE TABLE Participe (" +
            "idAthlete INT," +
            "idEpreuve INT," +
            "PRIMARY KEY (idAthlete, idEpreuve)," +
            "FOREIGN KEY (idAthlete) REFERENCES Athlete(idAthlete)," +
            "FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)" +
            ");",
    
            "CREATE TABLE ParticipeCollectif (" +
            "idEquipe INT," +
            "idEpreuve INT," +
            "PRIMARY KEY (idEquipe, idEpreuve)," +
            "FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe)," +
            "FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)" +
            ");"
        };
    
        try (Connection conn = this.mysql; Statement stmt = conn.createStatement()) {
            for (String drop : tableDrops) {
                stmt.executeUpdate(drop);
            }
            for (String create : tableCreates) {
                stmt.executeUpdate(create);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erreur de connection à la BDD");
        }
    }
    
}
