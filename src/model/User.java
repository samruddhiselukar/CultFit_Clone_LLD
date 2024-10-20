package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String tier;
    private int bookingLimit;
    private List<FitnessClass> bookedClasses;

    public User(String name, String email, String tier) {
        this.name = name;
        this.email = email;
        this.tier = tier;
        this.bookingLimit = setBookingLimit(tier);
        this.bookedClasses = new ArrayList<>();
    }

    private int setBookingLimit(String tier) {
        switch (tier) {
            case "Platinum":
                return 10;
            case "Gold":
                return 5;
            case "Silver":
                return 3;
            default:
                return 0;
        }
    }

    public boolean bookClass(FitnessClass fitnessClass) {
        if (bookedClasses.size() >= bookingLimit) {
            return false;
        }
        if (fitnessClass.addAttendee(this)) {
            bookedClasses.add(fitnessClass);
            return true;
        } else {
            fitnessClass.addToWaitlist(this);
            return false;
        }
    }

    public void cancelBooking(FitnessClass fitnessClass) {
        if (bookedClasses.contains(fitnessClass)) {
            fitnessClass.removeAttendee(this);
            bookedClasses.remove(fitnessClass);
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}