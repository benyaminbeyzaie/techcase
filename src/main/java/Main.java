import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Program takes a file and read it by producers
        // the output of program is yourFileName_output.txt
        // the .data file works like a buffer
        // delete the sample output first and then run the program!

        new Program(3, 3, new File("src/main/resources/input.txt")).execute();
    }
}
