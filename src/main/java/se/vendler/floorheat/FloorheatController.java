package se.vendler.floorheat;

import se.vendler.floorheat.config.FloorHeatConfiguration;

/**
 * Created by mattias on 2017-10-04.
 */
public interface FloorheatController {
    boolean heatOn(int room, boolean publish);
    boolean heatOff(int room, boolean publish);
    boolean configure(FloorHeatConfiguration floorHeatConfiguration);
    boolean isHeatOn(int room);
}
