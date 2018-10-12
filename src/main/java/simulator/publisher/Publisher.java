package simulator.publisher;

import simulator.data.generator.IDataGenerator;
import simulator.dataSender.DataSenderException;
import simulator.dataSender.IDataSender;

import java.util.concurrent.ExecutionException;

class Publisher implements Runnable {

    private IDataSender _dataSender;
    private IDataGenerator _generator;

    public Publisher(IDataSender dataSender, IDataGenerator generator) {
        _dataSender = dataSender;
        _generator = generator;
    }

    @Override
    public void run() {
        try {
            var data = _generator.generate().get();
            var json = data.createJson();
            _dataSender.send(json);
        } catch (DataSenderException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
