package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeldbetragTest
{
    @Test
    public final void testGeldbetrag()
    {
        Geldbetrag betrag = new Geldbetrag(100);
        assertEquals(100, betrag.getEurocent());
        assertEquals("1,00€", betrag.getFormatiertenString());

        betrag = new Geldbetrag(0);
        assertEquals(0, betrag.getEurocent());
        assertEquals("0,00€", betrag.getFormatiertenString());

        betrag = new Geldbetrag(99);
        assertEquals(99, betrag.getEurocent());
        assertEquals("0,99€", betrag.getFormatiertenString());

        betrag = new Geldbetrag(101);
        assertEquals(101, betrag.getEurocent());
        assertEquals("1,01€", betrag.getFormatiertenString());
    }
}
