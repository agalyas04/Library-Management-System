import java.util.*;

public class LibraryService {
    private InventoryService inventoryService = new InventoryService();
    private AuthService authService = new AuthService();

    public void menu(Scanner scanner, User user) {
        while (true) {
            if (user.isAdmin()) {
                System.out.println("\n=== Admin Menu ===");
                System.out.println("1. Search Book");
                System.out.println("2. Borrow Book");
                System.out.println("3. Return Book");
                System.out.println("4. Add Book");
                System.out.println("5. Remove Book");
                System.out.println("6. Update Book");
                System.out.println("7. View All Books");
                System.out.println("8. Borrowed Books Report");
                System.out.println("9. View All Users");
                System.out.println("10. View User History");
                System.out.println("11. View Statistics");
                System.out.println("12. Logout");
            } else {
                System.out.println("\n=== User Menu ===");
                System.out.println("1. Search Book");
                System.out.println("2. Borrow Book");
                System.out.println("3. Return Book");
                System.out.println("4. View My History");
                System.out.println("5. Logout");
            }

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (user.isAdmin()) {
                switch (choice) {
                    case 1: inventoryService.searchBook(scanner); break;
                    case 2: inventoryService.borrowBook(scanner, user); break;
                    case 3: inventoryService.returnBook(scanner, user); break;
                    case 4: inventoryService.addBook(scanner); break;
                    case 5: inventoryService.removeBook(scanner); break;
                    case 6: inventoryService.updateBook(scanner); break;
                    case 7: inventoryService.viewAllBooks(); break;
                    case 8: inventoryService.borrowedBooksReport(); break;
                    case 9: 
                        for (User u : authService.getAllUsers()) {
                            System.out.println(u.getUsername() + (u.isAdmin() ? " (Admin)" : ""));
                        }
                        break;
                    case 10:
                        System.out.print("Enter username: ");
                        String uname = scanner.nextLine();
                        inventoryService.userHistory(uname);
                        break;
                    case 11: inventoryService.statistics(); break;
                    case 12: return;
                    default: System.out.println("Invalid option.");
                }
            } else {
                switch (choice) {
                    case 1: inventoryService.searchBook(scanner); break;
                    case 2: inventoryService.borrowBook(scanner, user); break;
                    case 3: inventoryService.returnBook(scanner, user); break;
                    case 4: inventoryService.userHistory(user.getUsername()); break;
                    case 5: return;
                    default: System.out.println("Invalid option.");
                }
            }
        }
    }
}
