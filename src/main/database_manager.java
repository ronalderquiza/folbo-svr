package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	database_manager.java
 * Description:	Manages the Database Activity of the System
 * Version:		1.0.3
 *
 * @lastreview 20161203
 * Ron, Kat, Ran
 */
public class database_manager  {
	private final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;
	private String user;
	private String pass;
	private String host;
	private String database;
	
	/**
	 * @param host
	 * @param database
	 * @param user
	 * @param pass
	 * instantiation of the manager
	 */
	public database_manager(String host, String database, String user, String pass){
		system_manager.getSplashscreen().setLabel("Initializing database manager...");
		this.setUser(user);
		this.setPass(pass);
		this.setHost(host);
		this.setDatabase(database);
		system_manager.getSplashscreen().setLabel("Checking database connection...");
		checkConnection();
	}
	
	/**
	 * 
	 * @param host
	 * @param user
	 * @param pass
	 * instantiation of the manager if no database yet
	 * 
	 */
	public database_manager(String host, String user, String pass){
		system_manager.getSplashscreen().setLabel("Initializing database manager...");
		this.setUser(user);
		this.setPass(pass);
		this.setHost(host);
		system_manager.getSplashscreen().setLabel("Checking database connection...");
		checkConnection();
	}
	
	/**
	 * @param query
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * SQL query
	 * 
	 */
	public void query(String query) throws ClassNotFoundException, SQLException{
		Class.forName(getDRIVER_CLASS());
		setCon(DriverManager.getConnection(getHost()+getDatabase(), getUser(), getPass()));
		setSt(getCon().createStatement());
		setRs(getSt().executeQuery(query));
	}
	
	/**
	 * @param query
	 * @param database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * update query
	 * 
	 */
	public void update(String query, String database) throws ClassNotFoundException, SQLException{
		Class.forName(getDRIVER_CLASS());
		setCon(DriverManager.getConnection(getHost()+database, getUser(), getPass()));
		setSt(getCon().createStatement());
		getSt().executeUpdate(query);
	}

	/**
	 * @param query
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * update query if no database yet
	 * 
	 */
	public void update(String query) throws ClassNotFoundException, SQLException{
		Class.forName(getDRIVER_CLASS());
		setCon(DriverManager.getConnection(getHost(), getUser(), getPass()));
		setSt(getCon().createStatement());
		getSt().executeUpdate(query);
	}
	
	/**
	 * checking of database connection
	 */
	public void checkConnection(){
		try {
			Class.forName(getDRIVER_CLASS());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error Driver Class");
			System.exit(0);
		}

		try {
			setCon(DriverManager.getConnection(getHost(), getUser(), getPass())); //connection establishment
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error Database Connection: "
					+ "Check your MySQL Connector", null, JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	/**
	 * @return resultset
	 */
	public ResultSet getRs() {
		return rs;
	}

	/**
	 * @param rs
	 */
	public static void setRs(ResultSet rs) {
		database_manager.rs = rs;
	}

	/**
	 * @return driver_class
	 */
	public String getDRIVER_CLASS() {
		return DRIVER_CLASS;
	}

	/**
	 * @return con
	 */
	public static Connection getCon() {
		return con;
	}

	/**
	 * @param con
	 */
	public static void setCon(Connection con) {
		database_manager.con = con;
	}

	/**
	 * @return st
	 */
	public static Statement getSt() {
		return st;
	}

	/**
	 * @param st
	 */
	public static void setSt(Statement st) {
		database_manager.st = st;
	}

	/**
	 * @return pst
	 */
	public static PreparedStatement getPst() {
		return pst;
	}

	/**
	 * @param pst
	 */
	public static void setPst(PreparedStatement pst) {
		database_manager.pst = pst;
	}

	/**
	 * @return user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @param database
	 */
	public void setDatabase(String database) {
		this.database = database;
	}
}
