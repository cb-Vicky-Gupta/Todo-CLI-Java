package todo.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    public static String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean matchPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password,hashedPassword);
    }
}
