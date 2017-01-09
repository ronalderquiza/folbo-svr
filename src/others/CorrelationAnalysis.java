package others;

/**
 * @author		Ronald Erquiza
 * Email:		ronalderquiza@gmail.com,
 * Filename:	CorrelationAnalysis.java
 * Description:	
 * Version:		1.0.1
 *
 * @lastreview 
 * 
 */

public class CorrelationAnalysis {
	private double[] x;
    private double[] y;
    
	/**
	 * @param x
	 * @param y
	 */
	public CorrelationAnalysis(double[] x, double[] y){
		this.setX(x);
		this.setY(y);
		for(int i = 0; i < x.length; i++){
			//System.out.println("x = " + x[i] + " y = " + y[i]);
		}
	}
	
	/**
	 * @return p
	 */
	public double SROCC(){
		//double n = x.length;
		double p = 0;
		
		return p;
	}
	
	/**
	 * @return r
	 */
	public double PPMCC(){
		double n = getX().length;
		double sxy = sumMultiply(getX(),getY());
		double sx = sum(getX());
		double sy = sum(getY());
		double ssx = sumSquare(getX());
		double ssy = sumSquare(getY());
		double num = ((n * sxy) - (sx * sy));
		double den = Math.sqrt(((n * ssx) - square(sx))*((n * ssy) - square(sy)));
		
		System.out.println("Sxy = " + sxy);
		System.out.println("Sx = " + sx);
		System.out.println("Sy = " + sy);
		System.out.println("Sx^2 = " + ssx);
		System.out.println("Sy^2 = " + ssy);
		System.out.println("(Sx)^2 = " + square(sx));
		System.out.println("(Sy)^2 = " + square(sy));
		
		double r = num / den;
		System.out.println("r = " + r);
		if(-1 < r && r < -0.5)
			System.out.println("SNC");
		return r;
	}
	
	/**
	 * @param val
	 * @return square
	 */
	public double square(double val){
		return val * val;
	}
	/**
	 * @param x
	 * @param y
	 * @return sum
	 */
	public double sumMultiply(double[] x, double[] y){
		double sum = 0;
		for(int i = 0; i < x.length; i++){
			sum += x[i] * y[i];
		}
		return sum;
	}
	
	/**
	 * @param eq
	 * @return sum
	 */
	public double sumSquare(double[] eq){
		double sum = 0;
		for(int i = 0; i < eq.length; i++){
			sum += eq[i] * eq[i];
		}
		return sum;
	}
	
	/**
	 * @param eq
	 * @return sum
	 */
	public double sum(double[] eq){
		double sum = 0;
		for(int i = 0; i < eq.length; i++){
			sum += eq[i];
		}
		return sum;
	}

	/**
	 * @return the x
	 */
	public double[] getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double[] x) {
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
}
