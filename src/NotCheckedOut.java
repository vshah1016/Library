public class NotCheckedOut extends RuntimeException {
    public NotCheckedOut() {
        super("The book cannot be checked back in because it is not checked out.");
    }
}
