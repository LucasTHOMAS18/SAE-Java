package fr.aftek;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.crypto.Data;

import org.junit.Ignore;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.data.DataProvider;


public class TestBDD extends TestCase {
    private static final String NOM_SERVEUR = "localhost";
    private static final String NOM_BASE = "IUTO";
    private static final String USER = "root";
    private static final String PASS = "rootroot";

    private static List<String> noms = new ArrayList<>();
    private static List<String> prenoms = new ArrayList<>();
    private static List<Character> sexes = new ArrayList<>();
    private static List<Integer> forces = new ArrayList<>();
    private static List<Integer> agilites = new ArrayList<>();
    private static List<Integer> endurances = new ArrayList<>();
    private static List<String> nomsPays = new ArrayList<>();
    private static List<String> nomsSports = new ArrayList<>();
    private static Set<Sport> sportsSansDoublons = new HashSet<>();

    private ConnexionMySQL mysql = null;
    private DataProvider data;

    public static Test suite() {
        return new TestSuite(TestBDD.class);
    }

    @org.junit.Test
    public void testSaveBDD() throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");
        this.mysql = new ConnexionMySQL();
        this.mysql.connecter(NOM_SERVEUR, NOM_BASE, USER, PASS);
        chargerBatterieTest();
        creerBDD();
        data.saveSQL(mysql);
        DataProvider temp = new DataProvider();
        temp.loadSQL(mysql);
        assertEquals(data, temp);
    }

    @org.junit.Test
    public void testLoadBDD() throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");
        this.mysql = new ConnexionMySQL();
        this.mysql.connecter(NOM_SERVEUR, NOM_BASE, USER, PASS);
        chargerBatterieTest();
        creerBDD();
        insererDonneesTest();
        DataProvider provider = new DataProvider();
        provider.loadSQL(mysql);
        assertEquals(provider.getManager().getAthletes().size(), 50);
        assertTrue(provider.getManager().getPays().size()<=50);
        assertTrue(provider.getManager().getSports().size()<=8);
    }

    @Ignore
    private void chargerBatterieTest() throws FileNotFoundException{
        System.out.println("Loading data...");
        data = new DataProvider();
        data.loadCSV(getClass().getClassLoader().getResource("donnees.csv").getFile());
        List<Athlete> athletes = data.getManager().getAthletes().stream().collect(Collectors.toList()).subList(0,50);
        Collections.shuffle(athletes);
        noms = athletes.stream().map(Athlete::getNom).collect(Collectors.toList());
        prenoms = athletes.stream().map(Athlete::getPrenom).collect(Collectors.toList());
        sexes = athletes.stream().map(Athlete::getSexe).collect(Collectors.toList());
        forces = athletes.stream().map(Athlete::getForce).collect(Collectors.toList());
        agilites = athletes.stream().map(Athlete::getAgilite).collect(Collectors.toList());
        endurances = athletes.stream().map(Athlete::getEndurance).collect(Collectors.toList());
        nomsPays = athletes.stream().map(Athlete::getPays).map(Pays::getNom).collect(Collectors.toList());
        nomsSports = athletes.stream().map(Athlete::getSport).map(Sport::getNomSport).map(NomSport::getNom).collect(Collectors.toList());
        sportsSansDoublons = athletes.stream().map(Athlete::getSport).collect(Collectors.toSet());
        System.out.println("Data loaded");
    }
    @Ignore
    private void creerBDD() {
        String[] tableDrops = {
            // Suppression des tables existantes pour éviter les conflits
            "DROP TABLE IF EXISTS ParticipeCollectif;",
            "DROP TABLE IF EXISTS Participe;",
            "DROP TABLE IF EXISTS Athlete;",
            "DROP TABLE IF EXISTS Epreuve;",
            "DROP TABLE IF EXISTS Equipe;",
            "DROP TABLE IF EXISTS Sport;",
            "DROP TABLE IF EXISTS Pays;"
        };
    
        String[] tableCreates = {
            "CREATE TABLE Pays (\n" +
            "    nomPays VARCHAR(25) PRIMARY KEY NOT NULL\n" +
            ");",

            "CREATE TABLE Sport (\n" +
            "    nomSport VARCHAR(25) PRIMARY KEY NOT NULL,\n" +
            "    forcesRequis INT,\n" +
            "    agiliteRequis INT,\n" +
            "    enduranceRequis INT,\n" +
            "    individuel BOOLEAN NOT NULL,\n" +
            "    nbEquipes INT,\n" +
            "    nbJoueursParEquipe INT\n" +
            ");",

            "CREATE TABLE Equipe (\n" +
            "    idEquipe INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    nomEquipe VARCHAR(25),\n" +
            "    nomPays VARCHAR(25),\n" +
            "    FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)\n" +
            ");",

            "CREATE TABLE Epreuve (\n" +
            "    idEpreuve INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    nomEpreuve VARCHAR(25),\n" +
            "    sexeEpreuve CHAR(1),\n" +
            "    nomSport VARCHAR(25),\n" +
            "    collective BOOLEAN,\n" +
            "    FOREIGN KEY (nomSport) REFERENCES Sport(nomSport)\n" +
            ");",

            "CREATE TABLE Athlete (\n" +
            "    idAthlete INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
            "    nomAthlete VARCHAR(25),\n" +
            "    prenomAthlete VARCHAR(25),\n" +
            "    sexe CHAR(1),\n" +
            "    forces INT,\n" +
            "    agilite INT,\n" +
            "    endurance INT,\n" +
            "    nomPays VARCHAR(25),\n" +
            "    nomSport VARCHAR(25),\n" +
            "    idEquipe INT,\n" +
            "    Foreign Key (nomSport) REFERENCES Sport(nomSport),\n" +
            "    FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe),\n" +
            "    FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)\n" +
            ");",

            "CREATE TABLE Participe (\n" +
            "    idEpreuve INT AUTO_INCREMENT,\n" +
            "    idAthlete INT,\n" +
            "    PRIMARY KEY (idAthlete, idEpreuve),\n" +
            "    FOREIGN KEY (idAthlete) REFERENCES Athlete(idAthlete),\n" +
            "    FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)\n" +
            ");",

            "CREATE TABLE ParticipeCollectif (\n" +
            "    idEpreuve INT AUTO_INCREMENT,\n" +
            "    idEquipe INT,\n" +
            "    PRIMARY KEY (idEquipe, idEpreuve),\n" +
            "    FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe),\n" +
            "    FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)\n" +
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
    @Ignore
    private void insererDonneesTest() throws SQLException{
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
            throw new SQLException("Erreur de chargement des pays: "+e.getMessage());
        }
        try{
            System.out.println("Inserting Sports...");
            Statement st = mysql.createStatement();
            StringBuilder sb = new StringBuilder();
            List<Sport> sports = new ArrayList<Sport>(sportsSansDoublons);
            sb.append("INSERT INTO Sport(nomSport,forcesRequis,agiliteRequis,enduranceRequis,individuel,nbEquipes,nbJoueursParEquipe) values ");
            for (int i = 0; i < sports.size(); i++) {
                sb.append("('"+sports.get(i)+"',");
                sb.append(sports.get(i).getForce()+",");
                sb.append(sports.get(i).getAgilite()+",");
                sb.append(sports.get(i).getEndurance()+",");
                sb.append((sports.get(i).enEquipe() ? "false" : "true")+",");
                sb.append(sports.get(i).getNomSport().getNbEquipes()+",");
                sb.append(sports.get(i).getNomSport().getNbJoueursParEquipe()+"),");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(';');
            st.execute(sb.toString());
            System.out.println("Sports inserted");
        }catch(SQLException e) {
            throw new SQLException("Erreur de chargement des sports: "+e.getMessage());
        }
        try{
            System.out.println("Inserting Athlete...");
            Statement st = mysql.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO Athlete(nomAthlete, prenomAthlete,sexe,forces,agilite,endurance,nomPays,nomSport,idEquipe) values");
            for (int i = 0; i < 50; i++) {
                sb.append("('"+noms.get(i)+"',");
                sb.append("'"+prenoms.get(i)+"',");
                sb.append("'"+sexes.get(i)+"',");
                sb.append(forces.get(i)+",");
                sb.append(agilites.get(i)+",");
                sb.append(endurances.get(i)+",");
                sb.append("'"+nomsPays.get(i)+"',");
                sb.append("'"+nomsSports.get(i)+"',");
                sb.append("NULL),");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(';');
            st.execute(sb.toString());
            System.out.println("Athlete inserted");
        }catch (SQLException e) {
            throw new SQLException("Erreur de chargement des athletes: "+e.getMessage());
        }
    }
}
