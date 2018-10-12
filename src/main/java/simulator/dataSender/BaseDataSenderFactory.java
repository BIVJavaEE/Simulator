package simulator.dataSender;

public abstract class BaseDataSenderFactory {

    protected String[] _args;

    protected BaseDataSenderFactory(String[] args) {
        _args = args;
    }

    abstract public IDataSender create() throws IDataSenderFactoryException;
}
