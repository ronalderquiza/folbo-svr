package frames;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import main.*;
 
/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	SearchBoxModel.java
 * Description:	For searching of prequel movies.
 * @version		1.0.0
 *
 * @lastreview 
 * 
 */

@SuppressWarnings({ "rawtypes", "serial" })
public class SearchBoxModel extends AbstractListModel implements ComboBoxModel, KeyListener, ItemListener {
    ArrayList<String> db = new ArrayList<String>();
    ArrayList<String> data = new ArrayList<String>();
    String selection;
    JComboBox cb;
    ComboBoxEditor ComboBoxEd;
    int currPos = 0;
 
	/**
	 * @param ComboBox
	 */
	public SearchBoxModel(JComboBox ComboBox) {
        cb = ComboBox;
        ComboBoxEd = ComboBox.getEditor();
        ComboBoxEd.getEditorComponent().addKeyListener(this);        
        
        // GET MOVIES FROM THE DATABASE

		database_manager dbmngr = system_manager.getDb_mngr();
		try {
			dbmngr.query("SELECT * FROM `tblmovie` WHERE `sequel` = 1;");
			for (; dbmngr.getResultSet().next();) {
				db.add((dbmngr.getResultSet().getString("movieTitle"))+"");
			} 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("HAHA");
     	JComboBox<String> cbMovie = new JComboBox<String>();
     	cbMovie.setEditable(true);
     	SearchBoxModel sbm = new SearchBoxModel(cbMovie);
     	
     	cbMovie.addItemListener(sbm);
    	
     	frame.add(cbMovie);
     	frame.setVisible(true);
     	frame.setSize(500, 60);
  
    }
 
    /**
     * @param in
     */
    public void updateModel(String in) {
        data.clear();
        for (String s : db)
            if (s.contains(in))
                data.add(s);
        super.fireContentsChanged(this, 0, data.size());
        
        //DO NOT REMOVE
        cb.hidePopup();
        cb.showPopup();
        if (data.size() != 0)
            cb.setSelectedIndex(0);
    }
 
    public int getSize() {
        return data.size();
    }
 
    public Object getElementAt(int index) {
        return data.get(index);
    }
 
    public void setSelectedItem(Object anItem) {
        selection = (String) anItem;
    }
 
    public Object getSelectedItem() {
        return selection;
    }
 
    public void keyTyped(KeyEvent e) {
    }
 
    public void keyPressed(KeyEvent e) {
    }
 
    public void keyReleased(KeyEvent e) {
        String stringComboBoxEd = ComboBoxEd.getItem().toString();
        JTextField tfComboBoxEd = (JTextField) ComboBoxEd.getEditorComponent();
        currPos = tfComboBoxEd.getCaretPosition();
 
        if (e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) {
            if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                ComboBoxEd.setItem(stringComboBoxEd);
                tfComboBoxEd.setCaretPosition(currPos);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            cb.setSelectedIndex(cb.getSelectedIndex());
        } else {
            updateModel(cb.getEditor().getItem().toString());
            ComboBoxEd.setItem(stringComboBoxEd);
            tfComboBoxEd.setCaretPosition(currPos);
        }
    }
 
    public void itemStateChanged(ItemEvent e) {
        ComboBoxEd.setItem(e.getItem().toString());
        cb.setSelectedItem(e.getItem());
    }
    
}