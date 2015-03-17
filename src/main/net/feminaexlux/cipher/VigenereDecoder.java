package net.feminaexlux.cipher;

public class VigenereDecoder {

	private final int[] encoded;

	public VigenereDecoder(final String encodedString) {
		if (isNullOrEmpty(encodedString)) {
			throw new IllegalArgumentException("Must not be null or empty");
		}

		if (encodedString.length() % 2 != 0) {
			throw new IllegalArgumentException("Not a valid string length");
		}

		encoded = new int[encodedString.length() / 2];

		int index = 0;
		for (int start = 0; start < encodedString.length(); start += 2) {
			encoded[index] = Integer.parseInt(encodedString.substring(start, start + 2), 16);
			index++;
		}
	}

	public void findLength() {
		for (int length = 1; length < 14; length++) {
			int position = 0;
			int[] hits = new int[256];

			while ((position * length) < encoded.length) {
				int read = position * length;
				hits[encoded[read]] = hits[encoded[read]] + 1;
				position++;
			}

			double sum = 0;
			for (int hit : hits) {
				sum += Math.pow((double) hit / (double) (position + 1), 2);
			}

			System.out.println(length + ": " + sum);
		}
	}

	public String tryKey(char[] key, int length) {
		char[] decoded = new char[encoded.length];

		int pointer = 0;
		while (pointer < encoded.length) {
			for (int shift = 0; shift < length; shift++) {
				int location = pointer + shift;

				if (location >= encoded.length) {
					break;
				}

				if (shift < key.length) {
					decoded[location] = (char) (encoded[location] ^ (int) key[shift]);
				} else {
					decoded[location] = (char) encoded[location];
				}
			}

			pointer += length;
		}

		return new String(decoded);
	}

	public String tryCombination(char character, int offset, int length) {
		char[] decoded = new char[(encoded.length / length) + 1];

		int position = offset;
		int index = 0;
		while (position < encoded.length) {
			decoded[index] = (char) (encoded[position] ^ (int) character);
			index++;
			position += length;
		}

		for (char decodedChar : decoded) {
			if (decodedChar > (char) 127) {
				return null;
			}
		}

		return new String(decoded);
	}

	private boolean isNullOrEmpty(final String string) {
		return string == null || string.trim().length() == 0;
	}
}