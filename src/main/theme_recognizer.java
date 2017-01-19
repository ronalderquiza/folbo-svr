
package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import objects.NGram;

/**
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	theme_recognizer.java
 * Description:	Recognizes the theme from the Movie Information
 * @author		Ronald Erquiza, Katrina Buca
 * @version		2.3.0
 *
 * @lastreview 20170117
 *
 */
public class theme_recognizer {
	private NGram ngram;
	private int theme;
	private String[][] themeKeywords;
	private double[][] themePoints;
	private HashMap<Integer, Double> themeDivisor;
	final int KEYWORD = 0;
	final int THEME = 1;

	final int THEMEID = 0;
	final int POINTS = 1;
	
	/**
	 * Instantiaton of Theme Recognizer
	 */
	public theme_recognizer() {
		system_manager.getSplashscreen().setLabel("Initializing Theme Recognizer...");
		setNgram(new NGram());
		setThemeKeywords(fetchThemeKeywords());
		setThemePoints(fetchThemePoints());
		getDivisor();
	}
	
	/**
	 * Executes the Theme Recognition
	 * @param plot Plot
	 */
	public void execute(String plot){
		ngram.setStart(1);
		ngram.setOrder(2);
		ngram.setText(plot);
		ngram.collectGram();
		scoringThemes();
		double temp = 0;
		for(int i = 0; i < themePoints.length; i++){
			System.out.println(system_manager.getOutput_mngr().getThemes()[(int)themePoints[i][THEMEID]-1] + " - " + themePoints[i][POINTS]);
			if(themePoints[i][POINTS] > temp){
				setTheme((int)themePoints[i][THEMEID]);
				temp = themePoints[i][POINTS];
			}
		}
	}
	
	/**
	 * Scoring every theme to categorize the plot
	 */
	public void scoringThemes(){
		for(String gram: ngram.keySet()){
			for(int i = 0; i < themeKeywords.length; i++){
				if(gram.equals(themeKeywords[i][KEYWORD])){
					int theme = Integer.parseInt(themeKeywords[i][THEME]) - 1;
					String themeName = system_manager.getOutput_mngr().getThemes()[theme];
					System.out.println(gram + ">" + themeName + "[" + (theme+1) + "]");
					double temp = themePoints[theme][POINTS];
					themePoints[theme][POINTS] = temp + (double) ngram.get(gram);//themeDivisor.get(theme+1);
				}
			}
		}
	}
	
	/**
	 * Getting the Divisor
	 */
	public void getDivisor(){
		String query = "SELECT COUNT(*), `theme` FROM `tblkeywords` GROUP BY `theme`";
		themeDivisor = new HashMap<Integer, Double>();
		database_manager db = system_manager.getDb_mngr();
		try {
			db.query(query);
			for(;db.getResultSet().next();){
				int key = db.getResultSet().getInt("theme");
				double value = (double)db.getResultSet().getInt("COUNT(*)");
				themeDivisor.put(key, value);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Getting NGram
	 * @return Ngram
	 */
	public NGram getNgram() {
		return ngram;
	}
	
	/**
	 * Setting NGram
	 * @param ngram NGram
	 */
	public void setNgram(NGram ngram) {
		this.ngram = ngram;
	}

	/**
	 * Getting Theme
	 * @return Theme
	 */
	public int getTheme() {
		return theme;
	}

	/**
	 * Setting Theme
	 * @param theme Theme
	 */
	public void setTheme(int theme) {
		this.theme = theme;
	}

	/**
	 * Fetching Theme Keywords from the Database
	 * @return Theme Keywords
	 */
	public String[][] fetchThemeKeywords() {
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<Integer> keythemes = new ArrayList<Integer>();
		String[][] arrKeyThemes;
		database_manager db = system_manager.getDb_mngr();
		String query = "SELECT * FROM `tblkeywords`";
		try {
			db.query(query);
			for(;db.getResultSet().next();){
				keys.add(db.getResultSet().getString("keyword"));
				keythemes.add(db.getResultSet().getInt("theme"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arrKeyThemes = new String[keys.size()][2];
		for(int i = 0; i < keys.size(); i++){
			arrKeyThemes[i][0] = keys.get(i);
			arrKeyThemes[i][1] = "" + keythemes.get(i);
		}
		return arrKeyThemes;
	}

	/**
	 * Getting Theme Keywords
	 * @return themeKeywords
	 */
	public String[][] getThemeKeywords(){
		return themeKeywords;
	}
	
	/**
	 * Setting Theme Keywords
	 * @param themeKeywords
	 */
	public void setThemeKeywords(String[][] themeKeywords) {
		this.themeKeywords = themeKeywords;
	}

	/**
	 * Fetching Theme Points
	 * @return Theme Points
	 */
	public double[][] fetchThemePoints() {
		ArrayList<Integer> themes = new ArrayList<Integer>();
		double[][] arrThemes;
		database_manager db = system_manager.getDb_mngr();
		String query = "SELECT * FROM `tbltheme`";
		try {
			db.query(query);
			for(;db.getResultSet().next();){
				themes.add(db.getResultSet().getInt("themeID"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arrThemes = new double[themes.size()][2];
		for(int i = 0; i < themes.size(); i++){
			arrThemes[i][0] = themes.get(i);
			arrThemes[i][1] = 0;
		}
		return arrThemes;
	}
	
	/**
	 * Getting Theme Points
	 * @return Theme Points
	 */
	public double[][] getThemePoints() {
		return themePoints;
	}

	/**
	 * Setting Theme Points
	 * @param themePoints Theme Points
	 */
	public void setThemePoints(double[][] themePoints) {
		this.themePoints = themePoints;
	}

}
