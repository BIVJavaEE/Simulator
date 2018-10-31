package simulator.data.generator;

import org.json.JSONObject;
import simulator.data.BaseData;
import simulator.data.datas.WindDirection;
import simulator.data.generator.helpers.BaseOpenWeatherGenerator;

import java.io.IOException;

public class OpenWeatherWindDirectionGenerator extends BaseOpenWeatherGenerator {

    public OpenWeatherWindDirectionGenerator(int latitude, int longitude, String apiKey) {
        super(latitude, longitude, apiKey);
    }

    @Override
    public BaseData generate(Integer sensorId) throws DataGeneratorException {
        try {
            float windDirection = getWindDirection();
            return new WindDirection(sensorId, windDirection);
        } catch (IOException e) {
            throw new DataGeneratorException();
        }

    }

    private float getWindDirection() throws IOException {
        JSONObject jsonObject = getJsonObject();
        JSONObject windObject = jsonObject.getJSONObject("wind");
        return windObject.getFloat("deg");
    }
}
