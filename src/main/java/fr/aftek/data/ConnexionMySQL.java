package fr.aftek.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ConnexionMySQL {
	
	private Connection mysql=null;
	private boolean connecte=false;

	private Map<String, Integer> roles = Map.of(
		"journaliste", 1,
		"organisateur", 2,
		"admin", 3
	);

	public ConnexionMySQL() throws ClassNotFoundException{
        Class.forName("org.mariadb.jdbc.Driver");
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		// si tout c'est bien passé la connexion n'est plus nulle
		String url = "jdbc:mysql://" + nomServeur + "/" + nomBase;
        this.mysql = DriverManager.getConnection(url, nomLogin, motDePasse);
		this.connecte=this.mysql!=null;
	}

	public void connecter(String nomServeur, String nomLogin, String motDePasse) throws SQLException{
		// si tout c'est bien passé la connexion n'est plus nulle
        String url = "jdbc:mysql://" + nomServeur;
        this.mysql = DriverManager.getConnection(url, nomLogin, motDePasse);
        this.connecte=this.mysql!=null;
	}

	public void executerFichier(String nomFichier) throws SQLException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(nomFichier).getFile()));
          
          // String Builder to build the query line by line.
		  Statement statement = createStatement();
		  StringBuilder query = new StringBuilder();
        String line;
        
        while((line = br.readLine()) != null) {
          
            if(line.trim().startsWith("-- ")) {
                continue;
            }
          
          // Append the line into the query string and add a space after that
            query.append(line).append(" ");
         
            if(line.trim().endsWith(";")) {
              // Execute the Query
                statement.execute(query.toString().trim());
              // Empty the Query string to add new query from the file
                query = new StringBuilder();
            }
        }
		br.close();
	}

	public void creerStructure() throws SQLException, IOException {
		executerFichier("BD.sql");
	}

	public void close() throws SQLException {
		// fermer la connexion
		this.mysql.close();
		this.connecte=false;
	}

    public boolean isConnecte() { return this.connecte; }
	
    public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}

	public String getRole() throws SQLException {
		Statement st = this.createStatement();
		ResultSet grants = st.executeQuery("SHOW GRANTS");

		String meilleurRole = "journaliste";
		while (grants.next()) {
			String grant = grants.getString(1);
			for (String role : this.roles.keySet()) {
				if (grant.contains(role)) {
					if (this.roles.get(role) > this.roles.get(meilleurRole)) {
						meilleurRole = role;
					}
				}
			}
		}

		return meilleurRole;
	}
}
