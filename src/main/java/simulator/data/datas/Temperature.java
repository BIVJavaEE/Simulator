package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

import java.sql.Timestamp;

public class Temperature extends BaseData<Integer> {
    public Temperature(Timestamp timestamp, Integer sensorId, Integer value) {
        super(timestamp, "°C", sensorId, DataType.TEMPERATURE, value);
    }
}
