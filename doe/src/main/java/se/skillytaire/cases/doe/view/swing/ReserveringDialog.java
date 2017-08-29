package se.skillytaire.cases.doe.view.swing;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ReserveringDialog implements ActionListener {
	// JFrame appFrame;
	JDialog dialog;
	JLabel jlbTypeTocht, jlbTypeBoot, jlbDatum, jlbTijd, jlbDuur;
	JComboBox<String> jcbTypeTocht, jcbTypeBoot, jcbDuur;
	JTextField jtfDatum, jtfTijd;

	JButton jbbOK, jbnCancel;

	String typeTocht, typeBoot, datum, tijd;
	long duur;
	Container cPane;

	boolean actionOK;

	public static void main(final String args[]) {
		new ReserveringDialog("Demo", args);
	}

	public ReserveringDialog(final String title, final String args[]) {
		typeTocht = "";
		typeBoot = "";
		datum = LocalDate.now().toString();
		tijd = LocalTime.now().toString().substring(0, 5);
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
		jlbTypeTocht = new JLabel("Type tocht");
		jlbTypeBoot = new JLabel("Type boot");
		jlbDatum = new JLabel("Datum");
		jlbTijd = new JLabel("Tijd");
		jlbDuur = new JLabel("Duur");

		final String[] choicesTocht = { "MeerTocht", "RivierTocht" };
		jcbTypeTocht = new JComboBox<>(choicesTocht);
		final String[] choicesBoot = { "RoeiBoot", "Elektrisch" };
		jcbTypeBoot = new JComboBox<>(choicesBoot);
		jtfDatum = new JTextField(datum, 20);
		jtfTijd = new JTextField(tijd, 20);
		final String[] choicesDuur = { "1", "2", "4", "8" };
		jcbDuur = new JComboBox<>(choicesDuur);

		jbbOK = new JButton("Ok");
		jbnCancel = new JButton("Cancel");

		/* add all initialized components to the container */
		final GridBagConstraints gridBagConstraintsx01 = new GridBagConstraints();
		gridBagConstraintsx01.gridx = 0;
		gridBagConstraintsx01.gridy = 0;
		gridBagConstraintsx01.insets = new Insets(5, 5, 5, 5);
		cPane.add(jlbTypeTocht, gridBagConstraintsx01);

		final GridBagConstraints gridBagConstraintsx02 = new GridBagConstraints();
		gridBagConstraintsx02.gridx = 1;
		gridBagConstraintsx02.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx02.gridy = 0;
		gridBagConstraintsx02.gridwidth = 2;
		gridBagConstraintsx02.fill = GridBagConstraints.BOTH;
		cPane.add(jcbTypeTocht, gridBagConstraintsx02);

		final GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
		gridBagConstraintsx03.gridx = 0;
		gridBagConstraintsx03.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx03.gridy = 1;
		cPane.add(jlbTypeBoot, gridBagConstraintsx03);

		final GridBagConstraints gridBagConstraintsx04 = new GridBagConstraints();
		gridBagConstraintsx04.gridx = 1;
		gridBagConstraintsx04.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx04.gridy = 1;
		gridBagConstraintsx04.gridwidth = 2;
		gridBagConstraintsx04.fill = GridBagConstraints.BOTH;
		cPane.add(jcbTypeBoot, gridBagConstraintsx04);

		final GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
		gridBagConstraintsx05.gridx = 0;
		gridBagConstraintsx05.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx05.gridy = 2;
		cPane.add(jlbDatum, gridBagConstraintsx05);

		final GridBagConstraints gridBagConstraintsx06 = new GridBagConstraints();
		gridBagConstraintsx06.gridx = 1;
		gridBagConstraintsx06.gridy = 2;
		gridBagConstraintsx06.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx06.gridwidth = 2;
		gridBagConstraintsx06.fill = GridBagConstraints.BOTH;
		cPane.add(jtfDatum, gridBagConstraintsx06);

		final GridBagConstraints gridBagConstraintsx07 = new GridBagConstraints();
		gridBagConstraintsx07.gridx = 0;
		gridBagConstraintsx07.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx07.gridy = 3;
		cPane.add(jlbTijd, gridBagConstraintsx07);

		final GridBagConstraints gridBagConstraintsx08 = new GridBagConstraints();
		gridBagConstraintsx08.gridx = 1;
		gridBagConstraintsx08.gridy = 3;
		gridBagConstraintsx08.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx08.gridwidth = 2;
		gridBagConstraintsx08.fill = GridBagConstraints.BOTH;
		cPane.add(jtfTijd, gridBagConstraintsx08);

		final GridBagConstraints gridBagConstraintsx09 = new GridBagConstraints();
		gridBagConstraintsx09.gridx = 0;
		gridBagConstraintsx09.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx09.gridy = 4;
		cPane.add(jlbDuur, gridBagConstraintsx09);

		final GridBagConstraints gridBagConstraintsx10 = new GridBagConstraints();
		gridBagConstraintsx10.gridx = 1;
		gridBagConstraintsx10.gridy = 4;
		gridBagConstraintsx10.gridwidth = 2;
		gridBagConstraintsx10.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx10.fill = GridBagConstraints.BOTH;
		cPane.add(jcbDuur, gridBagConstraintsx10);

		final GridBagConstraints gridBagConstraintsx11 = new GridBagConstraints();
		gridBagConstraintsx11.gridx = 0;
		gridBagConstraintsx11.gridy = 5;
		gridBagConstraintsx11.insets = new Insets(5, 5, 5, 5);
		cPane.add(jbbOK, gridBagConstraintsx11);

		final GridBagConstraints gridBagConstraintsx12 = new GridBagConstraints();
		gridBagConstraintsx12.gridx = 1;
		gridBagConstraintsx12.gridy = 5;
		gridBagConstraintsx12.insets = new Insets(5, 5, 5, 5);
		cPane.add(jbnCancel, gridBagConstraintsx12);
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
		typeTocht = (String) jcbTypeTocht.getSelectedItem();
		typeBoot = (String) jcbTypeBoot.getSelectedItem();
		datum = jtfDatum.getText();
		tijd = jtfTijd.getText();
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
		boolean validation = true;

		if (datum.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a datum.");
			validation = false;
		}
		if (tijd.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a tijd.");
			validation = false;
		}
		return validation;
	}

	public void cancelAction() {
		// JOptionPane.showMessageDialog(null,"Cancel action");
		dialog.dispose();
	}

	public boolean isActionOK() {
		return actionOK;
	}

	public String getTypeTocht() {
		return typeTocht;
	}

	public String getTypeBoot() {
		return typeBoot;
	}

	public LocalDate getDatum() {
		return LocalDate.parse(datum);
	}

	public LocalTime getTijd() {
		return LocalTime.parse(tijd);
	}

	public Duration getDuur() {
		return Duration.ofHours(duur);
	}
}
