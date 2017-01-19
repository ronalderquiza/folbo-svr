package main;

import java.util.ArrayList;

import objects.*;
/**
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	input_manager.java
 * Description:	Manages the input of the system
 * @author		Ronald Erquiza, Katrina Buca
 * @version		1.1.3
 *
 * @lastreview 20161203
 * Ron, Kat, Ran
 */
public class input_manager {
    /**
     * Movie Input
     */
	private Movie input;

	/**
	 * Instantiation of  Input Manager
	 */
	public input_manager() {
		system_manager.getSplashscreen().setLabel("Initializing Input Manager...");
		input = new Movie();
	}

	/**
     * Input Story
	 * @param title Title
	 * @param plot Plot/Story
	 * @param theme Theme
	 */
	public void input_story(String title, String plot, int theme){
		system_manager.getInput_mngr().getMovie().setTitle(title);
		system_manager.getInput_mngr().getMovie().setPlot(plot);
		system_manager.getInput_mngr().getMovie().getMovieInfo().setTheme(theme);
	}
	
	/**
     * Input if the movie is Sequel or not
	 * @param sequel Sequel
	 */
	public void input_sequel(int sequel) {
		input.setSequel(sequel);
	}

	/**
     * Input Movie Information
	 * @param mg Main Genre
	 * @param sg Sub-genre
	 * @param origin Origin
	 * @param mtrcb MTRCB rating
	 */
	public void input_movieInfo(int mg, int sg, int origin, int mtrcb) {
		input.getMovieInfo().setMainGenre(mg);
		input.getMovieInfo().setSubGenre(sg);
		input.getMovieInfo().setOrigin(origin);
		input.getMovieInfo().setMTRCB(mtrcb);
	}
	
	/**
     * Input Production
	 * @param producer Producer
	 * @param director Director
	 * @param distributor Distributor
	 */
	public void input_prod(int producer, int director, int distributor) {
		input.getProdInfo().setProducer(producer);
		input.getProdInfo().setDirector(director);
		input.getProdInfo().setDistributor(distributor);
	}
	
	/**
     * Input Main Casts
	 * @param mainCasts Main Casts
	 */
	public void input_mainCasts(ArrayList<Integer> mainCasts){
		input.getProdInfo().setMainCasts(mainCasts);
		input.getProdInfo().getMainCasts();
		int year = system_manager.getCurrYear();
		input.getProdInfo().setStarValue(system_manager.getSv_calc().execute(mainCasts, year));
	}

	/**
	 * Input Release Information
     * @param month Month
	 * @param year Year
	 */
	public void input_release(int month, int year) {
		getMovie().getRelease().setMonth(month+1);
		getMovie().getRelease().setYear(year);
	}

	/**
     * Getting the Movie Input
	 * @return Movie Input
	 */
	public Movie getMovie() {
		return input;
	}
}
