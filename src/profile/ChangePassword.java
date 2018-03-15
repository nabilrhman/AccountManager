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
import javafx.stage.Stage;
import model.UserAccount;
import model.UserAccountManager;
import validate.InputValidator;

public class ChangePassword
{
    private final int SCENE_WIDTH = 800;
    private final int SCENE_HEIGHT = 600;

    private Stage mainStage;
    private Scene previousScene;
    private Scene currentScene;
    private UserAccountManager accountManager;
    private UserAccount account;
    private InputValidator validator;
    private GridPane gridPaneChangePassword;

    private PasswordField currentPasswordField;
    private PasswordField newPasswordField;
    private PasswordField confirmNewPasswordField;

    private Text newPasswordValidationText;
    private Text confirmNewPasswordValidationText;
    private Text currentPasswordValidationText;

    private Button updateButton;

    private Boolean isValidCurrentPassword = false;
    private Boolean isValidNewPassword = false;
    private Boolean isValidConfirmNewPassword = false;

    public ChangePassword(Stage mainStage, Scene previousScene, UserAccountManager accountManager, UserAccount account)
    {
        this.mainStage = mainStage;
        this.previousScene = previousScene;
        this.accountManager = accountManager;
        this.account = account;

        gridPaneChangePassword = new GridPane();
        gridPaneChangePassword.setAlignment(Pos.CENTER);
        gridPaneChangePassword.setHgap(10);
        gridPaneChangePassword.setVgap(10);
        gridPaneChangePassword.setPadding(new Insets(25, 25, 25, 25));
        addControllersToGridPane(gridPaneChangePassword);

        currentScene = new Scene(gridPaneChangePassword, SCENE_WIDTH, SCENE_HEIGHT);
        mainStage.setScene(currentScene);
    }

    private void addControllersToGridPane(GridPane gridPane)
    {
        validator = new InputValidator();

        Text sceneTitle = new Text("CHANGE PASSWORD");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);

        Label currentPasswordLabel = new Label("Current password:");
        currentPasswordField = new PasswordField();
        currentPasswordValidationText = new Text(" ");
        currentPasswordValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        currentPasswordValidationText.setFill(Color.RED);
        gridPaneChangePassword.add(currentPasswordLabel, 0, 3);
        gridPaneChangePassword.add(currentPasswordField, 1, 3);
        gridPaneChangePassword.add(currentPasswordValidationText, 1, 4);

        Label newPasswordLabel = new Label("New password:");
        newPasswordField = new PasswordField();
        newPasswordValidationText = new Text(" ");
        newPasswordValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        newPasswordValidationText.setFill(Color.RED);
        gridPaneChangePassword.add(newPasswordLabel, 0, 5);
        gridPaneChangePassword.add(newPasswordField, 1, 5);
        gridPaneChangePassword.add(newPasswordValidationText, 1, 6);

        Label confirmNewPasswordLabel = new Label("Confirm new password:");
        confirmNewPasswordField = new PasswordField();
        confirmNewPasswordValidationText = new Text(" ");
        confirmNewPasswordValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        confirmNewPasswordValidationText.setFill(Color.RED);
        confirmNewPasswordField.setPrefWidth(300);
        gridPaneChangePassword.add(confirmNewPasswordLabel, 0, 7);
        gridPaneChangePassword.add(confirmNewPasswordField, 1, 7);
        gridPaneChangePassword.add(confirmNewPasswordValidationText, 1, 8);

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
                    if(isValidCurrentPassword)
                    {
                        UserAccount oldAccount = accountManager.getUserAccount(account.getUserName(), account.getPassword());
                        oldAccount.setPassword(newPasswordField.getText());
                        actionTarget.setText("Updated");
                        actionTarget.setFill(Color.GREEN);
                        resetValidationText();
                        updateButton.setDisable(true);
                    }
                    else
                    {
                        currentPasswordValidationText.setText("Incorrect");
                        currentPasswordValidationText.setFill(Color.RED);
                    }
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

        addActionToInputControlsWithValidation(currentPasswordField, currentPasswordValidationText);
        addActionToInputControlsWithValidation(newPasswordField, newPasswordValidationText);
        addActionToInputControlsWithValidation(confirmNewPasswordField, confirmNewPasswordValidationText);
    }

    private boolean isAllInputValid()
    {
        return (isValidNewPassword && isValidConfirmNewPassword);
    }

    private void fixUpdateButtonStatus()
    {
        if (isAllInputValid())
            updateButton.setDisable(false);
        else
            updateButton.setDisable(true);
    }

    private void resetValidationText()
    {
        currentPasswordValidationText.setText(" ");
        newPasswordValidationText.setText(" ");
        confirmNewPasswordValidationText.setText(" ");
    }

    public void addActionToInputControlsWithValidation(Control control, Text validationText)
    {

        PasswordField controlTextField = (PasswordField) control;
        controlTextField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue)
            {
                if (controlTextField == currentPasswordField)
                {
                    if (!currentPasswordField.getText().trim().equalsIgnoreCase(""))
                    {
                        if (currentPasswordField.getText().equals(account.getPassword()))
                        {
                            isValidCurrentPassword = true;
                        }
                        else
                        {
                            isValidCurrentPassword = false;
                        }
                    }
                }
                else if (controlTextField == newPasswordField)
                {
                    if (!newPasswordField.getText().trim().equalsIgnoreCase(""))
                    {
                        if (validator.validatePassword(newPasswordField.getText()))
                        {
                            validationText.setText("Valid");
                            validationText.setFill(Color.GREEN);
                            isValidNewPassword = true;
                        }
                        else
                        {
                            validationText.setText("Must contain 3 chars: 1 lowercase, 1 uppercase, and 1 number");
                            validationText.setFill(Color.RED);
                            isValidNewPassword = false;
                        }
                    }
                }
                else if (controlTextField == confirmNewPasswordField)
                {
                    if (!confirmNewPasswordField.getText().trim().equalsIgnoreCase(""))
                    {
                        if (confirmNewPasswordField.getText().equals(newPasswordField.getText()))
                        {
                            if(validator.validatePassword(confirmNewPasswordField.getText()))
                            {
                                validationText.setText("Valid");
                                validationText.setFill(Color.GREEN);
                                isValidConfirmNewPassword = true;
                            }
                            else
                            {
                                validationText.setText("Must contain 3 chars: 1 lowercase, 1 uppercase, and 1 number");
                                validationText.setFill(Color.RED);
                                isValidNewPassword = false;
                            }
                        }
                        else
                        {
                            validationText.setText("Passwords do not match");
                            validationText.setFill(Color.RED);
                            isValidConfirmNewPassword = false;
                        }
                    }
                }
                fixUpdateButtonStatus();
            }
        });

    }


}
