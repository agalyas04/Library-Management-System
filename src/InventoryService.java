import java.util.*;

public class InventoryService {
    private static List<Book> books = new ArrayList<>();
    private static List<BorrowRecord> records = new ArrayList<>();

    static {
        books.add(new Book("The Alchemist", "Paulo Coelho"));
        books.add(new Book("1984", "George Orwell"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee"));
    }

    public void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        books.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    public void removeBook(Scanner scanner) {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();

        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        System.out.println("Book removed if existed.");
    }

    public void updateBook(Scanner scanner) {
        System.out.print("Enter book title to update: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                books.set(books.indexOf(book), new Book(title, newAuthor));
                System.out.println("Book updated!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchBook(Scanner scanner) {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor() +
                                   (book.isBorrowed() ? " (Borrowed)" : " (Available)"));
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void borrowBook(Scanner scanner, User user) {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isBorrowed()) {
                    book.borrow();
                    records.add(new BorrowRecord(user.getUsername(), title, java.time.LocalDate.now()));
                    System.out.println("You borrowed: " + book.getTitle());
                } else {
                    System.out.println("Book is already borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(Scanner scanner, User user) {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isBorrowed()) {
                    book.returned();
                    for (BorrowRecord record : records) {
                        if (record.getBookTitle().equalsIgnoreCase(title) &&
                            record.getUsername().equals(user.getUsername()) &&
                            record.getReturnDate() == null) {
                            record.setReturnDate(java.time.LocalDate.now());
                        }
                    }
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("This book was not borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void viewAllBooks() {
        System.out.println("\n--- Book Inventory ---");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() +
                               (book.isBorrowed() ? " (Borrowed)" : " (Available)"));
        }
    }

    public void borrowedBooksReport() {
        System.out.println("\n--- Borrowed Books Report ---");
        for (BorrowRecord record : records) {
            if (record.getReturnDate() == null) {
                System.out.println(record.getBookTitle() + " borrowed by " + record.getUsername() +
                                   " on " + record.getBorrowDate());
            }
        }
    }

    public void userHistory(String username) {
        System.out.println("\n--- Borrow History for " + username + " ---");
        for (BorrowRecord record : records) {
            if (record.getUsername().equals(username)) {
                System.out.println(record.getBookTitle() +
                                   " | Borrowed: " + record.getBorrowDate() +
                                   " | Returned: " + (record.getReturnDate() != null ? record.getReturnDate() : "Not yet"));
            }
        }
    }

    public void statistics() {
        long borrowed = records.stream().filter(r -> r.getReturnDate() == null).count();
        long total = books.size();
        System.out.println("\n--- Library Statistics ---");
        System.out.println("Total Books: " + total);
        System.out.println("Currently Borrowed: " + borrowed);
        System.out.println("Available: " + (total - borrowed));
    }
}
