import controller.BookingController;
import model.Admin;
import model.FitnessClass;
import model.User;
import view.ConsoleView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // View setup
        ConsoleView view = new ConsoleView();

        // Controller setup
        BookingController controller = new BookingController(view);

        Scanner scanner = new Scanner(System.in);

        // Admin registration
        System.out.println("Enter admin name: ");
        String adminName = scanner.nextLine();
        System.out.println("Enter admin email: ");
        String adminEmail = scanner.nextLine();
        Admin admin = new Admin(adminName, adminEmail);

        // Menu for options
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Create a class (Admin)");
            System.out.println("2. Register a new user");
            System.out.println("3. Book a class");
            System.out.println("4. Cancel a booking");
            System.out.println("5. Admin: Cancel a class");
            System.out.println("6. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: // Admin creates a class
                    System.out.println("Enter class name: ");
                    String className = scanner.nextLine();
                    System.out.println("Enter class type (e.g., Yoga, Gym): ");
                    String classType = scanner.nextLine();
                    System.out.println("Enter class capacity: ");
                    int capacity = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter class date and time (e.g., 2024-10-21 08:00 AM): ");
                    String classDate = scanner.nextLine();

                    controller.createClass(admin, className, classType, capacity, classDate);
                    System.out.println("Class created successfully!");
                    break;

                case 2: // Register a new user
                    System.out.println("Enter user name: ");
                    String userName = scanner.nextLine();
                    System.out.println("Enter user email: ");
                    String userEmail = scanner.nextLine();
                    System.out.println("Enter user tier (Gold/Silver/Platinum): ");
                    String userTier = scanner.nextLine();

                    User user = new User(userName, userEmail, userTier);
                    System.out.println("User registered successfully!");
                    break;

                case 3: // Book a class
                    System.out.println("Enter user email: ");
                    String bookingUserEmail = scanner.nextLine();
                    User bookingUser = findUser(bookingUserEmail, controller);
                    if (bookingUser == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    System.out.println("Enter class name to book: ");
                    String bookingClassName = scanner.nextLine();
                    FitnessClass bookingClass = findClass(bookingClassName, controller);
                    if (bookingClass == null) {
                        System.out.println("Class not found.");
                        break;
                    }

                    controller.bookClass(bookingUser, bookingClass);
                    System.out.println("Class booked successfully!");
                    break;

                case 4: // Cancel a booking
                    System.out.println("Enter user email: ");
                    String cancelUserEmail = scanner.nextLine();
                    User cancelUser = findUser(cancelUserEmail, controller);
                    if (cancelUser == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    System.out.println("Enter class name to cancel booking: ");
                    String cancelClassName = scanner.nextLine();
                    FitnessClass cancelClass = findClass(cancelClassName, controller);
                    if (cancelClass == null) {
                        System.out.println("Class not found.");
                        break;
                    }

                    controller.cancelBooking(cancelUser, cancelClass);
                    System.out.println("Booking canceled successfully!");
                    break;

                case 5: // Admin cancels a class
                    System.out.println("Enter class name to cancel: ");
                    String cancelAdminClassName = scanner.nextLine();
                    FitnessClass cancelAdminClass = findClass(cancelAdminClassName, controller);
                    if (cancelAdminClass == null) {
                        System.out.println("Class not found.");
                        break;
                    }

                    controller.cancelClass(admin, cancelAdminClass);
                    System.out.println("Class canceled successfully!");
                    break;

                case 6: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Helper method to find a user based on email
    private static User findUser(String email, BookingController controller) {
        for (User user : controller.getAllUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    // Helper method to find a class based on name
    private static FitnessClass findClass(String className, BookingController controller) {
        for (FitnessClass fitnessClass : controller.getAllClasses()) {
            if (fitnessClass.getName().equalsIgnoreCase(className)) {
                return fitnessClass;
            }
        }
        return null;
    }
}