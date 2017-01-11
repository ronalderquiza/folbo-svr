package objects;

import java.util.ArrayList;

import main.system_manager;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	ProdInfo.java
 * Description:	Production information object
 * @version		1.0.2
 *
 * @lastreview 
 * 
 */

public class ProdInfo {
	private ArrayList<Integer> mainCasts;
	private int producer;
	private double starValue;
	private int director;
	private int distributor;
	
	/**
	 * initialize ProdInfo
	 */
	public ProdInfo(){
		system_manager.getSplashscreen().setLabel("Initializing Production Info object...");
		mainCasts = new ArrayList<Integer>();
	}
	
	/**
	 * @param artist
	 */
	public void setMainCasts(ArrayList<Integer> artist){
		this.mainCasts.clear();
		this.mainCasts = artist;
	}
	
	/**
	 * @return mainCasts
	 */
	public ArrayList<Integer> getMainCasts(){
		return mainCasts;
	}
	
	/**
	 * @param producer
	 */
	public void setProducer(int producer){
		this.producer = producer;
	}
	
	/**
	 * @return producer
	 */
	public int getProducer(){
		return producer;
	}
	
	/**
	 * @param starValue
	 */
	public void setStarValue(double starValue){
		this.starValue = starValue;
	}
	
	/**
	 * @return starValue
	 */
	public double getStarValue(){
		return starValue;
	}
	

	/**
	 * @return director
	 */
	public int getDirector() {
		return director;
	}

	/**
	 * @param director
	 */
	public void setDirector(int director) {
		this.director = director;
	}

	/**
	 * @return distributor
	 */
	public int getDistributor() {
		return distributor;
	}

	/**
	 * @param distributor
	 */
	public void setDistributor(int distributor) {
		this.distributor = distributor;
	}
	
	/**
	 * to convert the object to String
	 */
	public String toString(){
		return "{\n\t\tmainCasts = " + mainCasts +
				",\n\t\tproducer = " + producer +
				",\n\t\tdistributor = " + distributor +
				",\n\t\tdirector = " + director +
				",\n\t\tstarValue = " + starValue +
				",\n\t\t}";
	}
}
