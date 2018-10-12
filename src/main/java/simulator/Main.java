package simulator;

import org.apache.commons.cli.*;
import simulator.dataSender.*;
import simulator.sensors.Sensor;
import simulator.sensors.SensorScheduler;
import simulator.sensors.SensorsCreator;

import java.util.List;

public class Main {

    public static void main(String[] args) throws IDataSenderFactoryException, DataSenderException {
        var cmd = getCommandLine(args);
        var dataSender = getDataSenderFactory(cmd).create();

        System.out.println("Initializing data senders...");
        dataSender.initialize();
        System.out.println("Done!");

        System.out.println("Retrieving sensors...");
        var sensors = new SensorsCreator(cmd, dataSender).create();
        System.out.println("Done!");

        scheduleSensors(dataSender, sensors);

        dataSender.shutdown();
    }

    private static void scheduleSensors(IDataSender dataSender, List<Sensor> sensors) throws DataSenderException {
        for (var sensor : sensors) {
            var scheduler = new SensorScheduler(dataSender, sensor, 10);
            scheduler.start();
        }
    }

    private static BaseDataSenderFactory getDataSenderFactory(CommandLine cmd) throws UnsupportedOperationException {
        var mode = cmd.getOptionValue("mode");
        switch (mode) {
            case "mqtt":
                return new MqttDataSenderFactory(cmd);
            case "http":
                throw new UnsupportedOperationException();
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
        var options = new Options();

        var modeOption = new Option("mode", true, "Publish mode (mqtt or http)");
        modeOption.setRequired(true);
        options.addOption(modeOption);

        var mqttOption = new Option("mqtt", true, "Path to the MQTT config");
        mqttOption.setRequired(false);
        options.addOption(mqttOption);

        var openWeatherApiKeyOption = new Option("openweatherapikey", true, "Open weather API key");
        openWeatherApiKeyOption.setRequired(false);
        options.addOption(openWeatherApiKeyOption);

        var sensorsOption = new Option("sensors", true, "Sensors");
        sensorsOption.setRequired(true);
        options.addOption(sensorsOption);

        return options;
    }
}
