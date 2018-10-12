package simulator.publisher;

import simulator.data.generator.RandomTemperatureGenerator;
import simulator.dataSender.DataSenderException;
import simulator.dataSender.IDataSender;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PublishersScheduler {

    private IDataSender _dataSender;
    private int _interval;
    private ScheduledExecutorService _executor;

    public PublishersScheduler(IDataSender dataSender, int interval) {
        _dataSender = dataSender;
        _interval = interval;
        _executor = Executors.newScheduledThreadPool(1);
    }

    public void start() throws DataSenderException {
        _dataSender.initialize();
        var temperatureGenerator = new RandomTemperatureGenerator();
        var publisher = new Publisher(_dataSender, temperatureGenerator);
        _executor.scheduleAtFixedRate(publisher, 0, _interval, TimeUnit.SECONDS);
    }

    public void stop() throws DataSenderException {
        _dataSender.shutdown();
        _executor.shutdown();
    }
}
