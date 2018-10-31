package simulator.data.generator;

import org.json.JSONObject;
import simulator.data.BaseData;
import simulator.data.datas.Pressure;
import simulator.data.generator.helpers.BaseOpenWeatherGenerator;

import java.io.IOException;
import java.sql.Timestamp;

public class OpenWeatherPressureGenerator extends BaseOpenWeatherGenerator {

    public OpenWeatherPressureGenerator(int latitude, int longitude, String apiKey) {
        super(latitude, longitude, apiKey);
    }

    @Override
    public BaseData generate(Integer sensorId) throws DataGeneratorException {
        try {
            int pressure = getPressure();
            return new Pressure(sensorId, pressure);
        } catch (IOException e) {
            throw new DataGeneratorException();
        }
    }

    private int getPressure() throws IOException {
        JSONObject jsonObject = getJsonObject();
        JSONObject mainJsonObject = jsonObject.getJSONObject("main");
        return mainJsonObject.getInt("pressure");
    }

}
