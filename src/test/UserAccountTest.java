package test;


import junit.framework.TestCase;
import model.UserAccount;

public class UserAccountTest extends TestCase {

    private UserAccount userAccount;

    protected void setUp() throws Exception {
        super.setUp();
        userAccount = new UserAccount("admin", "123456");
    }

    public void testNewAccount() {

        assertNotNull(userAccount);


        assertTrue(userAccount.matchUserName("admin"));
        assertTrue(userAccount.isValidCredential("admin", "123456"));
    }

    /**
     * Tests setters and getters for firstName.
     *
     * @author Edgar Sosa
     */
    public void testFirstName() {
        userAccount.setFirstName("Random");
        assertTrue(userAccount.getFirstName().equals("Random"));
    }


    /**
     * Tests setters and getters for lastName.
     *
     * @author Edgar Sosa
     */
    public void testLastName() {
        userAccount.setLastName("Reynoso");
        assertTrue(userAccount.getLastName().equals("Reynoso"));
    }

    /**
     * Tests setters and getters for email.
     *
     * @author Edgar Sosa
     */
    public void testEmail() {
        userAccount.setEmail("bobsmith@u.boisestate.edu");
        assertTrue(userAccount.getEmail().equals("bobsmith@u.boisestate.edu"));
    }

    /**
     * Tests setters and getters for password.
     *
     * @author Edgar Sosa
     */
    public void testPassword() {
        userAccount.setPassword("12345ab");
        assertTrue(userAccount.getPassword().equals("12345ab"));
    }

    /**
     * Tests setters and getters for userName.
     *
     * @author Edgar Sosa
     */
    public void testUserName() {
        userAccount.setUserName("doggo");
        assertTrue(userAccount.getUserName().equals("doggo"));
    }

}
