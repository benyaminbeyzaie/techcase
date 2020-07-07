package broker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Reader {
    private RandomAccessFile queue;

    Reader(RandomAccessFile queue){
        this.queue = queue;
    }

    String pullMessage() throws IOException {
        String s = queue.readLine();
        if (s == null) queue.seek(0);
        return s;
    }
}
