package broker;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class Writer {
    RandomAccessFile queue;
    Writer(RandomAccessFile queue){
        this.queue = queue;
    }


    public void pushMessage(String message) throws IOException {
        queue.writeChars(message);
    }
}
