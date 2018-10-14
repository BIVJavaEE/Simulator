package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

import java.sql.Timestamp;

public class Pressure extends BaseData<Integer> {
    public Pressure(Timestamp timestamp, Integer sensorId, Integer value) {
        super(timestamp, "", sensorId, DataType.PRESSURE, value);
    }
}
