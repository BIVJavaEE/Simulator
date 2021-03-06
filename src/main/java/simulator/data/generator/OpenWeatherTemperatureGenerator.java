package simulator.data.generator;

import org.json.JSONObject;
import simulator.data.BaseData;
import simulator.data.datas.Temperature;
import simulator.data.generator.helpers.BaseOpenWeatherGenerator;

import java.io.IOException;

public class OpenWeatherTemperatureGenerator extends BaseOpenWeatherGenerator {

    public OpenWeatherTemperatureGenerator(int latitude, int longitude, String apiKey) {
        super(latitude, longitude, apiKey);
    }

    @Override
    public BaseData generate(Integer sensorId) throws DataGeneratorException {
        try {
            int temp = getTemperature();
            return new Temperature(sensorId, temp);
        } catch (IOException e) {
            throw new DataGeneratorException();
        }
    }

    private int getTemperature() throws IOException {
        JSONObject jsonObject = getJsonObject();
        JSONObject mainJsonObject = jsonObject.getJSONObject("main");
        return mainJsonObject.getInt("temp") - 273;
    }

}
