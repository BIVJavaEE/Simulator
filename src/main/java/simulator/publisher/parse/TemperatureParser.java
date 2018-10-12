package simulator.publisher.parse;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;
import simulator.publisher.parse.subTemperatureParsers.RandomTemperatureGeneratorParser;

public class TemperatureParser implements IDataGeneratorParser {
    @Override
    public IDataGenerator parse(JSONObject jsonObject) {
        var generator = jsonObject.get("generator").toString();
        IDataGeneratorParser subParser;
        switch (generator) {
            case "random":
                subParser = new RandomTemperatureGeneratorParser();
                break;
            default:
                throw new IllegalArgumentException();
        }
        return subParser.parse(jsonObject);
    }
}
