package signup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.UserAccount;
import model.UserAccountManager;
import validate.InputValidator;

/**
 * @author nabilrahman
 * @author marufahmed
 * @author rogelio
 */
public class Signup
{
    private UserAccount newAccount;
    UserAccountManager accountManager;
    private GridPane gridPaneSignup;
    private InputValidator validator;

    private TextField usernameTextField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField emailTextField;
    private DatePicker birthDatePicker;
    private Button signUpButton;

    private Text usernameValidationText;
    private Text passwordValidationText;
    private Text confirmPasswordValidationText;
    private Text firstNameValidationText;
    private Text lastNameValidationText;
    private Text emailValidationText;
    private Text birthDateValidationText;

    private Boolean isValidUsername = false;
    private Boolean isValidPassword = false;
    private Boolean isValidConfirmPassword = false;
    private Boolean isValidFirstName = false;
    private Boolean isValidLastName = false;
    private Boolean isValidEmail = false;
    private Boolean isValidBirthDate = false;

    public Signup(Stage currentStage, Scene loginScene, UserAccountManager accountManager)
    {
        validator = new InputValidator();
        this.accountManager = accountManager;

        gridPaneSignup = new GridPane();
        gridPaneSignup.setAlignment(Pos.CENTER);
        gridPaneSignup.setHgap(10);
        gridPaneSignup.setVgap(10);
        gridPaneSignup.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("SIGN UP");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPaneSignup.add(sceneTitle, 0, 0, 2, 1);

        Label username = new Label("User Name:");
        usernameTextField = new TextField();
        usernameValidationText = new Text(" ");
        usernameValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        usernameValidationText.setFill(Color.RED);
        gridPaneSignup.add(username, 0, 1);
        gridPaneSignup.add(usernameTextField, 1, 1);
        gridPaneSignup.add(usernameValidationText, 1, 2);

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        passwordValidationText = new Text(" ");
        passwordValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        passwordValidationText.setFill(Color.RED);
        gridPaneSignup.add(passwordLabel, 0, 3);
        gridPaneSignup.add(passwordField, 1, 3);
        gridPaneSignup.add(passwordValidationText, 1, 4);

        Label confirmPasswordLabel = new Label("Confirm password:");
        confirmPasswordField = new PasswordField();
        confirmPasswordValidationText = new Text(" ");
        confirmPasswordValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        confirmPasswordValidationText.setFill(Color.RED);
        gridPaneSignup.add(confirmPasswordLabel, 0, 5);
        gridPaneSignup.add(confirmPasswordField, 1, 5);
        gridPaneSignup.add(confirmPasswordValidationText, 1, 6);

        Label firstNameLabel = new Label("First Name:");
        firstNameTextField = new TextField();
        firstNameValidationText = new Text(" ");
        firstNameValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        firstNameValidationText.setFill(Color.RED);
        gridPaneSignup.add(firstNameLabel, 0, 7);
        gridPaneSignup.add(firstNameTextField, 1, 7);
        gridPaneSignup.add(firstNameValidationText, 1, 8);

        Label lastNameLabel = new Label("Last Name:");
        lastNameValidationText = new Text(" ");
        lastNameValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        lastNameValidationText.setFill(Color.RED);
        lastNameTextField = new TextField();
        gridPaneSignup.add(lastNameLabel, 0, 9);
        gridPaneSignup.add(lastNameTextField, 1, 9);
        gridPaneSignup.add(lastNameValidationText, 1, 10);

        Label emailLabel = new Label("Email:");
        emailValidationText = new Text(" ");
        emailValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        emailValidationText.setFill(Color.RED);
        emailTextField = new TextField();
        gridPaneSignup.add(emailLabel, 0, 11);
        gridPaneSignup.add(emailTextField, 1, 11);
        gridPaneSignup.add(emailValidationText, 1, 12);

        Label birthDateLabel = new Label("Birth Date:");
        birthDateValidationText = new Text(" ");
        birthDateValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        birthDateValidationText.setFill(Color.RED);
        birthDatePicker = new DatePicker();
        birthDatePicker.setPrefWidth(350);
        gridPaneSignup.add(birthDateLabel, 0, 13);
        gridPaneSignup.add(birthDatePicker, 1, 13);
        gridPaneSignup.add(birthDateValidationText, 1, 14);

        signUpButton = new Button("Sign Up");
        signUpButton.setDisable(true);
        HBox hBoxSignUpButton = new HBox(10);
        hBoxSignUpButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxSignUpButton.getChildren().add(signUpButton);
        gridPaneSignup.add(hBoxSignUpButton, 1, 15);

        //button to go back
        Button goBackButton = new Button("Go Back");
        gridPaneSignup.add(goBackButton, 0, 15);
        

        final Text actionTarget = new Text();
        gridPaneSignup.add(actionTarget, 1, 16);

        signUpButton.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent e)
            {
                if (isAllInputValid())
                {
                    newAccount = new UserAccount(usernameTextField.getText(), passwordField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), birthDatePicker.getValue(), emailTextField.getText());
                    accountManager.addUserAccount(newAccount);
                    goBackButton.fire();
                }


            }
        });


        //TO-DO: Add backButton functionalities
        goBackButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                currentStage.setScene(loginScene);
            }
        });
         


        addActionToInputControlsWithValidation(usernameTextField, usernameValidationText);
        addActionToInputControlsWithValidation(birthDatePicker, birthDateValidationText);
        addActionToInputControlsWithValidation(passwordField, passwordValidationText);
        addActionToInputControlsWithValidation(firstNameTextField, firstNameValidationText);
        addActionToInputControlsWithValidation(lastNameTextField, lastNameValidationText);
        addActionToInputControlsWithValidation(emailTextField, emailValidationText);
        addActionToInputControlsWithValidation(confirmPasswordField, confirmPasswordValidationText);

    }

    public GridPane getGridPane()
    {
        return gridPaneSignup;
    }

    public void addActionToInputControlsWithValidation(Control control, Text validationText)
    {

        if(validationText == passwordValidationText || validationText == confirmPasswordValidationText)
        {
            PasswordField controlTextField = (PasswordField) control;
            controlTextField.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable,
                                    String oldValue, String newValue)
                {
                    if(controlTextField == passwordField)
                    {
                        if (!passwordField.getText().trim().equalsIgnoreCase(""))
                        {
                            if (validator.validatePassword(passwordField.getText()))
                            {
                                validationText.setText("Valid");
                                validationText.setFill(Color.GREEN);
                                isValidPassword = true;
                            }
                            else
                            {
                                validationText.setText("Must contain 6 chars: 1 lowercase, 1 uppercase, and 1 number");
                                validationText.setFill(Color.RED);
                                isValidPassword = false;
                            }

                        }

                    }
                    else if(controlTextField == confirmPasswordField)
                    {
                        if (!passwordField.getText().trim().equalsIgnoreCase(""))
                        {
                            if (passwordField.getText().equals(confirmPasswordField.getText()) && validator.validatePassword(confirmPasswordField.getText()))
                            {
                                validationText.setText("Valid");
                                validationText.setFill(Color.GREEN);
                                isValidConfirmPassword = true;
                            }
                            else
                            {
                                validationText.setText("Passwords do not match");
                                validationText.setFill(Color.RED);
                                isValidConfirmPassword = false;
                            }
                        }

                    }
                    checkSignupButtonStatus();
                }
            });
        }
        else if(validationText == birthDateValidationText)
        {
            DatePicker birthDatePicker = (DatePicker) control;
            birthDatePicker.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    if (birthDatePicker.getValue() != null)
                    {
                        if (validator.validateBirthdate(birthDatePicker.getValue()))
                        {
                            validationText.setText("Valid");
                            validationText.setFill(Color.GREEN);
                            isValidBirthDate = true;
                        }
                        else
                        {
                            validationText.setText("Birthdate must be between 1900 and 2018");
                            validationText.setFill(Color.RED);
                            isValidBirthDate = false;
                        }
                    }
                    checkSignupButtonStatus();
                }
            });

        }
        else
        {
            TextField controlTextField = (TextField) control;
            controlTextField.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable,
                                    String oldValue, String newValue)
                {
                    if(controlTextField == usernameTextField)
                    {
                        // FIX-ME: Fix the validation

                        if (!usernameTextField.getText().trim().equalsIgnoreCase(""))
                        {
                            if (validator.validateUsername(usernameTextField.getText()))
                            {
                                if(!accountManager.doesUserNameExist(usernameTextField.getText()))
                                {
                                    validationText.setText("Valid");
                                    validationText.setFill(Color.GREEN);
                                    isValidUsername = true;
                                }
                                else
                                {
                                    validationText.setText("Username already exists.");
                                    validationText.setFill(Color.RED);
                                    isValidUsername = false;
                                }
                            }
                            else
                            {
                                validationText.setText("At least 3 chars: alphanumeric, numbers, underscores, and dashes");
                                validationText.setFill(Color.RED);
                                isValidUsername = false;
                            }
                        }
                    }
                    else if(controlTextField == emailTextField)
                    {
                        if (!emailTextField.getText().trim().equalsIgnoreCase(""))
                        {
                            if (validator.validateEmail(emailTextField.getText()))
                            {
                            	if(!accountManager.doesEmailExist(emailTextField.getText()))
                            	{
                            		validationText.setText("Valid");
                            		validationText.setFill(Color.GREEN);
                            		isValidEmail = true;
                            		
                            	}
                            	else
                            	{
                                    validationText.setText("Email is already in use.");
                                    validationText.setFill(Color.RED);
                                    isValidEmail = false;
                         
                            	}
                            }
                            else
                            {
                                validationText.setText("Invalid email format");
                                validationText.setFill(Color.RED);
                                isValidEmail = false;
                            }
                        }
                    }
                    else if(controlTextField == firstNameTextField)
                    {
                        if (!firstNameTextField.getText().trim().equalsIgnoreCase(""))
                        {
                            if (validator.validateName(firstNameTextField.getText()))
                            {
                                validationText.setText("Valid");
                                validationText.setFill(Color.GREEN);
                                isValidFirstName = true;
                            }
                            else
                            {
                                validationText.setText("No numbers");
                                validationText.setFill(Color.RED);
                                isValidFirstName = false;
                            }
                        }
                    }
                    else if(controlTextField == lastNameTextField)
                    {
                        if (!firstNameTextField.getText().trim().equalsIgnoreCase(""))
                        {
                            if (validator.validateName(lastNameTextField.getText()))
                            {
                                validationText.setText("Valid");
                                validationText.setFill(Color.GREEN);
                                isValidLastName = true;
                            }
                            else
                            {
                                validationText.setText("No numbers");
                                validationText.setFill(Color.RED);
                                isValidLastName = false;
                            }
                        }
                        checkSignupButtonStatus();
                    }
                    checkSignupButtonStatus();
                }
            });
        }

    }

    public boolean isAllInputValid()
    {
        return (isValidUsername && isValidPassword && isValidConfirmPassword && isValidFirstName && isValidLastName && isValidEmail && isValidBirthDate);
    }

    public void checkSignupButtonStatus()
    {
        if(isAllInputValid())
            signUpButton.setDisable(false);
        else
            signUpButton.setDisable(true);
    }

}
