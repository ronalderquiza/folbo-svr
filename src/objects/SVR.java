package objects;


import java.text.DecimalFormat;
import java.util.ArrayList;

/*By: Ronald K. Erquiza
 *Date: 9/27/2016 12:50PM
 *Title: epsilon-Support Vector Regression (Linear Kernel)
 */

public class SVR {

	public int m;
	public int n;
	public double[] mu;
	public double[] stdev;
	public double[][] x;
	public double[] y;
	public double[] theta;
	public double epsilon;
	public double c;
	double alpha;
	public ArrayList<Double> jTheta = new ArrayList<Double>();
	
	public SVR(double[][] x, double[] y, int n) {
		// TODO Auto-generated constructor stub
		this.m = x.length;
		this.mu = new double[n];
		this.stdev = new double[n];
		this.x = x;
		this.y = y;
		this.n = n;
		this.theta = new double[x[0].length];
		this.c = power(upperbound() - lowerbound(), 2);
		this.epsilon = 0.001;
		this.alpha = 0.001;
		setStat();
	}

	void setStat(){
		double max = 0;
		for(int j = 0; j < n; j++){
			double total = 0;
			for(int i = 0; i < m; i++){
				total += x[i][j];
				if(x[i][j] > max)
					max = x[i][j];
			}
			mu[j] = total/(double)m;
			stdev[j] = max;
		}
	}
	
	public double trainData(){
		//System.out.println("Training...");
		return gradientDescent(true);
	}
	
	double gradientDescent(boolean converge){
		int iter = 0;
		double tempMin = 0;
		double min = cost(iter);
		for(int i = 0; i < m; i++)
			x[i] = meanNormalization(x[i]);
		while(converge){
			double[] temp = new double[theta.length];
			for(int j = 0; j < theta.length; j++){
				temp[j] = theta[j] - (alpha * derivatives(j));
			}
			for(int j = 0; j < theta.length; j++){
				theta[j] = temp[j];
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
		for(int j = 0; j < n; j++){
			x[j] = (x[j] - mu[j])/stdev[j];
		}
		return x;
	}
	
	double derivatives(int j){
		double sum = 0;
		for(int i = 0; i < m; i++){
			//System.out.println("x["+i+"]["+j+"]="+x[i][j]);
			if(j == 0)
				sum += (hypothesis(x[i])-y[i]);
			else
				sum += (hypothesis(x[i])-y[i]) * x[i][j];
		}
		return sum/m;
	}
	
	public double cost(int iter){
		double sum = 0;
		double cost = 0;
		for(int i = 0; i < m; i++){
			double error = power(hypothesis(x[i])-y[i],2);
			if((error) > (c * epsilon))
				sum += error; 
		}
		
		cost = sum/((double)(2 * m));
		//System.out.println("Iteration "+iter+": " + cost);
		jTheta.add(cost);
		return cost;
	}
	
	public double upperbound(){
		double ub = 0;
		for(int i = 0; i < m; i++){
			if(y[i] > ub)
				ub = y[i];
		}
		return ub;
	}
	
	public double lowerbound(){
		double lb = 1000;
		for(int i = 0; i < m; i++){
			if(y[i] < lb)
				lb = y[i];
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
	
	public double hypothesis(double[] x){
		double sum = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				if(i==0)
					sum += theta[i];
				else
					sum += theta[i]*x[j];
		}
		return sum;
	}
	
	public double predict(double input[]){
		input = meanNormalization(input);
		return hypothesis(input);
	}
	
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
}

