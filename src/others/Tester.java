package others;

import java.text.DecimalFormat;

import objects.SVR;
import objects.eSVR;

public class Tester {

	/*private static String host = "jdbc:mysql://localhost/";
	private static String database = "dbfolbo";
	private static String user = "root";
	private static String pass = "";
	static database_manager dbmngr = new database_manager(host, database, user, pass);*/
	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		double[][] x = {{7}, {15}};
		double[] y = {15, 44};
		SVR svr = new SVR(x, y, x[0].length);
		eSVR esvr = new eSVR();
		DecimalFormat dFormat = new DecimalFormat("#,###.##");
		esvr.setTrainingData(x, y);
		System.out.println(svr.trainData() + "\nn = " + x[0].length);
		System.out.println(esvr.trainData() + "\nn = " + x[0].length);
		for(int i = 0; i < x.length; i++){
			double output = svr.predict(x[i]);
			double output2 = esvr.predict(x[i]);
			System.out.println("Actual: " + y[i] + "\tPrediction: " + dFormat.format(output));
			System.out.println("\t\tPrediction: " + dFormat.format(output2));
		}
	}
}
