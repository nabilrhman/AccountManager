package test;

import javafx.stage.Stage;
import junit.framework.TestCase;
import login.Login;
import validate.InputValidator;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import java.time.LocalDate;

import org.junit.Test;


public class ForgotPasswordGUITests{

	private int TIME_ON_SCREEN = 1500;
	
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
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); 
    }
    
    @Test
    public void TestHyperLink() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                    	Login login = new Login();
                        login.start(new Stage());
                        login.forgotPasswordLink.fire();
                        

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); // Time to use the app, with out this, the thread
                            // will be killed before you can tell.
    }
}