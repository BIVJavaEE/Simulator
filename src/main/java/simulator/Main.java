package simulator;

import simulator.dataSender.DataSenderException;
import simulator.dataSender.IDataSenderFactoryException;
import simulator.dataSender.MqttDataSenderFactory;
import simulator.publisher.PublishersScheduler;

public class Main {

    public static void main(String[] args) throws IDataSenderFactoryException, DataSenderException {
        var dataSender = new MqttDataSenderFactory(args).create();
        var scheduler = new PublishersScheduler(dataSender, 10);
        scheduler.start();
    }

}
