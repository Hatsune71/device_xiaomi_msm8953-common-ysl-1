package com.xiaomi.parts;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class PerformanceTileService extends TileService {

    @Override
    public void onStartListening() {

        int currentState = Integer.parseInt(FileUtils.getValue(DeviceSettings.THERMAL_PATH));

        Tile tile = getQsTile();
        tile.setState(Tile.STATE_ACTIVE);
        tile.setLabel(getResources().getStringArray(R.array.thermal_profiles)[currentState]);

        tile.updateTile();
        super.onStartListening();
    }

    @Override
    public void onClick() {
        int currentState = Integer.parseInt(FileUtils.getValue(DeviceSettings.THERMAL_PATH));

       int nextState = 0;
        if (currentState == 16) {
            nextState = -1;
        } else {
	if (currentState == 4) {
	nextState = 8;
	} else {
            nextState = currentState + 1;
        }}

        Tile tile = getQsTile();
        FileUtils.setValue(DeviceSettings.THERMAL_PATH, nextState);
        tile.setLabel(getResources().getStringArray(R.array.thermal_profiles)[nextState]);

        tile.updateTile();
        super.onClick();
    }
}
