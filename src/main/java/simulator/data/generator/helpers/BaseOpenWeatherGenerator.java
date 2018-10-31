package simulator.data.generator.helpers;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;

import java.io.IOException;

public abstract class BaseOpenWeatherGenerator extends BaseDataGenerator {

    private int _latitude;
    private int _longitude;
    private String _apiKey;

    public BaseOpenWeatherGenerator(int latitude, int longitude, String apiKey) {
        _latitude = latitude;
        _longitude = longitude;
        _apiKey = apiKey;
    }

    protected JSONObject getJsonObject() throws IOException {
        return new JSONObject(getApiResponse());
    }

    private String getApiResponse() throws IOException {
        HttpUriRequest request = new HttpGet(getUrl());
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        return new BasicResponseHandler().handleResponse(response);
    }

    private String getUrl() {
        return "http://api.openweathermap.org/data/2.5/weather?lat={latitude}&lon={longitude}&appid={api}"
                .replace("{latitude}", Integer.toString(_latitude))
                .replace("{longitude}", Integer.toString(_longitude))
                .replace("{api}", _apiKey);
    }
}
