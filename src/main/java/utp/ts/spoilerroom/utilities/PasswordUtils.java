package utp.ts.spoilerroom.utilities;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtils
{
	public static boolean checkPassword(String password, String passwordSalt, String passwordHash)
	{
		ByteSource salt = ByteSource.Util.bytes(Hex.decode(passwordSalt));
		String newHash = hashPassword(password, salt);

		return passwordHash.equals(newHash);
	}

	public static ByteSource getSalt()
	{
		return new SecureRandomNumberGenerator().nextBytes();
	}

	public static String hashPassword(String password, ByteSource salt)
	{
		return new Sha256Hash(password, salt, 1024).toBase64();
	}
}
