package se.vendler.floorheat.config;

/**
 * Created by mattias on 2017-10-04.
 */
public class FloorHeatConfiguration {
    private String ip;
    private int port;
    private int outputCount;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getOutputCount() {
        return outputCount;
    }

    public void setOutputCount(int outputCount) {
        this.outputCount = outputCount;
    }
}
