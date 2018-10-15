package simulator.data.generator;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import simulator.data.BaseData;
import simulator.data.datas.Temperature;
import simulator.data.generator.helpers.BaseOpenWeatherGenerator;

import java.io.IOException;
import java.sql.Timestamp;

public class OpenWeatherTemperatureGenerator extends BaseOpenWeatherGenerator {

    public OpenWeatherTemperatureGenerator(String city, String apiKey) {
        super(city, apiKey);
    }

    @Override
    public BaseData generate(Integer sensorId) throws DataGeneratorException {
        try {
            var temp = getTemperature();
            return new Temperature(getUnixTime(), sensorId, temp);
        } catch (IOException e) {
            throw new DataGeneratorException();
        }
    }

    private int getTemperature() throws IOException {
        var jsonObject = getJsonObject();
        var mainJsonObject = jsonObject.getJSONObject("main");
        return mainJsonObject.getInt("temp") - 273;
    }

}
