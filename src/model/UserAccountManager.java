package model;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserAccountManager {
	
    private ArrayList<UserAccount> userAccounts;
    
    public UserAccountManager() {
        userAccounts = new ArrayList<UserAccount>();
    }
    
    public void addUserAccount(String userName, String password){
    	if (!doesUserNameExist(userName))
    		userAccounts.add(new UserAccount(userName,password));
    }
    
    public boolean doesAccountExist(String userName, String password) {
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.isValidCredential(userName, password))    
    			return true;   
       return false;
    }
    
    public boolean doesUserNameExist(String userName){
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.matchUserName(userName))   
    			return true;   
       return false;
    }

    public void saveJSON(File file) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, userAccounts);
    }

    public void loadJSON(File file) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        userAccounts = mapper.readValue(file, userAccounts.getClass());
    }

}
