package login;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.UserAccount;
import model.UserAccountManager;
import profile.Profile;
import signup.Signup;

import java.time.LocalDate;

import forgotUsername.ForgotUsername;
import forgotPassword.ForgotPassword;

/**
 * @author nabilrahman
 * @author brodydowns
 * @author marufahmed
 */
public class Login extends Application
{
    private final int SCENE_WIDTH = 800;
    private final int SCENE_HEIGHT = 600;
    private boolean lockedOut = false;
    private UserAccountManager accountManager;
    private Scene scene;
    private int failedLoginCount = 0;
    private UserAccount account;

    private TextField userTextField;
    private PasswordField passwordField;
    private Button signinButton;
    
    public Hyperlink forgotUsernameLink;
    public ForgotUsername forgotUsername;
    public Hyperlink forgotPasswordLink;
    public ForgotPassword forgotPassword;
    private Button registerButton;
    private Profile profile;

    public static void main(String[] args)
    {
        launch(args);
    }

    private void initUserAccounts()
    {
        accountManager = new UserAccountManager();
        accountManager.addUserAccount(new UserAccount("admin", "123456", "admin", "admin", LocalDate.of(2017, 1, 1), "admin@admin.com"));
        accountManager.addUserAccount(new UserAccount("nabilr", "ABcd1234", "Nabil", "Rahman", LocalDate.of(1970, 1, 1), "nabilr@outlook.com"));
    }

    @Override
    public void start(Stage primaryStage)
    {
        initUserAccounts();

        primaryStage.setTitle("Account Manager");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        Text sceneTitle = new Text("WELCOME");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        signinButton = new Button("Sign in");
        HBox hBoxSignInButton = new HBox(10);
        hBoxSignInButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxSignInButton.getChildren().add(signinButton);
        grid.add(hBoxSignInButton, 1, 4);

        registerButton = new Button("Register");
        HBox hBoxregisterButton = new HBox(10);
        hBoxregisterButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxregisterButton.getChildren().add(registerButton);

        grid.add(hBoxregisterButton, 0, 4);
        
        //forgot username link
        forgotUsernameLink = new Hyperlink();
        forgotUsernameLink.setText("Forgot Username?");
        HBox hBoxForgotUsernameBox = new HBox(10);
        hBoxForgotUsernameBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxForgotUsernameBox.getChildren().add(forgotUsernameLink);
        grid.add(hBoxForgotUsernameBox, 1, 6);

        //forgot password link
        forgotPasswordLink = new Hyperlink();
        forgotPasswordLink.setText("Forgot Password?");
        HBox hBoxForgotPasswordBox = new HBox(10);
        hBoxForgotPasswordBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxForgotPasswordBox.getChildren().add(forgotPasswordLink);
        grid.add(hBoxForgotPasswordBox, 1, 7);
        

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 8);

        //Sign in action
        signinButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                actionTarget.setFill(Color.FIREBRICK);
                if (lockedOut)
                {
                    actionTarget.setText("TOO MANY FAILED LOGINS\nPlease wait before trying again");
                }
                else if (accountManager.doesAccountExist(userTextField.getText(), passwordField.getText()))
                {
                    actionTarget.setFill(Color.GREEN);
                    actionTarget.setText("LOGIN SUCCEEDED");
                    account = accountManager.getUserAccount(userTextField.getText(), passwordField.getText());
                    profile = new Profile(primaryStage, scene, accountManager, account);
                    Scene profileScene = new Scene(profile.getGridPane(), SCENE_WIDTH, SCENE_HEIGHT);
                    primaryStage.setScene(profileScene);
                    primaryStage.show();
                }
                else
                {
                    actionTarget.setFill(Color.RED);
                    actionTarget.setText("LOGIN FAILED");
                    if (++failedLoginCount >= 3)
                    {
                        //reset failedLoginCount
                        failedLoginCount = 0;

                        //lock out
                        lockedOut = true;

                        //wait 60 seconds before unlocking
                        new Timeline(new KeyFrame(
                                Duration.millis(60000),
                                ae -> lockedOut = false))
                                .play();
                    }
                }
            }
        });

        //Register action
        registerButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Signup signup = new Signup(primaryStage, scene, accountManager);
                Scene signupScene = new Scene(signup.getGridPane(), SCENE_WIDTH, SCENE_HEIGHT);
                primaryStage.setScene(signupScene);
                primaryStage.show();
            }
        });
        
        //Forgot username action
        forgotUsernameLink.setOnAction(new EventHandler<ActionEvent>()
        {
        	@Override
        	public void handle(ActionEvent event)
        	{
        		//TODO
        		forgotUsername = new ForgotUsername(primaryStage, scene, accountManager);
        		Scene forgotUsernameScene = new Scene(forgotUsername.getGridPane(), SCENE_WIDTH, SCENE_HEIGHT);
        		primaryStage.setScene(forgotUsernameScene);
        		primaryStage.show();
        		
        	}
        });
        
        //Forgot password action
        forgotPasswordLink.setOnAction(new EventHandler<ActionEvent>()
        {
        	@Override
        	public void handle(ActionEvent event)
        	{
        	//TODO	
                ForgotPassword forgotPassword = new ForgotPassword(primaryStage, scene, accountManager);
                Scene forgotPasswordScene = new Scene(forgotPassword.getGridPane(), SCENE_WIDTH, SCENE_HEIGHT);
                primaryStage.setScene(forgotPasswordScene);
                primaryStage.show();
        	}
        });

        scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
        //skipLogin();
    }

    /*
    @Override
    public void stop() throws IOException
    {
        accountManager.saveJSON(new File("data/user_accounts.json"));
    }
    */

    private void skipLogin()
    {
        userTextField.setText("nabilr");
        passwordField.setText("ABcd1234");
        signinButton.fire();
    }
    
    public TextField getUserTextField()
    {
    	return userTextField;
    }
    
    public PasswordField getPasswordField()
    {
    	return passwordField;
    }
    
    public Button getSigninButton()
    {
    	return signinButton;
    }
    
    public Button getRegisterButton()
    {
    	return registerButton;
    }
    
    public Profile getProfile()
    {
    	return profile;
    
    }

}
