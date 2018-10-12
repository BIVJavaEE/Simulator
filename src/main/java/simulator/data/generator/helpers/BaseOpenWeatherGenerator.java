package simulator.data.generator.helpers;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;

import java.io.IOException;

public abstract class BaseOpenWeatherGenerator implements IDataGenerator {

    private String _city;
    private String _apiKey;

    public BaseOpenWeatherGenerator(String city, String apiKey) {
        _city = city;
        _apiKey = apiKey;
    }

    protected JSONObject getJsonObject() throws IOException {
        return new JSONObject(getApiResponse());
    }

    private String getApiResponse() throws IOException {
        var request = new HttpGet(getUrl());
        var client = HttpClientBuilder.create().build();
        var response = client.execute(request);
        return new BasicResponseHandler().handleResponse(response);
    }

    private String getUrl() {
        return "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={api}"
                .replace("{city}", _city)
                .replace("{api}", _apiKey);
    }
}
