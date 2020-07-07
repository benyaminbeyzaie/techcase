package broker;

import java.io.File;
import constants.Constants;

public class MessageBroker {
    private Writer writer;
    private Reader reader;
    private File queue;

    public MessageBroker(){
        queue = new File(Constants.FILE_PATH);
        writer = new Writer(queue);
        reader = new Reader(queue);
    }

    public synchronized void push(String producerName, String message){
        synchronized (this){
            // push the message to queue
            writer.pushMessage(message);
            notify();
        }
    }

    public synchronized String pull(String consumerName) throws InterruptedException {
        synchronized (this){
            // if there is no message to read wait for it ;)
            if (reader.hasNoMessage()){
                wait();
            }
            // get the messages from queue
            return reader.pullMessage();
        }
    }

}
