package profile;

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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.UserAccount;
import model.UserAccountManager;
import validate.InputValidator;

public class EditProfile
{
    private final int SCENE_WIDTH = 800;
    private final int SCENE_HEIGHT = 600;

    private Stage mainStage;
    private Scene previousScene;
    private Scene currentScene;
    private UserAccountManager accountManager;
    private UserAccount account;
    private InputValidator validator;
    private GridPane gridPaneEditProfile;

    private TextField usernameTextField;
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField emailTextField;
    private DatePicker birthDatePicker;
    private Button updateButton;

    private Text usernameValidationText;
    private Text firstNameValidationText;
    private Text lastNameValidationText;
    private Text emailValidationText;
    private Text birthDateValidationText;

    private Boolean isValidUsername = true;
    private Boolean isValidFirstName = true;
    private Boolean isValidLastName = true;
    private Boolean isValidEmail = true;
    private Boolean isValidBirthDate = true;

    public EditProfile(Stage mainStage, Scene previousScene, UserAccountManager accountManager, UserAccount account)
    {
        this.mainStage = mainStage;
        this.previousScene = previousScene;
        this.accountManager = accountManager;
        this.account = account;

        gridPaneEditProfile = new GridPane();
        gridPaneEditProfile.setAlignment(Pos.CENTER);
        gridPaneEditProfile.setHgap(10);
        gridPaneEditProfile.setVgap(10);
        gridPaneEditProfile.setPadding(new Insets(25, 25, 25, 25));
        addControllersToGridPane(gridPaneEditProfile);
        currentScene = new Scene(gridPaneEditProfile, SCENE_WIDTH, SCENE_HEIGHT);
        mainStage.setScene(currentScene);
    }

    private void addControllersToGridPane(GridPane gridPane)
    {
        validator = new InputValidator();

        Text sceneTitle = new Text("EDIT PROFILE");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);

        Label username = new Label("User Name:");
        usernameTextField = new TextField(account.getUserName());
        usernameValidationText = new Text(" ");
        usernameValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        usernameValidationText.setFill(Color.RED);
        gridPane.add(username, 0, 1);
        gridPane.add(usernameTextField, 1, 1);
        gridPane.add(usernameValidationText, 1, 2);

        Label firstNameLabel = new Label("First Name:");
        firstNameTextField = new TextField(account.getFirstName());
        firstNameValidationText = new Text(" ");
        firstNameValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        firstNameValidationText.setFill(Color.RED);
        gridPane.add(firstNameLabel, 0, 3);
        gridPane.add(firstNameTextField, 1, 3);
        gridPane.add(firstNameValidationText, 1, 4);

        Label lastNameLabel = new Label("Last Name:");
        lastNameValidationText = new Text(" ");
        lastNameValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        lastNameValidationText.setFill(Color.RED);
        lastNameTextField = new TextField(account.getLastName());
        gridPane.add(lastNameLabel, 0, 5);
        gridPane.add(lastNameTextField, 1, 5);
        gridPane.add(lastNameValidationText, 1, 6);

        Label emailLabel = new Label("Email:");
        emailValidationText = new Text(" ");
        emailValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        emailValidationText.setFill(Color.RED);
        emailTextField = new TextField(account.getEmail());
        gridPane.add(emailLabel, 0, 7);
        gridPane.add(emailTextField, 1, 7);
        gridPane.add(emailValidationText, 1, 8);

        Label birthDateLabel = new Label("Birth Date:");
        birthDateValidationText = new Text(" ");
        birthDateValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        birthDateValidationText.setFill(Color.RED);
        birthDatePicker = new DatePicker(account.getBirthDate());
        birthDatePicker.setPrefWidth(350);
        gridPane.add(birthDateLabel, 0, 9);
        gridPane.add(birthDatePicker, 1, 9);
        gridPane.add(birthDateValidationText, 1, 10);

        updateButton = new Button("Update");
        updateButton.setDisable(true);
        HBox hBoxSignUpButton = new HBox(10);
        hBoxSignUpButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxSignUpButton.getChildren().add(updateButton);
        gridPane.add(hBoxSignUpButton, 1, 15);

        //button to go back
        Button goBackButton = new Button("Go Back");
        gridPane.add(goBackButton, 0, 15);

        final Text actionTarget = new Text();
        //actionTarget.setTextAlignment(TextAlignment.CENTER);
        HBox hBoxActionTargetText = new HBox(10);
        hBoxActionTargetText.setAlignment(Pos.BASELINE_CENTER);
        hBoxActionTargetText.getChildren().add(actionTarget);
        gridPane.add(hBoxActionTargetText, 1, 16);

        updateButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if (isAllInputValid())
                {
                    UserAccount oldAccount = accountManager.getUserAccount(account.getUserName(), account.getPassword());
                    oldAccount.setUserName(usernameTextField.getText());
                    oldAccount.setFirstName(firstNameTextField.getText());
                    oldAccount.setLastName(lastNameTextField.getText());
                    oldAccount.setEmail(emailTextField.getText());
                    oldAccount.setBirthDate(birthDatePicker.getValue());
                    actionTarget.setText("Updated");
                    actionTarget.setFill(Color.GREEN);
                    resetValidationText();
                    updateButton.setDisable(true);
                }
            }
        });

        //TO-DO: Add backButton functionalities
        goBackButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                mainStage.setScene(previousScene);
            }
        });

        addActionToInputControlsWithValidation(usernameTextField, usernameValidationText);
        addActionToInputControlsWithValidation(birthDatePicker, birthDateValidationText);
        addActionToInputControlsWithValidation(firstNameTextField, firstNameValidationText);
        addActionToInputControlsWithValidation(lastNameTextField, lastNameValidationText);
        addActionToInputControlsWithValidation(emailTextField, emailValidationText);
    }

    private boolean isAllInputValid()
    {
        return (isValidUsername && isValidFirstName && isValidLastName && isValidEmail && isValidBirthDate);
    }

    private void checkSignupButtonStatus()
    {
        if(isValueChanged())
        {
            if (isAllInputValid())
                updateButton.setDisable(false);
            else
                updateButton.setDisable(true);
        }
        else
            updateButton.setDisable(true);
    }

    private boolean isValueChanged()
    {
        return !(usernameTextField.getText().trim().equalsIgnoreCase(account.getUserName()) && emailTextField.getText().trim().equalsIgnoreCase(account.getEmail()) &&
                firstNameTextField.getText().trim().equalsIgnoreCase(account.getFirstName()) && lastNameTextField.getText().trim().equalsIgnoreCase(account.getLastName())
                && birthDatePicker.getValue().equals(account.getBirthDate()));
    }

    private void resetValidationText()
    {
        usernameValidationText.setText(" ");
        firstNameValidationText.setText(" ");
        lastNameValidationText.setText(" ");
        emailValidationText.setText(" ");
        birthDateValidationText.setText(" ");
    }


    public void addActionToInputControlsWithValidation(Control control, Text validationText)
    {
        if (validationText == birthDateValidationText)
        {
            DatePicker birthDatePicker = (DatePicker) control;
            birthDatePicker.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    if (!birthDatePicker.getValue().equals(account.getBirthDate()))
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
                    else
                    {
                        validationText.setText(" ");
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
                    if (controlTextField == usernameTextField)
                    {
                        if (!usernameTextField.getText().trim().equalsIgnoreCase(account.getUserName()))
                        {
                            if (validator.validateUsername(usernameTextField.getText()))
                            {
                                if (!accountManager.doesUserNameExist(usernameTextField.getText()))
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
                        else
                        {
                            validationText.setText(" ");
                        }
                    }
                    else if (controlTextField == emailTextField)
                    {
                        if (!emailTextField.getText().trim().equalsIgnoreCase(account.getEmail()))
                        {
                            if (validator.validateEmail(emailTextField.getText()))
                            {
                                if (!accountManager.doesEmailExist(emailTextField.getText()))
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
                        else
                        {
                            validationText.setText(" ");
                        }
                    }
                    else if (controlTextField == firstNameTextField)
                    {

                        if (!firstNameTextField.getText().trim().equalsIgnoreCase(account.getFirstName()))
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
                        else
                        {
                            validationText.setText(" ");
                        }
                    }
                    else if (controlTextField == lastNameTextField)
                    {
                        if (!lastNameTextField.getText().trim().equalsIgnoreCase(account.getLastName()))
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
                        else
                        {
                            validationText.setText(" ");
                        }
                    }
                    checkSignupButtonStatus();
                }
            });
        }

    }


}