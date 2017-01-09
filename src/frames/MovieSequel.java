package frames;

import main.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieSequel.java
 * Description:	Frame for Movie Sequel
 * @version		1.0.0
 *
 * @lastreview 
 * 
 */

@SuppressWarnings("serial")
public class MovieSequel extends JFrame implements MouseListener, MouseMotionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();
	
	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelBack = new JLabel(cmpmngr.getIconBack());
	JLabel labelQuestion = new JLabel("Is it a SEQUEL?");
	
	JButton buttonYes = new JButton("YES");
	JButton buttonNo = new JButton("NO");
	
	private int counter;
	/**
	 * initialize MovieSequel
	 */
	public MovieSequel() {
		system_manager.getSplashscreen().setLabel("Initializing sequel screen...");
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
		labelBackground.add(labelQuestion);
		labelBackground.add(buttonYes);
		labelBackground.add(labelBack);
		labelBackground.add(buttonNo);
		
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelQuestion.setBounds(160, 190, 500, 50);
		labelBack.setBounds(20, 470, 80, 80);
		buttonYes.setBounds(95, 260, 150, 50);
		buttonNo.setBounds(290, 260, 150, 50);
		
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

		labelQuestion.setFont(component_manager.getFontXLargePlain());
		buttonYes.setFont(component_manager.getFontLargePlain());
		buttonNo.setFont(component_manager.getFontLargePlain());
		
		labelQuestion.setForeground(Color.white);

		buttonYes.setBackground(Color.white);
		buttonNo.setBackground(Color.white);
	
		//LISTENERS
		buttonNo.addMouseListener(this);
		labelCloseIcon.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
		buttonNo.addMouseListener(this);	
		buttonYes.addMouseListener(this);
		labelBack.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == buttonYes){
			system_manager.getInput_mngr().input_sequel(1);
			GUI_manager.getMoviePrequel().setVisible(true);
			this.setVisible(false);
		} else if(arg0.getSource() == buttonNo){
			system_manager.getInput_mngr().input_sequel(0);
			this.setVisible(false);
			GUI_manager.getMovieInfo().setVisible(true);
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
		} else if(arg0.getSource() == labelBack) {
			GUI_manager.getMovieStory().setVisible(true);
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

}
