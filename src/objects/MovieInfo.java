package objects;

import main.system_manager;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieInfo.java
 * Description:	Movie information object
 * Version:		1.0.1
 *
 * @lastreview 
 * 
 */

public class MovieInfo {
	private int mainGenre;
	private int subGenre;
	private int origin;
	private int mtrcbRating;
	private int theme;
	
	/**
	 * initialize MovieInfo
	 */
	public MovieInfo(){
		system_manager.getSplashscreen().setLabel("Initializing Movie Information object...");
	}
	
	/**
	 * @param mainGenre
	 */
	public void  setMainGenre(int mainGenre){
		this.mainGenre = mainGenre;
	}
	
	/**
	 * @return mainGenre
	 */
	public int getMainGenre(){
		return mainGenre;
	}
	
	/**
	 * @param subGenre
	 */
	public void setSubGenre(int subGenre){
		this.subGenre = subGenre;
	}
	
	/**
	 * @return subGenre
	 */
	public int getSubGenre(){
		return subGenre;
	}
	
	/**
	 * @param origin
	 */
	public void setOrigin(int origin ){
		this.origin = origin;
	}
	
	/**
	 * @return origin
	 */
	public int getOrigin(){
		return origin;
	}
	
	/**
	 * @param mtrcb
	 */
	public void setMTRCB(int mtrcb){
		this.mtrcbRating = mtrcb;
	} 
	
	/**
	 * @return mtrcbRating
	 */
	public int getMTRCB(){
		return mtrcbRating;
	}
	
	/**
	 * @param theme
	 */
	public void setTheme(int theme) {
		this.theme = theme;
	}

	/**
	 * @return theme
	 */
	public int getTheme() {
		return theme;
	}
	
	public String toString(){
		return "{\n\t\tmainGenre = " + mainGenre +
				",\n\t\tsubGenre = " + subGenre +
				",\n\t\torigin = " + origin + 
				",\n\t\tmtrcbRating = " + mtrcbRating +
				"\n\t\ttheme = " + theme +
				"\n\t\t}";
	}
}
