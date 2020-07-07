import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new Program(3, 3, new File("src/main/resources/input.txt")).execute();

    }
}
