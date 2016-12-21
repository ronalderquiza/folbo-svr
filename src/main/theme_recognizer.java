
package main;

import objects.NGram;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	theme_recognizer.java
 * Description:	Recognizes the theme from the Movie Information
 * Version:		1.0.0
 *
 * @lastreview
 *
 */
public class theme_recognizer {
	private NGram ngram;
	/**
	 * initialize theme_recognizer
	 */
	public theme_recognizer() {
		// TODO Auto-generated constructor stub
		system_manager.getSplashscreen().setLabel("Initializing Theme Recognizer...");
		setNgram(new NGram());
	}
	
	/**
	 * @return ngram
	 */
	public NGram getNgram() {
		return ngram;
	}
	
	/**
	 * @param ngram
	 */
	public void setNgram(NGram ngram) {
		this.ngram = ngram;
	}

}
