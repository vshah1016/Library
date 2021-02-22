import java.util.Scanner;

public class Client {
    Scanner scanner = new Scanner(System.in);
    private int runAgain = 0;

    public String gatherInput() {
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }

    public boolean isRunAgain() {
        return !(runAgain == 0);
    }

    public int runAgain() {
        System.out.println("0. exit");
        System.out.println("1. books by author");
        System.out.println("2. books by title");
        System.out.println("3. loan book");
        System.out.println("4. return book");
        System.out.println("5. borrowed books");
        System.out.println("6. overdue books");
        System.out.println("7. total amount books");
        System.out.println("8. add book");
        System.out.println("9. remove book");
        System.out.println("10. all books");
        runAgain = scanner.nextInt();
        if (runAgain > 10 || runAgain < 0) {
            runAgain = 0;
            System.out.println("Invalid, exiting.");
        }
        System.out.println();
        scanner.nextLine();
        return runAgain;
    }
}