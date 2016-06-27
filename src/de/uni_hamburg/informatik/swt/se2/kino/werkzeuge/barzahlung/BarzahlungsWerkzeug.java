package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;


public class BarzahlungsWerkzeug {
    private BarzahlungsWerkzeugUI _ui;

    public BarzahlungsWerkzeug() {
	_ui = new BarzahlungsWerkzeugUI();
    }

    public boolean verkaufe(int p) {
	return _ui.zeigeDialog(p);
    }
}
