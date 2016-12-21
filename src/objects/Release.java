package objects;

import main.system_manager;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	Release.java
 * Description:	Release date object
 * Version:		1.0.0
 *
 * @lastreview 
 * 
 */

public class Release {
	private int month;
	private int year;
	
	/**
	 * initialize Release
	 */
	public Release(){
		system_manager.getSplashscreen().setLabel("Initializing Release object...");
	}
	
	/**
	 * @param month
	 */
	public void setMonth(int month){
		this.month = month;
	}
	
	/**
	 * @return month
	 */
	public int getMonth(){
		return month;
	}
	
	/**
	 * @param year
	 */
	public void setYear(int year){
		this.year = year;
	}
	
	/**
	 * @return year
	 */
	public int getYear(){
		return year;
	}
	
	public String toString(){
		return "{\n\t\tmonth = " + month +
				",\n\t\tyear = " + year +
				"\n\t\t}";
	}
}
