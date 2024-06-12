package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.data.DataProvider;


public class TestBDD extends TestCase {
    private static final String NOM_SERVEUR = "servinfo-maria";
    private static final String NOM_BASE = "DBbeaujouan";
    private static final String USER = "beaujouan";
    private static final String PASS = "beaujouan";

    private static List<String> noms = new ArrayList<>();
    private static List<String> prenoms = new ArrayList<>();
    private static List<Character> sexes = new ArrayList<>();
    private static List<Integer> forces = new ArrayList<>();
    private static List<Integer> agilites = new ArrayList<>();
    private static List<Integer> endurances = new ArrayList<>();
    private static List<String> nomsPays = new ArrayList<>();

    private ConnexionMySQL mysql = null;

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
        this.mysql = new ConnexionMySQL();
        this.mysql.connecter(NOM_SERVEUR, NOM_BASE, USER, PASS);
        chargerBatterieTest();
        creerBDD();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (mysql != null && this.mysql.isConnecte()) {
            this.mysql.close();
        }
    }

    @org.junit.Test
    public void testSaveBDD() throws SQLException{
        
    }

    @org.junit.Test
    public void testLoadBDD() throws SQLException{
        insererDonneesTest();
        DataProvider provider = new DataProvider();
        provider.loadSQL(mysql);
        assertEquals(provider.getManager().getAthletes().size(), 50);
        assertTrue(provider.getManager().getPays().size()<=50);
        assertTrue(provider.getManager().getSports().size()<=8);
    }

    private void chargerBatterieTest(){
        try {
            System.out.println("Loading data...");
            DataProvider temp = new DataProvider();
            temp.loadCSV("../donnees.csv");
            List<Athlete> athletes = temp.getManager().getAthletes().stream().collect(Collectors.toList());
            Collections.shuffle(athletes);
            noms = athletes.subList(0, 50).stream().map(Athlete::getNom).collect(Collectors.toList());
            prenoms = athletes.subList(0, 50).stream().map(Athlete::getPrenom).collect(Collectors.toList());
            sexes = athletes.subList(0, 50).stream().map(Athlete::getSexe).collect(Collectors.toList());
            forces = athletes.subList(0, 50).stream().map(Athlete::getForce).collect(Collectors.toList());
            agilites = athletes.subList(0, 50).stream().map(Athlete::getAgilite).collect(Collectors.toList());
            endurances = athletes.subList(0, 50).stream().map(Athlete::getEndurance).collect(Collectors.toList());
            nomsPays = athletes.subList(0, 50).stream().map(Athlete::getPays).map(Pays::getNom).collect(Collectors.toList());
            System.out.println("Data loaded");
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException");
        }
    }

    private void creerBDD() {
        String[] tableDrops = {
            // Suppression des tables existantes pour éviter les conflits
            "DROP TABLE IF EXISTS ParticipeCollectif;",
            "DROP TABLE IF EXISTS Participe;",
            "DROP TABLE IF EXISTS Athlete;",
            "DROP TABLE IF EXISTS Epreuve;",
            "DROP TABLE IF EXISTS Equipe;",
            "DROP TABLE IF EXISTS Sport;",
            "DROP TABLE IF EXISTS Pays;",
        };
    
        String[] tableCreates = {
            // Création de la table Pays
            "CREATE TABLE Pays (" +
            "    nomPays VARCHAR(25) PRIMARY KEY NOT NULL" +
            ");",

            // Création de la table Sport
            "CREATE TABLE Sport (" +
            "    idSport INT PRIMARY KEY NOT NULL," +
            "    nomSport VARCHAR(25)," +
            "    forcesRequis INT," +
            "    agiliteRequis INT," +
            "    enduranceRequis INT," +
            "    collectif BOOLEAN" +
            ");",

            // Création de la table Equipe
            "CREATE TABLE Equipe (" +
            "    idEquipe INT PRIMARY KEY NOT NULL," +
            "    nomEquipe VARCHAR(25)," +
            "    nomPays VARCHAR(25)," +
            "    FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)" +
            ");",

            // Création de la table Epreuve
            "CREATE TABLE Epreuve (" +
            "    idEpreuve INT PRIMARY KEY," +
            "    nomEpreuve VARCHAR(25)," +
            "    sexeEpreuve CHAR(1)," +
            "    idSport INT," +
            "    collective BOOLEAN," +
            "    FOREIGN KEY (idSport) REFERENCES Sport(idSport)" +
            ");",

            // Création de la table Athlete
            "CREATE TABLE Athlete (" +
            "    idAthlete INT PRIMARY KEY NOT NULL," +
            "    nomAthlete VARCHAR(25)," +
            "    prenomAthlete VARCHAR(25)," +
            "    sexe CHAR(1)," +
            "    forces INT," +
            "    agilite INT," +
            "    endurance INT," +
            "    nomPays VARCHAR(25)," +
            "    idEquipe INT," +
            "    FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe)," +
            "    FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)" +
            ");",

            // Création de la table Participe
            "CREATE TABLE Participe (" +
            "    idAthlete INT," +
            "    idEpreuve INT," +
            "    PRIMARY KEY (idAthlete, idEpreuve)," +
            "    FOREIGN KEY (idAthlete) REFERENCES Athlete(idAthlete)," +
            "    FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)" +
            ");",

            // Création de la table ParticipeCollectif
            "CREATE TABLE ParticipeCollectif (" +
            "    idEquipe INT," +
            "    idEpreuve INT," +
            "    PRIMARY KEY (idEquipe, idEpreuve)," +
            "    FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe)," +
            "    FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)" +
            ");"
        };
    
        try (Statement stmt = this.mysql.createStatement()) {
            System.out.println("Dropping tables...");
            for (String drop : tableDrops) {
                stmt.executeUpdate(drop);
            }
            System.out.println("Tables dropped");
            System.out.println("Creating tables...");
            for (String create : tableCreates) {
                stmt.executeUpdate(create);
            }
            System.out.println("Tables created");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erreur de connection à la BDD");
        }
    }
    
    private void insererDonneesTest(){
        try{
            System.out.println("Inserting Pays...");
            Statement st = mysql.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO Pays(nomPays) VALUES ");
            Set<String> nomPaysDejaInserer = new HashSet<String>();
            for (int i = 0; i < 50; i++) {
                if(!nomPaysDejaInserer.contains(nomsPays.get(i))){
                    sb.append("('"+nomsPays.get(i)+"'),");
                    nomPaysDejaInserer.add(nomsPays.get(i));
                }
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(';');
            st.execute(sb.toString());
            System.out.println("Pays inserted");
        }catch (SQLException e) {
            fail("Erreur insertion des pays: "+e.getMessage());
        }
        try{
            System.out.println("Inserting Athlete...");
            Statement st = mysql.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO Athlete(idAthlete,nomAthlete, prenomAthlete,sexe,forces,agilite,endurance,nomPays,idEquipe) values");
            for (int i = 0; i < 50; i++) {
                sb.append("("+i+",");
                sb.append("'"+noms.get(i)+"',");
                sb.append("'"+prenoms.get(i)+"',");
                sb.append("'"+sexes.get(i)+"',");
                sb.append(forces.get(i)+",");
                sb.append(agilites.get(i)+",");
                sb.append(endurances.get(i)+",");
                sb.append("'"+nomsPays.get(i)+"',");
                sb.append("NULL),");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(';');
            st.execute(sb.toString());
            System.out.println("Athlete inserted");
        }catch (SQLException e) {
            fail("Erreur insertion des athletes: "+e.getMessage());
        }
    }
}
