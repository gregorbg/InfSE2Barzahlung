package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class BarzahlungsWerkzeugUI
{
    private static final String TITEL = "Barzahlung";

    private JDialog _dialog;
    private JButton _okButton;

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

        JLabel preisLabel = new JLabel("12,00€");
        JLabel restLabel = new JLabel("12,00€");
        Font boldFont = preisLabel.getFont().deriveFont(Font.BOLD).deriveFont(32f);
        preisLabel.setFont(boldFont);
        restLabel.setFont(boldFont);

        //FIXME Formatierungen richtig akzeptieren
        NumberFormat gezahltFormat = NumberFormat.getIntegerInstance();
        NumberFormatter gezahltFormatter = new NumberFormatter(gezahltFormat);
        JFormattedTextField gezahltField = new JFormattedTextField(gezahltFormatter);

        _okButton = new JButton("OK");
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

    public void zeigeDialog() {
        _dialog.setSize(500, 300);
        _dialog.setVisible(true);
    }

    public void schließeFenster() {
        _dialog.dispose();
    }
}
