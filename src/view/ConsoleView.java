package view;

public class ConsoleView {

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String errorMessage) {
        System.err.println(errorMessage);
    }
}