package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import objects.MovieInfo;
import tools.DBQueries;
/**
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	artist_recommender.java
 * Description:	Recommending artists
 * @author		Ronald Erquiza, Katrina Buca
 * @version		2.4.1
 *
 * @lastreview 20170117
 * Ron, Kat, Ran
 */
public class artist_recommender {
    /**
     * Movie Information
     */
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
	 * Instantiation of Artist Recommender
	 */
	@SuppressWarnings("unchecked")
	public artist_recommender() {
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
	 * Recommend Artists
	 * @param movieInfo Movie Information
	 * @param gender Gender
	 */
	public void recommendArtist(MovieInfo movieInfo, int gender) {
		int year = 2014;
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
	 * Setting all top artists to final array
	 */
	public void setAll(){
		setFinalTop(new int[getAllTop().size()]);
		for(int i = 0; i < getAllTop().size(); i++){
			getFinalTop()[i] = getAllTop().get(i);
		}
	}
	
	/**
	 * Collecting of artists from the database
     * @param gender Gender
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
				for (;db_mngr.getResultSet().next();) {
					int id = db_mngr.getResultSet().getInt("artistID");
					getArtist()[gender - 1].add(id);
					System.out.println(">" + allArtist.get(id) + "-" + id + "-" + artistmovie.get(id));
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			index++;
		}
		
	}
	
	/**
	 * Collects all the artists' movie count
	 * @param year Year
	 */
	private void collectArtistMovie(int year){
		database_manager db = system_manager.getDb_mngr();
		double divisor = 100000000;
		System.out.println("year:" + year);
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
			for(;db.getResultSet().next();){
				int artistID = db.getResultSet().getInt("artistID");
				int count = db.getResultSet().getInt("COUNT(`artistID`)");
				double gross = db.getResultSet().getDouble("SUM(`grossRevenue`)");
				artistmovie.put(artistID, (gross/count)/divisor);
				System.out.println(artistID + "---->" + (gross/count)/divisor);
			}
			db.getResultSet().close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
     * Getting the top artists
	 * @param gender Gender
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
	 * Collects all the artist from the database
	 */
	public void collectAllArtist(){
		try {
 			database_manager dbmngr = system_manager.getDb_mngr();
 			String query = "SELECT * FROM `tblartist`";
			dbmngr.query(query);
 			for (; dbmngr.getResultSet().next();) {
 				int id = dbmngr.getResultSet().getInt("artistID");
 				String name = dbmngr.getResultSet().getString("artistName");
 				allArtist.put(id, name);
 			}
 			
 		}catch (SQLException | ClassNotFoundException a) {
 			a.printStackTrace();
 		}
	}
	
	/**
	 * Getting the Final top artists
	 * @return Final Top Artists
	 */
	public int[] getFinalTop() {
		return finalTop;
	}
	
	/**
     * Setting the Final Top Artists
	 * @param finalTop Final Top Artists
	 */
	public void setFinalTop(int[] finalTop) {
		this.finalTop = finalTop;
	}
	
	/**
     * Getting Movie Information
	 * @return Movie Information
	 */
	public MovieInfo getMovieInfo() {
		return movieInfo;
	}

    /**
     * Setting Movie Information
	 * @param movieInfo Movie Information
	 */
	public void setMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}

    /**
     * Getting Artists
	 * @return Artists
	 */
	public ArrayList<Integer>[] getArtist() {
		return artist;
	}

    /**
     * Setting Artists
	 * @param artist Artists
	 */
	public void setArtist(ArrayList<Integer>[] artist) {
		this.artist = artist;
	}

    /**
     * Getting Top Male
	 * @return Top Male
	 */
	public ArrayList<Integer> getTopMale() {
		return topMale;
	}

    /**
     * Setting Top Male Artist
	 * @param topMale
	 */
	public void setTopMale(ArrayList<Integer> topMale) {
		this.topMale = topMale;
	}

    /**
     * Getting Top Female Artists
	 * @return Top Female Artists
	 */
	public ArrayList<Integer> getTopFemale() {
		return topFemale;
	}

	/**
     * Setting Top Female Artists
	 * @param topFemale Top Female Artists
	 */
	public void setTopFemale(ArrayList<Integer> topFemale) {
		this.topFemale = topFemale;
	}

    /**
     * Getting All Top Artists
	 * @return All Top Artists
	 */
	public ArrayList<Integer> getAllTop() {
		return allTop;
	}

    /**
     * Setting All Top Artists
	 * @param allTop All Top Artists
	 */
	public void setAllTop(ArrayList<Integer> allTop) {
		this.allTop = allTop;
	}

    /**
     * Getting All Artists
     * @param allArtist All Artists
     */
	public void setAllArtist(HashMap<Integer, String> allArtist) { this.allArtist = allArtist; }

    /**
     * Getting All Artists
     * @return All Artists
     */
    public HashMap<Integer, String> getAllArtist() { return allArtist; }
}
