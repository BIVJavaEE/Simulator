package simulator.sensors;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;
import simulator.dataSender.IDataSender;
import simulator.sensors.parse.*;

import java.util.LinkedList;
import java.util.List;

public class SensorsCreator {

    private JSONArray _sensors;
    private IDataSender _dataSender;
    private String _openWeatherApiKey;

    public SensorsCreator(JSONArray sensors, IDataSender dataSender, String openWeatherApiKey) {
        _sensors = sensors;
        _dataSender = dataSender;
        _openWeatherApiKey = openWeatherApiKey;
    }

    public List<Sensor> create() {
        LinkedList<Sensor> result = new LinkedList<>();
        _sensors.forEach((sensor) -> result.add(createPublisher((JSONObject)sensor)));
        return result;
    }

    private IDataGeneratorParser getParser(JSONObject jsonObject) {
        String type = jsonObject.get("type").toString();
        switch (type) {
            case "temperature":
                return new TemperatureParser(_openWeatherApiKey);
            case "pressure":
                return new PressureParser(_openWeatherApiKey);
            case "windspeed":
                return new WindSpeedParser(_openWeatherApiKey);
            case "winddirection":
                return new WindDirectionParser(_openWeatherApiKey);
            default:
                throw new IllegalArgumentException();
        }
    }

    private Sensor createPublisher(JSONObject jsonObject) {
        IDataGenerator generator = getParser(jsonObject).parse(jsonObject);
        int sensorId = jsonObject.getInt("sensorId");
        return new Sensor(sensorId, _dataSender, generator);
    }

}
