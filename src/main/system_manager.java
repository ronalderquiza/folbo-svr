
package main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import frames.Splash;
import tools.DBInstaller;

/**
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	system_manager.java
 * Description:	System Manager of the FoLBO System
 * @author		Ronald Erquiza, Katrina Buca
 * @version		1.1.7
 *
 * @lastreview 20170117
 * Ron, Kat, Ran
 */
public class system_manager implements Runnable {
	private String host = "jdbc:mysql://localhost/";
	private String database = "dbfolbo";
	private String user = "root";
	private String pass = "";

    /**
     * Splash Screen
     */
	private static Splash splashscreen = new Splash();

    /**
     * Database Manager
     */
    private static database_manager db_mngr;

    /**
     * Input Manager
     */
    private static input_manager input_mngr;

    /**
     * Theme Recognizer
     */
    private static theme_recognizer theme_recog;

    /**
     * Artist Recommender
     */
    private static artist_recommender artist_recom;

    /**
     * Star Value Calculator
     */
    private static starValue_calculator sv_calc;

    /**
     * Machine Learning Manager
     */
    private static machineLearning_manager ml_mngr;

    /**
     * GUI Manager
     */
    private static GUI_manager gui_mngr;

    /**
     * Output Manager
     */
    private static output_manager output_mngr;
	private static int time = 1000;
	private static int currYear = 2014;
	Thread runner;
	
	/**
	 * Instatation of the System Manager
	 */
	public system_manager() {
		start();
	}

	/**
     * Main Function of the System
	 * @param args Arguments
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
	 * Initialization of the Managers
	 */
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
	 * Runs the thread
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		initialize();
	}
	
	/**
	 * Start the thread
	 */
	public void start() {
        if (runner == null); {
            runner = new Thread(this);
            runner.start();
        }
    }
	
	/**
	 * Stop the thread
	 */
	@SuppressWarnings("deprecation")
	public void stop() {
        if (runner != null) {
            runner.stop();
            runner = null;
        }
    }
	
	/**
	 * Resetting the system
	 */
	public static void reset(){
		GUI_manager.getMovieSequel().setVisible(true);
	}
	
	/**
	 * Getting the GUI Manager
	 * @return GUI Manager
	 */
	public static GUI_manager getGUI_mngr(){
		return gui_mngr;
	}
	
	/**
     * Setting GUI Manager
	 * @param gui_manager GUI Manager
	 */
	public static void setGUI_mngr(GUI_manager gui_manager) {
		system_manager.gui_mngr = gui_manager;
	}

	/**
     * Getting Artist Recommender
	 * @return Artist Recommender
	 */
	public static artist_recommender getArtist_recom() {
		return artist_recom;
	}

	/**
	 * Setting Artist Recommender
	 * @param artist_recom Artist Recommender
	 */
	public static void setArtist_recom(artist_recommender artist_recom) {
		system_manager.artist_recom = artist_recom;
	}

	/**
     * Getting Input Manager
	 * @return Input Manager
	 */
	public static input_manager getInput_mngr() {
		return input_mngr;
	}

	/**
     * Setting Input Manager
	 * @param input_mngr Input Manager
	 */
	public static void setInput_mngr(input_manager input_mngr) {
		system_manager.input_mngr = input_mngr;
	}

	/**
     * Getting Database Manager
	 * @return db_mngr
	 */
	public static database_manager getDb_mngr() {
		return db_mngr;
	}

	/**
     * Setting Databaase Manager
	 * @param db_mngr
	 */
	public static void setDb_mngr(database_manager db_mngr) {
		system_manager.db_mngr = db_mngr;
	}

	/**
     * Getting Theme Recommender
	 * @return theme_recog
	 */
	public static theme_recognizer getTheme_recog() {
		return theme_recog;
	}

	/**
     * Setting Theme Recommender
	 * @param theme_recog
	 */
	public static void setTheme_recog(theme_recognizer theme_recog) {
		system_manager.theme_recog = theme_recog;
	}

	/**
     * Setting Machine Learning Manager
	 * @return Machine Learning Manager
	 */
	public static machineLearning_manager getMl_mngr() {
		return ml_mngr;
	}

	/**
     * Setting Machine Learning Manager
	 * @param ml_mngr Machine Learning Manager
	 */
	public static void setMl_mngr(machineLearning_manager ml_mngr) {
		system_manager.ml_mngr = ml_mngr;
	}
	
	/**
     * Getting Star Value Calculator
	 * @return Star Value Calculator
	 */
	public static starValue_calculator getSv_calc() {
		return sv_calc;
	}

	/**
     * Setting Star Value Calculator
	 * @param sv_calc Star Value Calculator
	 */
	public static void setSv_calc(starValue_calculator sv_calc) {
		system_manager.sv_calc = sv_calc;
	}

	/**
	 * Getting Output Manager
	 * @return Output Manager
	 */
	public static output_manager getOutput_mngr() {
		return output_mngr;
	}

	/**
	 * Setting Output Manager
	 * @param output_mngr Output Manager
	 */
	public static void setOutput_mngr(output_manager output_mngr) {
		system_manager.output_mngr = output_mngr;
	}

	/**
     * Getting Current Year
	 * @return Current Year
	 */
	public static int getCurrYear() {
		return currYear;
	}

	/**
     * Setting Current Year
	 * @param currYear Current Year
	 */
	public static void setCurrYear(int currYear) {
		system_manager.currYear = currYear;
	}

	/**
	 * Getting Splash Screen
	 * @return Splash Screen
	 */
	public static Splash getSplashscreen() {
		return splashscreen;
	}

	/**
	 * Setting Splash Screen
	 * @param splashscreen Splash Screen
	 */
	public static void setSplashscreen(Splash splashscreen) {
		system_manager.splashscreen = splashscreen;
	}

	
}
