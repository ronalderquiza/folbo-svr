package frames;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.*;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MoviePrequel.java
 * Description:	Frame for Movie Prequel
 * @version		3.2.0
 *
 * @lastreview
 *
 */

@SuppressWarnings("serial")
public class MoviePrequel extends JFrame implements MouseListener, MouseMotionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();


	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelNext = new JLabel(cmpmngr.getIconNext());
	JLabel labelBack = new JLabel(cmpmngr.getIconBack());
	JLabel labelTitle = new JLabel("MOVIE PREQUEL");
	//JLabel labelMovieTitle = new JLabel("MOVIE TITLE:");
	JLabel labelMoviePrequel = new JLabel("PREQUEL:");

	JComboBox<String> cbMovieSequel = new JComboBox<String>();
	//JTextField tfMovieTitle = new JTextField("");
	ArrayList<Integer> movieIDs = new ArrayList<Integer>();

	/**
	 * initialize MoviePrequel
	 */
	public MoviePrequel() {
		system_manager.getSplashscreen().setLabel("Initializing prequels...");
		this.setSize(550, 560);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frames/Photos/Others/FoLBO_Logo.png")));
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));

		this.add(labelBackground);
		labelBackground.add(labelTitle);
		labelBackground.add(labelCloseIcon);
		labelBackground.add(labelMinimizeIcon);
		labelBackground.add(labelNext);
		labelBackground.add(labelBack);
		labelBackground.add(cbMovieSequel);
		//labelBackground.add(labelMovieTitle);
		//labelBackground.add(tfMovieTitle);
		labelBackground.add(labelMoviePrequel);

		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelNext.setBounds(430, 470, 80, 80);
		labelBack.setBounds(20, 470, 80, 80);
		labelTitle.setBounds(160, 120, 500, 50);
		//labelMovieTitle.setBounds(50, 200, 150, 30);
		//tfMovieTitle.setBounds(50, 230, 430, 25);
		labelMoviePrequel.setBounds(50, 220, 150, 30);
		cbMovieSequel.setBounds(50, 250, 430, 30);

		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

		labelNext.setBackground(Color.white);
		labelBack.setBackground(Color.white);

		labelNext.setOpaque(false);
		labelBack.setOpaque(false);

		labelNext.setFont(component_manager.getFontMediumBold());
		labelBack.setFont(component_manager.getFontMediumBold());
		labelTitle.setFont(component_manager.getFontXLargePlain());
		cbMovieSequel.setFont(component_manager.getFontSmallPlain());
		//tfMovieTitle.setFont(component_manager.getFontSmallPlain());
		//labelMovieTitle.setFont(component_manager.getFontMediumBold());
		labelMoviePrequel.setFont(component_manager.getFontMediumBold());

		labelTitle.setForeground(Color.white);
		//labelMovieTitle.setForeground(Color.white);
		labelMoviePrequel.setForeground(Color.white);

		cbMovieSequel.setEditable(false);

		/*text.setText("");
		text.addKeyListener(new ComboKeyHandler(cbMovieSequel));*/

		AutoCompletion.enable(cbMovieSequel);

		getDatabase();

		//LISTENERS
		labelNext.addMouseListener(this);
		labelBack.addMouseListener(this);
		labelCloseIcon.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);


	}


	/**
	 * @param all
	 * @param ID
	 * @return true
	 */
	public boolean containing(ArrayList<Integer> all, int ID){
		for(int i = 0; i < all.size(); i++){
			if(ID == all.get(i)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == labelCloseIcon) {
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
		} else if(arg0.getSource() == labelNext){
			int selectedID = movieIDs.get(cbMovieSequel.getSelectedIndex());
			double[] infos = getInfos(selectedID);
			ArrayList<Integer> mainCasts = getMainCasts(selectedID);
			int mg = (int)infos[1];
			int sg = (int)infos[2];
			int mtrcb = (int)infos[3];
			int origin = (int)infos[4];
			int dir = (int)infos[5];
			int prod = (int)infos[6];
			int dist = (int)infos[7];
			system_manager.getInput_mngr().getMovie().setPrequel(selectedID);
			system_manager.getInput_mngr().input_movieInfo(mg, sg, origin, mtrcb);
			system_manager.getInput_mngr().input_prod(prod, dir, dist);
			system_manager.getInput_mngr().input_mainCasts(mainCasts);
			this.setVisible(false);
			GUI_manager.getMovieRelease().setVisible(true);
		} else if(arg0.getSource() == labelBack) {
			GUI_manager.getMovieSequel().setVisible(true);
			this.setVisible(false);
		}
	}

	private ArrayList<Integer> getMainCasts(int selectedID) {
		// TODO Auto-generated method stub

		database_manager dbmngr = system_manager.getDb_mngr();
		ArrayList<Integer> list = new ArrayList<Integer>();
		String query = "SELECT * FROM `tblartistmovie` WHERE `movieID` = " + selectedID;
		try {
			dbmngr.query(query);
			for(;dbmngr.getResultSet().next();){
				list.add(dbmngr.getResultSet().getInt("artistID"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	private double[] getInfos(int selectedID) {
		// TODO Auto-generated method stub
		database_manager dbmngr = system_manager.getDb_mngr();
		double[] infos = new double[8];
		String query = "SELECT * FROM `tblmovie` WHERE `movieID` = " + selectedID;
		try {
			dbmngr.query(query);
			for(;dbmngr.getResultSet().next();){
				infos[0] = dbmngr.getResultSet().getInt("theme");
				infos[1] = dbmngr.getResultSet().getInt("mainGenre");
				infos[2] = dbmngr.getResultSet().getInt("subGenre");
				infos[3] = dbmngr.getResultSet().getInt("mtrcbRating");
				infos[4] = dbmngr.getResultSet().getInt("origin");
				infos[5] = dbmngr.getResultSet().getInt("director");
				infos[6] = dbmngr.getResultSet().getInt("prodCompany");
				infos[7] = dbmngr.getResultSet().getInt("distributor");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return infos;
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

	/**
	 * initialize getDatabase
	 */
	public void getDatabase() {
		database_manager dbmngr = system_manager.getDb_mngr();
		ArrayList<Integer> all = new ArrayList<Integer>();
		int year = 2014; //system_manager.getCurrYear();
		String query = "SELECT * FROM `tblmovie` WHERE `year` < " + year+ " ORDER BY `year`" ;
		try {
			dbmngr.query(query);
			for (;dbmngr.getResultSet().next();) {
				Integer data = dbmngr.getResultSet().getInt("preSequel");
				all.add(data);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		try {
			dbmngr.query(query);
			for (; dbmngr.getResultSet().next();) {
				if(!containing(all,dbmngr.getResultSet().getInt("movieID"))) {
					cbMovieSequel.addItem(((dbmngr.getResultSet().getString("movieTitle")) + "(" + dbmngr.getResultSet().getInt("year") + ") "));
					movieIDs.add(dbmngr.getResultSet().getInt("movieID"));
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		}

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
}
