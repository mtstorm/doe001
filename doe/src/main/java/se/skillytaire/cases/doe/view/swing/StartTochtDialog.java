package se.skillytaire.cases.doe.view.swing;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class StartTochtDialog implements ActionListener {
	// JFrame appFrame;
	JDialog dialog;
	JLabel jlbTypeBoot, jlbDuur;
	JComboBox<String> jcbTypeBoot, jcbDuur;

	JButton jbbOK, jbnCancel;

	String typeBoot;
	long duur;
	Container cPane;

	boolean actionOK;

	public static void main(final String args[]) {
		new StartTochtDialog("Demo", args);
	}

	public StartTochtDialog(final String title, final String args[]) {
		typeBoot = "";
		duur = 1;
		actionOK = false;
		initialize(title);
	}

	public void initialize(final String title) {
		// Create frame, contentpane and set layout
		dialog = new JDialog();
		dialog.setTitle(title);
		// this.appFrame = new JFrame(title);
		dialog.setModal(true);
		cPane = dialog.getContentPane();
		cPane.setLayout(new GridBagLayout());
		// Add components
		addComponents();
		// Add Action Listeners
		addActionListeners();

		dialog.setSize(240, 240);
		dialog.setResizable(false);
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public void addComponents() {
		jlbTypeBoot = new JLabel("Type boot");
		jlbDuur = new JLabel("Duur");

		final String[] choicesBoot = { "RoeiBoot", "Elektrisch" };
		jcbTypeBoot = new JComboBox<>(choicesBoot);
		final String[] choicesDuur = { "1", "2", "4", "8" };
		jcbDuur = new JComboBox<>(choicesDuur);

		jbbOK = new JButton("Ok");
		jbnCancel = new JButton("Cancel");

		/* add all initialized components to the container */
		final GridBagConstraints gridBagConstraintsx01 = new GridBagConstraints();
		gridBagConstraintsx01.gridx = 0;
		gridBagConstraintsx01.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx01.gridy = 0;
		cPane.add(jlbTypeBoot, gridBagConstraintsx01);

		final GridBagConstraints gridBagConstraintsx02 = new GridBagConstraints();
		gridBagConstraintsx02.gridx = 1;
		gridBagConstraintsx02.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx02.gridy = 0;
		gridBagConstraintsx02.gridwidth = 2;
		gridBagConstraintsx02.fill = GridBagConstraints.BOTH;
		cPane.add(jcbTypeBoot, gridBagConstraintsx02);

		final GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
		gridBagConstraintsx03.gridx = 0;
		gridBagConstraintsx03.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx03.gridy = 1;
		cPane.add(jlbDuur, gridBagConstraintsx03);

		final GridBagConstraints gridBagConstraintsx04 = new GridBagConstraints();
		gridBagConstraintsx04.gridx = 1;
		gridBagConstraintsx04.gridy = 1;
		gridBagConstraintsx04.gridwidth = 2;
		gridBagConstraintsx04.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx04.fill = GridBagConstraints.BOTH;
		cPane.add(jcbDuur, gridBagConstraintsx04);

		final GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
		gridBagConstraintsx05.gridx = 0;
		gridBagConstraintsx05.gridy = 2;
		gridBagConstraintsx05.insets = new Insets(5, 5, 5, 5);
		cPane.add(jbbOK, gridBagConstraintsx05);

		final GridBagConstraints gridBagConstraintsx06 = new GridBagConstraints();
		gridBagConstraintsx06.gridx = 1;
		gridBagConstraintsx06.gridy = 2;
		gridBagConstraintsx06.insets = new Insets(5, 5, 5, 5);
		cPane.add(jbnCancel, gridBagConstraintsx06);
	}

	public void addActionListeners() {
		jbbOK.addActionListener(this);
		jbnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == jbbOK) {
			actionOK = true;
			okAction();
		} else if (e.getSource() == jbnCancel) {
			cancelAction();
			actionOK = false;
		}
	}

	// Handle ok button
	public void okAction() {
		typeBoot = (String) jcbTypeBoot.getSelectedItem();
		try {
			duur = Integer.parseInt((String) jcbDuur.getSelectedItem());
		} catch (final NumberFormatException e) {
			/*
			 * System.out.print("Input is a string"); JOptionPane.showMessageDialog(null,
			 * "Please enter Phone Number");
			 */
		}
		if (okActionValidation()) {
			dialog.dispose();
		}
	}

	private boolean okActionValidation() {
		final boolean validation = true;

		return validation;
	}

	public void cancelAction() {
		// JOptionPane.showMessageDialog(null,"Cancel action");
		dialog.dispose();
	}

	public boolean isActionOK() {
		return actionOK;
	}

	public String getTypeBoot() {
		return typeBoot;
	}

	public Duration getDuur() {
		return Duration.ofHours(duur);
	}
}
