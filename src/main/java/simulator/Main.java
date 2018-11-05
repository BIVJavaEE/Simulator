package simulator;

import org.apache.commons.cli.*;
import org.json.JSONObject;
import simulator.config.ConfigService;
import simulator.dataSender.*;
import simulator.sensors.Sensor;
import simulator.sensors.SensorScheduler;
import simulator.sensors.SensorsCreator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IDataSenderFactoryException, DataSenderException, IOException {
        CommandLine cmd = getCommandLine(args);
        Optional<String> configFilePath = Optional.ofNullable(cmd.getOptionValue("config"));
        ConfigService configService = new ConfigService(configFilePath.orElse("config/config.json"));
        JSONObject config = configService.get();

        IDataSender dataSender = getDataSenderFactory(config).create();

        System.out.println("Initializing data senders...");
        dataSender.initialize();
        System.out.println("Done!");

        System.out.println("Retrieving sensors...");
        List<Sensor> sensors = new SensorsCreator(config.getJSONArray("sensors"), dataSender, config.getString("openweatherapikey")).create();
        System.out.println("Done!");

        scheduleSensors(dataSender, sensors, config.getInt("delay"));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down data sender...");
            try {
                dataSender.shutdown();
            } catch (DataSenderException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Done !");
            }
        }));
    }

    private static void scheduleSensors(IDataSender dataSender, List<Sensor> sensors, int delay) {
        for (Sensor sensor : sensors) {
            SensorScheduler scheduler = new SensorScheduler(dataSender, sensor, delay);
            scheduler.start();
        }
    }

    private static BaseDataSenderFactory getDataSenderFactory(JSONObject config) throws UnsupportedOperationException {
        String mode = config.getString("mode");
        switch (mode) {
            case "mqtt":
                return new MqttDataSenderFactory(config.getJSONObject("mqtt"));
            case "http":
                return new HttpDataSenderFactory(config.getJSONObject("http"));
            default:
                throw new IllegalArgumentException(mode);
        }
    }

    private static CommandLine getCommandLine(String[] args) {
        try {
            return new DefaultParser().parse(createOptions(), args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private static Options createOptions() {
        Options options = new Options();

        Option mqttOption = new Option("c", "config", true, "Path to the config config");
        mqttOption.setRequired(false);
        options.addOption(mqttOption);

        return options;
    }
}
