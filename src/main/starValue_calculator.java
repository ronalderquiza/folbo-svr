package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	starValue_calculator.java
 * Description:	Star Value Calculator of the FoLBO System
 * Version:		1.0.1
 *
 * @lastreview
 */
public class starValue_calculator {
	private HashMap<Integer, Double> artistmovie = new HashMap<Integer, Double>();
	/**
	 * initialize starValue_calculator
	 */
	public starValue_calculator() {
		// TODO Auto-generated constructor stub
		system_manager.getSplashscreen().setLabel("Initializing star value calculator...");
	}

	/**
	 * execute the calculation
	 * @param artists
	 * @param year
	 * @return
	 */
	public double execute(ArrayList<Integer> artists, int year){
		double starValue = 0;
		ArrayList<Double> points = new ArrayList<Double>();		//points of every artist
		ArrayList<Double> starvalues = new ArrayList<Double>();	//star value, ranging 1-10
		collectArtistMovie(year);								//collection of artists' movie count
		double stdev = standarddev();
		for(int i = 0; i < artists.size(); i++){
			points.add(artistmovie.get(artists.get(i)));
			if(points.get(i) != null)
				starvalues.add(percentage((double)points.get(i), stdev));
			else
				starvalues.add(0.0);
		}
		starValue = average(starvalues);
		System.out.println("Star Value: " + starValue);
		return starValue;
	}
	
	/**
	 * collects all the artists' movie count
	 * @param year
	 */
	private void collectArtistMovie(int year){
		database_manager db = system_manager.getDb_mngr();
		double divisor = 100000000;
		String query = "SELECT SUM(`grossRevenue`), `artistID`, "
				+ "COUNT(`artistID`) FROM `tblartistmovie` "
				+ "INNER JOIN `tblmovie` ON `tblartistmovie`.`movieID` "
				+ "= `tblmovie`.`movieID` WHERE "
				+ "`tblmovie`.`year` <= " + (year-1) + " AND "
				+ "`tblmovie`.`year` > " + (year-5) 
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
	 * gets the standard deviation
	 * @return
	 */
	private double standarddev(){
		double max = 0;
		for(int num: artistmovie.keySet()){
			double movies = artistmovie.get(num);
			if(movies > max)
				max = movies;
		}
		return max;
	}
	
	/**
	 * gets the percentage and limit to 10
	 * @param x
	 * @param stdev
	 * @return
	 */
	private double percentage(double x, double stdev){
		double max = 10;
		return (x/stdev) * max;
	}
	
	/**
	 * sorts star value
	 * @param star
	 * @return
	 */
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
	 * average of the half artists' star values 
	 * @param starvalue
	 * @return
	 */
	private double average(ArrayList<Double> starvalue) {
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
}
