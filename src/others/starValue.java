package others;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import main.database_manager;

/**
 * @author		Ronald Erquiza
 * Email:		ronalderquiza@gmail.com
 * Filename:	starValue.java
 * Description:	
 * @version		1.0.1
 *
 * @lastreview 
 * 
 */

public class starValue {
	static String host = "jdbc:mysql://localhost/";
	static String database = "dbfolbo";
	static String user = "root";
	static String pass = "";
	static database_manager db = new database_manager(host, database, user, pass);
	static HashMap<Integer, Double> artistmovie = new HashMap<Integer, Double>();
	
	/**
	 * Initialize starValue
	 */
	public starValue() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		for(int year = 2012; year <= 2012; year++){
			artistmovie.clear();
			collectArtistMovie(year);
			System.out.println(artistmovie);
			ArrayList<Integer> movies = moviesToBeUpdated(year);
			//ArrayList<Integer> movies = new ArrayList<Integer>();
			//movies.add(197);
			double stdev = standarddev();
			double mu = mu();
			System.out.println("stdev = " + stdev);
			System.out.println("mu = " + mu);
			for(int i = 0; i < movies.size(); i++){
				System.out.println(movies.get(i) + "\n---------");
				try {
					String query = "SELECT * FROM `tblmovie` INNER JOIN `tblartistmovie` ON"
							+ " `tblmovie`.`movieID` = `tblartistmovie`.`movieID` INNER JOIN"
							+ " `tblartist` ON `tblartist`.`artistID` = `tblartistmovie`.`artistID`"
							+ " WHERE `tblmovie`.`movieID` = " + movies.get(i);
					db.query(query);
					ArrayList<Integer> artists = new ArrayList<Integer>();
					ArrayList<Double> points = new ArrayList<Double>();
					ArrayList<Double> starvalue = new ArrayList<Double>();
					System.out.println("ID\tpoints\tnorm");
					for(int j = 0; db.getRs().next(); j++){
						artists.add(db.getRs().getInt("artistID"));
						points.add(artistmovie.get(artists.get(j)));
						if(points.get(j) != null)
							starvalue.add(percentage((double)points.get(j), stdev));
						else
							starvalue.add(0.0);
						System.out.println(artists.get(j) + "\t"+ points.get(j) + "\t" + starvalue.get(j));
					}
					double value = 0;
					if(artists.size() > 0)
						value = average(starvalue);
					System.out.println("\nAverage: " + value + "\n---------");
					query = "UPDATE `dbfolbo`.`tblmovie` SET `starValue` = " + value + " WHERE `tblmovie`.`movieID` = " + movies.get(i);
					db.update(query, database);
					db.getRs().close();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
	
	private static double average(ArrayList<Double> starvalue) {
		// TODO Auto-generated method stub
		double sum = 0;
		if(starvalue.size() > 1){
			starvalue = sort(starvalue);
			for(int i = 0; i < (starvalue.size()/2); i++){
				sum += starvalue.get(i);
			}
			return sum/(starvalue.size()/2);
		}
		else{
			return starvalue.get(0);
		}
		
	}

	private static ArrayList<Double> sort(ArrayList<Double> star) {
		// TODO Auto-generated method stub
		double[] newValues = new double[star.size()];
		for(int i = 0; i < newValues.length; i++){
			newValues[i] = star.get(i);
		}
		
		for(int i = 0; i < newValues.length; i++){
			for(int j = i; j < newValues.length; j++){
				if(star.get(j) > star.get(i)){
					double temp = newValues[j];
					newValues[j] = newValues[i];
					newValues[i] = temp;
				}
			}
		}
		star.clear();
		for(int i = 0; i < newValues.length; i++){
			star.add(newValues[i]);
		}
		return star;
	}
	
	/**
	 * @param year
	 * @return movies
	 */
	public static ArrayList<Integer> moviesToBeUpdated(int year){
		ArrayList<Integer> movies = new ArrayList<Integer>();
		String query = "SELECT `movieID` FROM `tblmovie` WHERE `year` = " + year;
		try {
			db.query(query);
			for(;db.getRs().next();){
				movies.add(db.getRs().getInt("movieID"));
			}
			db.getRs().close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	/**
	 * @param year
	 */
	public static void collectArtistMovie(int year){
		double divisor = 100000000;
		String query = "SELECT SUM(`grossRevenue`), `artistID`, "
				+ "COUNT(`artistID`) FROM `tblartistmovie` "
				+ "INNER JOIN `tblmovie` ON `tblartistmovie`.`movieID` "
				+ "= `tblmovie`.`movieID` WHERE `tblmovie`.`year` <= " 
				+ (year-1) + " AND `tblmovie`.`year` > " + (year-5) 
				+ " GROUP BY `artistID`";
		try {
			db.query(query);
			for(;db.getRs().next();){
				int artistID = db.getRs().getInt("artistID");
				int count = db.getRs().getInt("COUNT(`artistID`)");
				double gross = db.getRs().getDouble("SUM(`grossRevenue`)");
				artistmovie.put(artistID, (gross/count)/divisor);
			}
			db.getRs().close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return max
	 */
	public static double standarddev(){
		double max = 0;
		for(int num: artistmovie.keySet()){
			double movies = artistmovie.get(num);
			if(movies > max)
				max = movies;
		}
		return max;
	}
	
	/**
	 * @return mu
	 */
	public static double mu(){
		double sum = 0;
		double len = artistmovie.size();
		for(int num: artistmovie.keySet()){
			double movies = artistmovie.get(num);
			sum += movies;
		}
		return sum/len;
	}
	
	/**
	 * @param x
	 * @param stdev
	 * @return percentage
	 */
	public static double percentage(double x, double stdev){
		return (x/stdev) * 10;
	}
}
