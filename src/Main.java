import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        LibraryService libraryService = new LibraryService();

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    authService.register(scanner);
                    break;
                case 2:
                    User loggedInUser = authService.login(scanner);
                    if (loggedInUser != null) {
                        libraryService.menu(scanner, loggedInUser);
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
