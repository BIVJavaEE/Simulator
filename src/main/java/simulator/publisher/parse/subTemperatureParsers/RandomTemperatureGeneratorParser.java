package simulator.publisher.parse.subTemperatureParsers;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;
import simulator.data.generator.RandomTemperatureGenerator;
import simulator.publisher.parse.IDataGeneratorParser;

public class RandomTemperatureGeneratorParser implements IDataGeneratorParser {

    @Override
    public IDataGenerator parse(JSONObject jsonObject) {
        var min = jsonObject.getInt("min");
        var max = jsonObject.getInt("max");
        return new RandomTemperatureGenerator(min, max);
    }

}
