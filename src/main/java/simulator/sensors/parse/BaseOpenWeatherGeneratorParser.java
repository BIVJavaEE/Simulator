package simulator.sensors.parse;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;

public abstract class BaseOpenWeatherGeneratorParser implements IDataGeneratorParser {

    protected String _apiKey;

    protected BaseOpenWeatherGeneratorParser(String apiKey) {
        _apiKey = apiKey;
    }

    protected abstract IDataGenerator parseOpenWeatherGenerator(int latitude, int longitude);

    @Override
    public IDataGenerator parse(JSONObject jsonObject) {
        String generator = jsonObject.getString("generator");
        if (!generator.equals("openweathermapapi")) {
            throw new IllegalArgumentException();
        }
        int longitude = jsonObject.getInt("longitude");
        int latitude = jsonObject.getInt("latitude");
        return parseOpenWeatherGenerator(latitude, longitude);
    }
}
