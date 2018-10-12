package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

public class Temperature extends BaseData<Integer> {
    public Temperature(Integer value) {
        super(DataType.TEMPERATURE, value);
    }
}
