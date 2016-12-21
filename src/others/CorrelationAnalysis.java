package others;


public class CorrelationAnalysis {
	public double[] x;
    public double[] y;
    
	public CorrelationAnalysis(double[] x, double[] y){
		this.x = x;
		this.y = y;
		for(int i = 0; i < x.length; i++){
			//System.out.println("x = " + x[i] + " y = " + y[i]);
		}
	}
	
	public double SROCC(){
		//double n = x.length;
		double p = 0;
		
		return p;
	}
	
	public double PPMCC(){
		double n = x.length;
		double sxy = sumMultiply(x,y);
		double sx = sum(x);
		double sy = sum(y);
		double ssx = sumSquare(x);
		double ssy = sumSquare(y);
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
	
	public double square(double val){
		return val * val;
	}
	public double sumMultiply(double[] x, double[] y){
		double sum = 0;
		for(int i = 0; i < x.length; i++){
			sum += x[i] * y[i];
		}
		return sum;
	}
	
	public double sumSquare(double[] eq){
		double sum = 0;
		for(int i = 0; i < eq.length; i++){
			sum += eq[i] * eq[i];
		}
		return sum;
	}
	
	public double sum(double[] eq){
		double sum = 0;
		for(int i = 0; i < eq.length; i++){
			sum += eq[i];
		}
		return sum;
	}
}
