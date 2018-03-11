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
import model.UserAccountManager;
import signup.Signup;

import java.io.File;
import java.io.IOException;

/**
 * @author nabilrahman
 * @author marufahmed
 */
public class Login extends Application
{
    private final int SCENE_WIDTH = 800;
    private final int SCENE_HEIGHT = 600;
    boolean lockedOut = false;
    private UserAccountManager accountManager;
    private Scene scene;
    private int failedLoginCount = 0;

    public static void main(String[] args)
    {
        launch(args);
    }

    private void initUserAccounts()
    {
        accountManager = new UserAccountManager();
        accountManager.addUserAccount("admin", "123456");
    }

    @Override
    public void start(Stage primaryStage)
    {
        initUserAccounts();

        primaryStage.setTitle("Login");
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

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        final PasswordField passwordBox = new PasswordField();
        grid.add(passwordBox, 1, 2);

        Button signinButton = new Button("Sign in");
        HBox hBoxSignInButton = new HBox(10);
        hBoxSignInButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxSignInButton.getChildren().add(signinButton);
        grid.add(hBoxSignInButton, 1, 4);

        Button registerButton = new Button("Register");
        HBox hBoxregisterButton = new HBox(10);
        hBoxregisterButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxregisterButton.getChildren().add(registerButton);

        grid.add(hBoxregisterButton, 0, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        signinButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                actionTarget.setFill(Color.FIREBRICK);
                if (lockedOut)
                {
                    actionTarget.setText("Too Many Failed Logins\nPlease wait before trying again");
                }
                else if (accountManager.doesAccountExist(userTextField.getText(), passwordBox.getText()))
                {
                    actionTarget.setText("Login Succeeded!");
                }
                else
                {
                    actionTarget.setText("Login Failed!");
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

        scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws IOException
    {
        accountManager.saveJSON(new File("data/user_accounts.json"));
    }

}
