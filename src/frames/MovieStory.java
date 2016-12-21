package frames;
import main.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieStory.java
 * Description:	Frame for Movie Story
 * Version:		
 *
 * @lastreview 
 * 
 */
@SuppressWarnings("serial")
public class MovieStory extends JFrame implements MouseListener, MouseMotionListener, ActionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();
	
	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());;
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelNext = new JLabel(cmpmngr.getIconNext());
	JLabel labelBack = new JLabel(cmpmngr.getIconBack());
	JLabel labelMovieTitle = new JLabel("MOVIE TITLE:");
	JLabel labelMovieTheme = new JLabel("MOVIE THEME:");
	JLabel labelTitle = new JLabel("MOVIE STORY");

	JTextField tfMovieTitle = new JTextField("");
	JTextField tfMovieTheme = new JTextField("");
	JEditorPane taPlot = new JEditorPane();
	JScrollPane scrollPlot = new JScrollPane(taPlot);
	JButton buttonBrowse = new JButton("Browse");
	
	String plot = new String();
	String title = new String();
	/**
	 * initialize MovieStory
	 */
	public MovieStory() {
		system_manager.getSplashscreen().setLabel("Initializing plot browser...");
		this.setSize(550, 560);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frames/Photos/FoLBOLogo.png")));

		this.add(labelBackground);
		labelBackground.add(labelCloseIcon);
		labelBackground.add(labelTitle);
		labelBackground.add(labelMinimizeIcon);
		labelBackground.add(labelMovieTitle);
		labelBackground.add(tfMovieTitle);
		labelBackground.add(buttonBrowse);
		labelBackground.add(scrollPlot, BorderLayout.CENTER);
		labelBackground.add(labelMovieTheme);
		labelBackground.add(tfMovieTheme);
		labelBackground.add(labelNext);
		labelBackground.add(labelBack);
		
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelTitle.setBounds(170, 110, 300, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelMovieTitle.setBounds(40, 147, 160, 50);
		tfMovieTitle.setBounds(160, 160, 340, 25);
		buttonBrowse.setBounds(420, 190, 80, 25);
		scrollPlot.setBounds(40, 220, 460, 210);
		labelMovieTheme.setBounds(40, 428, 160, 40);
		tfMovieTheme.setBounds(180, 435, 320, 25);
		labelNext.setBounds(430, 470, 80, 80);
		labelBack.setBounds(20, 470, 80, 80);
		
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		labelTitle.setFont(component_manager.getFontXLargePlain());
		labelMovieTitle.setFont(component_manager.getFontMediumBold());
		tfMovieTitle.setFont(component_manager.getFontMediumPlain());
		buttonBrowse.setFont(component_manager.getFontSmallPlain());
		taPlot.setFont(component_manager.getFontSmallPlain());
		labelMovieTheme.setFont(component_manager.getFontMediumBold());
		tfMovieTheme.setFont(component_manager.getFontMediumPlain());
		labelNext.setFont(component_manager.getFontMediumBold());
		labelBack.setFont(component_manager.getFontMediumBold());
		
		labelTitle.setForeground(Color.white);
		labelMovieTitle.setForeground(Color.white);
		tfMovieTitle.setForeground(Color.black);
		labelMovieTheme.setForeground(Color.white);
		tfMovieTheme.setForeground(Color.black);
	
		buttonBrowse.setBackground(Color.white);
		labelNext.setBackground(Color.white);
		labelBack.setBackground(Color.white);
		
		scrollPlot.setOpaque(false);
		scrollPlot.setOpaque(false);
		labelNext.setOpaque(false);
		labelBack.setOpaque(false);
		
		tfMovieTheme.setEditable(false);
		
		scrollPlot.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPlot.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPlot.getViewport().setOpaque(false);

		//LISTENERS
		labelNext.addMouseListener(this);
		labelBack.addMouseListener(this);
		buttonBrowse.addActionListener(this);	
		labelCloseIcon.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == labelNext){
			String title = tfMovieTitle.getText();
			system_manager.getInput_mngr().input_story(title, plot, 1);
			this.setVisible(false);
			GUI_manager.getMovieSequel().setVisible(true);
		} else if(arg0.getSource() == labelBack) {
			this.setVisible(false);
			GUI_manager.getHome().setVisible(true);
		} else if (arg0.getSource() == labelCloseIcon) {
			int dialogButton = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		} else if (arg0.getSource() == labelMinimizeIcon) {
			try {
				Thread.sleep(200);
			} catch (Throwable t) {

			}
			this.setState(Frame.ICONIFIED);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getSource() == this) {
			cmpmngr.setPosX(arg0.getX());
			cmpmngr.setPosY(arg0.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			BrowseFiles story = new BrowseFiles();
			taPlot.setText("");
			title = story.getTitle().toUpperCase();
			plot = story.getPlot();
			taPlot.setContentType("text/html");
			String wholeStory = "<html><p align='justify'><font face='Rockwell'><b>" + title + 
								"</b><br><br><i>" + plot + ".</i></font></p></html>";
			tfMovieTitle.setText(title);
			taPlot.setText(wholeStory);
			taPlot.setFont(component_manager.getFontSmallPlain());
			taPlot.setEditable(false);
		
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (arg0.getSource() == this) {
			this.setLocation(arg0.getXOnScreen() - cmpmngr.getPosX(), arg0.getYOnScreen() - cmpmngr.getPosY());
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
