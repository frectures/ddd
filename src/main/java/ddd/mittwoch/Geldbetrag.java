package ddd.mittwoch;

import java.util.Objects;

public class Geldbetrag {
	private final long betrag;
	private final Währung währung;

	public Geldbetrag(long betrag, Währung währung) {
		this.betrag = betrag;
		this.währung = Objects.requireNonNull(währung);
	}

	public long getBetrag() {
		return betrag;
	}

	public Währung getWährung() {
		return währung;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof Geldbetrag && equals((Geldbetrag) obj);
	}

	private boolean equals(Geldbetrag that) {
		return this.betrag == that.betrag && this.währung == that.währung;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(betrag);
	}

	@Override
	public String toString() {
		String mindestens3Stellen = String.format("%03d", betrag);
		int kommaIndex = mindestens3Stellen.length() - 2;

		String vorKomma = mindestens3Stellen.substring(0, kommaIndex);
		String nachKomma = mindestens3Stellen.substring(kommaIndex);
		return vorKomma + "," + nachKomma + " " + währung;
	}

	public Geldbetrag plus(Geldbetrag that) {
		assertSameWährung(that);

		return new Geldbetrag(this.betrag + that.betrag, währung);
	}

	public Geldbetrag minus(Geldbetrag that) {
		assertSameWährung(that);

		return new Geldbetrag(this.betrag - that.betrag, währung);
	}

	public int vergleicheMit(Geldbetrag that) {
		assertSameWährung(that);

		return Long.compare(this.betrag, that.betrag);
	}

	private void assertSameWährung(Geldbetrag that) {
		if (this.währung != that.währung)
			throw new AssertionError(this.währung + " != " + that.währung);
	}
}
