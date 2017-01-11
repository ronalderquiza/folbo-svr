package tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	TextUploader.java
 * Description:	
 * @version		1.0.1
 *
 * @lastreview 
 * 
 */
public class TextUploader {
	private String text;
	private Scanner scanner;
	
/**
 * @param path
 */
public TextUploader(String path) {
		// TODO Auto-generated constructor stub
		try {
			scanner = new Scanner(new File(path));
			this.text = scanner.useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return text
	 */
	public String getText(){
		return text;
	}

}
