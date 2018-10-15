package simulator.data.generator.helpers;

import simulator.data.generator.IDataGenerator;

public abstract class BaseDataGenerator implements IDataGenerator {

    protected long getUnixTime() {
        return System.currentTimeMillis() / 1000L;
    }

}
