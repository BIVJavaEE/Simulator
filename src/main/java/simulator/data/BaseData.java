package simulator.data;

import org.json.JSONObject;

import java.sql.Timestamp;

public abstract class BaseData<TData> {

    private Timestamp _timestamp;
    private DataType _type;
    private TData _value;
    private int _sensorId;
    private String _unit;

    protected BaseData(Timestamp timestamp, String unit, int sensorId, DataType type, TData value) {
        _timestamp = timestamp;
        _unit = unit;
        _sensorId = sensorId;
        _type = type;
        _value = value;
    }

    public String createJson() {
        var jsonObject = new JSONObject();
        jsonObject.put("sensorId", _sensorId);
        jsonObject.put("type", _type.toString());
        jsonObject.put("value", _value);
        jsonObject.put("timestamp", _timestamp.toString());
        jsonObject.put("unit", _unit);
        return jsonObject.toString();
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

    public Timestamp getTimestamp() {
        return _timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        _timestamp = timestamp;
    }

    public String getUnit() {
        return _unit;
    }

    public void setUnit(String unit) {
        _unit = unit;
    }
}
