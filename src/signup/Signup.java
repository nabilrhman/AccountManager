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
import model.UserAccountManager;
import validate.InputValidator;

/**
 * @author nabilrahman
 * @author marufahmed
 */
public class Signup
{

    private GridPane gridPaneSignup;
    private InputValidator validator;
    private boolean isAllInputsValid;

    private final TextField usernameTextField;
    private final PasswordField passwordField;
    private final PasswordField confirmPasswordField;
    private final TextField firstNameTextField;
    private final TextField lastNameTextField;
    private final TextField emailTextField;
    private final DatePicker birthDatePicker;

    Text usernameValidationText;
    Text passwordValidationText;
    Text confirmPasswordValidationText;
    Text firstNameValidationText;
    Text lastNameValidationText;
    Text emailValidationText;
    Text birthDateValidationText;


    public Signup(Stage currentStage, Scene loginScene, UserAccountManager accountManager)
    {
        isAllInputsValid = false;
        validator = new InputValidator();

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
        birthDatePicker.setPrefWidth(200);
        gridPaneSignup.add(birthDateLabel, 0, 13);
        gridPaneSignup.add(birthDatePicker, 1, 13);
        gridPaneSignup.add(birthDateValidationText, 1, 14);

        Button signUpButton = new Button("Sign Up");
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
                //TO-DO: Verify fields, add account to uam
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
                        //TO-DO: Add validation for passwordField
                    }
                    else if(controlTextField == confirmPasswordField)
                    {
                        //TO-DO: Add validation for confirmPasswordField
                    }
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
                        }
                        else
                        {
                            validationText.setText("Invalid: ");
                            validationText.setFill(Color.RED);
                        }
                    }
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
                                validationText.setText("Valid");
                                validationText.setFill(Color.GREEN);
                            }
                            else
                            {
                                validationText.setText("Invalid: ");
                                validationText.setFill(Color.RED);
                            }
                        }
                    }
                    else if(controlTextField == emailTextField)
                    {
                        //TO-DO: Add validation for emailTextField
                    }
                }
            });
        }

    }

}
