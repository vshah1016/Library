import java.util.InputMismatchException;

public class DataError extends InputMismatchException {
    DataError(String message){
        super(message);
    }

    DataError(){
        super();
    }
}
