package simulator.sensors;

import simulator.data.BaseData;
import simulator.data.generator.DataGeneratorException;
import simulator.data.generator.IDataGenerator;
import simulator.dataSender.DataSenderException;
import simulator.dataSender.IDataSender;

public class Sensor implements Runnable {

    private IDataSender _dataSender;
    private int _sensorId;
    private IDataGenerator _generator;

    public Sensor(Integer sensorId, IDataSender dataSender, IDataGenerator generator) {
        _sensorId = sensorId;
        _dataSender = dataSender;
        _generator = generator;
    }

    @Override
    public void run() {
        try {
            BaseData data = _generator.generate(_sensorId);
            String json = data.createJson();
            System.out.println("Sending " + json);
            _dataSender.send(json);
        } catch (DataSenderException | DataGeneratorException e) {
            e.printStackTrace();
        }
    }
}
