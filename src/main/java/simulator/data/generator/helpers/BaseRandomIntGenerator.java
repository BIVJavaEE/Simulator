package simulator.data.generator.helpers;

import simulator.data.generator.IDataGenerator;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseRandomIntGenerator implements IDataGenerator {

    private int _min, _max;

    protected BaseRandomIntGenerator(int min, int max) {
        _min = min;
        _max = max;
    }

    protected Integer getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(_min,_max + 1);
    }
}
