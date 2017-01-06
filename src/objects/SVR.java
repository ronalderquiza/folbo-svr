package objects;


import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * @author		Ronald Erquiza
 * Date:		9/27/2016 12:50PM
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	eSVR.java
 * Description:	epsilon-Support Vector Regression model
 * Version:		2.0.2
 *
 * @lastreview 
 * 
 */
public class SVR {

	private int m;
	private int n;
	private double[] mu;
	private double[] stdev;
	private double[][] x;
	private double[] y;
	private double[] theta;
	private double epsilon;
	private double c;
	double alpha;
	private ArrayList<Double> jTheta = new ArrayList<Double>();
	
	/**
	 * @param x
	 * @param y
	 * @param n
	 */
	public SVR(double[][] x, double[] y, int n) {
		// TODO Auto-generated constructor stub
		this.setM(x.length);
		this.setMu(new double[n]);
		this.setStdev(new double[n]);
		this.setX(x);
		this.setY(y);
		this.setN(n);
		this.setTheta(new double[x[0].length]);
		this.setC(power(upperbound() - lowerbound(), 2));
		this.setEpsilon(0.001);
		this.alpha = 0.001;
		setStat();
	}

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
	 * @return gradientDescent
	 */
	public double trainData(){
		//System.out.println("Training...");
		return gradientDescent(true);
	}
	
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
	
	
	double[] meanNormalization(double[] x){
		for(int j = 0; j < getN(); j++){
			x[j] = (x[j] - getMu()[j])/getStdev()[j];
		}
		return x;
	}
	
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
		//System.out.println("Iteration "+iter+": " + cost);
		getjTheta().add(cost);
		return cost;
	}
	
	/**
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
	double power(double num, int exponent){
		double init = 1;
		for(int i = 0; i < exponent; i++){
			init = init * num;
		}
		return init;
	}
	
	/**
	 * @param x
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
	 * @return hypoethesis
	 */ 
	public double predict(double input[]){
		input = meanNormalization(input);
		return hypothesis(input);
	}
	
	/**
	 * @param inputs
	 * @param actual
	 * @return average
	 */
	public double getAccuracy(double inputs[][], double actual[]){
		double total = 0;
		int samples = inputs.length;
		DecimalFormat dFormat = new DecimalFormat("#,###");
		for(int i = 0; i < samples; i++){
			double prediction = predict(inputs[i]);
			double error = (Math.abs(actual[i] - prediction)/actual[i]) * 100;
			System.out.println("Actual: " + dFormat.format(actual[i]) +
					" & Prediction: " + dFormat.format(prediction) + " -> Error: " + error);
			total += error;
		}
		double aveError = total/samples;
		System.out.println("\nAverage Error:" + aveError + "%");
		return 100-(total/(double)samples);
	}

	/**
	 * @return the m
	 */
	public int getM() {
		return m;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(int m) {
		this.m = m;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}

	/**
	 * @return the mu
	 */
	public double[] getMu() {
		return mu;
	}

	/**
	 * @param mu the mu to set
	 */
	public void setMu(double[] mu) {
		this.mu = mu;
	}

	/**
	 * @return the stdev
	 */
	public double[] getStdev() {
		return stdev;
	}

	/**
	 * @param stdev the stdev to set
	 */
	public void setStdev(double[] stdev) {
		this.stdev = stdev;
	}

	/**
	 * @return the c
	 */
	public double getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(double c) {
		this.c = c;
	}

	/**
	 * @return the epsilon
	 */
	public double getEpsilon() {
		return epsilon;
	}

	/**
	 * @param epsilon the epsilon to set
	 */
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	/**
	 * @return the x
	 */
	public double[][] getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double[][] x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double[] getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double[] y) {
		this.y = y;
	}

	/**
	 * @return the theta
	 */
	public double[] getTheta() {
		return theta;
	}

	/**
	 * @param theta the theta to set
	 */
	public void setTheta(double[] theta) {
		this.theta = theta;
	}

	/**
	 * @return the jTheta
	 */
	public ArrayList<Double> getjTheta() {
		return jTheta;
	}

	/**
	 * @param jTheta the jTheta to set
	 */
	public void setjTheta(ArrayList<Double> jTheta) {
		this.jTheta = jTheta;
	}
}

