package simulator.data.generator;

import simulator.data.BaseData;
import simulator.data.datas.Temperature;
import simulator.data.generator.helpers.BaseRandomIntGenerator;

public class RandomTemperatureGenerator extends BaseRandomIntGenerator {

    public RandomTemperatureGenerator(int min, int max) {
        super(min, max);
    }

    @Override
    public BaseData generate() {
        var randomTemperature = getRandomNumber();
        return new Temperature(randomTemperature);
    }

}