package a.b.c;
import java.awt.*; 
import javax.swing.*; 
import javax.swing.event.*; 
import javax.swing.text.*; 
import java.util.*; 
import java.util.regex.*;

public class CompletableJTextField extends JTextField 
	implements ListSelectionListener {

	Completer completer;
	JList completionList;
	DefaultListModel completionListModel;
	JScrollPane listScroller;
	JWindow listWindow;

	public CompletableJTextField (int col) { 
		super (col);        
		completer = new Completer();        
		completionListModel = new DefaultListModel(); 
		completionList = new JList(completionListModel); 
		completionList.setSelectionMode (ListSelectionModel.SINGLE_SELECTION); 
		completionList.addListSelectionListener (this); 
		listScroller =
			new JScrollPane (completionList, 
				 ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				 ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        listWindow = new JWindow();
		listWindow.getContentPane().add (listScroller);
	}

	public void addCompletion (String s) {
		completer.addCompletion (s); }

	public void removeCompletion (String s) {
		completer.removeCompletion (s); }

	public void clearCompletions (String s ) {
		completer.clearCompletions (); }

	public void valueChanged (ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) { return; }
		if (completionList.getModel().getSize() == 0) {return;}
		listWindow.setVisible (false);
		final String completionString =
           (String) completionList.getSelectedValue();
		Thread worker = new Thread() {
				public void run() {
				setText (completionString);
				}
			};
		SwingUtilities.invokeLater (worker);
	}

	/** inner class does the matching of the JTextField's
		document to completion strings kept in an ArrayList
	*/

	class Completer implements DocumentListener {
		private Pattern pattern;
		private ArrayList completions;
		public Completer() {
			completions = new ArrayList();
			getDocument().addDocumentListener (this);
		}
		
		public void addCompletion (String s) {
			completions.add (s);
			buildAndShowPopup();
		}

		public void removeCompletion (String s) {
			completions.remove (s);
			buildAndShowPopup();
		}
       
		public void clearCompletions () {
			completions.clear();
			buildPopup();
			listWindow.setVisible(false);
		}
		private void buildPopup() {            
			completionListModel.clear();            
			System.out.println ("buildPopup for " + completions.size() +
					" completions");
			Iterator it = completions.iterator();            
			pattern = Pattern.compile (getText() + ".+");			
			while (it.hasNext()) {
				// check if match
				String completion = (String) it.next();
				Matcher matcher = pattern.matcher (completion);
				if (matcher.matches()) {
				// add if match
				System.out.println ("matched "+ completion);
				completionListModel.add (completionListModel.getSize(),
								 completion); 
               } else {
				System.out.println ("pattern " +														pattern.pattern() + 
							" does not match " + 
                                        completion);
               }
			}
		}

		private void showPopup() {
			if (completionListModel.getSize() == 0) { 
				listWindow.setVisible(false); 
				return;
			}
			// figure out where the text field is,
			// and where its bottom left is
			java.awt.Point los = getLocationOnScreen();
			int popX = los.x;
			int popY = los.y + getHeight();
			listWindow.setLocation (popX, popY);
			listWindow.pack();
			listWindow.setVisible(true);
		}

		private void buildAndShowPopup() {
			if (getText().length() < 1)
				return;
			buildPopup();
			showPopup();
		}

		// DocumentListener implementation
		public void insertUpdate (DocumentEvent e) { buildAndShowPopup(); }
		public void removeUpdate (DocumentEvent e) { buildAndShowPopup(); }
		public void changedUpdate (DocumentEvent e) { buildAndShowPopup(); }

	} 
}

