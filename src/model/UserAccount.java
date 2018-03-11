package model;

import java.util.Date;

public class UserAccount {

    private String userName;
    private String password;
    private String firstName; // First name.
    private String lastName; // Last name.
    private String email; // Email.
    private Date birthDate; // Date of birth.

    public UserAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Secondary constructor which allows the use of a user name, password,
     * first name, last name, birth date, and email.
     *
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param email
     * @author Edgar Sosa
     */
    public UserAccount(String userName, String password, String firstName,
                       String lastName, Date birthDate, String email) {

        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    /**
     * Sets user's first name.
     *
     * @param firstName - first name
     * @author Edgar Sosa
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets user's first name.
     *
     * @return User account's first name.
     * @author Edgar Sosa
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * Sets user's last name.
     *
     * @param lastName - last name
     * @author Edgar Sosa
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's last name.
     *
     * @return User account's last name.
     * @author Edgar Sosa
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets user's date of birth.
     *
     * @param dateOfBirth
     * @author Edgar Sosa
     */
    public void setBirthDate(Date dateOfBirth) {
        this.birthDate = dateOfBirth;
    }

    /**
     * Gets the user account's date of birth.
     *
     * @return User account's date of birth.
     * @author Edgar Sosa
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the email of the user.
     *
     * @param email
     * @author Edgar Sosa
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's email.
     *
     * @return User account's email.
     * @author Edgar Sosa
     */
    public String getEmail() {
        return email;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidCredential(String userName, String password) {
        return matchUserName(userName) && matchPassword(password);
    }

    public boolean matchUserName(String userName) {
        return userName != null && userName.equals(this.userName);
    }

    private boolean matchPassword(String password) {
        return password != null && password.equals(this.password);
    }

}