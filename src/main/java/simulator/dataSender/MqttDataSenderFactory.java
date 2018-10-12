package simulator.dataSender;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class MqttDataSenderFactory extends BaseDataSenderFactory {

    private static final String DefaultMqttConfigPath = "config/mqtt.json";

    public MqttDataSenderFactory(CommandLine cmd) {
        super(cmd);
    }

    @Override
    public IDataSender create() throws IDataSenderFactoryException {
        try {
            var config = new JSONObject(readJsonFile());
            var mqttClient = createMqttClient(config);
            var topic = config.get("topic").toString();
            return new MqttDataSender(mqttClient, topic);
        } catch (Exception e) {
            throw new IDataSenderFactoryException();
        }
    }

    private Optional<String> getMqttConfigPath() {
        return Optional.ofNullable(_cmd.getOptionValue("mqtt"));
    }

    private String readJsonFile() throws IOException {
        var jsonFilePath = getMqttConfigPath().orElse(DefaultMqttConfigPath);
        var file = new File(jsonFilePath);
        var fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return new String(data, StandardCharsets.UTF_8);
    }

    private static MqttClient createMqttClient(JSONObject config) throws MqttException {
        var broker = config.get("broker").toString();
        var clientId = config.get("clientId").toString();
        return new MqttClient(broker, clientId, new MemoryPersistence());
    }

}
