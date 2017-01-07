package frames;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.*;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieProductionRelease.java
 * Description:	Frame for Movie Production Release
 * Version:		
 *
 * @lastreview 
 * 
 */

@SuppressWarnings("serial")
public class MovieRelease extends JFrame implements MouseListener, MouseMotionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();
	
	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelNext = new JLabel(cmpmngr.getIconNext());
	JLabel labelBack = new JLabel(cmpmngr.getIconBack());
	JLabel labelTitle = new JLabel("MOVIE RELEASE");
	JLabel labelMonth = new JLabel("MONTH:");
	JLabel labelYear = new JLabel("YEAR:");
	
	String[] strMonth = {"January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December"};
	String[] strYear = {"2014", "2015", "2016", "2017"};
	JComboBox<String> cbMonth = new JComboBox<String>(strMonth);
	JComboBox<String> cbYear = new JComboBox<String>(strYear);
	
	
	/**
	 * initialize MovieProdutionRelease
	 * 
	 */
	public MovieRelease() {
		system_manager.getSplashscreen().setLabel("Initializing movie release...");
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
		labelBackground.add(labelMonth);
		labelBackground.add(cbMonth);
		labelBackground.add(labelYear);
		labelBackground.add(cbYear);
		labelBackground.add(labelNext);
		labelBackground.add(labelBack);
		
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelTitle.setBounds(165, 110, 400, 50);
		labelMonth.setBounds(80, 185, 300, 30);
		cbMonth.setBounds(170, 190, 300, 25);
		labelYear.setBounds(90, 225, 300, 50);
		cbYear.setBounds(170, 235, 300, 25);
		labelNext.setBounds(430, 470, 80, 80);
		labelBack.setBounds(20, 470, 80, 80);
		
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		labelTitle.setFont(component_manager.getFontXLargePlain());
		labelMonth.setFont(component_manager.getFontMediumBold());
		cbMonth.setFont(component_manager.getFontMediumPlain());
		labelYear.setFont(component_manager.getFontMediumBold());
		cbYear.setFont(component_manager.getFontMediumPlain());
		labelNext.setFont(component_manager.getFontMediumBold());
		labelBack.setFont(component_manager.getFontMediumBold());
		
		labelTitle.setForeground(Color.white);
		labelMonth.setForeground(Color.white);
		cbMonth.setForeground(Color.black);
		labelYear.setForeground(Color.white);
		cbYear.setForeground(Color.black);
		
	
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
	public void mouseMoved(MouseEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == labelNext){
			 int dialogButton = JOptionPane.showConfirmDialog (null, "Are you sure you want to proceed?","Confirm",JOptionPane.YES_NO_OPTION);
				if(dialogButton == JOptionPane.YES_OPTION) {
					int month = cbMonth.getSelectedIndex();
					int year = Integer.parseInt(cbYear.getSelectedItem().toString());
					system_manager.getInput_mngr().input_release(month, year);
					system_manager.getOutput_mngr().execute();
					GUI_manager.getMovieGrossRevenue().setVisible(true);
					this.setVisible(false);
				}
		}
		else if(arg0.getSource() == labelBack){
			if(system_manager.getInput_mngr().getMovie().getSequel() == 1) {
				GUI_manager.getMoviePrequel().setVisible(true);
			} 
			else {
				GUI_manager.getMovieProduction().setVisible(true);
			}	
			this.setVisible(false);
		}else if(arg0.getSource() == labelCloseIcon) {
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
	
	public void mouseDragged(MouseEvent arg0) {
		if(arg0.getSource() == this) {
			this.setLocation(arg0.getXOnScreen()-cmpmngr.getPosX(),arg0.getYOnScreen()-cmpmngr.getPosY());			
		}
		
	}

}
