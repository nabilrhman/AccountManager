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
import signup.Signup;

public class Login extends Application {

    private UserAccountManager accountManager;
    private Scene scene;
    private final int SCENE_WIDTH = 800;
    private final int SCENE_HEIGHT = 600;
    
    private int failedLoginCount = 0;
    boolean lockedOut = false;

    private void initUserAccounts() {
        accountManager = new UserAccountManager();
        accountManager.addUserAccount("admin", "123456");
    }

    @Override
    public void start(Stage primaryStage) {
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
        HBox hbButton = new HBox(10);
        hbButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbButton.getChildren().add(signinButton);
        grid.add(hbButton, 1, 4);

        Button registerButton = new Button("Register");
        HBox hbregisterButton = new HBox(10);
        hbregisterButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbregisterButton.getChildren().add(registerButton);

        grid.add(hbregisterButton, 0, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        signinButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
                actionTarget.setFill(Color.FIREBRICK);
                if (lockedOut) 
                {
                	actionTarget.setText("Too Many Failed Logins\nPlease wait before trying again");
                }
                else if(accountManager.doesAccountExist(userTextField.getText(), passwordBox.getText()))
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

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Signup signup = new Signup(primaryStage, scene, accountManager);
                Scene signupscene = new Scene(signup.getGrid(), SCENE_WIDTH, SCENE_HEIGHT);
                primaryStage.setScene(signupscene);
                primaryStage.show();
            }
        });

        scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
