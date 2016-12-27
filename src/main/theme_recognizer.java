
package main;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.NGram;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	theme_recognizer.java
 * Description:	Recognizes the theme from the Movie Information
 * Version:		1.0.0
 *
 * @lastreview
 *
 */
public class theme_recognizer {
	private NGram ngram;
	private int theme;
	private String[][] themeKeywords;
	private int[][] themePoints;
	final int KEYWORD = 0;
	final int THEME = 1;

	final int THEMEID = 0;
	final int POINTS = 1;
	
	/**
	 * initialize theme_recognizer
	 */
	public theme_recognizer() {
		// TODO Auto-generated constructor stub
		system_manager.getSplashscreen().setLabel("Initializing Theme Recognizer...");
		setNgram(new NGram());
		setThemeKeywords(fetchThemeKeywords());
		setThemePoints(fetchThemePoints());
	}
	
	/**
	 * executes the theme recognizer
	 */
	public void execute(String plot){
		ngram.setStart(1);
		ngram.setOrder(2);
		ngram.setText(plot);
		ngram.collectGram();
		scoringThemes();
		int temp = 0;
		for(int i = 0; i < themePoints.length; i++){
			if(themePoints[i][POINTS] > temp){
				setTheme(themePoints[i][THEMEID]);
				temp = themePoints[i][POINTS];
			}
		}
	}
	
	/**
	 * scoring every theme to categorize the plot
	 */
	public void scoringThemes(){
		for(String gram: ngram.keySet()){
			for(int i = 0; i < themeKeywords.length; i++){
				if(gram.contains(themeKeywords[i][KEYWORD])){
					int theme = Integer.parseInt(themeKeywords[i][THEME]) - 1;
					String themeName = system_manager.getOutput_mngr().themes[theme];
					System.out.println(gram + ">" + themeName);
					int temp = themePoints[theme][POINTS];
					themePoints[theme][POINTS] = temp + ngram.get(gram);
				}
			}
		}
	}
	
	/**
	 * @return ngram
	 */
	public NGram getNgram() {
		return ngram;
	}
	
	/**
	 * @param ngram
	 */
	public void setNgram(NGram ngram) {
		this.ngram = ngram;
	}

	/**
	 * 
	 * @return theme
	 */
	public int getTheme() {
		return theme;
	}

	/**
	 * 
	 * @param theme
	 */
	public void setTheme(int theme) {
		this.theme = theme;
	}

	/**
	 * 
	 * @return fetch arrKeyThemes from the database
	 */
	public String[][] fetchThemeKeywords() {
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<Integer> keythemes = new ArrayList<Integer>();
		String[][] arrKeyThemes;
		database_manager db = system_manager.getDb_mngr();
		String query = "SELECT * FROM `tblkeywords`";
		try {
			db.query(query);
			for(;db.getRs().next();){
				keys.add(db.getRs().getString("keyword"));
				keythemes.add(db.getRs().getInt("theme"));
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
	 * 
	 * @return themeKeywords
	 */
	public String[][] getThemeKeywords(){
		return themeKeywords;
	}
	
	/**
	 * 
	 * @param themeKeywords
	 */
	public void setThemeKeywords(String[][] themeKeywords) {
		this.themeKeywords = themeKeywords;
	}

	public int[][] fetchThemePoints() {
		ArrayList<Integer> themes = new ArrayList<Integer>();
		int[][] arrThemes;
		database_manager db = system_manager.getDb_mngr();
		String query = "SELECT * FROM `tbltheme`";
		try {
			db.query(query);
			for(;db.getRs().next();){
				themes.add(db.getRs().getInt("themeID"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arrThemes = new int[themes.size()][2];
		for(int i = 0; i < themes.size(); i++){
			arrThemes[i][0] = themes.get(i);
			arrThemes[i][1] = 0;
		}
		return arrThemes;
	}
	
	/**
	 * 
	 * @return themePoints
	 */
	public int[][] getThemePoints() {
		return themePoints;
	}

	/**
	 * 
	 * @param themePoints
	 */
	public void setThemePoints(int[][] themePoints) {
		this.themePoints = themePoints;
	}

}
