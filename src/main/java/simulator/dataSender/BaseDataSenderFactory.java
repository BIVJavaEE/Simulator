package simulator.dataSender;

import org.apache.commons.cli.CommandLine;

public abstract class BaseDataSenderFactory {

    protected CommandLine _cmd;

    protected BaseDataSenderFactory(CommandLine cmd) {
        _cmd = cmd;
    }

    abstract public IDataSender create() throws IDataSenderFactoryException;
}
