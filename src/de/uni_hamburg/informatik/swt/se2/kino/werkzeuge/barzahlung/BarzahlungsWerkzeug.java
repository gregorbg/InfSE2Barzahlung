package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Geldbetrag;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Das Barzahlungs-Werkzeug
 */
public class BarzahlungsWerkzeug
{
    private BarzahlungsWerkzeugUI _ui;

    private Geldbetrag _betrag;

    /**
     * Erstellt ein neues Werkzeug
     */
    public BarzahlungsWerkzeug() {
        _ui = new BarzahlungsWerkzeugUI();

        _betrag = new Geldbetrag();

        registriereUIAktionen();
    }

    /**
     * Meldet alle Akionen in Form von Listenern auf relevante UI-Komponenten an
     */
    private void registriereUIAktionen()
    {
        _ui.gibOkButton().addActionListener(e -> pruefeEingabe());

        _ui.gibAbbrechenButton().addActionListener(e -> _ui.schließeFenster(0));

        _ui.gibGezahltTextField().getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                aktualisiereRestbetrag();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                aktualisiereRestbetrag();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                aktualisiereRestbetrag();
            }
        });

        _ui.gibGezahltTextField().addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) pruefeEingabe();
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) _ui.schließeFenster(0);
            }
        });
    }

    /**
     * Startet den Berkaufsprozess für einen Geldbetrag
     *
     * @param betrag Der Geldbetrag zu dem verkauft wird
     * @return Ob der Verkauf erflogreich war (also genug gezahlt und OK gedrückt wurde)
     *
     * @require betrag != null
     */
    public boolean verkaufe(Geldbetrag betrag) {
        assert betrag != null : "Vorbedingung verletzt: null!";

        _betrag = betrag;

        _ui.setzePreisText(betrag);
        _ui.setzeRestbetragText(betrag);

        _ui.gibGezahltTextField().setText("");
        aktualisiereRestbetrag();

        _ui.zeigeDialog();

        return _ui.gibStatusCode() == 1;
    }

    /**
     * Listener-Methode, die den Inhalt des Rstbetrag-Feldes passend aktualisiert
     */
    private void aktualisiereRestbetrag() {
        Geldbetrag gezahlt = _ui.gibEingegebenenBetrag();
        Geldbetrag rest = _betrag.diff(gezahlt);

        if (rest.gibEurocent() < 0) rest = new Geldbetrag();

        _ui.setzeRestbetragText(rest);
    }

    /**
     * Listener-Methode, die bei Druck auf den OK-Knopf prüft, ob der Verkauf passt
     */
    private void pruefeEingabe() {
        Geldbetrag gezahlt = _ui.gibEingegebenenBetrag();

        if (_betrag.diff(gezahlt).gibEurocent() <= 0) {
            _ui.schließeFenster(1);
        } else {
            JOptionPane.showMessageDialog(null, "Zu wenig gezahlt!", "Fehler", JOptionPane.ERROR_MESSAGE);
            _ui.gibGezahltTextField().requestFocus();

            int textLaenge = _ui.gibGezahltTextField().getText().length();
            _ui.gibGezahltTextField().setCaretPosition(textLaenge);
        }
    }
}
