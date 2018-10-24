package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

public class Pressure extends BaseData<Integer> {
    public Pressure(Integer sensorId, Integer value) {
        super(sensorId, value);
    }
}
