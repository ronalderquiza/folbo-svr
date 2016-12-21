package objects;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.system_manager;
/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	Tokenizer.java
 * Description:	tokenizer tool
 * Version:		1.0.1
 *
 * @lastreview 20161203
 * Ron, Kat, Ran
 *
 */
public class Tokenizer {
	
	/**
	 * initialize tokenizer
	 */
	public Tokenizer() {
		// TODO Auto-generated constructor stub
		system_manager.getSplashscreen().setLabel("Initializing Tokenizer...");
	}
	
	/**
	 * 
	 * @param string
	 * @return array of tokens
	 * tokenizes the string
	 */
	public ArrayList<String> tokenize(String string){
		final int FIRST = 0;
		ArrayList<String> tokenList = new ArrayList<String>();
		String regex = "([\\w’'`]+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		while(matcher.find()){
			String token = new String();
			token = matcher.group(FIRST).trim();
			tokenList.add(token);
		}
		return tokenList;
	}
}
