package simulator.dataSender;

import org.json.JSONObject;

public class HttpDataSenderFactory extends BaseDataSenderFactory {

    public HttpDataSenderFactory(JSONObject config) {
        super(config);
    }

    @Override
    public IDataSender create() throws IDataSenderFactoryException {
        String api = _config.getString("api");
        return new HttpDataSender(api);
    }
}
