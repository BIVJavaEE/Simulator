package simulator.data;

public abstract class BaseData<TData> {

    private TData _value;
    private int _sensorId;

    protected BaseData(int sensorId, TData value) {
        _sensorId = sensorId;
        _value = value;
    }

    public String createJson() {
        return String.valueOf(getSensorId()) +
                ";" +
                getValue();
    }

    public TData getValue() {
        return _value;
    }

    public void setValue(TData value) {
        _value = value;
    }

    public int getSensorId() {
        return _sensorId;
    }

    public void setSensorId(int _sensorId) {
        this._sensorId = _sensorId;
    }
}
