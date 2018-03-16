package test;

import javafx.stage.Stage;
import login.Login;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import java.time.LocalDate;

import org.junit.Test;


public class ForgotPasswordTest{

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
                        login.forgotPasswordLink.fire();
                        login.forgotPassword.goBackButton.fire();
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }


    @Test
    public void TestUserName() throws InterruptedException {
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
                        login.forgotPassword.usernameTextField.setText("admin");
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

                        login.forgotPasswordLink.fire();

                        login.forgotPassword.usernameTextField.setText("admin");
                        login.forgotPassword.birthDatePicker.setValue(LocalDate.of(2000,1,1));
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN);
    }

    @Test
    public void TestPassword() throws InterruptedException {
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
                        login.forgotPassword.usernameTextField.setText("admin");
                        login.forgotPassword.birthDatePicker.setValue(LocalDate.of(2000,1,1));
                        login.forgotPassword.newPasswordField.setText("Password1");
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(TIME_ON_SCREEN);

    }

}