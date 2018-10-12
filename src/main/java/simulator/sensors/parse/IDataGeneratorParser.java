package simulator.sensors.parse;

import org.json.JSONObject;
import simulator.data.generator.IDataGenerator;

public interface IDataGeneratorParser {
    IDataGenerator parse(JSONObject jsonObject);
}
