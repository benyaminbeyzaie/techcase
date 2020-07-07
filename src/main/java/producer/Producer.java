package producer;

import broker.MessageBroker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Producer extends Thread {
    private String name;
    private MessageBroker messageBroker;
    private File file;
    private static Scanner scanner;
    private static final Object lock = new Object();

    public Producer(String name, MessageBroker messageBroker, File file) throws FileNotFoundException {
        this.name = name;
        this.messageBroker = messageBroker;
        this.file = file;
        scanner = new Scanner(file);
    }

    @Override
    public synchronized void run() {
        synchronized (lock){
            try {
                while (scanner.hasNext()){
                    String message = scanner.nextLine();
                    push(message.substring(5));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void push(String message) throws IOException {
        messageBroker.push(name, message);
    }
}
