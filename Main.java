import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user";
    private static final String LOG_FILE = "user_history.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();
        TimerTask inactivityWarningTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Warning: You have been inactive for 30 seconds.");
            }
        };
        TimerTask autoLogoutTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("You have been logged out due to inactivity.");
                System.exit(0);
            }
        };

        System.out.println("Hint: Username and password are both 'user'.");

        timer.schedule(inactivityWarningTask, 30000);
        timer.schedule(autoLogoutTask, 60000);

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String inputUsername = scanner.nextLine();
            timer = resetTimer(timer);

            System.out.print("Enter password: ");
            String inputPassword = scanner.nextLine();
            timer = resetTimer(timer);

            if (USERNAME.equals(inputUsername) && PASSWORD.equals(inputPassword)) {
                System.out.println("Login successful!");
                logUserActivity();
                loggedIn = true;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        scanner.close();
    }

    private static Timer resetTimer(Timer timer) {
        TimerTask inactivityWarningTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Warning: You have been inactive for 30 seconds.");
            }
        };
        TimerTask autoLogoutTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("You have been logged out due to inactivity.");
                System.exit(0);
            }
        };
        timer.cancel();
        timer.purge();
        Timer newTimer = new Timer();
        newTimer.schedule(inactivityWarningTask, 30000);
        newTimer.schedule(autoLogoutTask, 60000);
        return newTimer;
    }

    private static void logUserActivity() {
        // Placeholder for user activity logging logic
    }
}