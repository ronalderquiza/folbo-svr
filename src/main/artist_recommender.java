package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import objects.MovieInfo;
import tools.DBQueries;
/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	artist_recommender.java
 * Description:	Recommending artists
 * @version		2.4.1
 *
 * @lastreview 20161203
 * Ron, Kat, Ran
 */
public class artist_recommender {
	private MovieInfo movieInfo;
	private ArrayList<Integer>[] artist;
	private ArrayList<Integer> topMale; 
	private ArrayList<Integer> topFemale;
	private ArrayList<Integer> allTop;
	private final int MALE = 1;
	private final int FEMALE = 2;
	private final int MALE_INDEX = 0;
	private final int FEMALE_INDEX = 1;
	private int[] finalTop;
	private HashMap<Integer, Double> artistmovie = new HashMap<Integer, Double>();
	private HashMap<Integer, String> allArtist = new HashMap<Integer, String>();

	/**
	 * initialize artist recommender
	 */
	@SuppressWarnings("unchecked")
	public artist_recommender() {
		// TODO Auto-generated constructor stub
		system_manager.getSplashscreen().setLabel("Initializing Artists Recommender...");
		collectAllArtist();
		setArtist(new ArrayList[2]);
		for(int i = 0; i < getArtist().length; i++){
			getArtist()[i] = new ArrayList<Integer>();
		}
		setTopMale(new ArrayList<Integer>());
		setTopFemale(new ArrayList<Integer>());
		setAllTop(new ArrayList<Integer>());
	}
	/**
	 * 
	 * @param movieInfo
	 * @param gender 
	 * i is the index of an array
	 * 
	 */
	public void recommendArtist(MovieInfo movieInfo, int gender) {
		int year = system_manager.getCurrYear();
		collectArtistMovie(year);	//collects all artists' movie count
		this.setMovieInfo(movieInfo);
		collectArtist(gender);		//collecting of artists from the database
		getTopArtist(gender);		//getting the top artists
		
		if(gender == MALE){
			for(int i = 0; i < getTopMale().size(); i++){
				getAllTop().add(getTopMale().get(i));		//adding the top male artist to over all top artists
			}
		}
		if(gender == FEMALE){
			for(int i = 0; i < getTopFemale().size(); i++){
				getAllTop().add(getTopFemale().get(i));		//adding the top female artist to over all top artists
			}
		}
	}
	
	/**
	 * setting all top artists to final array
	 * i is the index for rows
	 * j is the index for columns
	 * 
	 */
	public void setAll(){
		setFinalTop(new int[getAllTop().size()]);
		for(int i = 0; i < getAllTop().size(); i++){
			getFinalTop()[i] = getAllTop().get(i);
		}
	}
	
	/**
	 * 
	 * @param gender 
	 * collecting of artists from the database
	 * 
	 */
	public void collectArtist(int gender) {
		getArtist()[gender - 1] = new ArrayList<Integer>();
		DBQueries dbq = new DBQueries();
		int index = 0;
		while(getArtist()[gender - 1].isEmpty() && index < dbq.getArtistDataQueries(gender).length){
			try {
				database_manager db_mngr = system_manager.getDb_mngr();
				String query = dbq.getArtistDataQueries(gender)[index];
				System.out.println(query);
				db_mngr.query(query);
				for (;db_mngr.getRs().next();) {
					int id = db_mngr.getRs().getInt("artistID");
					getArtist()[gender - 1].add(id);
					System.out.println(">" + allArtist.get(id) + "-" + id + "-" + artistmovie.get(id));
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			index++;
		}
		
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
			System.out.println(query);
			db.query(query);
			for(;db.getRs().next();){
				int artistID = db.getRs().getInt("artistID");
				int count = db.getRs().getInt("COUNT(`artistID`)");
				double gross = db.getRs().getDouble("SUM(`grossRevenue`)");
				artistmovie.put(artistID, (gross/count)/divisor);
				System.out.println(artistID + "---->" + (gross/count)/divisor);
			}
			db.getRs().close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param gender
	 * i is the index of an array
	 * info is the artist's information
	 * curr is the current highest frequency
	 * currInfo is the info of the current highest frequency
	 * num is the number of artist;
	 * getting the top artists
	 * 
	 */
	public void getTopArtist(int gender){
		gender = gender - 1;
		final int max = 5;
		HashMap<Integer, Double> points = new HashMap<Integer, Double>();
		for(int i = 0; i < artist[gender].size(); i++){
			points.put(artist[gender].get(i), artistmovie.get(artist[gender].get(i)));
		}		
		for(int i = 0; i < max && i < points.size(); i++){
			int maxID = 0;
			double prev = 0;
			for(Integer key: points.keySet()){
				if(points.get(key) != null)
					if(points.get(key) > prev){
						prev = points.get(key);
						maxID = key;
					}
			}
			System.out.println(allArtist.get(maxID) + " -* " + points.get(maxID));
			points.put(maxID, null);
			if(gender == MALE_INDEX){
				topMale.add(maxID);
			}
			if(gender == FEMALE_INDEX){
				topFemale.add(maxID);
			}
		}
		
		
	}
	
	/**
	 * collects all the artist from the database
	 */
	public void collectAllArtist(){
		try {
 			database_manager dbmngr = system_manager.getDb_mngr();
 			String query = "SELECT * FROM `tblartist`";
			dbmngr.query(query);
 			for (; dbmngr.getRs().next();) {
 				int id = dbmngr.getRs().getInt("artistID");
 				String name = dbmngr.getRs().getString("artistName");
 				allArtist.put(id, name);
 			}
 			
 		}catch (SQLException | ClassNotFoundException a) {
 			a.printStackTrace();
 		}
	}
	
	/**
	 * 
	 * @return finalTop
	 * getting the Final top artists
	 * 
	 */
	public int[] getFinalTop() {
		return finalTop;
	}
	
	/**
	 * 
	 * @param finalTop
	 * setting the Final top artists
	 * 
	 */
	public void setFinalTop(int[] finalTop) {
		this.finalTop = finalTop;
	}
	
	/**
	 * @return movieInfo
	 */
	public MovieInfo getMovieInfo() {
		return movieInfo;
	}
	/**
	 * @param movieInfo
	 */
	public void setMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}
	/**
	 * @return artist
	 */
	public ArrayList<Integer>[] getArtist() {
		return artist;
	}
	/**
	 * @param artist
	 */
	public void setArtist(ArrayList<Integer>[] artist) {
		this.artist = artist;
	}
	/**
	 * @return topMale
	 */
	public ArrayList<Integer> getTopMale() {
		return topMale;
	}
	/**
	 * @param topMale
	 */
	public void setTopMale(ArrayList<Integer> topMale) {
		this.topMale = topMale;
	}
	/**
	 * @return topFemale
	 */
	public ArrayList<Integer> getTopFemale() {
		return topFemale;
	}
	/**
	 * @param topFemale
	 */
	public void setTopFemale(ArrayList<Integer> topFemale) {
		this.topFemale = topFemale;
	}
	/**
	 * @return allTop
	 */
	public ArrayList<Integer> getAllTop() {
		return allTop;
	}
	/**
	 * @param allTop
	 */
	public void setAllTop(ArrayList<Integer> allTop) {
		this.allTop = allTop;
	}

    /**
     *
     * @param allArtist
     */
	public void setAllArtist(HashMap<Integer, String> allArtist) { this.allArtist = allArtist; }

    /**
     *
     * @return allArtist
     */
    public HashMap<Integer, String> getAllArtist() { return allArtist; }
}
