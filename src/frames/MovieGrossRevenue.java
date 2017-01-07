package frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import main.*;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieGrossRevenue.java
 * Description:	Final frame of the system to show the output or result.
 * Version:		
 *
 * @lastreview 
 * 
 */
@SuppressWarnings("serial")
public class MovieGrossRevenue extends JFrame implements MouseListener, MouseMotionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();
	
	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelBackground = new JLabel(cmpmngr.getIconGrossRevBackground());
	JLabel labelHome = new JLabel(cmpmngr.getIconHome());
	JLabel labelReset = new JLabel(cmpmngr.getIconReset());
	JLabel labelExit = new JLabel(cmpmngr.getIconGRExit());
	JLabel labelExitHover = new JLabel(cmpmngr.getIconGRExitHover());
	JLabel labelTitle = new JLabel("BOX OFFICE REVENUE");
	JLabel labelSuggestedArtists = new JLabel("Suggested Artists:");
	private JLabel labelRevenue = new JLabel();
	private JLabel labelFirstSentence = new JLabel();
	
	/**
	 * 
	 */
	public JEditorPane taArtists = new JEditorPane();
	JScrollPane scrollArtists = new JScrollPane(taArtists);
	int counter;


	/**
	 * initialize MovieGrossRevenue
	 */
	public MovieGrossRevenue() {
		system_manager.getSplashscreen().setLabel("Initializing output...");
		this.setSize(550, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frames/Photos/Others/FoLBO_Logo.png")));
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		this.taArtists.setEditable(false);
		this.scrollArtists.setWheelScrollingEnabled(true);
		this.scrollArtists.setBorder(null);
	
		this.add(labelBackground);
		labelBackground.add(labelCloseIcon);
		labelBackground.add(labelTitle);
		labelBackground.add(getLabelRevenue());
		labelBackground.add(getLabelFirstSentence());
		labelBackground.add(scrollArtists, BorderLayout.CENTER);
		labelBackground.add(labelMinimizeIcon);
		labelBackground.add(labelHome);
		labelBackground.add(labelReset);
		labelBackground.add(labelExit);
		
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelTitle.setBounds(110, 125, 380, 50);
		getLabelRevenue().setBounds(100, 130, 400, 150);
		getLabelFirstSentence().setBounds(115, 200, 400, 300);
		scrollArtists.setBounds(100, 470, 350, 90);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelHome.setBounds(230, 565, 80, 80);
		labelReset.setBounds(140, 565, 80, 80);
		labelExit.setBounds(320, 565, 80, 80);
		
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		getLabelFirstSentence().setForeground(Color.white);
		taArtists.setForeground(Color.white);
		labelTitle.setForeground(Color.white);
		
		labelTitle.setFont(component_manager.getFontXLargePlain());
		labelFirstSentence.setFont(component_manager.getFontMediumPlain());
		labelHome.setFont(component_manager.getFontMediumBold());
		labelReset.setFont(component_manager.getFontLargeBold());
		getLabelRevenue().setFont(component_manager.getFontXXLargeBold());
		getLabelFirstSentence().setFont(component_manager.getFontMediumPlain());
		taArtists.setFont(component_manager.getFontMediumPlain());
		labelHome.setBackground(Color.white);
		labelReset.setBackground(Color.white);
		taArtists.setBackground(Color.black);
		
		taArtists.setContentType("text/html");
		
		scrollArtists.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollArtists.getViewport().setOpaque(false);

		labelHome.setOpaque(false);
		labelReset.setOpaque(false);

		
		//LISTENERS
		labelHome.addMouseListener(this);
		labelReset.addMouseListener(this);
		labelExit.addMouseListener(this);
		labelCloseIcon.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
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
		if(arg0.getSource() == labelHome){
			 int dialogButton = JOptionPane.showConfirmDialog (null, "Are you sure?","Confirm",JOptionPane.YES_NO_OPTION);
				if(dialogButton == JOptionPane.YES_OPTION) {
					@SuppressWarnings("unused")
					system_manager sm = new system_manager();
					@SuppressWarnings("unused")
					GUI_manager gm = new GUI_manager();
					this.setVisible(false);
				}
		}
		else if(arg0.getSource() == labelReset){
			system_manager.reset();
			this.setVisible(false);
		}else if (arg0.getSource() == labelExit) {
			int dialogButton = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
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
		} else if(arg0.getSource() == labelReset) {
			GUI_manager.getHome().setVisible(true);
			this.setVisible(false);
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == labelReset) {
			labelReset.setIcon(cmpmngr.getIconResetHover());
		} else if (arg0.getSource() == labelHome) {
			labelHome.setIcon(cmpmngr.getIconHomeHover());
		} else if (arg0.getSource() == labelExit) {
			labelExit.setIcon(cmpmngr.getIconExitHover());
		} 
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (arg0.getSource() == labelReset) {
			labelReset.setIcon(cmpmngr.getIconReset());
		} else if (arg0.getSource() == labelHome) {
			labelHome.setIcon(cmpmngr.getIconHome());
		} else if (arg0.getSource() == labelExit) {
			labelExit.setIcon(cmpmngr.getIconExit());
		}
		
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
	 * @return labelFirstSentence
	 */
	public JLabel getLabelFirstSentence() {
		return labelFirstSentence;
	}

	/**
	 * @param labelFirstSentence
	 */
	public void setLabelFirstSentence(JLabel labelFirstSentence) {
		this.labelFirstSentence = labelFirstSentence;
	}

	/**
	 * @return the labelRevenue
	 */
	public JLabel getLabelRevenue() {
		return labelRevenue;
	}

	/**
	 * @param labelRevenue the labelRevenue to set
	 */
	public void setLabelRevenue(JLabel labelRevenue) {
		this.labelRevenue = labelRevenue;
	}

	/**
	 * @return the taArtists
	 */
	public JEditorPane getTaArtists() {
		return taArtists;
	}

	/**
	 * @param taArtists the taArtists to set
	 */
	public void setTaArtists(JEditorPane taArtists) {
		this.taArtists = taArtists;
	}

}
