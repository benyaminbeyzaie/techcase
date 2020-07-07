package broker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import constants.Constants;

public class MessageBroker {
    private Writer writer;
    private Reader reader;

    public MessageBroker() throws FileNotFoundException {
        RandomAccessFile queue = new RandomAccessFile(Constants.FILE_PATH + "_binary.data", "rwd");
        writer = new Writer(queue);
        reader = new Reader(queue);
    }

    public synchronized void push(String producerName, String message) throws IOException {
        synchronized (this){
            // push the message to queue
            writer.pushMessage(message);
            notify();
        }
    }

    public synchronized String pull(String consumerName) throws InterruptedException, IOException {
        synchronized (this){
            // if there is no message to read wait for it ;)
            String message = reader.pullMessage();
            if (message == null){
                wait();
            }
            // get the messages from queue
            return message;
        }
    }

}
