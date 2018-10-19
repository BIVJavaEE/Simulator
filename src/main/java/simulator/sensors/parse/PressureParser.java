package simulator.sensors.parse;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;
import simulator.data.generator.OpenWeatherPressureGenerator;

public class PressureParser implements IDataGeneratorParser {

    private String _apiKey;

    public PressureParser(String apiKey) {
        _apiKey = apiKey;
    }

    @Override
    public IDataGenerator parse(JSONObject jsonObject) {
        String generator = jsonObject.getString("generator");
        if (!generator.equals("openweathermapapi")) {
            throw new IllegalArgumentException();
        }
        String city = jsonObject.getString("city");
        return new OpenWeatherPressureGenerator(city, _apiKey);
    }
}
