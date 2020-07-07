package broker;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class Writer {
    private RandomAccessFile queue;
    Writer(RandomAccessFile queue){
        this.queue = queue;
    }


    void pushMessage(String message) throws IOException {
        queue.writeBytes(message);
        queue.writeBytes("\n");
    }
}
