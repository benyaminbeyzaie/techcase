import broker.MessageBroker;
import consumer.Consumer;
import producer.Producer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    private ArrayList<Producer> producers;
    private ArrayList<Consumer> consumers;
    private int numberOfProducers;
    private int numberOfConsumers;
    private File input;
    private File output;
    private MessageBroker messageBroker;

    Program(int numberOfProducers, int numberOfConsumers, File input) throws IOException {
        messageBroker = new MessageBroker(input.getCanonicalPath());
        this.numberOfConsumers = numberOfConsumers;
        this.numberOfProducers = numberOfProducers;
        this.input = input;
        output = new File("src/main/resources/" + input.getName().substring(0 ,  input.getName().length() - 4) + "_output.txt");
    }

    void execute() throws FileNotFoundException {
        createProducers();
        createConsumers();
        startProducers();
        startConsumers();
    }
    private void startConsumers() {
        for (Consumer c :
                consumers) {
            c.start();
        }
    }

    private void startProducers() {
        for (Producer p :
                producers) {
            p.start();
        }
    }

    private void createConsumers() throws FileNotFoundException {
        consumers = new ArrayList<>();
        for (int i = 0; i < numberOfConsumers; i++) {
            consumers.add(new Consumer("Consumer_" + i, messageBroker, output));
        }
    }

    private void createProducers() throws FileNotFoundException {
        producers = new ArrayList<>();
        for (int i = 0; i < numberOfProducers; i++) {
            producers.add(new Producer("Producer_" + i, messageBroker, input));
        }
    }
}
