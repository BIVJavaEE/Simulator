package simulator.data.generator.helpers;

import simulator.data.generator.IDataGenerator;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseRandomIntGenerator extends BaseDataGenerator {

    private int _min, _max;

    protected BaseRandomIntGenerator(Integer min, Integer max) {
        _min = min;
        _max = max;
    }

    protected Integer getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(_min,_max + 1);
    }
}
