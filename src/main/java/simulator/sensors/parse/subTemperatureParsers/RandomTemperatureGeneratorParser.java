package simulator.sensors.parse.subTemperatureParsers;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;
import simulator.data.generator.RandomTemperatureGenerator;
import simulator.sensors.parse.IDataGeneratorParser;

public class RandomTemperatureGeneratorParser implements IDataGeneratorParser {

    @Override
    public IDataGenerator parse(JSONObject jsonObject) {
        int min = jsonObject.getInt("min");
        int max = jsonObject.getInt("max");
        return new RandomTemperatureGenerator(min, max);
    }

}
