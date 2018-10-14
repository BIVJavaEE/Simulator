package simulator.sensors;

import org.apache.commons.cli.CommandLine;
import org.json.JSONArray;
import org.json.JSONObject;
import simulator.dataSender.IDataSender;
import simulator.sensors.parse.IDataGeneratorParser;
import simulator.sensors.parse.TemperatureParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SensorsCreator {

    private CommandLine _cmd;
    private IDataSender _dataSender;

    public SensorsCreator(CommandLine cmd, IDataSender dataSender) {
        _cmd = cmd;
        _dataSender = dataSender;
    }

    private static String getSensorsJson(String jsonFilePath) throws IOException {
        var file = new File(jsonFilePath);
        var fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return new String(data, StandardCharsets.UTF_8);
    }

    public List<Sensor> create() {
        var path = _cmd.getOptionValue("sensors");
        JSONArray sensors;
        try {
            var sensorsJson = getSensorsJson(path);
            sensors = new JSONArray(sensorsJson);
        } catch (IOException e) {
            return new ArrayList<>();
        }

        var result = new LinkedList<Sensor>();
        sensors.forEach((sensor) -> result.add(createPublisher((JSONObject)sensor)));

        return result;
    }

    private IDataGeneratorParser getParser(JSONObject jsonObject) {
        var type = jsonObject.get("type").toString();
        switch (type) {
            case "temperature":
                return new TemperatureParser(_cmd.getOptionValue("openweatherapikey"));
            default:
                throw new IllegalArgumentException();
        }
    }

    private Sensor createPublisher(JSONObject jsonObject) {
        var generator = getParser(jsonObject).parse(jsonObject);
        var sensorId = jsonObject.getInt("sensorId");
        return new Sensor(sensorId, _dataSender, generator);
    }

}
