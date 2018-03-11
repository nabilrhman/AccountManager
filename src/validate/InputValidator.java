package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nabilrahman on 3/7/18.
 */
public class InputValidator
{


    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,30}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d$@$!%*#?&]{6,30}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //format is DD/MM/YYYY, might need to be MM/DD/YYYY
    private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
    private static final String NAME_PATTERN = "[^0-9]{1,20}";
    
    private Pattern pattern;
    private Matcher matcher;

    /**
     * Validate username with regular expression
     *
     * @param username username for validation
     * @return true valid username, false invalid username
     */
    public boolean validateUsername(String username)
    {

        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validatePassword(String password)
    {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Validate email with regular expression
     *
     * @param email email for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validateEmail(String email)
    {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * validate date with regular expression
     * @param date string in DD/MM/YYYY
     * @return true if valid, false if not
     */
    public boolean validateBirthdate(String date)
    {
    	pattern = Pattern.compile(DATE_PATTERN);
    	matcher = pattern.matcher(date);
    	return matcher.matches();
    	
    }
    
	/**
	 * validate First or Last name with regular expression
	 * allows anything that doesn't have a number in it
	 * between 1 to 20 characters
	 * 
	 * @param name String to be validated
	 * @return true if valid, false if not
	 */
	public boolean validateFirstLastName(String name) {
		pattern = Pattern.compile(NAME_PATTERN);
		matcher = pattern.matcher(name);
		return matcher.matches();
	}
}
