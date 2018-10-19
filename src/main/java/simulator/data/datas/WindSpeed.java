package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

public class WindSpeed extends BaseData<Float> {

    public WindSpeed(long timestamp, Integer sensorId, Float value) {
        super(timestamp, "km/h", sensorId, DataType.WINDSPEED, value);
    }

}
