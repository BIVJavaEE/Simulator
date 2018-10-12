package simulator.data.generator;

import simulator.data.BaseData;
import simulator.data.datas.Temperature;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTemperatureGenerator implements IDataGenerator {

    private final static int MinTemperature = -10;
    private final static int MaxTemperature = 40;

    @Override
    public Future<BaseData> generate() {
        var randomTemperature = ThreadLocalRandom.current().nextInt(MinTemperature, MaxTemperature + 1);
        return CompletableFuture.completedFuture(new Temperature(randomTemperature));
    }

}