 package test;

import javafx.stage.Stage;
import junit.framework.TestCase;
import login.Login;
import validate.InputValidator;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.time.LocalDate;

import org.junit.Test;


public class ProfileGUITests{

	private int TIME_ON_SCREEN = 3000;
    
    @Test
    public void TestStartUp() throws InterruptedException{
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Login login = new Login();
                        login.start(new Stage()); 
                        login.getUserTextField().setText("admin");
                        login.getPasswordField().setText("123456");
                        login.getSigninButton().fire();
                        
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); 
    }
    
    @Test
    public void TestEditButton() throws InterruptedException{
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Login login = new Login();
                        login.start(new Stage()); 
                        login.getUserTextField().setText("admin");
                        login.getPasswordField().setText("123456");
                        login.getSigninButton().fire();
                        login.getProfile().getEditButton().fire();
                        
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); 
    }
    
    

    
}