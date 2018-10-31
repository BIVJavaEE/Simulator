package simulator.sensors.parse;

import simulator.data.generator.IDataGenerator;
import simulator.data.generator.OpenWeatherPressureGenerator;

public class PressureParser extends  BaseOpenWeatherGeneratorParser {

    public PressureParser(String apiKey) {
        super(apiKey);
    }

    @Override
    protected IDataGenerator parseOpenWeatherGenerator(int latitude, int longitude) {
        return new OpenWeatherPressureGenerator(latitude, longitude, _apiKey);
    }

}
