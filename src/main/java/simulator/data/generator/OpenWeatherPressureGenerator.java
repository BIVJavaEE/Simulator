package simulator.data.generator;

import simulator.data.BaseData;
import simulator.data.datas.Pressure;
import simulator.data.generator.helpers.BaseOpenWeatherGenerator;

import java.io.IOException;

public class OpenWeatherPressureGenerator extends BaseOpenWeatherGenerator {

    public OpenWeatherPressureGenerator(String city, String apiKey) {
        super(city, apiKey);
    }

    @Override
    public BaseData generate() throws DataGeneratorException {
        try {
            var pressure = getPressure();
            return new Pressure(pressure);
        } catch (IOException e) {
            throw new DataGeneratorException();
        }
    }

    private int getPressure() throws IOException {
        var jsonObject = getJsonObject();
        var mainJsonObject = jsonObject.getJSONObject("main");
        return mainJsonObject.getInt("pressure");
    }

}