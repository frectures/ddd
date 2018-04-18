package ddd.mittwoch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Iban {
	private static final Pattern pattern = Pattern.compile("([A-Z]{2})([0-9]{2})([A-Z0-9]{16,30})");

	private final String iban;

	public Iban(String iban) {
		Matcher matcher = pattern.matcher(iban);
		if (!matcher.matches())
			throw new IllegalArgumentException(iban + " does not match " + pattern);

		String ländercode = matcher.group(1);
		String prüfsumme = matcher.group(2);
		String bban = matcher.group(3);

		int modulo = modulo(bban + ländercode + prüfsumme);
		if (modulo != 1)
			throw new IllegalArgumentException("modulo " + modulo + " != 1");

		this.iban = iban;
	}

	private static int modulo(String umgestellt) {
		int result = 0;
		for (int i = 0; i < umgestellt.length(); ++i) {
			char c = umgestellt.charAt(i);
			int digit = Character.digit(c, 36);
			int factor = (digit < 10) ? 10 : 100;
			result = (result * factor + digit) % 97;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof Iban && equals((Iban) obj);
	}

	private boolean equals(Iban that) {
		return this.iban.equals(that.iban);
	}

	@Override
	public int hashCode() {
		return iban.hashCode();
	}

	@Override
	public String toString() {
		return iban;
	}

	public String menschenfreundlich() {
		final int gruppenlänge = 4;
		StringBuilder result = new StringBuilder();
		int i;
		for (i = 0; i + gruppenlänge < iban.length(); i += gruppenlänge) {
			result.append(iban.substring(i, i + gruppenlänge));
			result.append(' ');
		}
		result.append(iban.substring(i));
		return result.toString();
	}
}
