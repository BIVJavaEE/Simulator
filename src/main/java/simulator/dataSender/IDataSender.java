package simulator.dataSender;

public interface IDataSender {
    void send(String data) throws DataSenderException;

    void initialize() throws DataSenderException;

    void shutdown() throws DataSenderException;
}
