package consumer;

import broker.MessageBroker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Consumer extends Thread {
    private String name;
    private MessageBroker messageBroker;
    private PrintWriter printWriter;
    private static final Object lock = new Object();

    public Consumer(String name, MessageBroker messageBroker, File output) throws FileNotFoundException {
        this.name = name;
        this.messageBroker = messageBroker;
        printWriter = new PrintWriter(output);
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                try {
                    sleep(1);
                    String message = messageBroker.pull(this.name);
                    if (message != null){
                        // System.out.println(message);
                        printWriter.println(message);
                        printWriter.flush();
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
