package objects;
import java.util.ArrayList;

import main.system_manager;
/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	NGram.java
 * Description:	N-Gram tool
 * @version		1.2.1
 *
 * @lastreview 20161203
 * Ron, Kat, Ran
 *
 */

@SuppressWarnings("serial")
public class NGram extends java.util.HashMap<String, Integer> {
	String text;
	int start;
	int order;
	Tokenizer tokenizer;
	
	/**
	 * initialize ngram
	 */
	public NGram() {
		// TODO Auto-generated constructor stub
		system_manager.getSplashscreen().setLabel("Initializing N-Gram...");
		start = 1;
		order = 1;
		text = new String();
		tokenizer = new Tokenizer();
	}
	
	/**
	 * @param start
	 */
	public void setStart(int start){
		this.start = start;
	}
	
	/**
	 * @param order
	 */
	public void setOrder(int order){
		this.order = order;
	}
	
	/**
	 * @param text
	 */
	public void setText(String text){
		this.text = text;
	}
	
	/**
	 * @return start
	 */
	public int getStart(){
		return this.start;
	}
	
	/**
	 * @return order
	 */
	public int getOrder(){
		return this.order;
	}
	
	/**
	 * @return text
	 */
	public String getText(){
		return this.text;
	}
	
	/**
	 * 
	 * @return NGram object
	 * i is the index of tokens
	 * j is the number of grams
	 * k is the index for collecting grams
	 * collects the N-Gram of the text
	 */
	public NGram collectGram(){
		ArrayList<String> tokenList = tokenizer.tokenize(text);
		for(int i = 0, j = start; j <= order; i++){
			String token = new String();
			if(i == tokenList.size()){
				i = 0;
				j++;
			}
			if(((i + j) <= tokenList.size()) && (j <= order)){
				for(int k = 0; k < j; k++){
					token = token + " " + tokenList.get(i + k);
				}
				token = token.trim();
				if(!token.equals("")){
					if(this.get(token)==null){
						this.put(token, 1);
					}
					else{
						int frequency = this.get(token.trim())+1;
						this.put(token, frequency);
					}
				}
			}
		}
		return this;
	}
}
