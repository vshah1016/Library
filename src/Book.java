import java.util.ArrayList;

public class Book {
    final String title;
    final String author;
    String borrower;
    Date borrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public boolean isBorrowed() {
        return borrowed != null;
    }

    public boolean borrowedOverTwoWeeks(Date date) {
        return date.isAfterBy(borrowed) > 14;
    }

    public void loanOut(String borrower, Date borrowed) {
        this.borrower = borrower;
        this.borrowed = borrowed;
    }

    public void returnBook() {
        if (!isBorrowed()) throw new NotCheckedOut();
        else {
            borrower = null;
            borrowed = null;
        }
    }

    ArrayList<String> toList() {
        ArrayList<String> book = new ArrayList<>();
        book.add(title);
        book.add(author);
        if (borrower != null) book.add(borrower);
        else book.add("");
        if (borrowed != null) book.add(borrowed.toString());
        else book.add("");
        return book;
    }

}
