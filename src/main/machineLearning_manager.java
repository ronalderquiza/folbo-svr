package main;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.Movie;
import svr.eSVR;
import tools.DBQueries;
/**
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	machineLearning_manager.java
 * Description:	Machine Learning Manager of the FoLBO System
 * @author		Ronald Erquiza, Katrina Buca
 * @version		2.1.3
 * @lastreview  20170117
 * Ron, Kat, Ran
 */
public class machineLearning_manager {
	/**
	 * Machine Learning Model used
	 */
	private eSVR model;

	/**
	 * The output of the Machine Learning
	 */
	private double output;

	/**
	 * Certainty of the Machine Learning output
	 */
	private double certainty;

	/**
	 * Index of the training data
	 */
	private int index = 0;
	
	/**
	 * Instantiation of the Machine Learning Manager
	 */
    machineLearning_manager(){
		system_manager.getSplashscreen().setLabel("Initializing machine learning manager...");
		setModel(new eSVR());
	}
	
	/**
	 * Execution of the Machine Learning Manager.
	 * Training and Predicting is included.
	 * @param input_data The input for the Model.
	 */
	public void execute(Movie input_data){
		Movie movie = input_data;
		double[][] training_dataX = setTrainingDataX(movie);	//features
		double[] training_dataY = setTrainingDataY(movie);		//targets
		double[] input = {movie.getProdInfo().getStarValue(), movie.getRelease().getYear(), movie.getRelease().getMonth(), movie.getMovieInfo().getTheme()};


		//setting the training data of the model
		getModel().setTrainingData(training_dataX, training_dataY);
		
		//training the model
		getModel().trainData();

		//computes the certainty of the algorithm's result
		setCertainty(getModel().computesCertainty(index));
		
		//setting the output
		setOutput(Math.abs(getModel().predict(input)));
	}
	
	/**
	 * Setting the Training data features
	 * @param movie Movie Input
	 * @return Training Data Input
	 */
	private double[][] setTrainingDataX(Movie movie) {
		// TODO Auto-generated method stub
		double[][] data;
		ArrayList<Double[]> dataList = new ArrayList<Double[]>();
		database_manager dbmngr = system_manager.getDb_mngr();
		DBQueries dbq = new DBQueries();
		String query = "";
		index = 0;
		while(dataList.size() <= 1){
			if(movie.getSequel() == 0){
					query = dbq.getTrainingDataQueries()[index];
				try {
					System.out.println(query);
					dbmngr.query(query);
					for(;dbmngr.getResultSet().next();){
						Double[] record = new Double[4];
						record[0] = dbmngr.getResultSet().getDouble("starValue");
						record[1] = (double)dbmngr.getResultSet().getInt("year");
						record[2] = (double)dbmngr.getResultSet().getInt("month");
						record[3] = (double)dbmngr.getResultSet().getInt("theme");
						dataList.add(record);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				boolean hasPrequel = true;
				int id = movie.getPrequel();
				while(hasPrequel){
					query = "SELECT * FROM `tblmovie` WHERE `movieID` = " + id + " AND "
							+ "`year` <= " + system_manager.getCurrYear();
					try {
						int count = 0;
						dbmngr.query(query);
						for(;dbmngr.getResultSet().next();){
							id = dbmngr.getResultSet().getInt("preSequel");
							Double[] record = new Double[3];
							record[0] = dbmngr.getResultSet().getDouble("starValue");
							record[1] = (double)dbmngr.getResultSet().getInt("year");
							record[2] = (double)dbmngr.getResultSet().getInt("month");
							dataList.add(record);
							count++;
						}
						if(count == 0){
							hasPrequel = false;
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			index++;
		}
		
		data = new double[dataList.size()][];
		for(int i = 0; i < dataList.size(); i++){
			data[i] = new double[dataList.get(i).length];
			for(int j = 0; j < dataList.get(i).length; j++){
				data[i][j] = dataList.get(i)[j];
			}
		}
		
		return data;
	}

	/**
	 * Setting the Training Data Label/Target
	 * @param movie Movie Input
	 * @return Training Data Target
	 */
	private double[] setTrainingDataY(Movie movie) {
		// TODO Auto-generated method stub
		double[] data;
		ArrayList<Double> dataList = new ArrayList<Double>();
		database_manager dbmngr = system_manager.getDb_mngr();
		DBQueries dbq = new DBQueries();
		String query = "";
		index = 0;
		while(dataList.size() <= 1){
			if(movie.getSequel() == 0){
				query = dbq.getTrainingDataQueries()[index];
				try {
					dbmngr.query(query);
					for(;dbmngr.getResultSet().next();){
						dataList.add(dbmngr.getResultSet().getDouble("grossRevenue"));
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				boolean hasPrequel = true;
				int id = movie.getPrequel();
				while(hasPrequel){
					query = "SELECT * FROM `tblmovie` WHERE `movieID` = " + id;
					try {
						int count = 0;
						dbmngr.query(query);
						for(;dbmngr.getResultSet().next();){
							id = dbmngr.getResultSet().getInt("preSequel");
							dataList.add(dbmngr.getResultSet().getDouble("grossRevenue"));
							count++;
						}
						if(count == 0){
							hasPrequel = false;
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			index++;
		}
		
		data = new double[dataList.size()];
		for(int i = 0; i < dataList.size(); i++){
			data[i] = dataList.get(i);
		}
		
		return data;
	}
	
	/**
     * Getting the Model
	 * @return model
	 */
	public eSVR getModel() {
		return model;
	}
	
	/**
     * Setting the Model
	 * @param model Model
	 */
	public void setModel(eSVR model) {
		this.model = model;
	}

	/**
     * Getting the Output
	 * @return output
	 */
	public double getOutput() {
		return output;
	}

	/**
     * Setting the Output
	 * @param output Output
	 */
	public void setOutput(double output) {
		this.output = output;
	}

	/**
     * Getting the Certainty
	 * @return certainty
	 */
	public double getCertainty() {
		return certainty;
	}

	/**
     * Setting the Certainty
	 * @param certainty Certainty
	 */
	public void setCertainty(double certainty) {
		this.certainty = certainty;
	}
	
	
}
