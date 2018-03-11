package signup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import login.UserAccountManager;

public class Signup {

    private GridPane gridSignup;
    public Signup()
    {

        gridSignup = new GridPane();
        gridSignup.setAlignment(Pos.CENTER);
        gridSignup.setHgap(10);
        gridSignup.setVgap(10);
        gridSignup.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("SIGN UP");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridSignup.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        gridSignup.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        gridSignup.add(userTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridSignup.add(passwordLabel, 0, 2);

        final PasswordField passwordBox = new PasswordField();
        gridSignup.add(passwordBox, 1, 2);

        Label FirstNameLabel = new Label( "First Name:");
        gridSignup.add(FirstNameLabel, 0, 3);

        final TextField FirstNameTextField = new TextField();
        gridSignup.add(FirstNameTextField, 1, 3);

        Label LastNameLabel = new Label( "Last Name:");
        gridSignup.add(LastNameLabel, 0, 4);

        final TextField LastNameTextField = new TextField();
        gridSignup.add(LastNameTextField, 1, 4);

        Label EmailLabel = new Label( "Email:");
        gridSignup.add(EmailLabel, 0, 5);

        final TextField EmailTextField = new TextField();
        gridSignup.add(EmailTextField, 1, 5);

        Label DateLabel = new Label( "Birth Date:");
        gridSignup.add(DateLabel, 0, 6);

        //final TextField DateTextField = new TextField();
        //gridSignup.add(DateTextField, 1, 6);

        final DatePicker birthDatePicker = new DatePicker();
        birthDatePicker.setPrefWidth(200);
        gridSignup.add(birthDatePicker, 1, 6);

        Button signinButton = new Button("Sign Up");
        HBox hbButton = new HBox(10);
        hbButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbButton.getChildren().add(signinButton);
        gridSignup.add(hbButton, 1, 8);

        final Text actionTarget = new Text();
        gridSignup.add(actionTarget, 1, 9);

        signinButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

            }
        });

    }

    public GridPane getGrid()
    {
        return gridSignup;
    }

}
