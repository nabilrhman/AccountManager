package model;

import java.time.LocalDate;

public class UserAccount {

    private String userName;
    private String password;
    private String firstName; // First name.
    private String lastName; // Last name.
    private LocalDate birthDate; // Date of birth.
    private String email; // Email.

    public UserAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserAccount(String userName, String password, String firstName,
                       String lastName, LocalDate dateOfBirth, String email) {

        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = dateOfBirth;
        this.email = email;
    }

    /**
     * User account for ForgotPassword
     *
     * @param userName - user name
     * @param dateOfBirth - Birthdate
     * @author Rogelio Gomez
     */
    public UserAccount(String userName, LocalDate dateOfBirth) {
        this.userName = userName;
        this.birthDate = dateOfBirth;
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
     * Sets user's last name.
     *
     * @param lastName - last name
     * @author Edgar Sosa
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets user's date of birth.
     *
     * @param birthDate
     * @author Edgar Sosa
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
     * Gets user's first name.
     *
     * @return User account's first name.
     * @author Edgar Sosa
     */
    public String getFirstName() {

        return firstName;
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
     * Gets the user account's date of birth.
     *
     * @return User account's date of birth.
     * @author Edgar Sosa
     */
    public LocalDate getBirthDate() {
        return birthDate;
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

    /**
     *
     * @param userName
     * @param birthDate
     * @return
     */
    public boolean isValidCredential(String userName, LocalDate birthDate) {
        return matchUserName(userName) && matchBirthDate(birthDate);
    }

    public boolean matchUserName(String userName) {
        return userName != null && userName.equals(this.userName);
    }

    private boolean matchPassword(String password) {
        return password != null && password.equals(this.password);
    }
    
    public boolean matchEmail(String email) {
        return email != null && email.equals(this.email);
    }

    public boolean matchBirthDate(LocalDate dateOfBirth) {
        return birthDate != null && birthDate.equals(this.birthDate);
    }

}