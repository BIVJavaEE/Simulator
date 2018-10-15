package simulator.data.generator;

import simulator.data.BaseData;
import simulator.data.datas.Temperature;
import simulator.data.generator.helpers.BaseRandomIntGenerator;

import java.sql.Timestamp;

public class RandomTemperatureGenerator extends BaseRandomIntGenerator {

    public RandomTemperatureGenerator(Integer min, Integer max) {
        super(min, max);
    }

    @Override
    public BaseData generate(Integer sensorId) {
        var randomTemperature = getRandomNumber();
        return new Temperature(getUnixTime(), sensorId, randomTemperature);
    }

}