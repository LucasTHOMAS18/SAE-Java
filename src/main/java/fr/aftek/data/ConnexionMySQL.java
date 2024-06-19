package fr.aftek.data;

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

	public void close() throws SQLException {
		// fermer la connexion
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
