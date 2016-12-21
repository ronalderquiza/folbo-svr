package main;

import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	component_manager.java
 * Description:	Component manager
 * Version:		1.0.1
 *
 * @lastreview 
 * 
 */
@SuppressWarnings("serial")
public class component_manager extends JFrame{
	//FONTS
	private static Font fontTinyPlain = new Font("Rockwell", Font.PLAIN, 5); //fontMini
	private static Font fontTinyPlain2 = new Font("Rockwell", Font.PLAIN, 11); //fontMini2
	private static Font fontSmallPlain = new Font("Rockwell", Font.PLAIN, 12); //fontSmallest
	private static Font fontMediumPlain = new Font("Rockwell", Font.PLAIN, 15); //fontSmall
	private static Font fontMediumBold = new Font("Rockwell", Font.BOLD, 15); //fontSmallBold
	private static Font fontLargePlain = new Font("Rockwell", Font.PLAIN, 25); //fontButtonBig
	private static Font fontLargeBold = new Font("Rockwell", Font.PLAIN, 20); //fontButtonBig
	private static Font fontXLargePlain = new Font("Rockwell", Font.PLAIN, 30); //fontHeader2
	private static Font fontXXLargePlain = new Font("Rockwell", Font.PLAIN, 40); //fontHeader1
	private static Font fontXXLargeBold = new Font("Rockwell", Font.PLAIN, 40); //fontHeader1
	
	//ICONS
	private Icon iconCloseIcon = new ImageIcon(getClass().getResource("/frames/Photos/Close_Icon.png"));
	private Icon iconMinimizeIcon = new ImageIcon(getClass().getResource("/frames/Photos/Minimize_Icon.png"));
	private Icon iconBackground = new ImageIcon(getClass().getResource("/frames/Photos/Background_3.png"));
	private Icon iconBack = new ImageIcon(getClass().getResource("/frames/Photos/Back.png"));
	private Icon iconNext = new ImageIcon(getClass().getResource("/frames/Photos/Next.png"));
	
	
	// HOME ICONS
	private Icon iconTitleHome = new ImageIcon(getClass().getResource("/frames/Photos/TitleHome.png"));
	private Icon iconInfo = new ImageIcon(getClass().getResource("/frames/Photos/Info.png"));
	private Icon iconInfoHover = new ImageIcon(getClass().getResource("/frames/Photos/Info_Hover.png"));
	private Icon iconStart = new ImageIcon(getClass().getResource("/frames/Photos/Start.png"));
	private Icon iconStartHover = new ImageIcon(getClass().getResource("/frames/Photos/Start_Hover.png"));
	private Icon iconExit = new ImageIcon(getClass().getResource("/frames/Photos/Exit.png"));
	private Icon iconExitHover = new ImageIcon(getClass().getResource("/frames/Photos/Exit_Hover.png"));
	
	//ABOUT ICONS
	private Icon iconTitleMovieAbout = new ImageIcon(getClass().getResource("/frames/Photos/About.png"));

	// GROSS REVENUE ICONS
	private Icon iconGrossRevBackground = new ImageIcon(getClass().getResource("/frames/Photos/Background_3_1.png"));
	private Icon iconMovieGrossRevenue = new ImageIcon(getClass().getResource("/frames/Photos/GrossRevenueMovieIcon.png"));
	private Icon iconReset = new ImageIcon(getClass().getResource("/frames/Photos/Reset.png"));
	private Icon iconResetHover = new ImageIcon(getClass().getResource("/frames/Photos/Reset_Hover.png"));
	private Icon iconHome = new ImageIcon(getClass().getResource("/frames/Photos/Home.png"));
	private Icon iconHomeHover = new ImageIcon(getClass().getResource("/frames/Photos/Home_Hover.png"));
	
	//JLABEL
	private JLabel labelMinimizeIcon = new JLabel(getIconMinimizeIcon());
	private JLabel labelCloseIcon = new JLabel(getIconCloseIcon());
	private JLabel labelBackground = new JLabel(getIconBackground());
	private JLabel labelNext = new JLabel(getIconNext());
	private JLabel labelBack = new JLabel(getIconBack());	
	
	//OTHERS
	private  Timer t;
	private int counter;
	private int posX = 0;
	private int posY = 0;
	
	/**
	 * initialize component_manager
	 */
	public component_manager() {
		system_manager.getSplashscreen().setLabel("Initializing Component Manager...");
		
		
	}

	/**
	 * @return fontTinyPlain
	 */
	public static Font getFontTinyPlain() {
		return fontTinyPlain;
	}

	/**
	 * @param fontTinyPlain
	 */
	public static void setFontTinyPlain(Font fontTinyPlain) {
		component_manager.fontTinyPlain = fontTinyPlain;
	}

	/**
	 * @return fontTinyPlain2
	 */
	public static Font getFontTinyPlain2() {
		return fontTinyPlain2;
	}

	/**
	 * @param fontTinyPlain2
	 */
	public static void setFontTinyPlain2(Font fontTinyPlain2) {
		component_manager.fontTinyPlain2 = fontTinyPlain2;
	}

	/**
	 * @return fontSmallPlain
	 */
	public static Font getFontSmallPlain() {
		return fontSmallPlain;
	}

	/**
	 * @param fontSmallPlain
	 */
	public static void setFontSmallPlain(Font fontSmallPlain) {
		component_manager.fontSmallPlain = fontSmallPlain;
	}

	/**
	 * @return fontMediumPlain
	 */
	public static Font getFontMediumPlain() {
		return fontMediumPlain;
	}

	/**
	 * @param fontMediumPlain
	 */
	public static void setFontMediumPlain(Font fontMediumPlain) {
		component_manager.fontMediumPlain = fontMediumPlain;
	}

	/**
	 * @return fontMediumBold
	 */
	public static Font getFontMediumBold() {
		return fontMediumBold;
	}

	/**
	 * @param fontMediumBold
	 */
	public static void setFontMediumBold(Font fontMediumBold) {
		component_manager.fontMediumBold = fontMediumBold;
	}

	/**
	 * @return fontLargePlain
	 */
	public static Font getFontLargePlain() {
		return fontLargePlain;
	}

	/**
	 * @param fontLargePlain
	 */
	public static void setFontLargePlain(Font fontLargePlain) {
		component_manager.fontLargePlain = fontLargePlain;
	}

	/**
	 * @return fontXLargePlain
	 */
	public static Font getFontXLargePlain() {
		return fontXLargePlain;
	}

	/**
	 * @param fontXLargePlain
	 */
	public static void setFontXLargePlain(Font fontXLargePlain) {
		component_manager.fontXLargePlain = fontXLargePlain;
	}

	/**
	 * @return fontXXLargePlain
	 */
	public static Font getFontXXLargePlain() {
		return fontXXLargePlain;
	}

	/**
	 * @param fontXXLargePlain
	 */
	public static void setFontXXLargePlain(Font fontXXLargePlain) {
		component_manager.fontXXLargePlain = fontXXLargePlain;
	}

	/**
	 * @return iconCloseIcon
	 */
	public Icon getIconCloseIcon() {
		return iconCloseIcon;
	}

	/**
	 * @param iconCloseIcon
	 */
	public void setIconCloseIcon(Icon iconCloseIcon) {
		this.iconCloseIcon = iconCloseIcon;
	}

	/**
	 * @return iconMinimizeIcon
	 */
	public Icon getIconMinimizeIcon() {
		return iconMinimizeIcon;
	}

	/**
	 * @param iconMinimizeIcon
	 */
	public void setIconMinimizeIcon(Icon iconMinimizeIcon) {
		this.iconMinimizeIcon = iconMinimizeIcon;
	}

	/**
	 * @return iconBackground
	 */
	public Icon getIconBackground() {
		return iconBackground;
	}

	/**
	 * @param iconBackground
	 */
	public void setIconBackground(Icon iconBackground) {
		this.iconBackground = iconBackground;
	}

	/**
	 * @return iconNext
	 */
	public Icon getIconNext() {
		return iconNext;
	}

	/**
	 * @param iconNext
	 */
	public void setIconNext(Icon iconNext) {
		this.iconNext = iconNext;
	}

	/**
	 * @return iconBack
	 */
	public Icon getIconBack() {
		return iconBack;
	}

	/**
	 * @param iconBack
	 */
	public void setIconBack(Icon iconBack) {
		this.iconBack = iconBack;
	}

	/**
	 * @return iconTitleHome
	 */
	public Icon getIconTitleHome() {
		return iconTitleHome;
	}

	/**
	 * @param iconTitleHome
	 */
	public void setIconTitleHome(Icon iconTitleHome) {
		this.iconTitleHome = iconTitleHome;
	}

	/**
	 * @return iconInfo
	 */
	public Icon getIconInfo() {
		return iconInfo;
	}

	/**
	 * @param iconInfo
	 */
	public void setIconInfo(Icon iconInfo) {
		this.iconInfo = iconInfo;
	}

	/** 
	 * @return iconInfoHover
	 */
	public Icon getIconInfoHover() {
		return iconInfoHover;
	}

	/**
	 * @param iconInfoHover
	 */
	public void setIconInfoHover(Icon iconInfoHover) {
		this.iconInfoHover = iconInfoHover;
	}

	/**
	 * @return iconStart
	 */
	public Icon getIconStart() {
		return iconStart;
	}

	/**
	 * @param iconStart
	 */
	public void setIconStart(Icon iconStart) {
		this.iconStart = iconStart;
	}

	/**
	 * @return iconStartHover
	 */
	public Icon getIconStartHover() {
		return iconStartHover;
	}

	/**
	 * @param iconStartHover
	 */
	public void setIconStartHover(Icon iconStartHover) {
		this.iconStartHover = iconStartHover;
	}

	/**
	 * @return iconExit
	 */
	public Icon getIconExit() {
		return iconExit;
	}

	/**
	 * @param iconExit
	 */
	public void setIconExit(Icon iconExit) {
		this.iconExit = iconExit;
	}

	/**
	 * @return iconExitHover
	 */
	public Icon getIconExitHover() {
		return iconExitHover;
	}

	/**
	 * @param iconExitHover
	 */
	public void setIconExitHover(Icon iconExitHover) {
		this.iconExitHover = iconExitHover;
	}

	/**
	 * @return the iconTitleMovieAbout
	 */
	public Icon getIconTitleMovieAbout() {
		return iconTitleMovieAbout;
	}

	/**
	 * @param iconTitleMovieAbout the iconTitleMovieAbout to set
	 */
	public void setIconTitleMovieAbout(Icon iconTitleMovieAbout) {
		this.iconTitleMovieAbout = iconTitleMovieAbout;
	}

	/**
	 * @return the iconMovieGrossRevenue
	 */
	public Icon getIconMovieGrossRevenue() {
		return iconMovieGrossRevenue;
	}

	/**
	 * @param iconMovieGrossRevenue the iconMovieGrossRevenue to set
	 */
	public void setIconMovieGrossRevenue(Icon iconMovieGrossRevenue) {
		this.iconMovieGrossRevenue = iconMovieGrossRevenue;
	}

	/**
	 * @return the iconReset
	 */
	public Icon getIconReset() {
		return iconReset;
	}

	/**
	 * @param iconReset the iconReset to set
	 */
	public void setIconReset(Icon iconReset) {
		this.iconReset = iconReset;
	}

	/**
	 * @return the iconResetHover
	 */
	public Icon getIconResetHover() {
		return iconResetHover;
	}

	/**
	 * @param iconResetHover the iconResetHover to set
	 */
	public void setIconResetHover(Icon iconResetHover) {
		this.iconResetHover = iconResetHover;
	}

	/**
	 * @return the iconHome
	 */
	public Icon getIconHome() {
		return iconHome;
	}

	/**
	 * @param iconHome the iconHome to set
	 */
	public void setIconHome(Icon iconHome) {
		this.iconHome = iconHome;
	}

	/**
	 * @return the iconHomeHover
	 */
	public Icon getIconHomeHover() {
		return iconHomeHover;
	}

	/**
	 * @param iconHomeHover the iconHomeHover to set
	 */
	public void setIconHomeHover(Icon iconHomeHover) {
		this.iconHomeHover = iconHomeHover;
	}

	/**
	 * @return the labelMinimizeIcon
	 */
	public JLabel getLabelMinimizeIcon() {
		return labelMinimizeIcon;
	}

	/**
	 * @param labelMinimizeIcon the labelMinimizeIcon to set
	 */
	public void setLabelMinimizeIcon(JLabel labelMinimizeIcon) {
		this.labelMinimizeIcon = labelMinimizeIcon;
	}

	/**
	 * @return the labelCloseIcon
	 */
	public JLabel getLabelCloseIcon() {
		return labelCloseIcon;
	}

	/**
	 * @param labelCloseIcon the labelCloseIcon to set
	 */
	public void setLabelCloseIcon(JLabel labelCloseIcon) {
		this.labelCloseIcon = labelCloseIcon;
	}

	/**
	 * @return the labelBackground
	 */
	public JLabel getLabelBackground() {
		return labelBackground;
	}

	/**
	 * @param labelBackground the labelBackground to set
	 */
	public void setLabelBackground(JLabel labelBackground) {
		this.labelBackground = labelBackground;
	}

	/**
	 * @return the labelNext
	 */
	public JLabel getLabelNext() {
		return labelNext;
	}

	/**
	 * @param labelNext the labelNext to set
	 */
	public void setLabelNext(JLabel labelNext) {
		this.labelNext = labelNext;
	}

	/**
	 * @return the labelBack
	 */
	public JLabel getLabelBack() {
		return labelBack;
	}

	/**
	 * @param labelBack the labelBack to set
	 */
	public void setLabelBack(JLabel labelBack) {
		this.labelBack = labelBack;
	}

	/**
	 * @return the t
	 */
	public Timer getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(Timer t) {
		this.t = t;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return the iconGrossRevBackground
	 */
	public Icon getIconGrossRevBackground() {
		return iconGrossRevBackground;
	}

	/**
	 * @param iconGrossRevBackground the iconGrossRevBackground to set
	 */
	public void setIconGrossRevBackground(Icon iconGrossRevBackground) {
		this.iconGrossRevBackground = iconGrossRevBackground;
	}

	/**
	 * @return the fontLargeBold
	 */
	public static Font getFontLargeBold() {
		return fontLargeBold;
	}

	/**
	 * @param fontLargeBold the fontLargeBold to set
	 */
	public static void setFontLargeBold(Font fontLargeBold) {
		component_manager.fontLargeBold = fontLargeBold;
	}

	/**
	 * @return the fontXXLargeBold
	 */
	public static Font getFontXXLargeBold() {
		return fontXXLargeBold;
	}

	/**
	 * @param fontXXLargeBold the fontXXLargeBold to set
	 */
	public static void setFontXXLargeBold(Font fontXXLargeBold) {
		component_manager.fontXXLargeBold = fontXXLargeBold;
	}


}
