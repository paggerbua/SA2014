import database.DatabaseManager;
import view.StopsGUI;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        DatabaseManager database = DatabaseManager.getInstance();
        StopsGUI stopsgui = new StopsGUI(database);

        database.disconnect();
    }
}
