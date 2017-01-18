package main;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.Movie;
import svr.eSVR;
import tools.DBQueries;
/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	machineLearning_manager.java
 * Description:	Machine Learning Manager of the FoLBO System
 * @version		2.1.3
 *
 * @lastreview
 */
public class machineLearning_manager {
	private eSVR model;
	private double output;
	private double certainty;
	private int index = 0;
	
	/**
	 * initializing machine learning manager
	 */
	public machineLearning_manager(){
		system_manager.getSplashscreen().setLabel("Initializing machine learning manager...");
		setModel(new eSVR());
	}
	
	/**
	 * execute the machine learning
	 * training
	 * predicting
	 * @param input_data 
	 */
	public void execute(Movie input_data){
		Movie movie = input_data;
		double[][] training_dataX = setTrainingDataX(movie);	//features
		double[] training_dataY = setTrainingDataY(movie);		//targets
		double[] input = {movie.getProdInfo().getStarValue(),
							movie.getRelease().getYear(),
							movie.getRelease().getMonth(),
							movie.getMovieInfo().getTheme()
						};


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
	 * setting the training data features
	 * @param movie
	 * @return
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
					for(;dbmngr.getRs().next();){
						Double[] record = new Double[4];
						record[0] = dbmngr.getRs().getDouble("starValue");
						record[1] = (double)dbmngr.getRs().getInt("year");
						record[2] = (double)dbmngr.getRs().getInt("month");
						record[3] = (double)dbmngr.getRs().getInt("theme");
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
						for(;dbmngr.getRs().next();){
							id = dbmngr.getRs().getInt("preSequel");
							Double[] record = new Double[3];
							record[0] = dbmngr.getRs().getDouble("starValue");
							record[1] = (double)dbmngr.getRs().getInt("year");
							record[2] = (double)dbmngr.getRs().getInt("month");
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
	 * setting the training data label
	 * @param movie
	 * @return
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
					for(;dbmngr.getRs().next();){
						dataList.add(dbmngr.getRs().getDouble("grossRevenue"));
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
						for(;dbmngr.getRs().next();){
							id = dbmngr.getRs().getInt("preSequel");
							dataList.add(dbmngr.getRs().getDouble("grossRevenue"));
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
	 * @return model
	 */
	public eSVR getModel() {
		return model;
	}
	
	/**
	 * @param model
	 */
	public void setModel(eSVR model) {
		this.model = model;
	}

	/**
	 * @return output
	 */
	public double getOutput() {
		return output;
	}

	/**
	 * @param output
	 */
	public void setOutput(double output) {
		this.output = output;
	}

	/**
	 * @return certainty
	 */
	public double getCertainty() {
		return certainty;
	}

	/**
	 * @param certainty
	 */
	public void setCertainty(double certainty) {
		this.certainty = certainty;
	}
	
	
}
