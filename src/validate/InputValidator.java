package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nabilrahman on 3/7/18.
 */
public static class InputValidator
{


    private Pattern pattern;
    private Matcher matcher;
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    /**
     * Validate username with regular expression
     * @param username username for validation
     * @return true valid username, false invalid username
     */
    public boolean validateUsername(final String username)
    {

        pattern = Pattern.compile(USERNAME_PATTERN);

        matcher = pattern.matcher(username);
        return matcher.matches();
    }

}
