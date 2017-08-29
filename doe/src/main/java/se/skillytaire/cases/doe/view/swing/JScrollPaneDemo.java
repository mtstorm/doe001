package se.skillytaire.cases.doe.view.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class JScrollPaneDemo extends JPanel {
	private static final long serialVersionUID = 1L;
	private static JFrame frame;

	public static void main(final String[] args) {
		final String[] columnNames = { "Type boot", "Aantal personen", "Verwachte duur", };

		final Object[][] columnData = { { "Roeiboot", 4, 4 } };

		JScrollPaneDemo.createAndShowGUI("SimpleTableDemo", columnNames, columnData);
	}

	public static void createAndShowGUI(final String title, final String[] columnNames, final Object[][] columnData) {
		// Create and set up the window.
		if (JScrollPaneDemo.frame == null) {
			JScrollPaneDemo.frame = new JFrame(title);
			JScrollPaneDemo.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			// Display the window.
			JScrollPaneDemo.frame.pack();
			JScrollPaneDemo.frame.setSize(500, 500);

			JScrollPaneDemo.frame.addWindowListener(new WindowListener() {

				@Override
				public void windowActivated(final WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowClosed(final WindowEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Window closed.");
					JScrollPaneDemo.frame = null;
				}

				@Override
				public void windowClosing(final WindowEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Window closing...");
				}

				@Override
				public void windowDeactivated(final WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowDeiconified(final WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowIconified(final WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowOpened(final WindowEvent e) {
					// TODO Auto-generated method stub

				}
			});

		}

		final JScrollPaneDemo panel = new JScrollPaneDemo(columnNames, columnData);
		panel.setOpaque(true);
		JScrollPaneDemo.frame.setContentPane(panel);
		JScrollPaneDemo.frame.setVisible(true);

	}

	public JScrollPaneDemo(final String[] columnNames, final Object[][] data) {
		super(new GridLayout(1, 0));

		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		// Create the scroll pane and add the table to it.
		final JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		this.add(scrollPane);

	}

	public static JFrame getFrame() {
		return JScrollPaneDemo.frame;
	}

}
