package simulator.dataSender;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

public class MqttDataSenderFactory extends BaseDataSenderFactory {

    public MqttDataSenderFactory(JSONObject config) {
        super(config);
    }

    @Override
    public IDataSender create() throws IDataSenderFactoryException {
        try {
            MqttClient mqttClient = createMqttClient(_config);
            String topic = _config.get("topic").toString();
            return new MqttDataSender(mqttClient, topic);
        } catch (Exception e) {
            throw new IDataSenderFactoryException();
        }
    }

    private static MqttClient createMqttClient(JSONObject config) throws MqttException {
        String broker = config.get("broker").toString();
        String clientId = config.get("clientId").toString();
        return new MqttClient(broker, clientId, new MemoryPersistence());
    }

}
