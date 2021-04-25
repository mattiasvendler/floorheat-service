package se.vendler;

import io.advantageous.qbit.http.client.HttpClient;
import io.advantageous.qbit.http.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import se.vendler.floorheat.FloorheatController;
import se.vendler.floorheat.config.FloorHeatConfiguration;
import se.vendler.floorheat.impl.ESPFloorheatController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 */
public class App {
    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Hello World!");
        FloorHeatConfiguration floorHeatConfiguration = new FloorHeatConfiguration();
        floorHeatConfiguration.setIp("192.168.1.110");
        floorHeatConfiguration.setPort(80);
        floorHeatConfiguration.setOutputCount(8);
        HttpClient httpClient = HttpClientBuilder.httpClientBuilder().setHost(floorHeatConfiguration.getIp()).setPort(floorHeatConfiguration.getPort()).buildAndStart();
        FloorheatController floorheatController = new ESPFloorheatController(floorHeatConfiguration.getIp(), floorHeatConfiguration.getPort());
        floorheatController.configure(floorHeatConfiguration);
        logger.info("Config done");
        for (int i = 1; i <= floorHeatConfiguration.getOutputCount(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            floorheatController.heatOn(i, false);
        }

        for (int i = floorHeatConfiguration.getOutputCount(); i >= 1; i--) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            floorheatController.heatOff(i, false);
        }


        httpClient.stop();
    }
}
