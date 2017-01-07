package objects;

import main.system_manager;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	Movie.java
 * Description:	Movie object
 * Version:		1.0.1
 *
 * @lastreview 
 * 
 */

public class Movie {
	private String title;
	private int sequel;
	private int prequel;
	private MovieInfo movieInfo;
	private ProdInfo prodInfo;
	private Release release;
	private String plot;

	/**
	 * initialize Movie
	 */
	public Movie() {
		// TODO Auto-generated constructor stub
		system_manager.getSplashscreen().setLabel("Initializing Movie object...");
		movieInfo = new MovieInfo();
		prodInfo = new ProdInfo();
		release = new Release();
		sequel = -1;
		plot = new String();
	}

	/**
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * @return title
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * @param plot
	 */
	public void setPlot(String plot){
		this.plot = plot;
	}
	
	/**
	 * @return plot
	 */
	public String getPlot(){
		return plot;
	}	
	
	/**
	 * @param sequel
	 */
	public void setSequel(int sequel) {
		this.sequel = sequel;
	}

	/**
	 * @return sequel
	 */
	public int getSequel() {
		return sequel;
	}

	/**
	 * @param movieInfo
	 */
	public void setMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}

	/**
	 * @return movieInfo
	 */
	public MovieInfo getMovieInfo() {
		return movieInfo;
	}

	/**
	 * @param prodInfo
	 */
	public void setProdInfo(ProdInfo prodInfo) {
		this.prodInfo = prodInfo;
	}

	/**
	 * @return prodInfo
	 */
	public ProdInfo getProdInfo() {
		return prodInfo;
	}

	/**
	 * @param release
	 */
	public void setRelease(Release release) {
		this.release = release;
	}

	/**
	 * @return release
	 */
	public Release getRelease() {
		return release;
	}
	
	public String toString(){
		return "Movie = {\n\ttitle = " + title +
				",\n\tsequel = " + sequel +
				",\n\tmovieInfo = " + movieInfo +
				",\n\tprodInfo = " + prodInfo +
				",\n\trelease = " + release +
				"\n\t}";
	}

	/**
	 * @return prequel
	 */
	public int getPrequel() {
		return prequel;
	}

	/**
	 * @param prequel
	 */
	public void setPrequel(int prequel) {
		this.prequel = prequel;
	}
}
