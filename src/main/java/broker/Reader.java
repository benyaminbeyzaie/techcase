package broker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Reader {
    private File queue;
    Reader(File queue){
        this.queue = queue;
    }

    String pullMessage() throws FileNotFoundException {
        Scanner scanner = new Scanner(queue);
        return scanner.nextLine();
    }
}
