package controller;

import model.FitnessClass;
import model.User;
import model.Admin;
import view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private ConsoleView view;
    private List<User> users;
    private List<FitnessClass> classes;

    public BookingController(ConsoleView view) {
        this.view = view;
        this.users = new ArrayList<>();
        this.classes = new ArrayList<>();
    }

    public void createClass(Admin admin, String name, String type, int capacity, String schedule) {
        FitnessClass fitnessClass = admin.createClass(name, type, capacity, schedule);
        classes.add(fitnessClass);
        view.showMessage("Class created: " + fitnessClass.getName());
    }

    public void registerUser(User user) {
        users.add(user);
        view.showMessage("User registered: " + user.getName());
    }

    public void bookClass(User user, FitnessClass fitnessClass) {
        if (user.bookClass(fitnessClass)) {
            view.showMessage(user.getName() + " booked " + fitnessClass.getName());
        } else {
            view.showMessage(user.getName() + " was added to the waitlist for " + fitnessClass.getName());
        }
    }

    public void cancelClass(Admin admin, FitnessClass fitnessClass) {
        admin.cancelClass(fitnessClass);
        classes.remove(fitnessClass);
        view.showMessage("Class cancelled: " + fitnessClass.getName());
    }

    public void cancelBooking(User user, FitnessClass fitnessClass) {
        user.cancelBooking(fitnessClass);
        view.showMessage(user.getName() + " cancelled the booking for " + fitnessClass.getName());
    }

    // Method to get all registered users
    public List<User> getAllUsers() {
        return users;
    }

    // Method to get all created classes
    public List<FitnessClass> getAllClasses() {
        return classes;
    }
}