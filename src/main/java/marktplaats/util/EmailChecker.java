package marktplaats.util;

public class EmailChecker {

    public static boolean isValideEmail(String email){
            if (email.length() > 64) {
                return false;
            }
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            return email.matches(regex);
    }
}
