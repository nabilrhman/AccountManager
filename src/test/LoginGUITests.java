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


public class LoginGUITests{

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
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); 
    }
    
    @Test
    public void TestRegisterButton() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                    	Login login = new Login();
                        login.start(new Stage());
                        login.getRegisterButton().fire();
                        

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); // Time to use the app, with out this, the thread
                            // will be killed before you can tell.
    }
    
    @Test
    public void TestLoginButton() throws InterruptedException{
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Login login = new Login();
                        login.start(new Stage()); 
                        
                        login.getSigninButton().fire();
                        
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); 
    }
    
    @Test
    public void TestLogin() throws InterruptedException{
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
    public void TestFailedLogins() throws InterruptedException{
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Login login = new Login();
                        login.start(new Stage()); 
                        login.getUserTextField().setText("badusername");
                        login.getPasswordField().setText("123456");
                        login.getSigninButton().fire();
                        login.getSigninButton().fire();
                        login.getSigninButton().fire();
                        login.getSigninButton().fire();
                        
                        
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); 
    }
    
}