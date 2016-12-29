package tools;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import main.database_manager;
import main.system_manager;
import tools.TextUploader;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	DBInstaller.java
 * Description:	For creating databases.
 * Version:		
 *
 * @lastreview 
 * 
 */
public class DBInstaller {
	static String host = "jdbc:mysql://localhost/";
	static String database = "dbfolbo";
	static String user = "root";
	static String pass = "";
	private static database_manager db = new database_manager(host, user, pass);
	private static Scanner s;
	private static String directory;

	/**
	 * initialize DBInstaller
	 * 
	 */
	public DBInstaller(){
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose Database folder");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			System.out.println("No Selection ");
		}

		if (chooser.getSelectedFile() == null) {
			System.exit(0);
		}
		setDirectory(chooser.getSelectedFile().toString());
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		DBInstaller installer = new DBInstaller();
		installer.installDB();
		JOptionPane.showMessageDialog(null, "The FoLBO Database has been installed");
		System.exit(0);
	}

	/**
	 * initialize installDB
	 */
	public void installDB() {
		String[] files = { "tblartist.sql", "tblartistmovie.sql", "tbldirector.sql", "tblgender.sql", "tblgenre.sql", 
				"tblkeywords.sql", "tblmonth.sql", "tblmovie.sql", "tblmtrcbrating.sql", "tblorigin.sql", "tblprodcompany.sql",
				"tbltheme.sql" };
		
		try {
			createDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < files.length; i++) {
			system_manager.getSplashscreen().setLabel("Importing " + files[i] + "...");
			TextUploader tu = new TextUploader(getDirectory() + "\\" + files[i]);
			String text = tu.getText();
			try {
				executeScript(text);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		system_manager.getSplashscreen().setLabel("Done database installation.");
	}

	/**
	 * @param text
	 * @throws SQLException
	 */
	public static void executeScript(String text) throws SQLException {
		s = new Scanner(text);
		s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

		try {
			while (s.hasNext()) {
				String line = s.next().trim();
				if (!line.isEmpty()) {
					getDb().update(line, database);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void createDB() throws SQLException, ClassNotFoundException {
		if (databaseExists(database)) {
			int dialogButton = JOptionPane.showConfirmDialog(null,
					"FoLBO Database is already installed. " + "Do you want to replace it?", "Database Message",
					JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION) {
				system_manager.getSplashscreen().setLabel("Replacing " + database + "...");
				getDb().update("DROP DATABASE " + database);
				getDb().update("CREATE DATABASE IF NOT EXISTS " + database);
			} else {
				System.exit(0);
			}
		} else {
			system_manager.getSplashscreen().setLabel("Creating " + database + "...");
			getDb().update("CREATE DATABASE IF NOT EXISTS " + database);
		}
	}

	/**
	 * @param dbname
	 * @return true, false
	 */
	@SuppressWarnings("static-access")
	public static boolean databaseExists(String dbname) {
		// iterate each catalog in the ResultSet
		try {

			getDb().setCon(DriverManager.getConnection(host, user, pass));
			ResultSet rs = getDb().getCon().getMetaData().getCatalogs();
			while (rs.next()) {
				// Get the database name, which is at position 1
				String databaseName = rs.getString(1);
				if (dbname.equals(databaseName)) {
					return true;
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @return db
	 */
	public static database_manager getDb() {
		return db;
	}

	/**
	 * @param db
	 */
	public static void setDb(database_manager db) {
		DBInstaller.db = db;
	}

	/**
	 * @return directory
	 */
	public static String getDirectory() {
		return directory;
	}

	/**
	 * @param directory
	 */
	public static void setDirectory(String directory) {
		DBInstaller.directory = directory;
	}

}