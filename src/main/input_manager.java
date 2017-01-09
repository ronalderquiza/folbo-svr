package main;

import java.util.ArrayList;

import objects.*;
/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	input_manager.java
 * Description:	Manages the input of the system
 * @version		1.1.3
 *
 * @lastreview 20161203
 * Ron, Kat, Ran
 */
public class input_manager {
	private Movie input;

	/**
	 * initialize input_manager
	 */
	public input_manager() {
		system_manager.getSplashscreen().setLabel("Initializing Input Manager...");
		input = new Movie();
	}

	/**
	 * @param title
	 * @param plot
	 * @param theme
	 */
	public void input_story(String title, String plot, int theme){
		system_manager.getInput_mngr().getMovie().setTitle(title);
		system_manager.getInput_mngr().getMovie().setPlot(plot);
		system_manager.getInput_mngr().getMovie().getMovieInfo().setTheme(theme);
	}
	
	/**
	 * @param sequel
	 * accepts sequel to put in Movie object
	 */
	public void input_sequel(int sequel) {
		input.setSequel(sequel);
	}

	/**
	 * @param mg - main genre	
	 * @param sg - sub-genre
	 * @param origin
	 * @param mtrcb - MTRCB rating
	 * accepts the movie information to put in Movie object
	 */
	public void input_movieInfo(int mg, int sg, int origin, int mtrcb) {
		input.getMovieInfo().setMainGenre(mg);
		input.getMovieInfo().setSubGenre(sg);
		input.getMovieInfo().setOrigin(origin);
		input.getMovieInfo().setMTRCB(mtrcb);
	}
	
	/**
	 * @param producer
	 * @param director
	 * @param distributor
	 * accepts movie production to put in Movie object
	 */
	public void input_prod(int producer, int director, int distributor) {
		input.getProdInfo().setProducer(producer);
		input.getProdInfo().setDirector(director);
		input.getProdInfo().setDistributor(distributor);
	}
	
	/**
	 * accepts main casts selected
	 * @param mainCasts
	 */
	public void input_mainCasts(ArrayList<Integer> mainCasts){
		input.getProdInfo().setMainCasts(mainCasts);
		input.getProdInfo().getMainCasts();
		int year = system_manager.getCurrYear();
		input.getProdInfo().setStarValue(system_manager.getSv_calc().execute(mainCasts, year));
	}

	/**
	 * accepts the release date
	 * @param month
	 * @param year
	 */
	public void input_release(int month, int year) {
		getMovie().getRelease().setMonth(month+1);
		getMovie().getRelease().setYear(year);
	}

	/**
	 * @return input
	 */
	public Movie getMovie() {
		return input;
	}
}
