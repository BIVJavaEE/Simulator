package simulator.sensors.parse;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;
import simulator.data.generator.OpenWeatherWindSpeedGenerator;

public class WindSpeedParser implements IDataGeneratorParser {

    private String _apiKey;

    public WindSpeedParser(String apiKey) {
        _apiKey = apiKey;
    }

    @Override
    public IDataGenerator parse(JSONObject jsonObject) {
        String generator = jsonObject.getString("generator");
        if (!generator.equals("openweathermapapi")) {
            throw new IllegalArgumentException();
        }
        String city = jsonObject.getString("city");
        return new OpenWeatherWindSpeedGenerator(city, _apiKey);
    }
}
