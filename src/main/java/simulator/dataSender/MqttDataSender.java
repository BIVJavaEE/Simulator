package simulator.dataSender;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttDataSender implements IDataSender {

    private MqttClient _mqttClient;
    private String _topic;

    public MqttDataSender(MqttClient mqttClient, String topic) {
        _mqttClient = mqttClient;
        _topic = topic;
    }

    @Override
    public void send(String data) throws DataSenderException {
        var message = new MqttMessage(data.getBytes());
        try {
            _mqttClient.publish(_topic, message);
        } catch (MqttException e) {
            throw new DataSenderException();
        }
    }

    @Override
    public void initialize() throws DataSenderException {
        try {
            _mqttClient.connect(getMqttConnectOption());
        } catch (MqttException e) {
            throw new DataSenderException();
        }
    }

    @Override
    public void shutdown() throws DataSenderException {
        try {
            _mqttClient.disconnect();
        } catch (MqttException e) {
            throw new DataSenderException();
        }
    }

    private static MqttConnectOptions getMqttConnectOption() {
        var mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        return mqttConnectOptions;
    }

}
