package forgotUsername;

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

public class ForgotUsername {
	
	private UserAccountManager accountManager;
	private GridPane gridPaneForgotUsername;
	
	public ForgotUsername(Stage currentStage, Scene loginScene, UserAccountManager accountManager) {
		this.accountManager=accountManager;
	
        gridPaneForgotUsername = new GridPane();
        gridPaneForgotUsername.setAlignment(Pos.CENTER);
        gridPaneForgotUsername.setHgap(10);
        gridPaneForgotUsername.setVgap(10);
        gridPaneForgotUsername.setPadding(new Insets(25, 25, 25, 25));
        
        Text sceneTitle = new Text("SIGN UP");
        gridPaneForgotUsername.add(sceneTitle, 0, 0, 2, 1);
        
		
	}
	
	public GridPane getGridPane() {
		return gridPaneForgotUsername;
	}
	
}