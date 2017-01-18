package main;

import frames.*;

/**
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	GUI_manager.java
 * Description:	Manages the Graphical User Interface of the System
 * @author		Ronald Erquiza, Katrina Buca
 * @version		1.0.5
 *
 * @lastreview 
 */
public class GUI_manager {
	private static Home home;
	private static MovieAbout movieAbout;
	private static MovieSequel movieSequel;
	private static MoviePrequel moviePrequel;
	private static MovieStory movieStory;
	private static MovieInformation movieInfo;
	private static MovieCasts movieCasts;
	private static MovieProduction movieProduction;
	private static MovieGrossRevenue movieGrossRevenue;
	private static MovieRelease movieRelease;
	private static component_manager cmp_mngr;
	
	Thread runner;

	/**
	 * initialize GUI_manager
	 */
	public GUI_manager() {
		cmp_mngr = new component_manager();
		system_manager.getSplashscreen().setLabel("Initializing GUI Manager...");
		setHome(new Home());
		setMovieAbout(new MovieAbout());
		setMovieSequel(new MovieSequel());
		setMoviePrequel(new MoviePrequel());
		setMovieStory(new MovieStory());
		setMovieInfo(new MovieInformation());
		setMovieCasts(new MovieCasts());
		setMovieProduction(new MovieProduction());
		setMovieGrossRevenue(new MovieGrossRevenue());
		setMovieRelease(new MovieRelease());
	}

	/**
	 * @return home
	 */
	public static Home getHome() {
		return home;
	}

	/**
	 * @param home
	 */
	public static void setHome(Home home) {
		GUI_manager.home = home;
	}

	/**
	 * @return movieAbout
	 */
	public static MovieAbout getMovieAbout() {
		return movieAbout;
	}

	/**
	 * @param movieAbout
	 */
	public static void setMovieAbout(MovieAbout movieAbout) {
		GUI_manager.movieAbout = movieAbout;
	}

	/**
	 * @return moviePreSequel
	 */
	public static MoviePrequel getMoviePrequel() {
		return moviePrequel;
	}


	/**
	 * @param moviePrequel
	 */
	public static void setMoviePrequel(MoviePrequel moviePrequel) {
		GUI_manager.moviePrequel = moviePrequel;
	}

	/**
	 * @return movieSequel
	 */
	public static MovieSequel getMovieSequel() {
		return movieSequel;
	}

	/**
	 * @param movieSequel
	 */
	public static void setMovieSequel(MovieSequel movieSequel) {
		GUI_manager.movieSequel = movieSequel;
	}

	/**
	 * @return movieInfo
	 */
	public static MovieInformation getMovieInfo() {
		return movieInfo;
	}

	/**
	 * @param movieInfo
	 */
	public static void setMovieInfo(MovieInformation movieInfo) {
		GUI_manager.movieInfo = movieInfo;
	}

	/**
	 * @return movieStory
	 */
	public static MovieStory getMovieStory() {
		return movieStory;
	}

	/**
	 * @param movieStory
	 */
	public static void setMovieStory(MovieStory movieStory) {
		GUI_manager.movieStory = movieStory;
	}

	/**
	 * @return movieCasts
	 */
	public static MovieCasts getMovieCasts() {
		return movieCasts;
	}

	/**
	 * @param movieCasts
	 */
	public static void setMovieCasts(MovieCasts movieCasts) {
		GUI_manager.movieCasts = movieCasts;
	}

	/**
	 * @return movieProduction
	 */
	public static MovieProduction getMovieProduction() {
		return movieProduction;
	}

	/**
	 * @param movieProduction
	 */
	public static void setMovieProduction(MovieProduction movieProduction) {
		GUI_manager.movieProduction = movieProduction;
	}

	/**
	 * @return movieGrossRevenue
	 */
	public static MovieGrossRevenue getMovieGrossRevenue() {
		return movieGrossRevenue;
	}

	/**
	 * @param movieGrossRevenue
	 */
	public static void setMovieGrossRevenue(MovieGrossRevenue movieGrossRevenue) {
		GUI_manager.movieGrossRevenue = movieGrossRevenue;
	}

	/**
	 * @return movieRelease
	 */
	public static MovieRelease getMovieRelease() {
		return movieRelease;
	}

	/**
	 * @param movieRelease
	 */
	public static void setMovieRelease(MovieRelease movieRelease) {
		GUI_manager.movieRelease = movieRelease;
	}


	/**
	 * @return the cmp_mngr
	 */
	public static component_manager getCmp_mngr() {
		return cmp_mngr;
	}

	/**
	 * @param cmp_mngr the cmp_mngr to set
	 */
	public static void setCmp_mngr(component_manager cmp_mngr) {
		GUI_manager.cmp_mngr = cmp_mngr;
	}
}
