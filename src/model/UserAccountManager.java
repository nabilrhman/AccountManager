package model;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

//import com.fasterxml.jackson.databind.ObjectMapper;

public class UserAccountManager {

    private ArrayList<UserAccount> userAccounts;

    public UserAccountManager() {
        userAccounts = new ArrayList<UserAccount>();
    }

    public void addUserAccount(UserAccount userAccount){
        userAccounts.add(userAccount);
    }

    public void addUserAccount(String userName, String password){
    	if (!doesUserNameExist(userName))
    		userAccounts.add(new UserAccount(userName,password));
    }

    /**
     *
     * @param userName
     * @param password
     */
    public void removeUserAccount(String userName, String password){
        if (doesUserNameExist(userName))
            userAccounts.remove(new UserAccount(userName,password));
    }

    public boolean doesAccountExist(String userName, String password) {
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.isValidCredential(userName, password))
    			return true;
       return false;
    }

    /**
     * @author Edgar Sosa
     * @param firstName
     * @param lastName
     * @param email
     * @param birthDate
     * @return
     */
    public boolean doesAccountExist(String firstName, String lastName, String
            email, LocalDate birthDate) {
        for (UserAccount userAccount: userAccounts)
            if (userAccount.isValidCredential(firstName, lastName, email,
                    birthDate))
                return true;
        return false;
    }

    /**
     *
     * @param userName
     * @param dateOfBirth
     * @return
     */
    public boolean doesAccountExist(String userName, LocalDate dateOfBirth) {
        for (UserAccount userAccount: userAccounts)
            if(userAccount.isValidCredential(userName, dateOfBirth))
                return true;
        return false;
    }

    public boolean doesUserNameExist(String userName){
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.matchUserName(userName))
    			return true;
       return false;
    }

    public boolean doesEmailExist(String email) {
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.matchEmail(email))
    			return true;
    	return false;

    }

    public UserAccount getUserAccount(String userName, String password)
    {
        for(int i = 0; i < userAccounts.size(); i++)
        {
            if (userAccounts.get(i).matchUserName(userName) && userAccounts.get(i).matchPassword(password))
            {
                return userAccounts.get(i);
            }
        }
        throw new NoSuchElementException("Account not found");
    }

    public UserAccount getUserAccount(String userName)
    {
        for(int i = 0; i < userAccounts.size(); i++)
        {
            if (userAccounts.get(i).matchUserName(userName))
            {
               return userAccounts.get(i);
            }
        }
        throw new NoSuchElementException("Account not found");
    }

    public UserAccount getUserAccountEmail (String email)
    {
        for(int i = 0; i < userAccounts.size(); i++)
        {
            if (userAccounts.get(i).matchEmail(email))
            {
                return userAccounts.get(i);
            }
        }
        throw new NoSuchElementException("Account not found");
    }

    public void removeUserAccount(UserAccount account)
    {
        userAccounts.remove(account);
    }

}
