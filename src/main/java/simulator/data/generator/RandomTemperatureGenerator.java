package simulator.data.generator;

import simulator.data.BaseData;
import simulator.data.datas.Temperature;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTemperatureGenerator implements IDataGenerator {

    private int _min;
    private int _max;

    public RandomTemperatureGenerator(int min, int max) {
        _min = min;
        _max = max;
    }

    @Override
    public Future<BaseData> generate() {
        var randomTemperature = ThreadLocalRandom.current().nextInt(_min,_max + 1);
        return CompletableFuture.completedFuture(new Temperature(randomTemperature));
    }

}