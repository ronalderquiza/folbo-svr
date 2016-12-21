package frames;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * @author		Ronald Erquiza, Katrina Buca
 * Email:		ronalderquiza@gmail.com, izabellebuca@gmail.com
 * Filename:	ComboKeyHandler.java
 * Description:	
 * Version:		
 *
 * @lastreview 
 * 
 */
public class ComboKeyHandler extends KeyAdapter {
	private final JComboBox<String> cbMovieSequel;
	private final List<String> list = new ArrayList<>();
	private boolean shouldHide;

	/**
	 * @param combo
	 */
	public ComboKeyHandler(JComboBox<String> combo) {
		super();
		this.cbMovieSequel = combo;
		for (int i = 0; i < cbMovieSequel.getModel().getSize(); i++)
			list.add(cbMovieSequel.getItemAt(i));
	}

	private static void setSuggestionModel(JComboBox<String> cbMovieSequel, ComboBoxModel<String> cml, String str) {
		cbMovieSequel.setModel(cml);
		cbMovieSequel.setSelectedIndex(-1);
		((JTextField) cbMovieSequel.getEditor().getEditorComponent()).setText(str);

		@SuppressWarnings("unused")
		JTextField text = (JTextField) cbMovieSequel.getEditor().getEditorComponent();

	}

	private static ComboBoxModel<String> getSuggestedModel(List<String> list, String text) {
		DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
		for (String s : list) {
			if (s.toLowerCase().startsWith(text.toLowerCase())) {
				m.addElement(s);
			}
		}
		return m;
	}

	public void keyTyped(final KeyEvent e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				String text = ((JTextField) e.getComponent()).getText();
				ComboBoxModel<String> m;
				if (text.isEmpty()) {
					String[] array = list.toArray(new String[list.size()]);
					m = new DefaultComboBoxModel<String>(array);
					setSuggestionModel(cbMovieSequel, m, "");
					cbMovieSequel.hidePopup();
				} else {
					m = getSuggestedModel(list, text);
					if (m.getSize() == 0 || shouldHide) {
						cbMovieSequel.hidePopup();
					} else {
						setSuggestionModel(cbMovieSequel, m, text);
						cbMovieSequel.showPopup();
					}
				}
			}
		});
	}
}