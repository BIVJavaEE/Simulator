package simulator.sensors.parse.subTemperatureParsers;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;
import simulator.data.generator.OpenWeatherTemperatureGenerator;
import simulator.sensors.parse.IDataGeneratorParser;

public class OpenWeatherTemperatureGeneratorParser implements IDataGeneratorParser {

    private String _apiKey;

    public OpenWeatherTemperatureGeneratorParser(String apiKey) {
        _apiKey = apiKey;
    }

    @Override
    public IDataGenerator parse(JSONObject jsonObject) {
        var city = jsonObject.get("city").toString();
        return new OpenWeatherTemperatureGenerator(city, _apiKey);
    }
}
