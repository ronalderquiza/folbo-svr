package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * @author Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	database_manager.java
 * Description:	Manages the Database Activity of the System
 * @version 1.0.3
 * @lastreview 20161203
 * Ron, Kat, Ran
 */
public class database_manager {
    /**
     * Driver for the Database
     */
	private final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    /**
     * Connection of the Database
     */
    private Connection connection;

    /**
     * Statement from the Database
     */
    private Statement statement;

    /**
     * Result Set from the Database
     */
    private ResultSet resultSet;

    /**
     * Prepared Statement from the Database
     */
    private PreparedStatement preparedStatement;

    /**
     * Username of the Database
     */
    private String user;

    /**
     * Password of the Database
     */
    private String pass;

    /**
     * Host of the Database
     */
    private String host;

    /**
     * Database Name
     */
    private String database;
	
	/**
     * Instantiation of the Database Manager
     *
     * @param host     Host Name
     * @param database Database Name
     * @param user     Username
     * @param pass     Password
     */
    public database_manager(String host, String database, String user, String pass) {
        system_manager.getSplashscreen().setLabel("Initializing database manager...");
        this.setUser(user);
        this.setPass(pass);
        this.setHost(host);
        this.setDatabase(database);
        system_manager.getSplashscreen().setLabel("Checking database connection...");
        checkConnection();
    }

    /**
     * Instantiation of the Database Manager if No Database yet
     *
     * @param host Host Name
     * @param user Username
     * @param pass Password
     */
    public database_manager(String host, String user, String pass) {
        system_manager.getSplashscreen().setLabel("Initializing database manager...");
        this.setUser(user);
        this.setPass(pass);
        this.setHost(host);
        system_manager.getSplashscreen().setLabel("Checking database connection...");
        checkConnection();
    }

    /**
     * SQL Query
     *
     * @param query Query
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void query(String query) throws ClassNotFoundException, SQLException {
        Class.forName(getDRIVER_CLASS());
        setConnection(DriverManager.getConnection(getHost() + getDatabase(), getUser(), getPass()));
        setStatement(getConnection().createStatement());
        setResultSet(getStatement().executeQuery(query));
    }

    /**
     * SQL Update Query
     *
     * @param query    Query
     * @param database Database Name
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void update(String query, String database) throws ClassNotFoundException, SQLException {
        Class.forName(getDRIVER_CLASS());
        setConnection(DriverManager.getConnection(getHost() + database, getUser(), getPass()));
        setStatement(getConnection().createStatement());
        getStatement().executeUpdate(query);
    }

    /**
     * SQL Update Query if no database yet
     *
     * @param query Query
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void update(String query) throws ClassNotFoundException, SQLException {
        Class.forName(getDRIVER_CLASS());
        setConnection(DriverManager.getConnection(getHost(), getUser(), getPass()));
        setStatement(getConnection().createStatement());
        getStatement().executeUpdate(query);
    }

    /**
     * Checking of Database Connection
     */
    public void checkConnection() {
        try {
            Class.forName(getDRIVER_CLASS());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Driver Class");
            System.exit(0);
        }

        try {
            setConnection(DriverManager.getConnection(getHost(), getUser(), getPass())); //connection establishment
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Database Connection: "
                    + "Check your MySQL Connector", null, JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Getting Result Set
     *
     * @return Result Set
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * Setting Result Set
     *
     * @param resultSet Result Set
     */
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    /**
     * Getting the Driver Class
     *
     * @return Driver Class
     */
    public String getDRIVER_CLASS() {
        return DRIVER_CLASS;
    }

    /**
     * Getting Connection
     *
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Setting Connection
     *
     * @param connection Connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Getting Statement
     *
     * @return Statement
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * Setting Statement
     *
     * @param statement Statement
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * Getting Prepared Statement
     *
     * @return Prepared Statement
     */
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    /**
     * Setting Prepared Statement
     *
     * @param preparedStatement Prepared Statement
     */
    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    /**
     * Getting username
     *
     * @return username
     */
    public String getUser() {
        return user;
    }

    /**
     * Setting username
     *
     * @param user username
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Getting password
     *
     * @return password
     */
    public String getPass() {
        return pass;
    }

    /**
     * Setting password
     *
     * @param pass password
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Getting Host name
     *
     * @return Host name
     */
    public String getHost() {
        return host;
    }

    /**
     * Setting Host name
     *
     * @param host Host name
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Getting Database Name
     *
     * @return Database Name
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Setting Database Name
     *
     * @param database Database Name
     */
    public void setDatabase(String database) {
        this.database = database;
    }
}
