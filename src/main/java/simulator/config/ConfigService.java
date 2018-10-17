package simulator.config;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConfigService {

    private String _path;

    public ConfigService(String path) {
        _path = path;
    }

    public JSONObject get() throws IOException {
        return new JSONObject(readJsonFile());
    }

    private String readJsonFile() throws IOException {
        File file = new File(_path);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return new String(data, StandardCharsets.UTF_8);
    }

}
