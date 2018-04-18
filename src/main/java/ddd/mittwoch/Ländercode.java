package ddd.mittwoch;

public enum Ländercode {
	DE(13), AT(10), CH(10);

	public final int beginnDerKontonummerInIban;

	Ländercode(int beginnDerKontonummerInIban) {
		this.beginnDerKontonummerInIban = beginnDerKontonummerInIban;
	}
}
