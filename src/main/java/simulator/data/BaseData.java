package simulator.data;

import org.json.JSONObject;

public abstract class BaseData<TData> {

    private DataType _type;
    private TData _value;

    protected BaseData(DataType type, TData value) {
        _type = type;
        _value = value;
    }

    public TData getValue() {
        return _value;
    }

    public void setValue(TData value) {
        _value = value;
    }

    public String createJson() {
        var jsonObject = new JSONObject();
        jsonObject.put("type", _type.toString());
        jsonObject.put("value", _value);
        return jsonObject.toString();
    }

}
