package simulator.sensors.parse;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;
import simulator.sensors.parse.subTemperatureParsers.OpenWeatherTemperatureGeneratorParser;
import simulator.sensors.parse.subTemperatureParsers.RandomTemperatureGeneratorParser;

public class TemperatureParser implements IDataGeneratorParser {

    private String _openWeatherApiKey;

    public TemperatureParser(String openWeatherApiKey) {
        _openWeatherApiKey = openWeatherApiKey;
    }

    @Override
    public IDataGenerator parse(JSONObject jsonObject) {
        String generator = jsonObject.get("generator").toString();
        IDataGeneratorParser subParser;
        switch (generator) {
            case "random":
                subParser = new RandomTemperatureGeneratorParser();
                break;
            case "openweathermapapi":
                subParser = new OpenWeatherTemperatureGeneratorParser(_openWeatherApiKey);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return subParser.parse(jsonObject);
    }
}
