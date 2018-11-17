
///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
////                                                                                                   ////
////                           ACHTUNG! DIESE DATEI NICHT VERAENDERN!                                  ////
////                                                                                                   ////
////     Aenderungen an dieser Datei werden bei der Korrektur NICHT beruecksichtig, selbst             ////
////     wenn Sie die Datei mit abgeben. Ihr*e Tutor*in wird den Code auch mit der vorgegebenen        ////
////     Version dieser Datei ausprobieren!                                                            ////
////                                                                                                   ////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 * Diese Klasse enthaelt statische Methoden zum Berechnen von digitalen
 * Signaturen und Hilfsmethoden, um zwischen Hexadezimalstrings und Bytearrays
 * zu konvertieren
 *
 */
public class Hasher {

	/**
	 * Diese main Methode kann genutzt werden, um den Code der Aufgaben zu testen.
	 *
	 * Falls Sie noch nicht alle Teilaufgaben bearbeitet haben, muessen Sie ggf.
	 * Teile des Codes auskommentieren, damit das Programm compiliert werden kann.
	 *
	 * @param args Kommandozeilenparameter (wird ignoriert)
	 */
	public static void main(String[] args) {
		String secret = "5a0a092a14be892fb83ed29f394abee1" + "792ff3b26b6b6093b09a7cb8af7585ad"
				+ "466df1ad4fceb9612361bf0157b8ffa3" + "b09ff7ac14a824df8b4a5fa7c0494e2c";

		/*----------------------------------------------------------------------------------------------*/
		/* Aufgabenteil a) */
		/*----------------------------------------------------------------------------------------------*/
		System.out.println("---- Aufgabenteil a) ----");
		Algorithm a1 = Algorithm.HMAC_MD5;
		Algorithm a2 = Algorithm.HMAC_SHA1;
		Algorithm a3 = Algorithm.HMAC_SHA256;

		System.out.println("OK: Verfuebare Algorithmen: " + a1 + ", " + a2 + ", " + a3);

		/*----------------------------------------------------------------------------------------------*/
		/* Aufgabenteil b) */
		/*----------------------------------------------------------------------------------------------*/
		System.out.println("\n---- Aufgabenteil b) ----");

		Pair p1 = new Pair("a", "test");
		if (p1.getKey().equals("a") && p1.getValue().equals("test")) {
			System.out.println("OK: Erstellen von Paaren mit String-Wert");
		} else {
			System.out.println("FEHLER: Erstellen von Paaren mit String-Wert");
			System.out.println("\tKey erwartet: a\tKey: " + p1.getKey());
			System.out.println("\tValue erwartet: test\tValue: " + p1.getValue());
		}

		Pair p2 = new Pair("int", 42);
		if (p2.getKey().equals("int") && p2.getValue().equals("42")) {
			System.out.println("OK: Erstellen von Paaren mit Integer-Wert");
		} else {
			System.out.println("FEHLER: Erstellen von Paaren mit Integer-Wert");
			System.out.println("\tKey erwartet: int\tKey: " + p2.getKey());
			System.out.println("\tValue erwartet: 42\tValue: " + p2.getValue());
		}

		if (p2.toString().equals("'int': '42'")) {
			System.out.println("OK: Pair.toString");
		} else {
			System.out.println("FEHLER: Pair.toString");
			System.out.println("\tErwartet: 'int': '42'\tErhalten: " + p2.toString());
		}

		Pair p3 = Pair.fromString("'key': 'value'");
		if (p3.getKey().equals("key") && p3.getValue().equals("value")) {
			System.out.println("OK: Erstellen von Paaren aus String");
		} else {
			System.out.println("FEHLER: Erstellen von Paaren aus String");
			System.out.println("\tKey erwartet: key\tKey: " + p3.getKey());
			System.out.println("\tValue erwartet: value\tValue: " + p3.getValue());
		}
		/*----------------------------------------------------------------------------------------------*/
		/* Aufgabenteil c) */
		/*----------------------------------------------------------------------------------------------*/


		System.out.println("\n---- Aufgabenteil c) ----");

		Certificate sc = new Certificate(Algorithm.HMAC_SHA256, new Pair("i", 1), new Pair("str", "value"));

		if (sc.get("i").equals("1") && sc.get("str").equals("value") && sc.get("foo") == null) {
			System.out.println("OK: Erstellen von Zertifikaten");
		} else {
			System.out.println("FEHLER:  Erstellen von Zertifikaten");
			System.out.println("\ti erwartet: 1\ti: " + sc.get("i"));
			System.out.println("\tstr erwartet: value\tstr: " + sc.get("str"));
			System.out.println("\tfoo erwartet: null\tfoo: " + sc.get("foo"));
		}

		Certificate sc1 = Certificate
				.fromString("HMAC_MD5;'i': '42','string': 'string';9b55eef8e51651bb7f265204d281354c");
		if (sc1.get("i").equals("42") && sc1.get("string").equals("string") && sc1.get("foo") == null) {
			System.out.println("OK: Erstellen von Zertifikaten aus String");
		} else {
			System.out.println("FEHLER: Erstellen von Zertifikaten aus String");
			System.out.println("\ti erwartet: 42\ti: " + sc1.get("i"));
			System.out.println("\tstring erwartet: string\tstring: " + sc1.get("string"));
			System.out.println("\tfoo erwartet: null\tfoo: " + sc1.get("foo"));
		}

		/*----------------------------------------------------------------------------------------------*/
		/* Aufgabenteil d) */
		/*----------------------------------------------------------------------------------------------*/


		System.out.println("\n---- Aufgabenteil d) ----");
		System.out.println("\nNeu berechnete Signaturen sollten korrekt sein:\n");
		System.out.println("\t" + sc.getSignedString(secret));
		if (sc.validateSignature(secret)) {
			System.out.println("OK: above is valid");
		} else {
			System.out.println("FEHLER: above is not valid, but should be!");
		}

		sc = new Certificate(Algorithm.HMAC_SHA1, new Pair("i", 1), new Pair("str", "value"));

		System.out.println("\t" + sc.getSignedString(secret));
		if (sc.validateSignature(secret)) {
			System.out.println("OK: above is valid");
		} else {
			System.out.println("FEHLER: above is not valid, but should be!");
		}

		sc = new Certificate(Algorithm.HMAC_MD5, new Pair("i", 1), new Pair("str", "value"));

		System.out.println("\t" + sc.getSignedString(secret));
		if (sc.validateSignature(secret)) {
			System.out.println("OK: above is valid");
		} else {
			System.out.println("FEHLER: above is not valid, but should be!");
		}

		System.out.println("\nTest mit bekannten Signaturen:\n");

		System.out.println("\t" + sc1.getSignedString(secret));
		if (!sc1.validateSignature(secret)) {
			System.out.println("OK: above is invalid");
		} else {
			System.out.println("FEHLER: above is valid, but should not be!");
		}
		Certificate sc2 = Certificate.fromString("HMAC_MD5;'i': '1','str': 'value';9b55eef8e51651bb7f265204d281354c");

		System.out.println("\t" + sc2.getSignedString(secret));
		if (sc2.validateSignature(secret)) {
			System.out.println("OK: above is valid");
		} else {
			System.out.println("FEHLER: above is not valid, but should be!");
		}
		Certificate sc3 = Certificate.fromString(
				"HMAC_SHA256;'i': '1','str': 'value';93d0de88bef2c1cc63d485e330e067d3096aaa6fcc8c722d2de4d87991a0b54e");

		System.out.println("\t" + sc3.getSignedString(secret));
		if (sc3.validateSignature(secret)) {
			System.out.println("OK: above is valid");
		} else {
			System.out.println("FEHLER: above is not valid, but should be!");
		}

	}

	/**
	 * Konvertiert ein Bytearray in einen Hexadezimalstring.
	 *
	 * Beispiel: Das Array {@code 0, -61, 26} wuerde zum String {@code "00c31a"}
	 * konvertiert.
	 *
	 * @param bytes das zu konvertierende Bytearray
	 * @return Stringrepraesentation des Bytearrays
	 */
	public static String byteArrayToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder((new BigInteger(1, bytes)).toString(16));
		int pad = (bytes.length * 2) - sb.length();
		if (pad > 0) {
			sb.reverse();
			for (int i = 0; i < pad; ++i) {
				sb.append('0');
			}
			sb.reverse();
		}
		return sb.toString();
	}

	/**
	 * Erstellt ein Bytearray aus einem Hexadezimalstring
	 *
	 * Beispiel: Der String {@code "00c31a"} wuerde zum Array {@code 0, -61, 26}
	 * konvertiert.
	 *
	 * @param hex Die Hexadezimalzahl als String
	 * @return Ein Bytearray, das dem String entspricht
	 */
	public static byte[] hexStringToByteArray(String hex) {
		byte[] barr = (new BigInteger(hex, 16)).toByteArray();
		byte[] barr2 = new byte[hex.length() / 2];

		int pos = barr2.length;
		for (int i = barr.length; i > 0 && pos > 0; --i) {
			--pos;
			barr2[pos] = barr[i - 1];
		}

		return barr2;
	}

	/**
	 * Berechnet die HMAC SHA-256 Signatur fuer den gegebenen String mit dem
	 * gegebenen Geheimnis
	 * 
	 * @param toSign Der String, der signiert werden soll
	 * @param secret Das Geheimnis fuer die Signatur
	 * @return Die digitale Signatur
	 */
	public static byte[] sha256Hmac(String toSign, String secret) {
		return computeHmac("SHA-256", toSign, secret);
	}

	/**
	 * Berechnet die HMAC SHA-1 Signatur fuer den gegebenen String mit dem gegebenen
	 * Geheimnis
	 * 
	 * @param toSign Der String, der signiert werden soll
	 * @param secret Das Geheimnis fuer die Signatur
	 * @return Die digitale Signatur
	 */
	public static byte[] sha1Hmac(String toSign, String secret) {
		return computeHmac("SHA-1", toSign, secret);
	}

	/**
	 * Berechnet die HMAC MD5 Signatur fuer den gegebenen String mit dem gegebenen
	 * Geheimnis
	 * 
	 * @param toSign Der String, der signiert werden soll
	 * @param secret Das Geheimnis fuer die Signatur
	 * @return Die digitale Signatur
	 */
	public static byte[] md5Hmac(String toSign, String secret) {
		return computeHmac("MD5", toSign, secret);
	}

	private static final byte innerPad = 0x36;
	private static final byte outerPad = 0x5c;

	private static byte[] computeHmac(String algorithm, String toSign, String secret) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);

			byte[] secretBytes;
			if (secret.length() > 128) {
				secretBytes = md.digest(Hasher.hexStringToByteArray(secret));
				md.reset();
			} else if (secret.length() < 128) {
				secret = String.format("%128s", secret).replace(' ', '0');
				secretBytes = Hasher.hexStringToByteArray(secret);
			} else {
				secretBytes = Hasher.hexStringToByteArray(secret);
			}
			byte[] innerSecret = secretBytes;
			byte[] outerSecret = secretBytes;
			for (int i = 0; i < innerSecret.length; ++i) {
				innerSecret[i] ^= innerPad;
				outerSecret[i] ^= outerPad;
			}
			md.update(innerSecret);
			md.update(toSign.getBytes("UTF-8"));
			byte[] innerHash = md.digest();
			md.reset();
			md.update(outerSecret);
			md.update(innerHash);

			return md.digest();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException("Something went seriously wrong in Hasher.java");
		}
	}
}