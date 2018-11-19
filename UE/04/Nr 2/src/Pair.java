/**
 * Ein nicht-volatiles Paar aus Schluessel und Wert
 */
public class Pair {
	
	// Private members
	private final String key, value;

	/**
	 * Konstruiert ein Schluessel-Wert-Paar
	 * @param Key Der Schluessel
	 * @param Value Der Wert
	 */
	public Pair(String Key, String Value) {
		// Copy strings to be safe
		key = new String (Key);
		value = new String (Value);
	}

	/**
	 * Konstruiert ein Schluessel-Wert-Paar mit einem Integer-Wert
	 * @param Key   Der Schluessel
	 * @param Value Der Integer-Wert - wird als String gespeichert
	 */
	public Pair(String Key, Integer Value) {
		// Copy strings to be safe
		key = new String (Key);
		value = Value.toString();
	}

	/**
	 * Gibt den Schluessel des Schluessel-Wert-Paares zurueck
	 * @return Der Schluessel
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gibt den Wert des Schluessel-Wert-Paares zurueck
	 * @return Der Wert
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Serialisiert das Schluessel-Wert-Paar zu Textform
	 * @return Das Schluessel-Wert-Paar in Textform serialisiert
	 */
	public String toString() {
		return "'" + getKey() + "': '" + getValue() + "'";
	}

	/**
	 * Deserialisiert ein Schluessel-Wert-Paar aus Textform
	 * @param pair Textform eines Schluessel-Wert-Paares
	 * @return Deserialisiertes Schluessel-Wert-Paar Objekt
	 */
	public static Pair fromString(String pair) {
		String[] data = pair.split("\\s*'\\s*");
		// resolves in { "", key, ":", value }
		if (data.length != 4)
			return null;
		return new Pair(data[1], data[3]);
	}
}