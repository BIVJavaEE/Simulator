package simulator.sensors;

import simulator.data.generator.DataGeneratorException;
import simulator.data.generator.IDataGenerator;
import simulator.dataSender.DataSenderException;
import simulator.dataSender.IDataSender;

public class Sensor implements Runnable {

    private IDataSender _dataSender;
    private IDataGenerator _generator;

    public Sensor(IDataSender dataSender, IDataGenerator generator) {
        _dataSender = dataSender;
        _generator = generator;
    }

    @Override
    public void run() {
        try {
            var data = _generator.generate();
            var json = data.createJson();
            System.out.println("Sending " + json);
            _dataSender.send(json);
        } catch (DataSenderException | DataGeneratorException e) {
            e.printStackTrace();
        }
    }
}
