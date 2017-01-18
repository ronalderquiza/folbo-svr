package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tools.TextUploader;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	CSVtoDB.java
 * Description:	Extracting excel file to database
 * @version		1.0.0
 *
 * @lastreview 
 * 
 */

public class CSVtoDB {
	private final static String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;

	static String host = "jdbc:mysql://localhost/";
	static String database = "dbfolbo";
	static String user = "root";
	static String pass = "";
	
	private static ArrayList<String> record = new ArrayList<String>();
	private static ArrayList<String> company = new ArrayList<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args){
		collectProd();
		String path = "C:\\Users\\LAPTOP\\Desktop\\db2.csv";
		TextUploader tu = new TextUploader(path);
		String content = tu.getText();
		String regex = "[^\n]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			//System.out.println(matcher.group(0).trim());
			getRecord().add(matcher.group(0).trim());
		}
		String[][] recordData = new String[getRecord().size()][12];
		try {
			Class.forName(getDriverClass());
			setCon(DriverManager.getConnection(host+database, user, pass));
			setSt(getCon().createStatement());
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 0; i < getRecord().size(); i++){
			regex = "[^,]+";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(getRecord().get(i));
			int j = 0;
			while(matcher.find()){
				recordData[i][j] = matcher.group(0).trim();
				j++;
			}
			String title = recordData[i][0].replace('\'', '\0');
			int mGenre = Integer.parseInt(recordData[i][1]);
			int sGenre = Integer.parseInt(recordData[i][2]);
			int mtrcb  = Integer.parseInt(recordData[i][3]);
			int sequel = getSequel(recordData[i][4]);
			int origin = getOrigin(recordData[i][5]);
			int month = Integer.parseInt(recordData[i][6]);
			int year = Integer.parseInt(recordData[i][7]);
			double revenue = Double.parseDouble(recordData[i][8]);
			int dir = Integer.parseInt(recordData[i][9]);
			int prod = getProdCompany(recordData[i][10]);
			int dist = getProdCompany(recordData[i][11]);
			try {
				String query = "INSERT INTO `dbfolbo`.`tblmovie` "
						+ "(`movieTitle`, `theme`, `preSequel`, `mainGenre`, `subGenre`, `mtrcbRating`, `sequel`, `origin`, "
						+ "`year`, `month`, `director`, `prodCompany`, `distributor`, `grossRevenue`) "
						+ "VALUES ('" + title + "', 0, 0, " + mGenre + ", " + sGenre + ", " + mtrcb + ", "
						+ sequel + ", " + origin + ", " + year + ", "+ month+ ", " + dir+ ", " + prod + ", "
						+ dist + ", " + revenue + " );";
				System.out.println(query);
				getSt().executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param seq
	 * @return seq
	 */
	public static int getSequel(String seq){
		switch(seq){
		case "Yes": seq = "1"; break;
		case "No": seq = "0"; break;
		default: seq = "0"; break;
		}
		return Integer.parseInt(seq);
	}
	
	/**
	 * @param orig
	 * @return orig
	 */
	public static int getOrigin(String orig){
		switch(orig){
		case "Original": orig = "1"; break;
		case "Crossover": orig = "2"; break;
		case "Adaptation": orig = "3"; break;
		case "Remake": orig = "4"; break;
		default: orig = "1"; break;
		}
		return Integer.parseInt(orig);
	}
	
	/**
	 * @param prodComp
	 * @return i
	 */
	public static int getProdCompany(String prodComp){
		for(int i = 0; i < getCompany().size(); i++){
			if(prodComp.contains(getCompany().get(i))){
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * initialize collectProd
	 */
	public static void collectProd(){
			try {
				Class.forName(getDriverClass());
				setCon(DriverManager.getConnection(host+database, user, pass));
				setSt(getCon().createStatement());
				setRs(getSt().executeQuery("SELECT * FROM `tblprodcompany`"));
				for (; getResultSet().next();) {
					getCompany().add(getResultSet().getString("prodCompany"));
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @return DRIVER_CLASS
	 */
	public static String getDriverClass() {
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
		CSVtoDB.con = con;
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
		CSVtoDB.st = st;
	}

	/**
	 * @return rs
	 */
	public static ResultSet getResultSet() {
		return rs;
	}

	/**
	 * @param rs
	 */
	public static void setRs(ResultSet rs) {
		CSVtoDB.rs = rs;
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
		CSVtoDB.pst = pst;
	}

	/**
	 * @return record
	 */
	public static ArrayList<String> getRecord() {
		return record;
	}

	/**
	 * @param record
	 */
	public static void setRecord(ArrayList<String> record) {
		CSVtoDB.record = record;
	}

	/**
	 * @return company
	 */
	public static ArrayList<String> getCompany() {
		return company;
	}

	/**
	 * @param company
	 */
	public static void setCompany(ArrayList<String> company) {
		CSVtoDB.company = company;
	}
}

