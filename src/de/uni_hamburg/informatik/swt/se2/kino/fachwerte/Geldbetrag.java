package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.text.NumberFormat;
import java.util.Locale;

public class Geldbetrag
{
    private static final NumberFormat euroFormat = NumberFormat.getCurrencyInstance(
            Locale.getDefault());

    private int _eurocent;

    public Geldbetrag() {
        this(0);
    }

    public Geldbetrag(int eurocent) {
        _eurocent = eurocent;
    }

    public int getEurocent() {
        return _eurocent;
    }

    public Geldbetrag diff(Geldbetrag other) {
        return new Geldbetrag(_eurocent - other.getEurocent());
    }

    public String getFormatiertenString() {
        return euroFormat.format((float) _eurocent / 100);
    }
}
