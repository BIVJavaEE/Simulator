package simulator.publisher;

import simulator.dataSender.DataSenderException;
import simulator.dataSender.IDataSender;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SensorScheduler {

    private IDataSender _dataSender;
    private int _interval;
    private ScheduledExecutorService _executor;
    private Sensor _sensor;

    public SensorScheduler(IDataSender dataSender, Sensor sensor, int interval) {
        _dataSender = dataSender;
        _interval = interval;
        _sensor = sensor;
    }

    public void start() throws DataSenderException {
        _executor = Executors.newScheduledThreadPool(1);
        _executor.scheduleAtFixedRate(_sensor, 0, _interval, TimeUnit.SECONDS);
    }

    public void stop() throws DataSenderException {
        _executor.shutdown();
    }
}
