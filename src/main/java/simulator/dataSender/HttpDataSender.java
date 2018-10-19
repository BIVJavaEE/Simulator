package simulator.dataSender;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpDataSender implements IDataSender {

    private String _apiAddress;

    public HttpDataSender(String apiAddress) {
        _apiAddress = apiAddress;
    }

    @Override
    public void send(String data) throws DataSenderException {
        try {
            HttpPost request = new HttpPost(_apiAddress);
            request.setEntity(new StringEntity(data));
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                System.err.println("Bad status code for request ! " + statusCode);
            } else {
                System.out.println("Sent !");
            }
        } catch (IOException e) {
            throw new DataSenderException();
        }
    }

    @Override
    public void initialize() throws DataSenderException {

    }

    @Override
    public void shutdown() throws DataSenderException {
    }
}
