package simulator.data;

public enum DataType {

    TEMPERATURE("temperature"),
    PRESSURE("pressure"),
    WINDSPEED("windspeed");

    private String _typeString;

    DataType(String typeString) {
        _typeString = typeString;
    }

    @Override
    public String toString() {
        return _typeString;
    }

}
