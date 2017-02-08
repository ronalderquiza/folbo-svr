package tools;

import main.system_manager;
import objects.Movie;
import objects.MovieInfo;

/**
 * @author		Ronald Erquiza
 * Email:		ronalderquiza@gmail.com
 * Filename:	DBQueries.java
 * Description:	
 * @version		1.0.1
 *
 * @lastreview 
 * 
 */

public class DBQueries {

	Movie movie = system_manager.getInput_mngr().getMovie();
	
	/**
	 * Initialize DBQueries
	 */
	public DBQueries() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gender
	 * @return queries
	 */
	public String[] getArtistDataQueries(int gender){
		MovieInfo movieInfo = movie.getMovieInfo();
		int currYear = 2014;

		String query1 = "SELECT * FROM `tblartist` " + "INNER JOIN `tblartistmovie` ON "
				+ "`tblartist`.`artistID` = `tblartistmovie`.`artistID` " + "INNER JOIN `tblmovie` ON "
				+ "`tblmovie`.`movieID` = `tblartistmovie`.`movieID` " + "WHERE "
				+ "`tblartist`.`gender` = " + gender + " AND " 
				+ "(((`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " AND "
				+ "`tblmovie`.`subGenre` = " + movieInfo.getSubGenre() + ") OR "
				+ "(`tblmovie`.`mainGenre` = " + movieInfo.getSubGenre() + " AND "
				+ "`tblmovie`.`subGenre` = " + movieInfo.getMainGenre() + ") AND "
				+ "`tblmovie`.`theme` = " + movieInfo.getTheme() + " ) AND "
				+ "`tblmovie`.`mtrcbRating` <= " + movieInfo.getMTRCB() + ") AND "
				+ "`tblmovie`.`year` <= " + (currYear-1) + " AND "
				+ "`tblmovie`.`year` > " + (currYear-5) + " GROUP BY `tblartist`.`artistID`";
		
		String query2 = "SELECT * FROM `tblartist` " + "INNER JOIN `tblartistmovie` ON "
				+ "`tblartist`.`artistID` = `tblartistmovie`.`artistID` " + "INNER JOIN `tblmovie` ON "
				+ "`tblmovie`.`movieID` = `tblartistmovie`.`movieID` " + "WHERE "
				+ "`tblartist`.`gender` = " + gender + " AND " 
				+ "(((`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " OR "
				+ "`tblmovie`.`subGenre` = " + movieInfo.getSubGenre() + ") OR "
				+ "(`tblmovie`.`mainGenre` = " + movieInfo.getSubGenre() + " OR "
				+ "`tblmovie`.`subGenre` = " + movieInfo .getMainGenre() + ") OR "
				+ "`tblmovie`.`theme` = " + movieInfo.getTheme() + " ) AND "
				+ "`tblmovie`.`mtrcbRating` <= " + movieInfo.getMTRCB() + ") AND "
				+ "`tblmovie`.`year` <= " + (currYear-1) + " AND "
				+ "`tblmovie`.`year` > " + (currYear-5)  + " GROUP BY `tblartist`.`artistID`";
		
		String query3 = "SELECT * FROM `tblartist` " + "INNER JOIN `tblartistmovie` ON "
				+ "`tblartist`.`artistID` = `tblartistmovie`.`artistID` " + "INNER JOIN `tblmovie` ON "
				+ "`tblmovie`.`movieID` = `tblartistmovie`.`movieID` " + "WHERE "
				+ "`tblartist`.`gender` = " + gender + " AND " 
				+ "(((`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " OR "
				+ "`tblmovie`.`subGenre` = " + movieInfo.getSubGenre() + ") OR "
				+ "(`tblmovie`.`mainGenre` = " + movieInfo.getSubGenre() + " OR "
				+ "`tblmovie`.`subGenre` = " + movieInfo .getMainGenre() + ") OR "
				+ "`tblmovie`.`theme` = " + movieInfo.getTheme() + " ) AND "
				+ "`tblmovie`.`mtrcbRating` <= " + movieInfo.getMTRCB() + ") AND "
				+ "`tblmovie`.`year` <= " + (currYear-1) + " AND "
				+ "`tblmovie`.`year` > " + (currYear-5)  + " GROUP BY `tblartist`.`artistID`";
		
		String[] queries = {query1, query2, query3};
		
		String queryNS1 = "SELECT * FROM `tblartist` " + "INNER JOIN `tblartistmovie` ON "
				+ "`tblartist`.`artistID` = `tblartistmovie`.`artistID` " + "INNER JOIN `tblmovie` ON "
				+ "`tblmovie`.`movieID` = `tblartistmovie`.`movieID` " + "WHERE "
				+ "`tblartist`.`gender` = " + gender + " AND " 
				+ "(((`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " AND "
				+ "`tblmovie`.`subGenre` = " + movieInfo.getMainGenre() + ") OR "
				+ "(`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " AND "
				+ "`tblmovie`.`subGenre` = " + movieInfo.getMainGenre() + ") AND "
				+ "`tblmovie`.`theme` = " + movieInfo.getTheme() + " ) AND "
				+ "`tblmovie`.`mtrcbRating` <= " + movieInfo.getMTRCB() + ") AND "
				+ "`tblmovie`.`year` <= " + (currYear-1) + " AND "
				+ "`tblmovie`.`year` > " + (currYear-5) + " GROUP BY `tblartist`.`artistID`";
		
		String queryNS2 = "SELECT * FROM `tblartist` " + "INNER JOIN `tblartistmovie` ON "
				+ "`tblartist`.`artistID` = `tblartistmovie`.`artistID` " + "INNER JOIN `tblmovie` ON "
				+ "`tblmovie`.`movieID` = `tblartistmovie`.`movieID` " + "WHERE "
				+ "`tblartist`.`gender` = " + gender + " AND " 
				+ "(((`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " OR "
				+ "`tblmovie`.`subGenre` = " + movieInfo.getMainGenre() + ") OR "
				+ "(`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " OR "
				+ "`tblmovie`.`subGenre` = " + movieInfo .getMainGenre() + ") OR "
				+ "`tblmovie`.`theme` = " + movieInfo.getTheme() + " ) AND "
				+ "`tblmovie`.`mtrcbRating` <= " + movieInfo.getMTRCB() + ") AND "
				+ "`tblmovie`.`year` <= " + (currYear-1) + " AND "
				+ "`tblmovie`.`year` > " + (currYear-5)  + " GROUP BY `tblartist`.`artistID`";
		
		String queryNS3 = "SELECT * FROM `tblartist` " + "INNER JOIN `tblartistmovie` ON "
				+ "`tblartist`.`artistID` = `tblartistmovie`.`artistID` " + "INNER JOIN `tblmovie` ON "
				+ "`tblmovie`.`movieID` = `tblartistmovie`.`movieID` " + "WHERE "
				+ "`tblartist`.`gender` = " + gender + " AND " 
				+ "(((`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " OR "
				+ "`tblmovie`.`subGenre` = " + movieInfo.getMainGenre() + ") OR "
				+ "(`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " OR "
				+ "`tblmovie`.`subGenre` = " + movieInfo .getMainGenre() + ") OR "
				+ "`tblmovie`.`theme` = " + movieInfo.getTheme() + " ) AND "
				+ "`tblmovie`.`mtrcbRating` <= " + movieInfo.getMTRCB() + ") AND "
				+ "`tblmovie`.`year` <= " + (currYear-1) + " AND "
				+ "`tblmovie`.`year` > " + (currYear-5)  + " GROUP BY `tblartist`.`artistID`";
		
		String[] queriesNS = {queryNS1, queryNS2, queryNS3};
		
		if(movieInfo.getSubGenre() > 1){
			return queries;
		}
		else{
			return queriesNS;
		}
	}	
	
	/**
	 * @return queries
	 */
	public String[] getTrainingDataQueries(){
		int currYear = 2016;
		String q1 = "SELECT * FROM `tblmovie` WHERE "
				+ "(`theme` = " + movie.getMovieInfo().getTheme() + " OR "
				+ "((`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " AND "
				+ "`subGenre` = " + movie.getMovieInfo().getSubGenre() + ") OR"
				+ "(`subGenre` = " + movie.getMovieInfo().getMainGenre() + " AND "
				+ "`mainGenre` = " + movie.getMovieInfo().getSubGenre() + "))) AND "
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + " AND "
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " ) AND "
				+ "`mtrcbRating` <= " + movie.getMovieInfo().getMTRCB() + " AND "
				+ "`year` <= " + currYear;
		
		String qs1 = "SELECT * FROM `tblmovie` WHERE "
				+ "(`theme` = " + movie.getMovieInfo().getTheme() + " OR "
				+ "(`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`subGenre` = " + movie.getMovieInfo().getMainGenre() + ")) AND "
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + " AND "
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " ) AND "
				+ "`mtrcbRating` <= " + movie.getMovieInfo().getMTRCB() + " AND "
				+ "`year` <= " + currYear;
		
		String q2 = "SELECT * FROM `tblmovie` WHERE "
				+ "`theme` = " + movie.getMovieInfo().getTheme() + " AND ("
				+ "((`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " AND "
				+ "`subGenre` = " + movie.getMovieInfo().getSubGenre() + ") OR"
				+ "(`subGenre` = " + movie.getMovieInfo().getMainGenre() + " AND "
				+ "`mainGenre` = " + movie.getMovieInfo().getSubGenre() + ")) AND "
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + ") AND "
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " ) AND "
				+ "`mtrcbRating` <= " + movie.getMovieInfo().getMTRCB()  + " AND "
						+ "`year` <= " + currYear;
		
		String qs2 = "SELECT * FROM `tblmovie` WHERE "
				+ "`theme` = " + movie.getMovieInfo().getTheme() + " AND ("
				+ "(`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`subGenre` = " + movie.getMovieInfo().getMainGenre() + ") AND"
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + ") AND " 
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " ) AND "
				+ "`mtrcbRating` <= " + movie.getMovieInfo().getMTRCB()  + " AND "
				+ "`year` <= " + currYear;
		
		String q3 = "SELECT * FROM `tblmovie` WHERE "
				+ "(`theme` = " + movie.getMovieInfo().getTheme() + " AND "
				+ "((`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`subGenre` = " + movie.getMovieInfo().getSubGenre() + ") OR"
				+ "(`subGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`mainGenre` = " + movie.getMovieInfo().getSubGenre() + ")) AND "
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + ") OR "
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " ) AND "
				+ "`mtrcbRating` <= " + movie.getMovieInfo().getMTRCB()  + " AND "
				+ "`year` <= " + currYear;
		
		String qs3 = "SELECT * FROM `tblmovie` WHERE "
				+ "(`theme` = " + movie.getMovieInfo().getTheme() + " AND "
				+ "(`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`subGenre` = " + movie.getMovieInfo().getMainGenre() + ") AND"
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + ") OR " 
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " ) AND "
				+ "`mtrcbRating` <= " + movie.getMovieInfo().getMTRCB()  + " AND "
				+ "`year` <= " + currYear;
		
		String q4 = "SELECT * FROM `tblmovie` WHERE "
				+ "(`theme` = " + movie.getMovieInfo().getTheme() + " AND "
				+ "((`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`subGenre` = " + movie.getMovieInfo().getSubGenre() + ") OR"
				+ "(`subGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`mainGenre` = " + movie.getMovieInfo().getSubGenre() + ")) AND "
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + ") OR "
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " )" + " AND "
				+ "`year` <= " + currYear;

		String qs4 = "SELECT * FROM `tblmovie` WHERE "
				+ "(`theme` = " + movie.getMovieInfo().getTheme() + " AND "
				+ "(`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`subGenre` = " + movie.getMovieInfo().getMainGenre() + ") AND"
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + ") OR " 
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " )" + " AND "
				+ "`year` <= " + currYear;

		String q5 = "SELECT * FROM `tblmovie` WHERE "
				+ "(`theme` = " + movie.getMovieInfo().getTheme() + " AND "
				+ "(`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`subGenre` = " + movie.getMovieInfo().getMainGenre() + ") AND"
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + ") OR " 
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " )" + " AND "
				+ "`year` <= " + currYear;
		
		String qs5 = "SELECT * FROM `tblmovie` WHERE "
				+ "(`theme` = " + movie.getMovieInfo().getTheme() + " AND "
				+ "(`mainGenre` = " + movie.getMovieInfo().getMainGenre() + " OR "
				+ "`subGenre` = " + movie.getMovieInfo().getMainGenre() + ") AND"
				+ "`origin` = " + movie.getMovieInfo().getOrigin() + ") OR " 
				+ "(`director` = " + movie.getProdInfo().getDirector() + " OR "
				+ "`prodCompany` = " + movie.getProdInfo().getProducer() + " OR "
				+ "`distributor` = " + movie.getProdInfo().getDistributor() + " )" + " AND "
				+ "`year` <= " + currYear;
		
		String[] queries = {q1,q2,q3,q4,q5};
		String[] queriesNoSub = {qs1, qs2, qs3, qs4, qs5};
		if(movie.getMovieInfo().getSubGenre() > 1)
			return queries;
		else
			return queriesNoSub;
	}
}
