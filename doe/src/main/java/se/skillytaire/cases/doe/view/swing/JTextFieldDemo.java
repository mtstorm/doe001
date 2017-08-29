package se.skillytaire.cases.doe.view.swing;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class JTextFieldDemo implements ActionListener {
	// JFrame appFrame;
	JDialog dialog;
	JLabel jlbName, jlbAddress, jlbCity, jlbPhone;
	JTextField jtfName, jtfAddress, jtfCity, jtfPhone;
	JButton jbbOK, jbnCancel;

	private String name, address, city;
	private String phone;
	Container cPane;

	boolean actionOK;

	public static void main(final String args[]) {
		new JTextFieldDemo("Demo", args);
	}

	public JTextFieldDemo(final String title, final String args[]) {
		if (args.length == 4) {
			name = args[0];
			address = args[1];
			city = args[2];
			phone = args[3];
		} else {
			name = "";
			address = "";
			city = "";
			phone = "";
		}
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
		jlbName = new JLabel("Naam");
		jlbAddress = new JLabel("Adres");
		jlbCity = new JLabel("Woonplaats");
		jlbPhone = new JLabel("Telefoon");

		jtfName = new JTextField(20);
		jtfAddress = new JTextField(20);
		jtfCity = new JTextField(20);
		jtfPhone = new JTextField(20);

		jbbOK = new JButton("Ok");
		jbnCancel = new JButton("Cancel");

		/* add all initialized components to the container */
		final GridBagConstraints gridBagConstraintsx01 = new GridBagConstraints();
		gridBagConstraintsx01.gridx = 0;
		gridBagConstraintsx01.gridy = 0;
		gridBagConstraintsx01.insets = new Insets(5, 5, 5, 5);
		cPane.add(jlbName, gridBagConstraintsx01);

		final GridBagConstraints gridBagConstraintsx02 = new GridBagConstraints();
		gridBagConstraintsx02.gridx = 1;
		gridBagConstraintsx02.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx02.gridy = 0;
		gridBagConstraintsx02.gridwidth = 2;
		gridBagConstraintsx02.fill = GridBagConstraints.BOTH;
		cPane.add(jtfName, gridBagConstraintsx02);

		final GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
		gridBagConstraintsx03.gridx = 0;
		gridBagConstraintsx03.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx03.gridy = 1;
		cPane.add(jlbAddress, gridBagConstraintsx03);

		final GridBagConstraints gridBagConstraintsx04 = new GridBagConstraints();
		gridBagConstraintsx04.gridx = 1;
		gridBagConstraintsx04.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx04.gridy = 1;
		gridBagConstraintsx04.gridwidth = 2;
		gridBagConstraintsx04.fill = GridBagConstraints.BOTH;
		cPane.add(jtfAddress, gridBagConstraintsx04);

		final GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
		gridBagConstraintsx05.gridx = 0;
		gridBagConstraintsx05.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx05.gridy = 2;
		cPane.add(jlbCity, gridBagConstraintsx05);

		final GridBagConstraints gridBagConstraintsx06 = new GridBagConstraints();
		gridBagConstraintsx06.gridx = 1;
		gridBagConstraintsx06.gridy = 2;
		gridBagConstraintsx06.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx06.gridwidth = 2;
		gridBagConstraintsx06.fill = GridBagConstraints.BOTH;
		cPane.add(jtfCity, gridBagConstraintsx06);

		final GridBagConstraints gridBagConstraintsx07 = new GridBagConstraints();
		gridBagConstraintsx07.gridx = 0;
		gridBagConstraintsx07.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx07.gridy = 3;
		cPane.add(jlbPhone, gridBagConstraintsx07);

		final GridBagConstraints gridBagConstraintsx08 = new GridBagConstraints();
		gridBagConstraintsx08.gridx = 1;
		gridBagConstraintsx08.gridy = 3;
		gridBagConstraintsx08.gridwidth = 2;
		gridBagConstraintsx08.insets = new Insets(5, 5, 5, 5);
		gridBagConstraintsx08.fill = GridBagConstraints.BOTH;
		cPane.add(jtfPhone, gridBagConstraintsx08);

		final GridBagConstraints gridBagConstraintsx09 = new GridBagConstraints();
		gridBagConstraintsx09.gridx = 0;
		gridBagConstraintsx09.gridy = 4;
		gridBagConstraintsx09.insets = new Insets(5, 5, 5, 5);
		cPane.add(jbbOK, gridBagConstraintsx09);

		final GridBagConstraints gridBagConstraintsx10 = new GridBagConstraints();
		gridBagConstraintsx10.gridx = 1;
		gridBagConstraintsx10.gridy = 4;
		gridBagConstraintsx10.insets = new Insets(5, 5, 5, 5);
		cPane.add(jbnCancel, gridBagConstraintsx10);
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
		name = jtfName.getText();
		address = jtfAddress.getText();
		city = jtfCity.getText();
		try {
			phone = jtfPhone.getText();
		} catch (final NumberFormatException e) {
			/*
			 * System.out.print("Input is a string"); JOptionPane.showMessageDialog(null,
			 * "Please enter Phone Number");
			 */
		}

		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a name.");
		} else {
			// Handle the entered data
			// JOptionPane.showMessageDialog(null, "OK action");
			dialog.dispose();
		}
	}

	public void cancelAction() {
		// JOptionPane.showMessageDialog(null,"Cancel action");
		dialog.dispose();
	}

	public boolean isActionOK() {
		return actionOK;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getPhone() {
		return phone;
	}
}
