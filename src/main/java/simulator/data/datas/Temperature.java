package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

public class Temperature extends BaseData<Integer> {
    public Temperature(long timestamp, Integer sensorId, Integer value) {
        super(timestamp, "°C", sensorId, DataType.TEMPERATURE, value);
    }
}
