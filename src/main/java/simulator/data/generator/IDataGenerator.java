package simulator.data.generator;

import simulator.data.BaseData;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface IDataGenerator {
    BaseData generate(Integer sensorId) throws DataGeneratorException;
}
