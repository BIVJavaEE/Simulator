package simulator;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Main {

    public static void main(String[] args) {

        var broker = "tcp://ouaz.me:1883";
        var clientId = "simulator";
        var persistence = new MemoryPersistence();
        try {

            var client = new MqttClient(broker, clientId, persistence);
            var connectionOptions = new MqttConnectOptions();
            connectionOptions.setCleanSession(true);

            client.connect();

            System.out.println("connected");

            client.disconnect();

            System.out.println("disconnected");
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
