package simulator.sensors.parse;

import simulator.data.generator.IDataGenerator;
import simulator.data.generator.OpenWeatherWindDirectionGenerator;

public class WindDirectionParser extends BaseOpenWeatherGeneratorParser {

    public WindDirectionParser(String apiKey) {
        super(apiKey);
    }

    @Override
    protected IDataGenerator parseOpenWeatherGenerator(int latitude, int longitude) {
        return new OpenWeatherWindDirectionGenerator(latitude, longitude, _apiKey);
    }

}
