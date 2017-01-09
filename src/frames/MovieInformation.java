package frames;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import objects.MovieInfo;
import main.*;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieInformation.java
 * Description:	Frame for Movie Information
 * @version		1.0.0
 *
 * @lastreview 
 * 
 */
@SuppressWarnings("serial")
public class MovieInformation extends JFrame implements MouseListener, MouseMotionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();
	
	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelNext = new JLabel(cmpmngr.getIconNext());
	JLabel labelBack = new JLabel(cmpmngr.getIconBack());
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelTitle = new JLabel("MOVIE INFORMATION");
	JLabel labelMainGenre = new JLabel("MAIN GENRE:");
	JLabel labelSubGenre = new JLabel("SUB GENRE:");
	JLabel labelMTRCBRating = new JLabel("MTRCB RATING:");
	JLabel labelOrigin = new JLabel("ORIGIN:");
	
	/**
	 * cbMainGenre
	 */
	public JComboBox<String> cbMainGenre = new JComboBox<String>();
	/**
	 * cbSubGenre
	 */
	public JComboBox<String> cbSubGenre = new JComboBox<String>();
	/**
	 * cbMTRCBRating
	 */
	public JComboBox<String> cbMTRCBRating = new JComboBox<String>();
	/**
	 * cbOrigin
	 */
	public JComboBox<String> cbOrigin = new JComboBox<String>();
	
	/**
	 * initialize MovieInformation
	 */
	public MovieInformation() {
		system_manager.getSplashscreen().setLabel("Initializing movie information...");
		getDatabase();
		this.setSize(550, 560);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frames/Photos/Others/FoLBO_Logo.png")));

		this.add(labelBackground);
		labelBackground.add(labelCloseIcon);
		labelBackground.add(labelMinimizeIcon);
		labelBackground.add(labelTitle);
		labelBackground.add(labelMainGenre);
		labelBackground.add(cbMainGenre);	
		labelBackground.add(labelSubGenre);
		labelBackground.add(cbSubGenre);
		labelBackground.add(labelMTRCBRating);
		labelBackground.add(cbMTRCBRating);
		labelBackground.add(labelOrigin);
		labelBackground.add(cbOrigin);
		labelBackground.add(labelNext);
		labelBackground.add(labelBack);
		
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelTitle.setBounds(125, 110, 360, 50);
		labelMainGenre.setBounds(60, 175, 300, 30);
		cbMainGenre.setBounds(200, 180, 300, 25);
		labelSubGenre.setBounds(75, 215, 300, 50);		
		cbSubGenre.setBounds(200, 230, 300, 25);
		labelMTRCBRating.setBounds(40, 265, 300, 50);
		cbMTRCBRating.setBounds(200, 280, 300, 25);
		labelOrigin.setBounds(105, 317, 300, 50);
		cbOrigin.setBounds(200, 330, 300, 30);
		labelNext.setBounds(430, 470, 80, 80);
		labelBack.setBounds(20, 470, 80, 80);
		
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		labelTitle.setFont(component_manager.getFontXLargePlain());
		labelMainGenre.setFont(component_manager.getFontMediumBold());
		cbMainGenre.setFont(component_manager.getFontMediumPlain());
		labelSubGenre.setFont(component_manager.getFontMediumBold());
		cbSubGenre.setFont(component_manager.getFontMediumPlain());
		labelMTRCBRating.setFont(component_manager.getFontMediumBold());
		cbMTRCBRating.setFont(component_manager.getFontMediumPlain());
		labelOrigin.setFont(component_manager.getFontMediumBold());
		cbOrigin.setFont(component_manager.getFontMediumPlain());
		labelNext.setFont(component_manager.getFontMediumBold());
		labelBack.setFont(component_manager.getFontMediumBold());
		
		labelTitle.setForeground(Color.white);
		labelMainGenre.setForeground(Color.white);
		cbMainGenre.setForeground(Color.black);
		labelSubGenre.setForeground(Color.white);
		cbSubGenre.setForeground(Color.black);
		labelMTRCBRating.setForeground(Color.white);
		cbMTRCBRating.setForeground(Color.black);
		labelOrigin.setForeground(Color.white);
		
		labelNext.setBackground(Color.white);
		labelBack.setBackground(Color.white);

		labelNext.setOpaque(false);
		labelBack.setOpaque(false);
		
		//LISTENERS
		labelNext.addMouseListener(this);	
		labelBack.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
		this.addMouseMotionListener(this);
		labelCloseIcon.addMouseListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == labelNext){
			final int MALE = 1;
			final int FEMALE = 2;
			int mainGenre = getCbMainGenre().getSelectedIndex() + 2;
			int subGenre = getCbSubGenre().getSelectedIndex() + 1;
			int origin = getCbOrigin().getSelectedIndex() + 1;
			int mtrcb = getCbMTRCBRating().getSelectedIndex() + 1;
			input_manager input = system_manager.getInput_mngr();
			input.input_movieInfo(mainGenre, subGenre, origin, mtrcb);
			artist_recommender recom = system_manager.getArtist_recom();
			recom.getAllTop().clear();
			if(hasArtist(input.getMovie().getMovieInfo(),MALE))
				recom.recommendArtist(input.getMovie().getMovieInfo(),MALE);
			if(hasArtist(input.getMovie().getMovieInfo(),FEMALE))
				recom.recommendArtist(input.getMovie().getMovieInfo(),FEMALE);
			if(!recom.getAllTop().isEmpty()){
				recom.setAll();
				for(int i = 0; i < recom.getAllTop().size(); i++){
					System.out.println(recom.getAllTop().get(i) + "*");
				}
				GUI_manager.getMovieCasts().setData(recom.getFinalTop());
			}
			else
				GUI_manager.getMovieCasts().setData();
			GUI_manager.getMovieCasts().setVisible(true);
			this.setVisible(false);
		} else if(arg0.getSource() == labelBack) {
			this.setVisible(false);
			GUI_manager.getMovieSequel().setVisible(true);
		} else if(arg0.getSource() == labelCloseIcon) {
			 int dialogButton = JOptionPane.showConfirmDialog (null, "Do you really want to exit?","Confirm",JOptionPane.YES_NO_OPTION);
				if(dialogButton == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
		} else if(arg0.getSource() == labelMinimizeIcon) {
			try { 
				Thread.sleep( 200 ); 
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
		if(arg0.getSource() == this){
			cmpmngr.setPosX(arg0.getX());
			cmpmngr.setPosY(arg0.getY());
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(arg0.getSource() == this) {
			this.setLocation(arg0.getXOnScreen()-cmpmngr.getPosX(),arg0.getYOnScreen()-cmpmngr.getPosY());			
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	
	/**
	 * initialize getDatabase
	 */
	public void getDatabase() {
		database_manager dbmngr = system_manager.getDb_mngr();
		try {
			dbmngr.query("SELECT * FROM `tblgenre`;");
			for (int count = 0; dbmngr.getRs().next(); count++) {
				if(count > 0)
					getCbMainGenre().addItem(dbmngr.getRs().getString("genre"));
				getCbSubGenre().addItem(dbmngr.getRs().getString("genre"));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			dbmngr.query("SELECT * FROM `tblmtrcbrating`;");
			for (; dbmngr.getRs().next();) {
				getCbMTRCBRating().addItem((dbmngr.getRs().getString("mtrcbRate"))+"");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			dbmngr.query("SELECT * FROM `tblorigin`;");
			for (; dbmngr.getRs().next();) {
				getCbOrigin().addItem((dbmngr.getRs().getString("origin"))+"");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private boolean hasArtist(MovieInfo movieInfo, int gender){
		int counter = 0;
		try {
			database_manager db_mngr = system_manager.getDb_mngr();
			String query = "SELECT * FROM `tblartist` " + "INNER JOIN `tblartistmovie` ON "
					+ "`tblartist`.`artistID` = `tblartistmovie`.`artistID` " + "INNER JOIN `tblmovie` ON "
					+ "`tblmovie`.`movieID` = `tblartistmovie`.`movieID` " + "WHERE `tblartist`.`gender` = " + gender + " "
					+ "AND " + "(((`tblmovie`.`mainGenre` = " + movieInfo.getMainGenre() + " "
					+ "AND `tblmovie`.`subGenre` = " + movieInfo.getSubGenre() + ") " + "OR (`tblmovie`.`mainGenre` = "
					+ movieInfo.getSubGenre() + " " + "AND `tblmovie`.`subGenre` = " + movieInfo.getMainGenre() + ") "
					+ "OR `tblmovie`.`theme` = " + movieInfo.getTheme() + " ) "
					+ "AND `tblmovie`.`mtrcbRating` <= " + movieInfo.getMTRCB() + ")";
			db_mngr.query(query);
			for (;db_mngr.getRs().next();) {
				counter++;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return (counter > 0);
	}

	/**
	 * @return cbMainGenre
	 */
	public JComboBox<String> getCbMainGenre() {
		return cbMainGenre;
	}

	/**
	 * @param cbMainGenre
	 */
	public void setCbMainGenre(JComboBox<String> cbMainGenre) {
		this.cbMainGenre = cbMainGenre;
	}

	/**
	 * @return cbSubGenre
	 */
	public JComboBox<String> getCbSubGenre() {
		return cbSubGenre;
	}

	/**
	 * @param cbSubGenre
	 */
	public void setCbSubGenre(JComboBox<String> cbSubGenre) {
		this.cbSubGenre = cbSubGenre;
	}

	/**
	 * @return cbOrigin
	 */
	public JComboBox<String> getCbOrigin() {
		return cbOrigin;
	}

	/**
	 * @param cbOrigin
	 */
	public void setCbOrigin(JComboBox<String> cbOrigin) {
		this.cbOrigin = cbOrigin;
	}

	/**
	 * @return cbMTRCBRating
	 */
	public JComboBox<String> getCbMTRCBRating() {
		return cbMTRCBRating;
	}

	/**
	 * @param cbMTRCBRating
	 */
	public void setCbMTRCBRating(JComboBox<String> cbMTRCBRating) {
		this.cbMTRCBRating = cbMTRCBRating;
	}

}
