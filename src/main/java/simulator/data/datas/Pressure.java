package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

public class Pressure extends BaseData<Integer> {
    public Pressure(long timestamp, Integer sensorId, Integer value) {
        super(timestamp, "", sensorId, DataType.PRESSURE, value);
    }
}
