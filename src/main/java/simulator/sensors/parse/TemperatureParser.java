package simulator.sensors.parse;

import simulator.data.generator.IDataGenerator;
import simulator.data.generator.OpenWeatherTemperatureGenerator;

public class TemperatureParser extends BaseOpenWeatherGeneratorParser {

    public TemperatureParser(String apiKey) {
        super(apiKey);
    }

    @Override
    protected IDataGenerator parseOpenWeatherGenerator(int latitude, int longitude) {
        return new OpenWeatherTemperatureGenerator(latitude, longitude, _apiKey);
    }

}
