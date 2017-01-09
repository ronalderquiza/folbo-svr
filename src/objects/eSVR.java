package objects;


import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author		Ronald Erquiza
 * Date:		9/27/2016 12:50PM
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	eSVR.java
 * Description:	epsilon-Support Vector Regression model
 * @version		2.0.2
 *
 * @lastreview 
 * 
 */

public class eSVR {

	private int m;			//the number of training data
	private int n;			//the number of features
	private double[] mu;	//mu - average of features
	private double[] stdev;	//standard deviation
	private double[][] x;	//training data features
	private double[] y;		//training data targets
	private double[] theta;	//parameter of the model
	private double epsilon;	//error tolerance level
	private double c;		//constant boundary
	double alpha;			//learning rate
	private ArrayList<Double> jTheta = new ArrayList<Double>();
	
	/**
	 * initialize eSVR
	 */
	public eSVR() {
		// TODO Auto-generated constructor stub
		//system_manager.getSplashscreen().setLabel("Initializing epsilon-Support Vector Regression...");
		this.setEpsilon(0.001);
		this.alpha = 0.0001;
	}
	
	/**
	 * @param x the features
	 * @param y the targets
	 * @param n the number of features
	 */
	public void setTrainingData(double[][] x, double[] y){
		this.setM(x.length);
		this.setN(x[0].length);
		this.setMu(new double[n]);
		this.setStdev(new double[n]);
		this.setX(x);
		this.setY(y);
		this.setN(n);
		this.setTheta(new double[x[0].length]);
		this.setC(power(upperbound() - lowerbound(), 2));
		setStat();
	}

	/**
	 * setting the statistic values
	 */
	void setStat(){
		double max = 0;
		for(int j = 0; j < getN(); j++){
			double total = 0;
			for(int i = 0; i < getM(); i++){
				total += getX()[i][j];
				if(getX()[i][j] > max)
					max = getX()[i][j];
			}
			getMu()[j] = total/(double)getM();
			getStdev()[j] = max;
		}
	}
	
	/**
	 * training of the model
	 * @return gradientDescent()
	 */
	public double trainData(){
		System.out.println("Training...");
		return gradientDescent(true);
	}
	
	/**
	 * learning algorithm of the model
	 * @param converge
	 * @return min - the local minima/optima
	 */
	double gradientDescent(boolean converge){
		int iter = 0;
		double tempMin = 0;
		double min = cost(iter);
		for(int i = 0; i < getM(); i++)
			getX()[i] = meanNormalization(getX()[i]);
		while(converge){
			double[] temp = new double[getTheta().length];
			for(int j = 0; j < getTheta().length; j++){
				temp[j] = getTheta()[j] - (alpha * derivatives(j));
			}
			for(int j = 0; j < getTheta().length; j++){
				getTheta()[j] = temp[j];
			}
			tempMin = min;
			iter++;
			min = cost(iter);
			converge = (min < tempMin);
			tempMin = min;
		}
		
		return min;
	}
	
	/**
	 * mean normalization for feature scaling
	 * @param x
	 * @return
	 */
	double[] meanNormalization(double[] x){
		for(int j = 0; j < getN(); j++){
			x[j] = (x[j] - getMu()[j])/getStdev()[j];
		}
		return x;
	}
	
	/**
	 * derivation for the gradient descent
	 * @param j
	 * @return derivative of the expression
	 */
	double derivatives(int j){
		double sum = 0;
		for(int i = 0; i < getM(); i++){
			//System.out.println("x["+i+"]["+j+"]="+x[i][j]);
			if(j == 0)
				sum += (hypothesis(getX()[i])-getY()[i]);
			else
				sum += (hypothesis(getX()[i])-getY()[i]) * getX()[i][j];
		}
		return sum/getM();
	}
	
	/**
	 * cost function, sum up the error
	 * @param iter
	 * @return cost
	 */
	public double cost(int iter){
		double sum = 0;
		double cost = 0;
		for(int i = 0; i < getM(); i++){
			double error = power(hypothesis(getX()[i])-getY()[i],2);
			if((error) > (getC() * getEpsilon()))
				sum += error; 
		}
		
		cost = sum/((double)(2 * getM()));
		getjTheta().add(cost);
		return cost;
	}
	
	/**
	 * calculates the upper boundary
	 * @return ub
	 */
	public double upperbound(){
		double ub = 0;
		for(int i = 0; i < getM(); i++){
			if(getY()[i] > ub)
				ub = getY()[i];
		}
		return ub;
	}
	
	/**
	 * calculates the lower boundary
	 * @return lb
	 */
	public double lowerbound(){
		double lb = 1000;
		for(int i = 0; i < getM(); i++){
			if(getY()[i] < lb)
				lb = getY()[i];
		}
		return lb;
	}
	
	/**
	 * power
	 * @param num
	 * @param exponent
	 * @return
	 */
	double power(double num, int exponent){
		double init = 1;
		for(int i = 0; i < exponent; i++){
			init = init * num;
		}
		return init;
	}
	
	/**
	 * the hypothesis of the model
	 * @param x - the features
	 * @return sum
	 */
	public double hypothesis(double[] x){
		double sum = 0;
		for(int i = 0; i < getN(); i++){
			for(int j = 0; j < getN(); j++)
				if(i==0)
					sum += getTheta()[i];
				else
					sum += getTheta()[i]*x[j];
		}
		return sum;
	}
	
	/**
	 * @param input
	 * @return hypothesis()
	 */
	public double predict(double input[]){
		input = meanNormalization(input);
		return hypothesis(input);
	}
	
	/**
	 * computes the certainty of the model
	 * @param index 
	 * @param inputs
	 * @param actual
	 * @return accuracy
	 */
	public double computesCertainty(int index){
		double inputs[][] = x;
		double actual[] = y;
		double total = 0;
		int samples = inputs.length;
		double certainty = 0;
		final double HUNDRED = 100;
		double percent = (HUNDRED - (HUNDRED/(double)index)) / 2;
		DecimalFormat dFormat = new DecimalFormat("#,###");
		for(int i = 0; i < samples; i++){
			double prediction = predict(inputs[i]);
			double error = 0;
			if(actual[i] > prediction)
				error = (Math.abs(actual[i] - prediction)/actual[i]) * HUNDRED;
			else
				error = (Math.abs(actual[i] - prediction)/prediction) * HUNDRED;
			System.out.println("Actual: " + dFormat.format(actual[i]) +
					" & Prediction: " + dFormat.format(prediction) + " -> Error: " + error);
			total += error;
		}
		double aveError = (((total)/(double)samples));
		certainty = HUNDRED-aveError;
		System.out.println("\nAverage Error:" + aveError + "% Percent+: " + percent);
		return certainty;
	}

	/**
	 * @return m
	 */
	public int getM() {
		return m;
	}

	/**
	 * @param m
	 */
	public void setM(int m) {
		this.m = m;
	}

	/**
	 * @return n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @param n
	 */
	public void setN(int n) {
		this.n = n;
	}

	/**
	 * @return mu
	 */
	public double[] getMu() {
		return mu;
	}

	/**
	 * @param mu
	 */
	public void setMu(double[] mu) {
		this.mu = mu;
	}

	/**
	 * @return stdev
	 */
	public double[] getStdev() {
		return stdev;
	}

	/**
	 * @param stdev
	 */
	public void setStdev(double[] stdev) {
		this.stdev = stdev;
	}

	/**
	 * @return c
	 */
	public double getC() {
		return c;
	}

	/**
	 * @param c
	 */
	public void setC(double c) {
		this.c = c;
	}

	/**
	 * @return epsilon
	 */
	public double getEpsilon() {
		return epsilon;
	}

	/**
	 * @param epsilon
	 */
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	/**
	 * @return theta
	 */
	public double[] getTheta() {
		return theta;
	}

	/**
	 * @param theta
	 */
	public void setTheta(double[] theta) {
		this.theta = theta;
	}

	/**
	 * @return y
	 */
	public double[] getY() {
		return y;
	}

	/**
	 * @param y
	 */
	public void setY(double[] y) {
		this.y = y;
	}

	/**
	 * @return x
	 */
	public double[][] getX() {
		return x;
	}

	/**
	 * @param x
	 */
	public void setX(double[][] x) {
		this.x = x;
	}

	/**
	 * @return jTheta
	 */
	public ArrayList<Double> getjTheta() {
		return jTheta;
	}

	/**
	 * @param jTheta
	 */
	public void setjTheta(ArrayList<Double> jTheta) {
		this.jTheta = jTheta;
	}
}

