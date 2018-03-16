package forgotPassword;

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

public class ForgotPassword {
    private Stage mainStage;
    private Scene previousScene;
    private UserAccountManager accountManager;
    private UserAccount account;
    private InputValidator validator;
    private GridPane gridPaneForgotPassword;
    public Button resetPasswordButton;
    public Button goBackButton;

    public TextField usernameTextField;
    private Text usernameValidationText;
    private Text birthDateValidationText;
    public DatePicker birthDatePicker;
    public TextField newPasswordField;
    private Text newPasswordValidationText;

    public ForgotPassword(Stage mainStage, Scene loginScene, UserAccountManager accountManager)
    {
        this.mainStage = mainStage;
        previousScene = loginScene;
        this.accountManager = accountManager;

        gridPaneForgotPassword = new GridPane();
        gridPaneForgotPassword.setAlignment(Pos.CENTER);
        gridPaneForgotPassword.setHgap(10);
        gridPaneForgotPassword.setVgap(10);
        gridPaneForgotPassword.setPadding(new Insets(25, 25, 25, 25));
        addControllersToGridPane(gridPaneForgotPassword);
    }

    private void addControllersToGridPane(GridPane gridPane) {
        validator = new InputValidator();

        gridPaneForgotPassword.setAlignment(Pos.CENTER);
        gridPaneForgotPassword.setHgap(10);
        gridPaneForgotPassword.setVgap(10);
        gridPaneForgotPassword.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("FORGOT PASSWORD");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPaneForgotPassword.add(sceneTitle, 0, 0, 2, 1);

        Label username = new Label("Enter Username:");
        usernameTextField = new TextField();
        usernameValidationText = new Text(" ");
        usernameValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        usernameValidationText.setFill(Color.RED);
        gridPaneForgotPassword.add(username, 0, 1);
        gridPaneForgotPassword.add(usernameTextField, 1, 1);
        gridPaneForgotPassword.add(usernameValidationText, 1, 2);

        Label birthDateLabel = new Label("Enter Birth Date:");
        birthDateValidationText = new Text(" ");
        birthDateValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        birthDateValidationText.setFill(Color.RED);
        birthDatePicker = new DatePicker();
        birthDatePicker.setPrefWidth(350);
        gridPaneForgotPassword.add(birthDateLabel, 0, 3);
        gridPaneForgotPassword.add(birthDatePicker, 1, 3);
        gridPaneForgotPassword.add(birthDateValidationText, 1, 4);

        Label newPasswordLabel = new Label("Enter New Password:");
        newPasswordField = new PasswordField();
        newPasswordValidationText = new Text(" ");
        newPasswordValidationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        newPasswordValidationText.setFill(Color.RED);
        gridPaneForgotPassword.add(newPasswordLabel, 0, 5);
        gridPaneForgotPassword.add(newPasswordField, 1, 5);
        gridPaneForgotPassword.add(newPasswordValidationText, 1, 6);

        resetPasswordButton = new Button("Reset Password");
        resetPasswordButton.setDisable(true);
        HBox hBoxSignUpButton = new HBox(10);
        hBoxSignUpButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxSignUpButton.getChildren().add(resetPasswordButton);
        gridPaneForgotPassword.add(hBoxSignUpButton, 1, 7);

        goBackButton = new Button("Go Back");
        gridPaneForgotPassword.add(goBackButton, 0, 7);

        goBackButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                mainStage.setScene(previousScene);
            }
        });

        resetPasswordButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(accountManager.doesUserNameExist(usernameTextField.getText())){
                    if(accountManager.doesAccountExist(usernameTextField.getText(),birthDatePicker.getValue())){
                        account = accountManager.getUserAccount(usernameTextField.getText());
                        UserAccount oldAccount = accountManager.getUserAccount(account.getUserName(), account.getPassword());
                        oldAccount.setPassword(newPasswordField.getText());
                        goBackButton.fire();
                    }

                } else {
                    usernameValidationText.setText("Username does not exist");
                    usernameValidationText.setFill(Color.RED);
                }
            }
        });

        addActionToInputControlsWithValidation(usernameTextField, usernameValidationText);
        addActionToInputControlsWithValidation(newPasswordField, newPasswordValidationText);
    }

    public GridPane getGridPane() {
        return gridPaneForgotPassword;
    }

    public void addActionToInputControlsWithValidation(Control control, Text validationText)
    {

        if(validationText == newPasswordValidationText)
        {
            PasswordField controlTextField = (PasswordField) control;
            controlTextField.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable,
                                    String oldValue, String newValue)
                {
                    if(controlTextField == newPasswordField)
                    {
                        if (!newPasswordField.getText().trim().equalsIgnoreCase(""))
                        {
                            if (validator.validatePassword(newPasswordField.getText()))
                            {
                                validationText.setText("Valid");
                                validationText.setFill(Color.GREEN);
                                resetPasswordButton.setDisable(false);
                            }
                            else
                            {
                                validationText.setText("Must contain 6 chars: 1 lowercase, 1 uppercase, and 1 number");
                                validationText.setFill(Color.RED);
                                resetPasswordButton.setDisable(true);
                            }

                        }

                    }
                }
            });
        }


    }

}
