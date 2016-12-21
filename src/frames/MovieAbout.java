package frames;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.*;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieAbout.java
 * Description:	This shows the description of the system.
 * Version:		1.0.1
 *
 * @lastreview 
 * 
 */
@SuppressWarnings("serial")
public class MovieAbout extends JFrame implements MouseListener, MouseMotionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();

	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelBack = new JLabel(cmpmngr.getIconBack());
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelTitle = new JLabel(cmpmngr.getIconTitleMovieAbout());	
	
	JButton buttonYes = new JButton("YES");
	JButton buttonNo = new JButton("NO");
	
	/**
	 * initialize MovieAbout
	 */
	public MovieAbout() {

		system_manager.getSplashscreen().setLabel("Initializing about screen...");
		this.setSize(550, 560);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setUndecorated(true);
	    this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frames/Photos/FoLBOLogo.png")));

		this.add(labelBackground);
		labelBackground.add(labelCloseIcon);
		labelBackground.add(labelTitle);
		labelBackground.add(labelBack);
		labelBackground.add(labelMinimizeIcon);
		
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelTitle.setBounds(25, 70, 500, 400);
		labelBack.setBounds(20, 470, 80, 80);
		
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		labelBack.setBackground(Color.white);
		labelBack.setOpaque(false);
		labelBack.setFont(component_manager.getFontMediumBold());
	

		labelCloseIcon.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
		labelBack.addMouseListener(this);
		this.addMouseMotionListener(this);
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
		} else if(arg0.getSource() == labelBack) {
			GUI_manager.getHome().setVisible(true);
			this.setVisible(false);
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

}
