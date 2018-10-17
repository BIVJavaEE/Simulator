package simulator.dataSender;

import org.apache.commons.cli.CommandLine;
import org.json.JSONObject;

public abstract class BaseDataSenderFactory {

    protected JSONObject _config;

    protected BaseDataSenderFactory(JSONObject config) {
        _config = config;
    }

    abstract public IDataSender create() throws IDataSenderFactoryException;
}
