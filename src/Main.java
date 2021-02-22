import javax.swing.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

public class Main {

    private static final Client client = new Client();
    private static final BooleanSupplier runAgain = client::isRunAgain;
    private static Library library;


    public static void main(String[] args) {
        System.out.print("What is the month? ");
        int month = Integer.parseInt(client.gatherInput());
        System.out.print("What is the day? ");
        int day = Integer.parseInt(client.gatherInput());
        System.out.print("What is the year? ");
        int year = Integer.parseInt(client.gatherInput());
        library = new Library(new Date(month, day, year));
        int choice = client.runAgain();
        while (runAgain.getAsBoolean()) {
            switch (choice) {
                case 1:
                    String[] columnNames = {"Title", "Author", "Borrower", "Date Borrowed"};
                    System.out.print("Author's Name: ");
                    String author = client.gatherInput();
                    String[][] data = library.books.stream().filter(book -> book.author.equalsIgnoreCase(author)).map(Book::toList).map(e -> e.toArray(new String[0])).toArray(String[][]::new);
                    JFrame f = new JFrame();
                    JTable jt = new JTable(data, columnNames);
                    JScrollPane sp = new JScrollPane(jt);
                    f.add(sp);
                    f.setSize(300, 400);
                    f.setVisible(true);
                    break;
                case 2:
                    columnNames = new String[]{"Title", "Author", "Borrower", "Date Borrowed"};
                    System.out.print("Title: ");
                    String title = client.gatherInput();
                    data = library.books.stream().filter(book -> book.title.equalsIgnoreCase(title)).map(Book::toList).map(e -> e.toArray(new String[0])).toArray(String[][]::new);
                    f = new JFrame();
                    jt = new JTable(data, columnNames);
                    sp = new JScrollPane(jt);
                    f.add(sp);
                    f.setSize(300, 400);
                    f.setVisible(true);
                    break;
                case 3:
                    System.out.print("Name of Book to Lend: ");
                    title = client.gatherInput();
                    ArrayList<Book> books = library.books.stream().filter(book -> book.title.equalsIgnoreCase(title) && !book.isBorrowed()).collect(Collectors.toCollection(ArrayList::new));
                    if (books.size() == 0) {
                        System.out.println("No books to lend.");
                        break;
                    }
                    System.out.print("Who is lending the book? ");
                    String lender = client.gatherInput();
                    System.out.print("What is the month? ");
                    month = Integer.parseInt(client.gatherInput());
                    System.out.print("What is the day? ");
                    day = Integer.parseInt(client.gatherInput());
                    System.out.print("What is the year? ");
                    year = Integer.parseInt(client.gatherInput());
                    Date date = new Date(month, day, year);
                    if (date.isAfter(library.currentDate)) {
                        throw new DataError("Cannot lend in a date after today!");
                    }
                    books.get(0).loanOut(lender, date);
                    break;
                case 4:
                    System.out.print("Name of book to return: ");
                    title = client.gatherInput();
                    books = library.books.stream().filter(book -> book.title.equalsIgnoreCase(title)).collect(Collectors.toCollection(ArrayList::new));
                    if (books.size() == 0) {
                        System.out.println("No books with that name.");
                        break;
                    }
                    if (!books.get(0).isBorrowed()) throw new NotCheckedOut();
                    books.get(0).returnBook();
                    break;
                case 5:
                    columnNames = new String[]{"Title", "Author", "Borrower", "Date Borrowed"};
                    data = library.borrowed().stream().map(Book::toList).map(e -> e.toArray(new String[0])).toArray(String[][]::new);
                    f = new JFrame();
                    jt = new JTable(data, columnNames);
                    sp = new JScrollPane(jt);
                    f.add(sp);
                    f.setSize(300, 400);
                    f.setVisible(true);
                    break;
                case 6:
                    columnNames = new String[]{"Title", "Author", "Borrower", "Date Borrowed"};
                    data = library.overdue().stream().map(Book::toList).map(e -> e.toArray(new String[0])).toArray(String[][]::new);
                    f = new JFrame();
                    jt = new JTable(data, columnNames);
                    sp = new JScrollPane(jt);
                    f.add(sp);
                    f.setSize(300, 400);
                    f.setVisible(true);
                    break;
                case 7:
                    System.out.println("There are " + library.totalBooks() + " inside the library, and " + library.books.size() + " books owned by the library.");
                    break;
                case 8:
                    System.out.print("Title of book to add: ");
                    title = client.gatherInput();
                    System.out.print("Author of book to add: ");
                    author = client.gatherInput();
                    library.addBook(new Book(title, author));
                    break;
                case 9:
                    System.out.println("Title of book to remove: ");
                    title = client.gatherInput();
                    library.removeByTitle(title);
                    System.out.println("If there was a book with that title, it was removed. Thanks.");
                    break;
                case 10:
                    columnNames = new String[]{"Title", "Author", "Borrower", "Date Borrowed"};
                    data = library.books.stream().map(Book::toList).map(e -> e.toArray(new String[0])).toArray(String[][]::new);
                    f = new JFrame();
                    jt = new JTable(data, columnNames);
                    sp = new JScrollPane(jt);
                    f.add(sp);
                    f.setSize(300, 400);
                    f.setVisible(true);
                    break;
            }
            choice = client.runAgain();
        }
        System.out.println("Goodbye!");
        System.exit(0);
    }
}