package frames;

import java.awt.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	Splash.java
 * Description:	Splash Screen of the System
 * @version		2.1.1
 *
 * @lastreview 
 * 
 */

@SuppressWarnings("serial")
public class Splash extends JFrame {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	Icon iconEffects = new ImageIcon(getClass().getResource("/frames/Photos/Others/FoLBO_Loading_2.gif"));
	Icon titleImage = new ImageIcon(getClass().getResource("/frames/Photos/Texts/splash.png"));
	JLabel title = new JLabel(titleImage);
	JLabel gif = new JLabel(iconEffects);
	JLabel load = new JLabel();
	JPanel pane = new JPanel();
	JPanel paneInit = new JPanel();
	Font fontMini = new Font("Rockwell", Font.PLAIN, 18);

	/**
	 * initialize Loading
	 */
	public Splash() {
		this.setSize(titleImage.getIconWidth() + 50, titleImage.getIconHeight() + 50);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setLayout(new FlowLayout());
		pane.add(title);
		paneInit.add(load);
		pane.setLayout(new FlowLayout());
		paneInit.setLayout(new FlowLayout());
		load.setFont(fontMini);
		this.add(pane);
		this.add(paneInit);
		this.setBackground(new Color(0, 0, 0, 0));
		pane.setOpaque(false);
		paneInit.setOpaque(false);
		this.setVisible(true);
	}

	/**
	 * @param text
	 */
	public void setLabel(String text) {
		load.setText(text);
	}

}
