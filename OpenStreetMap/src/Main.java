import database.DatabaseManager;
import view.StopsGUI;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        StopsGUI stopsgui = new StopsGUI();
        DatabaseManager database = DatabaseManager.getInstance();
        database.disconnect();
    }
}
