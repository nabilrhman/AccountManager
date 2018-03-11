package test;

import junit.framework.TestCase;

import validate.InputValidator;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
/**
 * @author Brody Downs
 * 
 * jUnit tests for InputValidator
 *
 */
public class ValidatorTest extends TestCase {
	
	private InputValidator val;
	
	protected void setUp() throws Exception {
		super.setUp();
		val = new InputValidator();
	}
	
	/*
	 * tests for first and last names
	 */
	public void testNames () {
		
		assertTrue(val.validateName("john"));
		assertFalse(val.validateName("john1"));
		assertTrue(val.validateName("Andr�s"));
		assertTrue(val.validateName("Pierre-Louis"));
		assertTrue(val.validateName("O'Reilly"));
		
	}
	
	/*
	 * tests for usernames
	 */
	public void testUsernames() {
		assertTrue(val.validateUsername("abc"));
		assertFalse(val.validateUsername("a")); //too short
		assertFalse(val.validateUsername("$noopDogg")); //special character 
		assertFalse(val.validateUsername("bad.user.name")); //special character
		assertFalse(val.validateUsername("01234567890123456789012345667890")); //too long
		assertTrue(val.validateUsername("Good_user-name123"));
	}
	
	/*
	 * tests for emails
	 */
	public void testEmails() {
		assertTrue(val.validateEmail("myemail@google.com"));
		assertFalse(val.validateEmail("myemail@google"));
		assertFalse(val.validateEmail("@gmail.com"));
		assertTrue(val.validateEmail("brodydowns@u.boisestate.edu"));
		assertFalse(val.validateEmail("myemailgoogle.com"));
	}
	
	/*
	 * tests for passwords
	 */
	public void testPasswords() {
		assertFalse(val.validatePassword("2Shrt"));
		assertFalse(val.validatePassword("nocapitalss1"));
		assertFalse(val.validatePassword("NOLOWERS1"));
		assertTrue(val.validatePassword("GoodPassword1"));
		assertTrue(val.validatePassword("GoodPass1!"));
	}
	
	
	/*
	 * tests for birthdate
	 */
	public void testDate() {
		
		
		
		assertTrue(val.validateBirthdate(LocalDate.of(1992, 4, 30)));
		assertTrue(val.validateBirthdate(LocalDate.of(1992, 1, 1)));
		assertFalse(val.validateBirthdate(LocalDate.of(1800, 4, 30)));
		assertFalse(val.validateBirthdate(LocalDate.of(2019, 4, 5)));
	}
	 

}