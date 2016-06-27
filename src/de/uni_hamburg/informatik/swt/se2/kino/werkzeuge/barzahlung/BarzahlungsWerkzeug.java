package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.util.Set;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;

public class BarzahlungsWerkzeug {
    private BarzahlungsWerkzeugUI _ui;

    public BarzahlungsWerkzeug() {
	_ui = new BarzahlungsWerkzeugUI();
    }

    public boolean verkaufe(Set<Platz> t, int p) {
	return _ui.zeigeDialog(t, p);
    }
}
