package simulator.data.generator;

import org.json.JSONObject;
import simulator.data.BaseData;
import simulator.data.datas.WindSpeed;
import simulator.data.generator.helpers.BaseOpenWeatherGenerator;

import java.io.IOException;

public class OpenWeatherWindSpeedGenerator extends BaseOpenWeatherGenerator {

    public OpenWeatherWindSpeedGenerator(int latitude, int longitude, String apiKey) {
        super(latitude, longitude, apiKey);
    }

    @Override
    public BaseData generate(Integer sensorId) throws DataGeneratorException {
        try {
            float windSpeed = getWindSpeed();
            return new WindSpeed(sensorId, windSpeed);
        } catch (IOException e) {
            throw new DataGeneratorException();
        }

    }

    private float getWindSpeed() throws IOException {
        JSONObject jsonObject = getJsonObject();
        JSONObject windObject = jsonObject.getJSONObject("wind");
        return windObject.getFloat("speed");
    }
}
