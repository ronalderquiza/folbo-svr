package main;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import objects.Movie;
import main.GUI_manager;
import main.database_manager;
import main.system_manager;

/**
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	output_manager.java
 * Description:	Manages the output of the system
 * @author		Ronald Erquiza, Katrina Buca
 * @version		1.3.1
 *
 * @lastreview 20170117
 * 
 */
public class output_manager {
	private String[] themes;
	private String[] genres;
	private String[] producers;
	private String[] directors;
	private HashMap<Integer, String> artistNames;
	
	/**
	 * Instantiation of Output Manager
	 */
	public output_manager() {
		system_manager.getSplashscreen().setLabel("Initializing output manager...");
		setThemes(getList("tbltheme", "theme"));
		setGenres(getList("tblgenre", "genre"));
		setProducers(getList("tblprodcompany", "prodCompany"));
		setDirectors(getList("tbldirector", "directorName"));
		setArtistNames(getListHM("tblartist", "artistID", "artistName"));
	}
	
	/**
	 * Execution of the Output Manager
	 */
	public void execute(){
		Movie movie = system_manager.getInput_mngr().getMovie();
		String movieTitle = movie.getTitle();
		int theme = movie.getMovieInfo().getTheme()-1;
		int mainGenre = movie.getMovieInfo().getMainGenre()-1;
		int subGenre = movie.getMovieInfo().getSubGenre()-1;
		int producer = movie.getProdInfo().getProducer()-1;
		int director = movie.getProdInfo().getDirector()-1;
		int distributor = movie.getProdInfo().getDistributor()-1;
		ArrayList<Integer> artists = movie.getProdInfo().getMainCasts();
		System.out.println(movie);
		system_manager.getMl_mngr().execute(movie);
		double BOR = system_manager.getMl_mngr().getOutput();
		double certainty = system_manager.getMl_mngr().getCertainty();
		String css = "text-align: center;";
		DecimalFormat dFormat = new DecimalFormat("#,###.##");
		String movieSummary1 = "<html><div style='" + css + "'> THE MOVIE THAT WILL" + " BE PRODUCED IS," + "<br>" + "<h2><font color = '#2ecc71'>" +
				movieTitle + "</h2></font>" +  "that has <b>'" + getThemes()[theme] + "'</b></font> theme" + "<br>" +
              "It is a <b>" + getGenres()[mainGenre] + "</b>" + " film" + "<br>" + "<br>" + 
              "Directed by <b>" + getDirectors()[director] + "</b> " + "<br>" +
              "Produced by <b>" + getProducers()[producer] + "</b> " + "<br>" +
              "Distributed by <b>" + getProducers()[distributor] + "</b> " + "<br>" +
              "</div></html>";

		String movieSummary2 = "<html><div style='" + css + "'> THE MOVIE THAT WILL" + " BE PRODUCED IS," + "<br>" + "<h2><font color = '#2ecc71'>" +
				movieTitle + "</h2></font>" +  "that has <b>'" + getThemes()[theme] + "'</b></font> theme" + "<br>" +
              "It is a <b>" + getGenres()[mainGenre] + "</b> and <b>" + getGenres()[subGenre] + "</b> " + "film" + "<br>" + "<br>" + 
              "Directed by <b>" + getDirectors()[director] + "</b> " + "<br>" +
              "Produced by <b>" + getProducers()[producer] + "</b> " + "<br>" +
              "Distributed by <b>" + getProducers()[distributor] + "</b> " + "<br>" 
              + "</div></html>";
		
		String wholetext = "";
		
		if(getGenres()[mainGenre] == getGenres()[subGenre]) {
			wholetext = "<html><div style='text-align: center;'>" + movieSummary1 + "</div></html>";
		} if(subGenre == 0) {
			wholetext = "<html><div style='text-align: center;'>" + movieSummary1 + "</div></html>";
		} else {
			wholetext = "<html><div style='text-align: center;'>" + movieSummary2 + "</div></html>";
		}
		
		GUI_manager.getMovieGrossRevenue().getLabelFirstSentence().setText(wholetext);
		
		if(BOR <= 10000000) {
			GUI_manager.getMovieGrossRevenue().getLabelRevenue().setText("<html><div style='" + css + "'>" + "<font color = 'red'>" +  "PHP " + dFormat.format(BOR) + "</font></div>") ;
							//+ "<font size='6' color='white'><i><br>(" + dFormat.format(certainty) + "% Certainty)</i></font>" + "</div></html>");
		} else {
			GUI_manager.getMovieGrossRevenue().getLabelRevenue().setText("<html><div style='" + css + "'>" + "<font color = '#2ecc71'>" +  "PHP " + dFormat.format(BOR) + "</font></div>");
							//+ "<font size='6' color='white'><i><br>(" + dFormat.format(certainty) + "% Certainty)</i></font>" + "</div></html>");
		}
		
		String currString = "<html><div style='" + css + "'>"
				+ "<font color = 'white' face = 'Rockwell' size = '3'><b>CASTS</b><br><br>";
		for(int ctr = 0; ctr < artists.size(); ctr++) {
			currString = currString + getArtistNames().get(artists.get(ctr)) + "<br>";
		}
		currString = currString + "</div> </font></html>";
		
		GUI_manager.getMovieGrossRevenue().getTaArtists().setText(currString);
	}
	
	/**
     * Getting the List
	 * @param tblName Table Name
	 * @param colName Column Name
	 * @return result
	 */
	public String[] getList(String tblName, String colName){
		database_manager dbmngr = system_manager.getDb_mngr();
		ArrayList<String> list = new ArrayList<String>();
		String query = "SELECT * FROM `" + tblName + "`";
		try {
			dbmngr.query(query);
			for(;dbmngr.getResultSet().next();)
				list.add(dbmngr.getResultSet().getString(colName));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] result = new String[list.size()];
		for(int i = 0; i < list.size(); i++){
			result[i] = list.get(i);
		}
		
		return result;
	}
	
	/**
	 * Getting the List in HashMap Data
	 * @param tblName Table Name
	 * @param colID  Column ID
	 * @param colName Column Name
	 * @return result
	 */
	public HashMap<Integer, String> getListHM(String tblName, String colID, String colName){
		database_manager dbmngr = system_manager.getDb_mngr();
		HashMap<Integer, String> list = new HashMap<Integer, String>();
		String query = "SELECT * FROM `" + tblName + "`";
		try {
			dbmngr.query(query);
			for(;dbmngr.getResultSet().next();)
				list.put(dbmngr.getResultSet().getInt(colID), dbmngr.getResultSet().getString(colName));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


	/**
     * Getting Artist Names
	 * @return Artist Names
	 */
	public HashMap<Integer, String> getArtistNames() {
		return artistNames;
	}

	/**
     * Setting Artist Names
	 * @param artistNames Artist Names
	 */
	public void setArtistNames(HashMap<Integer, String> artistNames) {
		this.artistNames = artistNames;
	}

	/**
     * Getting Themes
	 * @return Themes
	 */
	public String[] getThemes() {
		return themes;
	}

	/**
     * Setting Themes
	 * @param themes Themes
	 */
	public void setThemes(String[] themes) {
		this.themes = themes;
	}

	/**
     * Getting Genres
	 * @return Genres
	 */
	public String[] getGenres() {
		return genres;
	}

	/**
     * Setting Genres
	 * @param genres Genres
	 */
	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	/**
     * Getting Producers
	 * @return Producers
	 */
	public String[] getProducers() {
		return producers;
	}

	/**
     * Setting Producers
	 * @param producers Producers
	 */
	public void setProducers(String[] producers) {
		this.producers = producers;
	}

	/**
     * Getting Directors
	 * @return Directors
	 */
	public String[] getDirectors() {
		return directors;
	}

	/**
     * Setting Directors
	 * @param directors Directors
	 */
	public void setDirectors(String[] directors) {
		this.directors = directors;
	}
	
}
