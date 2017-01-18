package main;

        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.HashMap;

/**
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	starValue_calculator.java
 * Description:	Star Value Calculator of the FoLBO System
 * @author		Ronald Erquiza, Katrina Buca
 * @version		1.0.1
 *
 * @lastreview 20170117
 */
public class starValue_calculator {
    /**
     * Artist Movies
     */
    private HashMap<Integer, Double> artistMovie = new HashMap<Integer, Double>();

    /**
     * Instantiate Star Value Calculator
     */
    starValue_calculator() {
        system_manager.getSplashscreen().setLabel("Initializing star value calculator...");
    }

    /**
     * Execute the calculation
     * @param artists List of artists
     * @param year Current year
     * @return Star Value
     */
    public double execute(ArrayList<Integer> artists, int year){
        double starValue = 0;
        ArrayList<Double> points = new ArrayList<Double>();		//points of every artist
        ArrayList<Double> starvalues = new ArrayList<Double>();	//star value, ranging 1-10
        collectArtistMovie(year);								//collection of artists' movie count
        double stdev = standarddev();
        for(int i = 0; i < artists.size(); i++){
            points.add(artistMovie.get(artists.get(i)));
            if(points.get(i) != null)
                starvalues.add(normalize(points.get(i), stdev));
            else
                starvalues.add(0.0);
        }
        starValue = average(starvalues);
        System.out.println("Star Value: " + starValue);
        return starValue;
    }

    /**
     * Collects all the artists' movie count
     * @param year Year
     */
    private void collectArtistMovie(int year){
        database_manager db = system_manager.getDb_mngr();
        double divisor = 100000000;
        String query = "SELECT SUM(`grossRevenue`), `artistID`, "
                + "COUNT(`artistID`) FROM `tblartistmovie` "
                + "INNER JOIN `tblmovie` ON `tblartistmovie`.`movieID` "
                + "= `tblmovie`.`movieID` WHERE "
                + "`tblmovie`.`year` <= " + (year-1) + " AND "
                + "`tblmovie`.`year` > " + (year-5)
                + " GROUP BY `artistID`";
        try {
            db.query(query);
            for(;db.getResultSet().next();){
                int artistID = db.getResultSet().getInt("artistID");
                int count = db.getResultSet().getInt("COUNT(`artistID`)");
                double gross = db.getResultSet().getDouble("SUM(`grossRevenue`)");
                artistMovie.put(artistID, (gross/count)/divisor);
            }
            db.getResultSet().close();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Calculation of the standard deviation
     * @return Standard Deviation
     */
    private double standarddev(){
        double stdev = 0;
        for(int num: artistMovie.keySet()){
            double movies = artistMovie.get(num);
            if(movies > stdev)
                stdev = movies;
        }
        return stdev;
    }

    /**
     * Normalizing number
     * @param x Number
     * @param stdev Standard Deviation
     * @return percentage
     */
    private double normalize(double x, double stdev){
        double max = 10;
        return (x/stdev) * max;
    }

    /**
     * Sorting Star Value
     * @param star List of Star Value
     * @return List of Star Value
     */
    private static ArrayList<Double> sort(ArrayList<Double> star) {
        double[] newValues = new double[star.size()];
        for(int i = 0; i < newValues.length; i++){
            newValues[i] = star.get(i);
        }

        for(int i = 0; i < newValues.length; i++){
            for(int j = i; j < newValues.length; j++){
                if(star.get(j) > star.get(i)){
                    double temp = newValues[j];
                    newValues[j] = newValues[i];
                    newValues[i] = temp;
                }
            }
        }
        star.clear();
        for(int i = 0; i < newValues.length; i++){
            star.add(newValues[i]);
        }
        return star;
    }

    /**
     * Average of the half artists' star values
     * @param starvalue List of Star Value
     * @return Star Value
     */
    private double average(ArrayList<Double> starvalue) {
        // TODO Auto-generated method stub
        double sum = 0;
        if(starvalue.size() > 1){
            starvalue = sort(starvalue);
            for(int i = 0; i < (starvalue.size()/2); i++){
                sum += starvalue.get(i);
            }
            return sum/(starvalue.size()/2);
        }
        else{
            return starvalue.get(0);
        }

    }
}
