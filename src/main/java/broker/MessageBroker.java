package broker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import constants.Constants;

public class MessageBroker {
    private Writer writer;
    private Reader reader;
    private static int pushCount = 0;
    private static int pullCount = 0;

    public MessageBroker(String inputFileAddress) throws FileNotFoundException {
        String randomAccessFileName = inputFileAddress.substring(0, inputFileAddress.length() - 4) + "_data.data";
        RandomAccessFile queueReader = new RandomAccessFile(randomAccessFileName, "rwd");
        RandomAccessFile queueWriter = new RandomAccessFile(randomAccessFileName, "rwd");

        writer = new Writer(queueReader);
        reader = new Reader(queueWriter);
    }

    public synchronized void push(String producerName, String message) throws IOException {
        synchronized (this){
            // push the message to queue
            System.out.println("pushed: " + message);
            writer.pushMessage(message);
            pushCount++;
            notify();
        }
    }

    public synchronized String pull(String consumerName) throws InterruptedException, IOException {
        synchronized (this){
            // if there is no message to read wait for it ;)
            if (pullCount == pushCount) {
                wait();
            }
            System.out.println(pullCount + ", " + pushCount);
            String message = reader.pullMessage();
            pullCount++;
            // get the messages from queue
            return message;
        }
    }

}
