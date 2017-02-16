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
 * Filename:	Home.java
 * Description:	Main GUI of the System
 * @version		1.3.0
 *
 * @lastreview 
 * 
 */

@SuppressWarnings("serial")
public class Home extends JFrame implements MouseListener, MouseMotionListener {
	component_manager cmpmngr = GUI_manager.getCmp_mngr();
	
	JLabel labelTitleHome = new JLabel(cmpmngr.getIconTitleHome());
	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelInfo = new JLabel(cmpmngr.getIconInfo());
    JLabel labelInfoHover = new JLabel(cmpmngr.getIconInfoHover());
    JLabel labelStart = new JLabel(cmpmngr.getIconStart());
    JLabel labelStartHover = new JLabel(cmpmngr.getIconStartHover());
	JLabel labelExit = new JLabel(cmpmngr.getIconExit());
	JLabel labelExitHover = new JLabel(cmpmngr.getIconExitHover());

	JButton buttonYes = new JButton("YES");
	JButton buttonNo = new JButton("NO");

	/**
	 * initialize Home
	 */
	public Home() {
		System.out.println("PROGRAM STARTED");
		system_manager.getSplashscreen().setLabel("Initializing home screen...");
		this.setTitle("HOME");
		this.setSize(550, 560);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frames/Photos/Others/FoLBO_Logo.png")));

		this.add(labelBackground);
		labelBackground.add(labelCloseIcon);
		labelBackground.add(labelMinimizeIcon);
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

		labelBackground.add(labelTitleHome);
		labelBackground.add(labelInfo);
		labelBackground.add(labelStart);
		labelBackground.add(labelExit);
		labelBackground.add(buttonNo);

		labelTitleHome.setBounds(25, 50, 500, 400);
		labelInfo.setBounds(140, 280, 80, 80);
		labelStart.setBounds(230, 280, 80, 80);
		labelExit.setBounds(320, 280, 80, 80);

		// Not needed, but do not remove
		buttonNo.setBounds(290, 400, 150, 50);
		buttonNo.setBackground(Color.white);
		buttonNo.setFont(component_manager.getFontLargePlain());
		buttonNo.setVisible(false);
		
		//LISTENERS
		labelCloseIcon.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
		labelStart.addMouseListener(this);
		labelInfo.addMouseListener(this);
		labelExit.addMouseListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == labelStart) {
			GUI_manager.getMovieStory().setVisible(true);
			this.setVisible(false);
		} else if (arg0.getSource() == labelInfo) {
			GUI_manager.getMovieAbout().setVisible(true);
			this.setVisible(false);
		} else if (arg0.getSource() == labelExit) {
			int dialogButton = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
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
		if (arg0.getSource() == labelStart) {
			labelStart.setIcon(cmpmngr.getIconStartHover());
		} else if (arg0.getSource() == labelInfo) {
			labelInfo.setIcon(cmpmngr.getIconInfoHover());
		} else if (arg0.getSource() == labelExit) {
			labelExit.setIcon(cmpmngr.getIconExitHover());
		} 
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (arg0.getSource() == labelStart) {
			labelStart.setIcon(cmpmngr.getIconStart());
		} else if (arg0.getSource() == labelInfo) {
			labelInfo.setIcon(cmpmngr.getIconInfo());
		} else if (arg0.getSource() == labelExit) {
			labelExit.setIcon(cmpmngr.getIconExit());
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getSource() == this) {
			cmpmngr.setPosX(arg0.getX());
			cmpmngr.setPosY(arg0.getY());
		}
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

	@Override
	public void mouseReleased(MouseEvent arg0) {
	
	}

}
