/**
 * Zertifikat zum Signieren, Validieren und Serialisieren von
 * Schluessel-Wert-Paaren
 */
public class Certificate {

	// Private Members
	private Algorithm algorithm;
	private Pair[] pairs;
	private String signatur;

	/**
	 * Konstruiert ein neues Certificat
	 * 
	 * @param Algorithm Der gewuenschte Hash-Algorithmus
	 * @param Pairs     Beliebige Anzahl an Schluessel-Wert-Paaren
	 */
	public Certificate(Algorithm Algorithm, Pair... Pairs) {
		algorithm = Algorithm;
		pairs = Pairs;
	}

	/**
	 * Ruft den zum Schluessel gehoerigen Wert ab
	 * 
	 * @param key Der Schluessel des Schluessel-Wert-Paares
	 * @return Der Wert des Schluessel-Wert-Paares, oder null falls ein solches
	 *         Schluessel-Wert-Paar nicht im Zertifikat vorhanden ist
	 */
	public String get(String key) {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i] != null && pairs[i].getKey().equals(key))
				return pairs[i].getValue();
		}
		return null;
	}

	/**
	 * Deserialisiert ein Zertifikat Objekt aus Textform
	 * 
	 * @param certificate Textform eines serialisiertes Zertifikates
	 * @return Deserialisiertes Zertifikat Objekt
	 */
	public static Certificate fromString(String certificate) {
		String[] data = certificate.split(";");
		// Resolves to { Header, Body, Signatur }

		Algorithm algorithm = Algorithm.valueOf(data[0]);
		// Only throws an error for corrupted header data - in which it should not be
		// accepted either way

		String[] pairData = data[1].split("\\s*,\\s*");
		// Resolves to a list of raw pair strings

		Pair[] pairs = new Pair[pairData.length];
		for (int i = 0; i < pairData.length; i++)
			pairs[i] = Pair.fromString(pairData[i]);
		// Raw pair data has been read in

		// Create certificate
		Certificate cert = new Certificate(algorithm, pairs);
		// Write signature manually since it's private
		cert.signatur = data[2];

		return cert;
	}

	/**
	 * Signiere dieses Zertifikat mit gegebenen Geheimnis, falls noch nicht
	 * geschehen, und gebe das Zertifikat in serialisierter Textform zurueck
	 * 
	 * @param secret Geheimnis zum Signieren - wird nur genutzt, falls das
	 *               Zertifikat noch nicht signiert wurde
	 * @return Serialisiertes Zertifikat mit Signatur in Textform
	 */
	public String getSignedString(String secret) {
		String content = getHeaderBodyString();
		if (signatur == null || signatur.length() == 0) { // Recreate signature
			signatur = Hasher.byteArrayToHex(getSignature(content, secret));
		}
		return content + signatur;
	}

	/**
	 * Validiere die Signatur dieses Zertifikats mit gegebenen Geheimnis
	 * 
	 * @param secret Das Geheimnis, welches zum Signieren verwendet wurde
	 * @return Wahrheitswert, ob die signatur dieses Zertifikats mit dem gegebenen
	 *         Geheimnis generiert wurde
	 */
	public Boolean validateSignature(String secret) {

		if (signatur == null || signatur.length() == 0) { // This gives no information away since a signature is never
															// empty
			return false;
		}

		String content = getHeaderBodyString();

		// (1) This is considered 'secret information'
		byte[] validSignature = getSignature(content, secret);

		// (2) This is considered 'public information'
		byte[] existingSignature = Hasher.hexStringToByteArray(signatur);

		// Create a timing-attack resistant comparision
		// This assumes the hashing function used in (1) is timing-attack resistant!
		// Hasher.hexStringToByteArray used in (2) is NOT timing-attack resistant,
		// but this is irrelevant since it only processes 'public information'.

		// This neglects that WebServices can't be accurately timed either way due to
		// latency

		// 1. Create copy of the validate byte array with length of existing signature,
		// so that the number of instructions required is solely dependant on the
		// existing sign length
		int sharedLength = existingSignature.length;
		byte[] validSignEq = new byte[sharedLength];
		for (int i = 0; i < sharedLength; i++) {
			// Modulo-Operator is time-constant in most common implementations
			int safeIndex = i % sharedLength;
			validSignEq[i] = validSignature[safeIndex];
		}

		// 2. Compare byte arrays on full length with same instructions, regardless of
		// byte state
		// Neither "byte1 == byte2 && eq" or "eq && byte1 == byte2" 
		// will execute the second comparison if the first is already false, so both are out of question
		// An alternative is a counter, so that both the comparision AND the addition is always performed.
		// 2 and 1 has been chosen to make sure the compiler does not optimize this out.
		int cnt = 0; 
		for (int i = 0; i < sharedLength; i++) {
			cnt += (existingSignature[i] == validSignEq[i])? 2 : 1;
		}
		
		// Return true only if ALL bytes are equal
		return cnt == sharedLength * 2;
		

		// Now both steps combined for performance:
		/*int cnt = 0; 
		int sharedLength = existingSignature.length;
		for (int i = 0; i < sharedLength; i++) {
			cnt += (existingSignature[i] == validSignature[i % sharedLength])? 2 : 1;
		}

		return cnt == sharedLength * 2;*/
	}

	/**
	 * Signiere gegebenen Inhalt den Algorithmus dieses Zertifikats
	 * 
	 * @param content Inhalt, der signiert werden soll
	 * @param secret  Geheimnis, um den Inhalt zu signieren
	 * @return Valide Signatur dieses Inhalts
	 */
	private byte[] getSignature(String content, String secret) {
		switch (algorithm) {
		case HMAC_MD5:
			return Hasher.md5Hmac(content, secret);
		case HMAC_SHA1:
			return Hasher.sha1Hmac(content, secret);
		case HMAC_SHA256:
			return Hasher.sha256Hmac(content, secret);
		default:
			return null;
		}
	}

	/**
	 * Serialisiere dieses Zertifikats ohne Signatur in Textform
	 * 
	 * @return Serialisiertes Zertifikat ohne Signatur in Textform
	 */
	private String getHeaderBodyString() {
		// Use StringBuilder in case of many key-value pairs
		StringBuilder builder = new StringBuilder();
		builder.append(algorithm.toString());
		builder.append(";");
		for (int i = 0; i < pairs.length; i++) {
			builder.append(pairs[i].toString());
			if (i < pairs.length - 1)
				builder.append(",");
		}
		builder.append(";");
		return builder.toString();
	}
}