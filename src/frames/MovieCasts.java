package frames;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import main.*;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	MovieCasts.java
 * Description:	Frame for Movie Cast
 * Version:		
 *
 * @lastreview 
 * 
 */

@SuppressWarnings("serial")
public class MovieCasts extends JFrame implements MouseListener, MouseMotionListener{
	component_manager cmpmngr = GUI_manager.getCmp_mngr();
	
	JLabel labelMinimizeIcon = new JLabel(cmpmngr.getIconMinimizeIcon());
	JLabel labelCloseIcon = new JLabel(cmpmngr.getIconCloseIcon());
	JLabel labelBackground = new JLabel(cmpmngr.getIconBackground());
	JLabel labelNext = new JLabel(cmpmngr.getIconNext());
	JLabel labelBack = new JLabel(cmpmngr.getIconBack());
	JLabel labelTitle = new JLabel("MOVIE CASTS");
	JLabel labelSuggestedArtists = new JLabel("Suggested Artists:");
	JLabel labelListArtists = new JLabel("List of Artists:");

	int counter;
	int ctr = 0;
	
	CheckboxListItem[] cbi;
	
	JList<CheckboxListItem> list = new JList<CheckboxListItem>();
	JScrollPane scrollList = new JScrollPane(list);
	ArrayList<Integer> artistList = new ArrayList<Integer>();
	ArrayList<Boolean> selectedArtist = new ArrayList<Boolean>();
	HashMap<Integer, String> allArtist = new HashMap<Integer, String>();
	/**
	 * initialize MovieCasts
	 */
	public MovieCasts() {
		system_manager.getSplashscreen().setLabel("Initializing artists...");
		this.setSize(550, 560);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frames/Photos/FoLBOLogo.png")));

		this.add(labelBackground);
		labelBackground.add(labelCloseIcon);
		labelBackground.add(labelMinimizeIcon);

		labelBackground.add(labelTitle);
		labelBackground.add(labelNext);
		labelBackground.add(labelBack);
		
		labelCloseIcon.setBounds(495, 0, 50, 50);
		labelTitle.setBounds(170, 110, 300, 50);
		labelMinimizeIcon.setBounds(460, 0, 50, 50);
		labelNext.setBounds(430, 470, 80, 80);
		labelBack.setBounds(20, 470, 80, 80);
		
		labelCloseIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelMinimizeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		labelNext.setOpaque(false);	
		labelBack.setOpaque(false);
		
		labelNext.setBackground(Color.white);
		labelBack.setBackground(Color.white);
		
		labelTitle.setFont(component_manager.getFontXLargePlain());
		labelTitle.setForeground(Color.white);
		
		//LISTENERS
		labelNext.addMouseListener(this);
		labelBack.addMouseListener(this);
		labelCloseIcon.addMouseListener(this);
		labelMinimizeIcon.addMouseListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		collectArtist();
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
			ArrayList<Integer> selected = new ArrayList<Integer>();
			for(int i = 0; i < artistList.size(); i++){
				if(selectedArtist.get(i)){
					selected.add(artistList.get(i));
				}
			}
			system_manager.getInput_mngr().input_mainCasts(selected);
			GUI_manager.getMovieProduction().setVisible(true);
			this.setVisible(false);
		}
		else if(arg0.getSource() == labelBack){
			GUI_manager.getMovieInfo().setVisible(true);
			this.setVisible(false);
		}
		else if(arg0.getSource() == labelCloseIcon){
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
	 * @param allTop
	 */
	public void setData(int[] allTop) {
		database_manager dbmngr = system_manager.getDb_mngr();
		artistList.clear();
		selectedArtist.clear();
 	   	int lastIndex = 0;
 	   	int ctr = allTop.length;
 		try {
 			String query = "SELECT * FROM `tblartist`";
 			dbmngr.query(query);
 			for (; dbmngr.getRs().next();) {
 				if(!isExisting(allTop, dbmngr.getRs().getString("artistID"))){
 	 				ctr++;
 				}
 			}
 			
 		}catch (SQLException | ClassNotFoundException a) {
 			a.printStackTrace();
 		}
 		
		cbi = new CheckboxListItem[ctr];
		final String artistIDNum[] = new String[ctr];
		
 		for (int count = 0; count<allTop.length; count++) {
 			String name = allArtist.get(allTop[count]);
			cbi[count] = new CheckboxListItem(name + "(Recommended)");
			artistList.add(allTop[count]);
			selectedArtist.add(false);
			lastIndex = count;
			}
 			lastIndex++;
 		try {
 			String query = "SELECT * FROM `tblartist`";
 			dbmngr.query(query);
 			for (int count = lastIndex; dbmngr.getRs().next(); count++) {
 				if(!isExisting(allTop, dbmngr.getRs().getString("artistID"))){
 	 				cbi[count] = new CheckboxListItem(dbmngr.getRs().getString("artistName"));
 	 				artistIDNum[count] = new String(dbmngr.getRs().getInt("artistID") + "");
 	 				artistList.add(dbmngr.getRs().getInt("artistID"));
 	 				selectedArtist.add(false);
 				}
 				else{
 					count--;
 				}
 			}
 			
 		}catch (SQLException | ClassNotFoundException a) {
 			a.printStackTrace();
 		}
 		list.setListData(cbi);
	    list.setCellRenderer(new CheckboxListRenderer());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setFont(component_manager.getFontMediumPlain());
	    labelBackground.add(scrollList, BorderLayout.CENTER);
		scrollList.setBounds(40, 160, 460, 240);
		scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollList.setOpaque(false);
		scrollList.getViewport().setOpaque(false);
		
	    
	    list.addMouseListener(new MouseAdapter() {
	        @SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent event) {
	       	
	           JList<CheckboxListItem> list = (JList<CheckboxListItem>) event.getSource();

	           int index = list.locationToIndex(event.getPoint());
	           CheckboxListItem item = (CheckboxListItem) list.getModel().getElementAt(index);
	           
	           item.setSelected(!item.isSelected());
	           list.repaint(list.getCellBounds(index, index));

	           if(!selectedArtist.get(index))
	        	   selectedArtist.set(index, true);
	           else
	        	   selectedArtist.set(index, false);
	        }
	     });
	}
	
	/**
	 * initialize setData
	 */
	public void setData() {
		database_manager dbmngr = system_manager.getDb_mngr();
		artistList.clear();
		selectedArtist.clear();
 	   	int ctr = 0;
 		try {
 			String query = "SELECT * FROM `tblartist`";
 			dbmngr.query(query);
 			for (; dbmngr.getRs().next();) {
 	 			ctr++;
 			}
 		}catch (SQLException | ClassNotFoundException a) {
 			a.printStackTrace();
 		}
 		
		cbi = new CheckboxListItem[ctr];
		final String artistIDNum[] = new String[ctr];
		
 		try {
 			String query = "SELECT * FROM `tblartist`";
 			dbmngr.query(query);
 			for (int count = 0; dbmngr.getRs().next(); count++) {
 				cbi[count] = new CheckboxListItem(dbmngr.getRs().getString("artistName"));
 				artistIDNum[count] = new String(dbmngr.getRs().getInt("artistID") + "");
 				artistList.add(dbmngr.getRs().getInt("artistID"));
 				selectedArtist.add(false);
 			}
 			
 		}catch (SQLException | ClassNotFoundException a) {
 			a.printStackTrace();
 		}
 		list.setListData(cbi);
	    list.setCellRenderer(new CheckboxListRenderer());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setFont(component_manager.getFontMediumPlain());
	    labelBackground.add(scrollList, BorderLayout.CENTER);
		scrollList.setBounds(40, 160, 460, 240);
		scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollList.setOpaque(false);
		scrollList.getViewport().setOpaque(false);
		
	    
	    list.addMouseListener(new MouseAdapter() {
	        @SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent event) {
	       	
	           JList<CheckboxListItem> list = (JList<CheckboxListItem>) event.getSource();

	           int index = list.locationToIndex(event.getPoint());
	           CheckboxListItem item = (CheckboxListItem) list.getModel()
	                 .getElementAt(index);
	           
	           item.setSelected(!item.isSelected());
	           list.repaint(list.getCellBounds(index, index));
	           
	           if(!selectedArtist.get(index))
	        	   selectedArtist.set(index, true);
	           else
	        	   selectedArtist.set(index, false);
	         
	        }
	     });
	}
	
	/**
	 * 
	 * @param allTop
	 * @param id
	 * @return if the artist already exists
	 */
	private boolean isExisting(int[] allTop, String id){
		for(int i = 0; i < allTop.length; i++){
			if(id.equals(allTop[i])){
				return true;
			}
		}
		return false;
	}
	
	private void collectArtist(){
		try {
 			database_manager dbmngr = system_manager.getDb_mngr();
 			String query = "SELECT * FROM `tblartist`";
			dbmngr.query(query);
 			for (; dbmngr.getRs().next();) {
 				int id = dbmngr.getRs().getInt("artistID");
 				String name = dbmngr.getRs().getString("artistName");
 				allArtist.put(id, name);
 			}
 			
 		}catch (SQLException | ClassNotFoundException a) {
 			a.printStackTrace();
 		}
	}
	
}

class CheckboxListItem {
 private String label;
 private boolean isSelected = false;

 public CheckboxListItem(String label) {
    this.label = label;
 }

 public boolean isSelected() {
    return isSelected;
 }

 public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
 }

 public String toString() {
    return label;
 }
}

@SuppressWarnings("serial")
class CheckboxListRenderer extends JCheckBox implements ListCellRenderer<CheckboxListItem> {
	 
	   @Override
	   public Component getListCellRendererComponent(
	         JList<? extends CheckboxListItem> list, CheckboxListItem value,
	         int index, boolean isSelected, boolean cellHasFocus) {
	      setEnabled(list.isEnabled());
	      setSelected(value.isSelected());
	      setFont(list.getFont());
	      setBackground(list.getBackground());
	      setForeground(list.getForeground());
	      setText(value.toString());
	      return this;
	   }
}

