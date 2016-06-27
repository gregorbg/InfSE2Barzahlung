package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;

public class BarzahlungsWerkzeugUI {
    private static final String TITEL = "Barzahlung";

    // private Set<Platz> _plaetze;
    private JDialog _dialog;
    private JButton _okButton;
    private DecimalFormat formatter = new DecimalFormat("00.00€");
    private double _preis = 10.00;
    // private double _gezahlt = 10.00;
    private double _rest = 00.00;
    private JFormattedTextField gezahltField;
    private boolean _bezahlt = false;
    private JLabel preisLabel;
    private JLabel restLabel;

    public BarzahlungsWerkzeugUI() {
	_dialog = new JDialog();
	_dialog.setTitle(TITEL);
	_dialog.setModal(true);
	_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	_dialog.setLayout(new BorderLayout());

	GridLayout layout = new GridLayout(4, 2, 20, 20);
	JPanel contentPane = new JPanel(layout);
	contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

	JLabel preisTitelLabel = new JLabel("Preis");
	JLabel gezahltTitelLabel = new JLabel("gezahlt");
	JLabel restTitelLabel = new JLabel("Restbetrag");

	preisLabel = new JLabel("12,00€");
	restLabel = new JLabel("12,00€");
	Font boldFont = preisLabel.getFont().deriveFont(Font.BOLD).deriveFont(32f);
	preisLabel.setFont(boldFont);
	restLabel.setFont(boldFont);

	// FIXME Formatierungen richtig akzeptieren
	NumberFormat gezahltFormat = NumberFormat.getIntegerInstance();
	NumberFormatter gezahltFormatter = new NumberFormatter(gezahltFormat);
	gezahltField = new JFormattedTextField(gezahltFormatter);
	gezahltField.getDocument().addDocumentListener(new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		if (isInteger(gezahltField.getText()))
		    UpdateRestBetrag();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		if (isInteger(gezahltField.getText()))
		    UpdateRestBetrag();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		JOptionPane.showMessageDialog(null, "changedUpdate", "", JOptionPane.INFORMATION_MESSAGE);

	    }
	});

	_okButton = new JButton("OK");
	_okButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		okListener();
	    }
	});

	JButton beendenButton = new JButton("Beenden");
	beendenButton.addActionListener(e -> _dialog.dispose());

	contentPane.add(preisTitelLabel);
	contentPane.add(preisLabel);
	contentPane.add(gezahltTitelLabel);
	contentPane.add(gezahltField);
	contentPane.add(restTitelLabel);
	contentPane.add(restLabel);
	contentPane.add(_okButton);
	contentPane.add(beendenButton);

	_dialog.getContentPane().add(contentPane, BorderLayout.CENTER);
    }

    public boolean zeigeDialog(Set<Platz> t, int p) {
	_preis = p;
	preisLabel.setText(formatter.format((p / 100)));

	_dialog.setSize(500, 300);
	_dialog.setVisible(true);

	// JOptionPane.showMessageDialog(null, "Tester", "", JOptionPane.INFORMATION_MESSAGE);

	return _bezahlt;
    }

    private void okListener() {
	_gezahlt = Double.parseDouble((gezahltField.getText()));

	// JOptionPane.showMessageDialog(null, "_PReis: " + _gezahlt, "", JOptionPane.INFORMATION_MESSAGE);

	/*
	 * JOptionPane.showMessageDialog(null, "_PReis: " + _preis, "", JOptionPane.INFORMATION_MESSAGE);
	 * JOptionPane.showMessageDialog(null, "_Gezahlt: " + _gezahlt, "", JOptionPane.INFORMATION_MESSAGE);
	 */

	if (_rest > 0) {
	    JOptionPane.showMessageDialog(null, "Entweder vollen Preis bezahlen oder abbrechen.", "", JOptionPane.INFORMATION_MESSAGE);
	    return;
	}

	_bezahlt = true;

	_dialog.dispose();
    }

    private void UpdateRestBetrag() {
	try {
	    // getFirstVal
	    restLabel.setText(formatter.format((_preis - Integer.parseInt(gezahltField.getText())) / 100));
	    _rest = _preis - Integer.parseInt(gezahltField.getText());
	}
	catch (Exception e) {
	    // TODO: handle exception
	}
    }

    public void schließeFenster() {
	_bezahlt = false;
	_dialog.dispose();
    }

    public static boolean isInteger(String s) {
	try {
	    Integer.parseInt(s);
	}
	catch (NumberFormatException e) {
	    return false;
	}
	catch (NullPointerException e) {
	    return false;
	}
	// only got here if we didn't return false
	return true;
    }
}
