package simulator.data.generator;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import simulator.data.BaseData;
import simulator.data.datas.Temperature;
import simulator.data.generator.helpers.BaseOpenWeatherGenerator;

import java.io.IOException;

public class OpenWeatherTemperatureGenerator extends BaseOpenWeatherGenerator {

    public OpenWeatherTemperatureGenerator(String city, String apiKey) {
        super(city, apiKey);
    }

    @Override
    public BaseData generate() throws DataGeneratorException {
        try {
            var temp = getTemperature();
            return new Temperature(temp);
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
