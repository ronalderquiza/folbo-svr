package frames;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import main.*;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieProduction.java
 * Description:	Frame for Movie Production
 * @version		1.0.0
 *
 * @lastreview 
 * 
 */

@SuppressWarnings("serial")
public class MovieProduction extends JFrame implements MouseListener, MouseMotionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();
	
	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelNext = new JLabel(cmpmngr.getIconNext());
	JLabel labelBack = new JLabel(cmpmngr.getIconBack());
	JLabel labelTitle = new JLabel("MOVIE PRODUCTION");
	JLabel labelProduction = new JLabel("PRODUCTION COMPANY:");
	JLabel labelDistributor = new JLabel("DISTRIBUTED BY:");
	JLabel labelDirector = new JLabel("DIRECTOR:");
	
	JComboBox<String> cbDirector = new JComboBox<String>();
	JComboBox<String> cbProduction = new JComboBox<String>();
	JComboBox<String> cbDistributor = new JComboBox<String>();
	
	/**
	 * initialize MovieProduction
	 */
	public MovieProduction() {
		system_manager.getSplashscreen().setLabel("Initializing movie production...");
		getDatabase();
		this.setSize(550, 560);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frames/Photos/Others/FoLBO_Logo.png")));
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		this.setVisible(false);
		
		this.add(labelBackground);
		labelBackground.add(labelCloseIcon);
		labelBackground.add(labelMinimizeIcon);
		labelBackground.add(labelTitle);
		labelBackground.add(labelDirector);
		labelBackground.add(cbDirector);
		labelBackground.add(labelProduction);
		labelBackground.add(cbProduction);
		labelBackground.add(labelDistributor);
		labelBackground.add(cbDistributor);
		labelBackground.add(labelNext);
		labelBackground.add(labelBack);
		
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelTitle.setBounds(140, 110, 400, 50);
		labelDirector.setBounds(120, 175, 300, 30);
		cbDirector.setBounds(230, 180, 300, 25);
		labelProduction.setBounds(10, 215, 300, 50);
		cbProduction.setBounds(230, 230, 300, 25);
		labelDistributor.setBounds(75, 265, 300, 50);
		cbDistributor.setBounds(230, 280, 300, 25);
		labelNext.setBounds(430, 470, 80, 80);
		labelBack.setBounds(20, 470, 80, 80);
		
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
		labelTitle.setFont(component_manager.getFontXLargePlain());
		labelDirector.setFont(component_manager.getFontMediumBold());
		cbDirector.setFont(component_manager.getFontMediumPlain());
		labelProduction.setFont(component_manager.getFontMediumBold());
		cbProduction.setFont(component_manager.getFontMediumPlain());
		labelDistributor.setFont(component_manager.getFontMediumBold());
		cbDistributor.setFont(component_manager.getFontMediumPlain());
		labelNext.setFont(component_manager.getFontMediumBold());
		labelBack.setFont(component_manager.getFontMediumBold());
		
		labelTitle.setForeground(Color.white);
		labelDirector.setForeground(Color.white);
		cbDirector.setForeground(Color.black);
		labelProduction.setForeground(Color.white);
		cbProduction.setForeground(Color.black);
		labelDistributor.setForeground(Color.white);
		cbDistributor.setForeground(Color.black);
		
		labelNext.setBackground(Color.white);
		labelBack.setBackground(Color.white);
	
		labelNext.setOpaque(false);	
		labelBack.setOpaque(false);
		
		//LISTENERS
		labelNext.addMouseListener(this);	
		labelBack.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
		labelCloseIcon.addMouseListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == labelNext){
			GUI_manager.getMovieRelease().setVisible(true);
			this.setVisible(false);
			int distributor = cbDistributor.getSelectedIndex()+1;
			int director = cbDirector.getSelectedIndex()+1;
			int producer = cbProduction.getSelectedIndex()+1;
			
			input_manager input = system_manager.getInput_mngr();
			input.input_prod(distributor, director, producer);
			
			system_manager.getInput_mngr().input_prod(producer, director, distributor);
		}
		else if(arg0.getSource() == labelBack){
			GUI_manager.getMovieCasts().setVisible(true);
			this.setVisible(false);
		} else if(arg0.getSource() == labelCloseIcon){
			int dialogButton = JOptionPane.showConfirmDialog (null, "Do you really want to exit?","Confirm",JOptionPane.YES_NO_OPTION);
			if(dialogButton == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		else if(arg0.getSource() == labelMinimizeIcon){
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
	
	/**
	 * initialize getDatabase
	 */
	public void getDatabase() {
		database_manager dbmngr = system_manager.getDb_mngr();
		try {
			dbmngr.query("SELECT * FROM `tbldirector`;");
			for (; dbmngr.getResultSet().next();) {
				cbDirector.addItem(dbmngr.getResultSet().getString("directorName"));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			dbmngr.query("SELECT * FROM `tblprodcompany`");
			for (; dbmngr.getResultSet().next();) {
				cbProduction.addItem((dbmngr.getResultSet().getString("prodCompany"))+"");
				cbDistributor.addItem((dbmngr.getResultSet().getString("prodCompany"))+"");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		
	}

}
