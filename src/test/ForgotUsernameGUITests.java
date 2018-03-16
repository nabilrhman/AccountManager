package test;

import javafx.stage.Stage;
import junit.framework.TestCase;
import login.Login;
import validate.InputValidator;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import java.time.LocalDate;

import org.testng.annotations.Test;


public class ForgotUsernameGUITests{

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
                        login.forgotUsernameLink.fire();
                        

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); // Time to use the app, with out this, the thread
                            // will be killed before you can tell.
    }
    
    @Test
    public void TestBackButton() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                    	Login login = new Login();
                        login.start(new Stage());
                        login.forgotUsernameLink.fire();
                        login.forgotUsername.goBackButton.fire();

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); // Time to use the app, with out this, the thread
                            // will be killed before you can tell.
    }

    
    @Test
    public void TestFirstName() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Login login = new Login();
                        login.start(new Stage()); 
                        
                        login.forgotUsernameLink.fire();
                        login.forgotUsername.firstNameTextField.setText("admin");
                        

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN);
    }
    
    @Test
    public void TestLastName() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Login login = new Login();
                        login.start(new Stage()); 
                        
                        login.forgotUsernameLink.fire();
                     
                        login.forgotUsername.firstNameTextField.setText("admin");
                        login.forgotUsername.lastNameTextField.setText("admin");
                        

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN);  
    }
    
    @Test
    public void TestEmail() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Login login = new Login();
                        login.start(new Stage()); 
                        
                        login.forgotUsernameLink.fire();
                        login.forgotUsername.firstNameTextField.setText("admin");
                        login.forgotUsername.lastNameTextField.setText("admin");
                        login.forgotUsername.emailTextField.setText("admin@admin.com");


                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN);  
        
    }
    
    @Test
    public void TestBirthDate() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Login login = new Login();
                        login.start(new Stage()); 
                        
                        login.forgotUsernameLink.fire();
                        login.forgotUsername.firstNameTextField.setText("admin");
                        login.forgotUsername.lastNameTextField.setText("admin");
                        login.forgotUsername.emailTextField.setText("admin@admin.com");
                        login.forgotUsername.birthDatePicker.setValue(LocalDate.of(1970, 1, 1));

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN);  
        
    }
}