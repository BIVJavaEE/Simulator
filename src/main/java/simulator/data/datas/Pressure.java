package simulator.data.datas;

import simulator.data.BaseData;
import simulator.data.DataType;

public class Pressure extends BaseData<Integer> {
    public Pressure(Integer value) {
        super(DataType.PRESSURE, value);
    }
}
