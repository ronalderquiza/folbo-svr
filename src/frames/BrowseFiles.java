package frames;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

class BrowseFiles {
	public static String plot;
	public static String title = new String();
	public static String directory;

	public BrowseFiles() {
		plot = "";
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		jfc.setFileFilter(filter);
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("C:\\Users\\LAPTOP\\Desktop\\"));
		chooser.setDialogTitle("Choose Movie Plot");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(filter);

		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			System.out.println("No Selection ");
		}
		directory = chooser.getSelectedFile().toString();
		System.out.println(directory);
		title = cutTitle(directory);
		System.out.println(title);
		try (BufferedReader br = new BufferedReader(new FileReader(directory))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				plot = plot + sCurrentLine;
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// plot = plot.substring(4,plot.length()-1);
	}

	public static String cutTitle(String path) {
		String word = new String();
		int last = 0;
		int start = 0;
		for (int i = path.length() - 1; i > 0; i--) {
			if (path.charAt(i) == '.') {
				last = i;
			}
			if (path.charAt(i) == '\\' || path.charAt(i) == '/') {
				start = i;
				word = path.substring(start + 1, last);
				return word;
			}
		}
		return word;
	}

	public String getPlot() {
		return plot;
	}

	public String getTitle() {
		return title;
	}

}