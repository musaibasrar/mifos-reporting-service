package org.ideoholic.mrs.util;

import java.security.SecureRandom;
import java.util.Base64;

public class HashUtil {

	private static final SecureRandom random = new SecureRandom();

	public static String generateShortId() {
		byte[] buffer = new byte[6]; // 6 bytes = 48 bits
		random.nextBytes(buffer);
		String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(buffer);
		return base64.substring(0, 8); // Trim to 8 characters
	}
}
