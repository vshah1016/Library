import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Library {
    ArrayList<Book> books = new ArrayList<>();

    public Library(Date currentDate) {
        this.currentDate = currentDate;
    }

    final Date currentDate;

    public ArrayList<Book> borrowed() {
        return books.stream().filter(Book::isBorrowed).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Book> overdue(){
        return books.stream().filter(book -> book.borrowedOverTwoWeeks(currentDate)).collect(Collectors.toCollection(ArrayList::new));
    }

    public int totalBooks(){
        return (int) books.stream().filter(book -> !book.isBorrowed()).count();
    }

    public void addBook(Book book){
        books.add(book);
        System.out.println("Added");
    }

    public void removeByTitle(String title){
        books.removeIf(book -> book.title.equalsIgnoreCase(title));
    }
}
