package ddd.mittwoch;

import java.util.Objects;

public class Konto {
	private final Iban iban;
	private Geldbetrag saldo;

	public Konto(Iban iban, Geldbetrag startsaldo) {
		this.iban = Objects.requireNonNull(iban);
		this.saldo = Objects.requireNonNull(startsaldo);
	}

	public Iban getIban() {
		return iban;
	}

	public Geldbetrag getSaldo() {
		return saldo;
	}

	public void zahleEin(Geldbetrag betrag) {
		saldo = saldo.plus(betrag);
	}

	public void hebeAb(Geldbetrag betrag) {
		saldo = saldo.minus(betrag);
	}
}
