import java.time.LocalDate;

public class BorrowRecord {
    private String username;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(String username, String bookTitle, LocalDate borrowDate) {
        this.username = username;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
    }

    public String getUsername() { return username; }
    public String getBookTitle() { return bookTitle; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
