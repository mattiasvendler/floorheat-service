package se.vendler.floorheat.impl;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;
import se.vendler.floorheat.Esp8266DigitalPinResonse;
import se.vendler.floorheat.FloorheatController;
import se.vendler.floorheat.HeatElement;
import se.vendler.floorheat.HeatElementState;
import se.vendler.floorheat.config.FloorHeatConfiguration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * Created by mattias on 2017-10-04.
 */
public class ESPFloorheatController implements FloorheatController {
    private FloorHeatConfiguration configuration;
    private HttpClient httpClient;
    private Logger logger = Logger.getLogger(this.getClass());
    private ConnectionFactory connectionFactory;
    private Channel channel;
    private String key;
    private String exchange;
    private Gson gson = new Gson();
    private String httpIp;
    private int port;

    public ESPFloorheatController(String httpHost, int httpPort) throws IOException, TimeoutException {
        connectionFactory = new ConnectionFactory();
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.181");
        connectionFactory.setPort(5672);
        channel = connectionFactory.newConnection().createChannel();
        key = "se.vendler.floorheat.event";
        exchange = "se.vendler.exchange.floorheat";
        channel.exchangeDeclare(exchange, "direct");
        configuration = new FloorHeatConfiguration();
        configuration.setIp(httpHost);
        configuration.setPort(port);

    }

    private void createHttpConnection() {
        if (httpClient == null) {
            logger.info("Create new http client");
            httpClient = HttpClient.newHttpClient();//HttpClientBuilder.httpClientBuilder().setHost(configuration.getIp()).setPort(configuration.getPort()).buildAndStart();
        }
    }

    private boolean send(String url, int room , boolean publish) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url)).build();
            HttpResponse<String> httpTextResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.info("Resonse " + httpTextResponse.statusCode());
            logger.info(httpTextResponse.body());
            if (httpTextResponse.statusCode() == 200) {
                HeatElement heatElement = new HeatElement();
                heatElement.setId(room);
                heatElement.setState(HeatElementState.ON);
                if (publish) {
                    try {
                        channel.basicPublish(exchange, key, null, gson.toJson(heatElement).getBytes());
                    } catch (IOException e) {
                        logger.warn(e.getMessage());
                    }
                }
                return true;
            } else {
                createHttpConnection();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean heatOn(int room, boolean publish) {
        logger.info("Heat on room " + room);
        if (configuration.getOutputCount() >= room) {
            String url = String.format("http://%s:%d/%d/high", configuration.getIp(),configuration.getPort(), room);
            logger.debug(url);
            return send(url, room, publish);
        }
        return false;
    }

    @Override
    public boolean heatOff(int room, boolean publish) {
        logger.info("Heat off room " + room);
        if (configuration.getOutputCount() >= room) {
            String url = String.format("http://%s:%d/%d/low", configuration.getIp(), configuration.getPort(), room);
            logger.debug(url);
            return send(url, room, publish);
        }
        return false;
    }

    @Override
    public boolean configure(FloorHeatConfiguration floorHeatConfiguration) {
        configuration = floorHeatConfiguration;
        String url;
        createHttpConnection();
//        for (int i = 1; i <= floorHeatConfiguration.getOutputCount(); i++) {
//            url = String.format("http://%s/mode/%d/o",configuration.getIp(), i);
//            logger.info(url);
//            send(url, 0, false);
//        }
        return false;
    }

    public boolean isHeatOn(int room) {
//        String url = String.format("http://%s/digital/%d",configuration.getIp(), room);
//        createHttpConnection();
//        HttpTextResponse httpTextResponse = httpClient.get(url);
//        if (httpTextResponse.code() == 200) {
//            Esp8266DigitalPinResonse resonse = gson.fromJson(httpTextResponse.body(), Esp8266DigitalPinResonse.class);
//            if (resonse.getReturn_value() > 0) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//
//        logger.info("Resonse code: " + httpTextResponse.code());
//        logger.info(httpTextResponse.body());
        return false;
    }
}
