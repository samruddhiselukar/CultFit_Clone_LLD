package model;

public class Admin extends User {

    public Admin(String name, String email) {
        super(name, email, "Admin");
    }

    public FitnessClass createClass(String name, String type, int capacity, String schedule) {
        return new FitnessClass(name, type, capacity, schedule);
    }

    public void cancelClass(FitnessClass fitnessClass) {
        fitnessClass.cancelClass();
    }

    public void scheduleClass(FitnessClass fitnessClass, String newSchedule) {
        fitnessClass.setSchedule(newSchedule);
    }
}