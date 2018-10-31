package simulator.sensors.parse;

import simulator.data.generator.IDataGenerator;
import simulator.data.generator.OpenWeatherWindSpeedGenerator;

public class WindSpeedParser extends BaseOpenWeatherGeneratorParser {

    public WindSpeedParser(String apiKey) {
        super(apiKey);
    }

    @Override
    protected IDataGenerator parseOpenWeatherGenerator(int latitude, int longitude) {
        return new OpenWeatherWindSpeedGenerator(latitude, longitude, _apiKey);
    }
}
