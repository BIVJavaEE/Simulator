package simulator.data.generator;

import simulator.data.BaseData;

import java.util.concurrent.Future;

public interface IDataGenerator {
    Future<BaseData> generate();
}
