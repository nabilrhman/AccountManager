package test;


import junit.framework.TestCase;
import model.UserAccount;

public class UserAccountTest extends TestCase {

    private UserAccount userAccount;

    public void testNewAccount() {

        userAccount = new UserAccount("admin", "123456");
        assertNotNull(userAccount);

        // Tests setters and getters for firstName.
        userAccount.setFirstName("Random");
        assertTrue(userAccount.getFirstName().equals("Random"));

        // Tests setters and getters for lastName.
        userAccount.setLastName("Reynoso");
        assertTrue(userAccount.getLastName().equals("Reynoso"));

        // Tests setters and getters for email.
        userAccount.setEmail("bobsmith@u.boisestate.edu");
        assertTrue(userAccount.getEmail().equals("bobsmith@u.boisestate.edu"));

        // Tests setters and getters for password.
        userAccount.setPassword("12345ab");
        assertTrue(userAccount.getPassword().equals("12345ab"));

        // Tests setters and getters for userName.
        userAccount.setUserName("doggo");
        assertTrue(userAccount.getUserName().equals("doggo"));

        assertTrue(userAccount.matchUserName("admin"));
        assertTrue(userAccount.isValidCredential("admin", "123456"));
    }

}
