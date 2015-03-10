package net.feminaexlux.cipher;

public class VigenereDecoder {

	// Frequencies of a-z in alphabetical order; FREQUENCIES[0] = frequency of "A", FREQUENCIES[3] = frequency of "D"
	private static final double[] FREQUENCIES = new double[] { 8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966,
			0.153, 0.772, 4.025, 2.406, 6.749, 7.507, 1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150, 1.974,
			0.074 };

	private static final char MIN_CHAR = (char) 32;
	private static final char MAX_CHAR = (char) 127;

	private final char[] encoded;

	public VigenereDecoder(final String encodedString) {
		if (isNullOrEmpty(encodedString)) {
			throw new IllegalArgumentException("Must not be null or empty");
		}

		if (encodedString.length() % 2 != 0) {
			throw new IllegalArgumentException("Not a valid string length");
		}

		encoded = new char[encodedString.length() / 2];

		int index = 0;
		for (int start = 0; start < encodedString.length(); start += 2) {
			encoded[index] = (char) Integer.parseInt(encodedString.substring(start, start + 2), 16);
			index++;
		}
	}

	public double calculateProbability(final char key, final int length) {
		int count = 0;
		int charIndex = 0;
		char[] shifted = new char[encoded.length / length];
		while (charIndex < encoded.length) {
			shifted[count] = encoded[charIndex];
			charIndex += length;
		}

		return 0;
	}

	public String printDecoded() {
		return String.valueOf(encoded);
	}

	private boolean isNullOrEmpty(final String string) {
		return string == null || string.trim().length() == 0;
	}

}