
package main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import frames.Splash;
import tools.DBInstaller;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	system_manager.java
 * Description:	System Manager of the FoLBO System
 * @version		1.1.7
 *
 * @lastreview 20161203
 * Ron, Kat, Ran
 */
public class system_manager implements Runnable {
	private String host = "jdbc:mysql://localhost/";
	private String database = "dbfolbo";
	private String user = "root";
	private String pass = "";
	
	private static Splash splashscreen = new Splash();
	private static database_manager db_mngr;
	private static input_manager input_mngr;
	private static theme_recognizer theme_recog;
	private static artist_recommender artist_recom;
	private static starValue_calculator sv_calc;
	private static machineLearning_manager ml_mngr;
	private static GUI_manager gui_mngr;
	private static output_manager output_mngr;
	private static int time = 1000;
	private static int currYear = 2014;
	Thread runner;
	
	/**
	 * initialize system manager
	 */
	public system_manager() {
		start();
	}

	/**
	 * @param args
	 * main function of the system
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				@SuppressWarnings("unused")
				system_manager system_mngr = new system_manager();	//start of system manager
			} 
		});

	}
	
	/**
	 * initializing system_manager
	 */
	@SuppressWarnings("static-access")
	public void initialize(){
		setDb_mngr(new database_manager(host, database, user, pass));
		if(!DBInstaller.databaseExists(database)){	//checks the database existence
			int dialogButton = 
					JOptionPane.showConfirmDialog(null, "FoLBO Database is not installed. "
							+ "Install it now?",
							"Database Error",
					JOptionPane.WARNING_MESSAGE);
			if (dialogButton == JOptionPane.YES_OPTION) {
				DBInstaller sqlreader = new DBInstaller();
				sqlreader.installDB();
			}
			else{
				System.exit(0);
			}
		}
		setInput_mngr(new input_manager());
		setTheme_recog(new theme_recognizer());
		setArtist_recom(new artist_recommender());
		setSv_calc(new starValue_calculator());
		setGUI_mngr(new GUI_manager());
		setOutput_mngr(new output_manager());
		setMl_mngr(new machineLearning_manager());
		try {
			Thread.sleep(time);		//paused for 1 second
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getSplashscreen().setVisible(false);
		gui_mngr.getHome().setVisible(true);
		stop();
	}
	
	/**
	 * run the thread
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		initialize();
	}
	
	/**
	 * start the thread
	 */
	public void start() {
        if (runner == null); {
            runner = new Thread(this);
            runner.start();
        }
    }
	
	/**
	 * stop the thread
	 */
	@SuppressWarnings("deprecation")
	public void stop() {
        if (runner != null) {
            runner.stop();
            runner = null;
        }
    }
	
	/**
	 * resetting the system
	 */
	public static void reset(){
		GUI_manager.getMovieSequel().setVisible(true);
	}
	
	/**
	 * 
	 * @return gui_mngr
	 */
	public static GUI_manager getGUI_mngr(){
		return gui_mngr;
	}
	
	/**
	 * @param gui_manager
	 * sets the gui_manager
	 */
	public static void setGUI_mngr(GUI_manager gui_manager) {
		system_manager.gui_mngr = gui_manager;
	}

	/**
	 * @return artist_recom
	 */
	public static artist_recommender getArtist_recom() {
		return artist_recom;
	}

	/**
	 * set artist recommender
	 * @param artist_recom
	 */
	public static void setArtist_recom(artist_recommender artist_recom) {
		system_manager.artist_recom = artist_recom;
	}

	/**
	 * @return input_mngr
	 */
	public static input_manager getInput_mngr() {
		return input_mngr;
	}

	/**
	 * @param input_mngr
	 */
	public static void setInput_mngr(input_manager input_mngr) {
		system_manager.input_mngr = input_mngr;
	}

	/**
	 * @return db_mngr
	 */
	public static database_manager getDb_mngr() {
		return db_mngr;
	}

	/**
	 * @param db_mngr
	 */
	public static void setDb_mngr(database_manager db_mngr) {
		system_manager.db_mngr = db_mngr;
	}

	/**
	 * @return theme_recog
	 */
	public static theme_recognizer getTheme_recog() {
		return theme_recog;
	}

	/**
	 * @param theme_recog
	 */
	public static void setTheme_recog(theme_recognizer theme_recog) {
		system_manager.theme_recog = theme_recog;
	}

	/**
	 * @return ml_mngr
	 */
	public static machineLearning_manager getMl_mngr() {
		return ml_mngr;
	}

	/**
	 * @param ml_mngr
	 */
	public static void setMl_mngr(machineLearning_manager ml_mngr) {
		system_manager.ml_mngr = ml_mngr;
	}
	
	/**
	 * @return sv_calc
	 */
	public static starValue_calculator getSv_calc() {
		return sv_calc;
	}

	/**
	 * @param sv_calc
	 */
	public static void setSv_calc(starValue_calculator sv_calc) {
		system_manager.sv_calc = sv_calc;
	}

	/**
	 * 
	 * @return output_mngr
	 */
	public static output_manager getOutput_mngr() {
		return output_mngr;
	}

	/**
	 * 
	 * @param output_mngr
	 */
	public static void setOutput_mngr(output_manager output_mngr) {
		system_manager.output_mngr = output_mngr;
	}

	/**
	 * @return the currYear
	 */
	public static int getCurrYear() {
		return currYear;
	}

	/**
	 * @param currYear the currYear to set
	 */
	public static void setCurrYear(int currYear) {
		system_manager.currYear = currYear;
	}

	/**
	 * 
	 * @return splashscreen
	 */
	public static Splash getSplashscreen() {
		return splashscreen;
	}

	/**
	 * 
	 * @param splashscreen
	 */
	public static void setSplashscreen(Splash splashscreen) {
		system_manager.splashscreen = splashscreen;
	}

	
}
