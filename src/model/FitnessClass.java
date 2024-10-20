package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FitnessClass {
    private String name;
    private String type;
    private int capacity;
    private String schedule;
    private List<User> attendees;
    private Queue<User> waitlist;

    public FitnessClass(String name, String type, int capacity, String schedule) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.schedule = schedule;
        this.attendees = new ArrayList<>();
        this.waitlist = new LinkedList<>();
    }

    public boolean addAttendee(User user) {
        if (attendees.size() < capacity) {
            attendees.add(user);
            return true;
        }
        return false;
    }

    public void addToWaitlist(User user) {
        waitlist.add(user);
    }

    public void removeAttendee(User user) {
        if (attendees.remove(user)) {
            allocateWaitlistSlot();
        }
    }

    public void allocateWaitlistSlot() {
        if (!waitlist.isEmpty()) {
            User nextUser = waitlist.poll();
            if (nextUser != null) {
                attendees.add(nextUser);
            }
        }
    }

    public void cancelClass() {
        attendees.clear();
        waitlist.clear();
    }

    public String getName() {
        return name;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}