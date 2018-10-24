package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

public class Temperature extends BaseData<Integer> {
    public Temperature(Integer sensorId, Integer value) {
        super(sensorId, value);
    }
}
