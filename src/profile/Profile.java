package profile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.UserAccount;
import model.UserAccountManager;
import validate.InputValidator;

public class Profile
{
    private final int SCENE_WIDTH = 800;
    private final int SCENE_HEIGHT = 600;
    private UserAccount newAccount;
    private UserAccountManager accountManager;
    private GridPane gridPaneProfile;
    private InputValidator validator;
    private Scene currentScene;

    private Button signUpButton;

    public Profile(Stage currentStage, Scene previousScene, UserAccountManager accountManager, UserAccount account)
    {
        this.accountManager = accountManager;
        this.validator = new InputValidator();

        gridPaneProfile = new GridPane();
        gridPaneProfile.setAlignment(Pos.CENTER);
        gridPaneProfile.setHgap(10);
        gridPaneProfile.setVgap(10);
        gridPaneProfile.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("WELCOME, " + account.getFirstName().toUpperCase());
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPaneProfile.add(sceneTitle, 0, 0, 2, 1);

        Text profileInfo = new Text("Profile Information");
        profileInfo.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPaneProfile.add(profileInfo, 0, 1);

        Label firstNameLabel = new Label("First Name: " + account.getFirstName());
        gridPaneProfile.add(firstNameLabel, 0, 2);

        Label lastNameLabel = new Label("Last Name: " + account.getLastName());
        gridPaneProfile.add(lastNameLabel, 0, 3);

        Label emailLabel = new Label("Email: " + account.getEmail());
        gridPaneProfile.add(emailLabel, 0, 4);

        Label birthDateLabel = new Label("Birth Date: " + account.getBirthDate());
        birthDateLabel.setPrefWidth(350);
        gridPaneProfile.add(birthDateLabel, 0, 5);

        Button editProfileButton = new Button("Edit");
        gridPaneProfile.add(editProfileButton, 1, 6);

        Button changePassword = new Button("Change password");
        gridPaneProfile.add(changePassword, 2, 6);

        //button to go back
        Button goBackButton = new Button("Go Back");
        gridPaneProfile.add(goBackButton, 0, 15);

        final Text actionTarget = new Text();
        gridPaneProfile.add(actionTarget, 1, 16);

        //TO-DO: Add backButton functionalities
        goBackButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                currentStage.setScene(previousScene);
            }
        });

        editProfileButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                EditProfile editProfile = new EditProfile(currentStage, currentStage.getScene(), accountManager, account);
            }
        });
    }

    public GridPane getGridPane()
    {
        return gridPaneProfile;
    }



}